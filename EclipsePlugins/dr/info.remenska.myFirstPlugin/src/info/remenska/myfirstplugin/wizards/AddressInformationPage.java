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
	public static void createQuestionnaire(){
		TreeNode<String> answ11 = questionnaire.addChild("Yes, the behavior is only required to hold within restricted interval(s) in the event sequence. ", false);
		TreeNode<String> quest111 = answ11.addChild("Which of the following choices best describes the restricted interval(s)?", true);
			TreeNode<String> answ1111 = quest111.addChild("There can be at most one restricted interval ", false);
			TreeNode<String> answ1112 = quest111.addChild("Can have both blabla ", false);
			TreeNode<String> answ1113 = quest111.addChild("Anything can happen hello ", false);
		TreeNode<String> quest112 = answ11.addChild("Really are you sure? ", true);
			TreeNode<String> answ1121 = quest112.addChild("Blaaah yah1", false);
			TreeNode<String> quest11211 = answ1121.addChild("Im asking once again", true);
				TreeNode<String> answ112111 = quest11211.addChild("PRETTY SURE DAMNIT", false);
				TreeNode<String> answ112112 = quest11211.addChild("NOT SO SURE HMM", false);

			TreeNode<String> answ1122 = quest112.addChild("Blaaah yah2", false);
			TreeNode<String> quest11221 = answ1122.addChild("Im asking one last time ", true);
				TreeNode<String> answ112211 = quest11221.addChild("Answering one last time", false);
				TreeNode<String> answ112212 = quest11221.addChild("Ok I'm done here!", false);
		TreeNode<String> quest113 = answ11.addChild("Really are you sure sure? ", true);
			TreeNode<String> answ1131 = quest113.addChild("Blaaah blaah yah1", false);
			TreeNode<String> answ1132 = quest113.addChild("Blaaah blaah yah2", false);		
	TreeNode<String> answ12 = questionnaire.addChild("No, the behavior is required to hold throughout the entire event sequence", false);
				
			    // traverse for debugging purposes?
		TreeNode<String > treeRoot = questionnaire;
		for(TreeNode<String> node: treeRoot) {
			String ident = createIndent(node.getLevel());
			String hmmm = node.isQuestion?"Q: ":"A: ";
			
			System.out.println(ident+hmmm+node.data);
		}
	}
	public static TreeNode<String> questionnaire = new TreeNode<String>("Is the behavior only required to hold within the restricted interval(s) in the event sequence?", true);
	Composite parentLe = null;
	protected AddressInformationPage(String pageName){
		super(pageName);
		setTitle("Address Information");
		setDescription("Please enter your address information");
		setPageComplete(false);
	}
	@Override
	public void createControl(Composite parent) {
		createQuestionnaire();
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
		question.setText((String) questionnaire.data);
		 root.addExpandListener(myExpandListener);
		 
		Composite answersContainer = new Composite(root, SWT.NONE);
		GridLayout layout1 = new GridLayout(1, true);
		answersContainer.setLayout(layout1);
		answersContainer.setVisible(true);
		question.setControl(answersContainer); //
		// now the answers
		List<TreeNode<String>> deca = questionnaire.children;
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

	public void adjustHeight(SelectionEvent e, int oldHeight, int newHeight) {
		Button button = (Button) e.getSource();
		Composite compositeParent1 = button.getParent();
		ExpandBar barParent = (ExpandBar) compositeParent1.getParent();
		ExpandItem[] itemss = barParent.getItems();
		ExpandItem itemExpanded = null; //TODO change
		for(ExpandItem item:itemss){
			if(item.getControl().equals(compositeParent1))
				itemExpanded = item;
		}
		ExpandItem theOneWeLookFor = null; 
//		itemExpanded.setHeight(itemExpanded.getHeight()+newHeight-oldHeight);
		compositeParent1.layout();

		while(itemExpanded!=null){
			theOneWeLookFor = null;
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
			Composite compositeControlled = (Composite) itemExpanded.getControl();
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
//					System.out.println("Height to add:"+ heightToAdd);

					System.out.println("The one we look for: "+ theOneWeLookFor);
					System.out.println("ParentItem height BEFORE = " + theOneWeLookFor.getHeight());
					theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()+newHeight-oldHeight);
					System.out.println("ParentItem height AFTER = " + theOneWeLookFor.getHeight());

					compositeParent.layout();
				}
				
			}
			itemExpanded = theOneWeLookFor; //TODO change this!!

		}
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		// ExpandBar contains: ExpandItem and Composite(contains:
		// Buttons))
		boolean isSelected = ((Button) e.getSource()).getSelection();
		System.out.println("-----------");
		System.out.println("Selected: "+  ((Button) e.getSource()).getText());
		System.out.println("Trying to find a TreeNode, it's children are..: "+ AddressInformationPage.questionnaire.findTreeNode(((Button) e.getSource()).getText()).children);
		if (isSelected) {
			
			List<TreeNode<String>> newQuestions = AddressInformationPage.questionnaire.findTreeNode(((Button) e.getSource()).getText()).children;
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

			//recalculate the height
			if(theOneWeNeed!=null){
//				int oldHeight = theOneWeNeed.getHeight();
				theOneWeNeed.setHeight(questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y); // <-- this is fishy
			}
			questionsHolder.layout();

			System.out.println("\n");
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		widgetSelected(e);
	}
}

