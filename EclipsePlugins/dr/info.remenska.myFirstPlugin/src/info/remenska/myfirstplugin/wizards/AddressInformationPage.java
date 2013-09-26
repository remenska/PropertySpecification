package info.remenska.myfirstplugin.wizards;

import java.util.List;
import org.eclipse.swt.widgets.Control;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class AddressInformationPage extends WizardPage {

	private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
                sb.append(' ');
                sb.append(' ');
        }
        return sb.toString();
	}

	Composite parentLe = null;
	protected AddressInformationPage(String pageName){
		super(pageName);
		setTitle("Address Information");
		setDescription("Please enter your address information");
		setPageComplete(false);
	}
	@Override
	public void createControl(Composite parent) {
		System.out.println("PARENTTT:" + parent);
		this.getShell().setMaximized(true);
		Composite composite = new Composite(parent, SWT.NONE);
		parentLe = composite;
		composite.setLayout(new FillLayout());
		setControl(composite);
//		new Label(composite, SWT.NONE).setText("Street");
//		street = new Text(composite, SWT.NONE);
//		new Label(composite, SWT.NONE).setText("City");
//		city = new Text(composite, SWT.NONE);	
//		new Label(composite, SWT.NONE).setText("State");
//		state = new Text(composite, SWT.NONE);	
		ExpandBar root = new ExpandBar(composite, SWT.V_SCROLL);;
		final MySelectionListener mySelectionListener = new MySelectionListener();
		final MyExpandListener myExpandListener = new MyExpandListener();
//		root.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
//		root.setBackgroundImage(new Image(display,"/home/daniela/Downloads/background.jpg"));
		ExpandItem question = new ExpandItem(root, SWT.NONE, 0);
		question.setText((String) Questionnaire.scopeQuestionTree.data);
		 root.addExpandListener(myExpandListener);
		 
		Composite answersContainer = new Composite(root, SWT.NONE);
		GridLayout layout1 = new GridLayout(1, true);
		answersContainer.setLayout(layout1);
		answersContainer.setVisible(true);
		question.setControl(answersContainer); //
		// now the answers
		List<TreeNode<String>> deca = Questionnaire.scopeQuestionTree.children;
		System.out.println("DECA?:" + deca);
		for(TreeNode dete:deca){
			Button answer = new Button(answersContainer, SWT.RADIO);
			answer.setText((String) dete.data);
			answer.addSelectionListener(mySelectionListener);
		}
		
		question.setHeight(answersContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

//		createChangeAddressPagePage(composite);
	}
	
}

class MyExpandListener implements ExpandListener{

	@Override
	public void itemCollapsed(ExpandEvent e) {
		// TODO Auto-generated method stub
		 // we need to propagate the height to all ExpandItem parents in the hierarchy
		
		ExpandItem itemExpanded = (ExpandItem) e.item;
		System.out.println("Action: COLLAPSE");
		System.out.println("ItemCollapsed: " + itemExpanded + "; height = " + itemExpanded.getHeight());
		ExpandItem theOneWeLookFor = null; 
		Composite compositeControlled = (Composite) itemExpanded.getControl(); // what if it's a top level? Where we have Composite(Composite)
		int heightToRemove = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		while(itemExpanded!=null){
			theOneWeLookFor = null;
			
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
			compositeControlled = (Composite) itemExpanded.getControl();
//			int heightToRemove = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//			int heightToRemove = itemExpanded.getHeight();
			
			Composite compositeParent =itemParentBar.getParent();
			if(compositeParent!=null){
				ExpandBar expandBarSuperParent = (ExpandBar) compositeParent.getParent();
				if(expandBarSuperParent!=null){
					ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
					
					for(ExpandItem item:allItemsOfSuperParent){
						if(item.getControl().equals(compositeParent))
							theOneWeLookFor = item;
					}
//					heightToAdd = compositeParent.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
					System.out.println("Height to remove:"+ heightToRemove);

					System.out.println("The one we look for: "+ theOneWeLookFor);
					System.out.println("ParentItem height BEFORE = " + theOneWeLookFor.getHeight());
					theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()-heightToRemove);
					System.out.println("ParentItem height AFTER = " + theOneWeLookFor.getHeight());

					compositeParent.layout();
					compositeControlled.layout();
				}
				
			}
			itemExpanded = theOneWeLookFor; //TODO change this!!

		}
	}


	@Override
	public void itemExpanded(ExpandEvent e) {
     // we need to propagate the height to all ExpandItem parents in the hierarchy
		ExpandItem itemExpanded = (ExpandItem) e.item;
		System.out.println("ItemExpanded: " + itemExpanded + "; height = " + itemExpanded.getHeight());
		ExpandItem theOneWeLookFor = null; 
		Composite compositeControlled = (Composite) itemExpanded.getControl();
		int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		while(itemExpanded!=null){
			theOneWeLookFor = null;
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
			compositeControlled = (Composite) itemExpanded.getControl();
//			int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			Composite compositeParent =itemParentBar.getParent();
			if(compositeParent!=null){
				ExpandBar expandBarSuperParent = (ExpandBar) compositeParent.getParent();
				if(expandBarSuperParent!=null){
					ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
					
					for(ExpandItem item:allItemsOfSuperParent){
						if(item.getControl().equals(compositeParent))
							theOneWeLookFor = item;
					}
//					heightToAdd = compositeParent.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
					System.out.println("Height to add:"+ heightToAdd);

					System.out.println("The one we look for: "+ theOneWeLookFor);
					System.out.println("ParentItem height BEFORE = " + theOneWeLookFor.getHeight());
					theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()+heightToAdd);
					System.out.println("ParentItem height AFTER = " + theOneWeLookFor.getHeight());

					compositeParent.layout();
				}
				
			}
			itemExpanded = theOneWeLookFor; //TODO change this!!

		}
	}
}

class MySelectionListener implements SelectionListener{

	
	@Override
	public void widgetSelected(SelectionEvent e) {
		// ExpandBar contains: ExpandItem and Composite(contains:
		// Buttons))
		boolean isSelected = ((Button) e.getSource()).getSelection();
		System.out.println("-----------");
		System.out.println("Selected: "+  ((Button) e.getSource()).getText());
		System.out.println("Trying to find a TreeNode, it's children are..: "+ Questionnaire.scopeQuestionTree.findTreeNode(((Button) e.getSource()).getText()).children);
		if (isSelected) {
			
			List<TreeNode<String>> newQuestions = Questionnaire.scopeQuestionTree.findTreeNode(((Button) e.getSource()).getText()).children;
			Composite questionsHolder = ((Button) e.getSource()).getParent();
	//DEBUG
			//OK
			System.out.println("QuestionsHolder BEFORE:" + questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
			ExpandBar findBar = (ExpandBar) questionsHolder.getParent(); 
			ExpandItem[] findItems = findBar.getItems();
			for(ExpandItem item:findItems){
				if(item.getControl().equals(questionsHolder))
					// NOT OK!
					System.out.println("ExpandItem BEFORE:" + item.getHeight());
			}
	//END_DEBUG
			
//			questionsHolder.redraw();
//			questionsHolder.update();
			int oldOldHeight = questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			//first remove existing expandBars. This means only answers remain, no nested questions
			 for (Control control : questionsHolder.getChildren()) {
			        if (!(control instanceof Button)){
			        	// here collect the OldOldHeight
			        	control.dispose();
			        }
			    }		
			 
			 questionsHolder.layout();

			//dynamically create new questions. 
			ExpandBar expandBarNewQuestions = new ExpandBar(questionsHolder, SWT.NONE);
//			expandBarNewQuestions.setBackgroundImage(new Image(Display.getCurrent(),"/home/daniela/Downloads/background.jpg"));
//			expandBarNewQuestions.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE)); 
			MyExpandListener myExpandListener = new MyExpandListener();
			expandBarNewQuestions.addExpandListener(myExpandListener);
			
			expandBarNewQuestions.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
					true, true, 2, 1));
			
			int height = 0; 
			ExpandItem prevQuest = null;
			System.out.println("NEW QUESTIONS: " + newQuestions);
			for(TreeNode<String> newQuestion:newQuestions){
				Composite questionComposite = new Composite(expandBarNewQuestions, SWT.NONE);
				GridLayout layout1 = new GridLayout(1, true);
				questionComposite.setLayout(layout1);
				
				ExpandItem question = new ExpandItem(expandBarNewQuestions, SWT.NONE, 0);
				question.setText((String) newQuestion.data);
				System.out.println("QUESTION INSERTED, TEXT:"+ question.getText());
				question.setControl(questionComposite);//Sets the control that is shown when the item is expanded.

				//new answers for each question
				// these kids are ANSWERS
				List<TreeNode<String>> answers = newQuestion.children;
				for(TreeNode<String> answer:answers){
					Button label1 = new Button(questionComposite, SWT.RADIO);
					label1.setText((String) answer.data);
					label1.addSelectionListener(this);
				}
//				question.setHeight(300);
				question.setHeight(questionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
				height += questionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//				if(prevQuest!=null)
//					prevQuest.setHeight(prevQuest.getHeight() + height);
				questionComposite.setVisible(true);
				questionComposite.layout();
				questionsHolder.layout();
//				prevQuest = question;
			}
			
			//we need to adjust the (parent) ExpandItem's height
			//first let's find it
//			adjustHeight(e, oldOldHeight, height);
			ExpandBar expandBarParent = (ExpandBar) questionsHolder.getParent();
			ExpandItem[] expandItems = expandBarParent.getItems();
			ExpandItem theOneWeNeed = null;
			for(ExpandItem expandItem:expandItems){
				if(expandItem.getControl().equals(questionsHolder))
					theOneWeNeed = expandItem;
			}
			questionsHolder.layout();

			//recalculate the height // <-- needs to be propagated to outer levels
			if(theOneWeNeed!=null){
				int oldHeight = theOneWeNeed.getHeight();
				theOneWeNeed.setHeight(questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y); // <-- this is fishy
//				theOneWeNeed.setHeight(oldHeight+height); // <-- this is fishy

			}
			
			recallibrateHeight(height, oldOldHeight, questionsHolder);
			questionsHolder.layout();

			System.out.println("\n");
		}
	}

	private void recallibrateHeight(int height, int oldOldHeight,
			Composite questionsHolder) {
		// we need to propagate the height to all ExpandItem parents in the hierarchy
		ExpandBar barHeigher = (ExpandBar) questionsHolder.getParent();
		ExpandItem[] items = barHeigher.getItems();
		ExpandItem itemExpanded = null;
		for(ExpandItem item:items){
			if(item.getControl().equals(questionsHolder))
				itemExpanded = item;
		}
//		ExpandItem itemExpanded = (ExpandItem) e.item;
		System.out.println("ItemExpanded: " + itemExpanded + "; height = " + itemExpanded.getHeight());
		ExpandItem theOneWeLookFor = null; 
		Composite compositeControlled = (Composite) itemExpanded.getControl();
		int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		while(itemExpanded!=null){
			theOneWeLookFor = null;
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
//			int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			Composite compositeParent =itemParentBar.getParent();
			if(compositeParent!=null){
				ExpandBar expandBarSuperParent = (ExpandBar) compositeParent.getParent();
				if(expandBarSuperParent!=null){
					ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
					
					for(ExpandItem item:allItemsOfSuperParent){
						if(item.getControl().equals(compositeParent))
							theOneWeLookFor = item;
					}
//					heightToAdd = compositeParent.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
					System.out.println("Height to add:"+ heightToAdd);

					System.out.println("The one we look for: "+ theOneWeLookFor);
					System.out.println("ParentItem height BEFORE = " + theOneWeLookFor.getHeight());
					theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()+questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y - oldOldHeight);
					System.out.println("ParentItem height AFTER = " + theOneWeLookFor.getHeight());

					compositeParent.layout();
				}
				
			}
			itemExpanded = theOneWeLookFor; //TODO change this!!

		}
	
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}
}

