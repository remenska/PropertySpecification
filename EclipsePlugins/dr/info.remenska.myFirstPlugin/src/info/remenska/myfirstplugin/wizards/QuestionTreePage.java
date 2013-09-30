package info.remenska.myfirstplugin.wizards;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Control;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.gmf.runtime.common.ui.services.elementselection.ElementSelectionScope;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
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
import org.eclipse.uml2.uml.Operation;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import com.ibm.xtools.uml.type.UMLElementTypes;
import com.ibm.xtools.uml.ui.internal.dialogs.UMLSelectExistingElementDialog;
import com.ibm.xtools.uml.ui.internal.dialogs.UMLSelectElementDialog;
import com.ibm.xtools.uml.ui.internal.dialogs.UMLSelectElementDialogAPN;
public class QuestionTreePage extends WizardPage {
	class MySelectionListener implements SelectionListener{
		@Override
		public void widgetSelected(SelectionEvent e) {
			// ExpandBar contains: ExpandItem and Composite(contains:
			// Buttons))
			boolean isSelected = ((Button) e.getSource()).getSelection();
//			System.out.println("-----------");
			if (isSelected) {
				
				List<TreeNode<String>> newQuestions = questionnaire.findTreeNode(((Button) e.getSource()).getText()).children;
				String selected = ((Button) e.getSource()).getText();
				// (1) find parent 
				// (2) remove all children
				// (3) add this clicked one
//				System.out.println("SELECTED: "+ selected);

				TreeNode<String> staticNode = questionnaire.findTreeNode(selected);
//				System.out.println("FOUND STATIC NODE: "+ staticNode.data);

				TreeNode<String> staticParentNode = staticNode.parent;
//				System.out.println("STATIC NODE PARENT: "+ staticParentNode.data);

				// (1)
				TreeNode<String> dynamicParentNode =  dynamicQuestionnaire.findTreeNode(staticParentNode.data);
//				System.out.println("DYNAMIC NODE PARENT: "+ dynamicParentNode.data);
//				System.out.println("DYNAMIC PARENT HAS: "+ dynamicParentNode.children.size());

				// (2)
				dynamicParentNode.removeChildren();

				// (3)
				TreeNode<String> dynamicNode = dynamicParentNode.addChild(selected, false); 

				Composite questionsHolder = ((Button) e.getSource()).getParent();
				
				int oldOldHeight = questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
				//first remove existing expandBars. This means only answers remain, no nested questions
				 for (Control control : questionsHolder.getChildren()) {
				        if (!(control instanceof Button)){
				        	control.dispose();
				        }
				    }		
				 
				 questionsHolder.layout();

				//dynamically create new questions. 
				ExpandBar expandBarNewQuestions = new ExpandBar(questionsHolder, SWT.NONE);
//				expandBarNewQuestions.setBackgroundImage(new Image(Display.getCurrent(),"/home/daniela/Downloads/background.jpg"));
//				expandBarNewQuestions.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLUE)); 
				MyExpandListener myExpandListener = new MyExpandListener();
				expandBarNewQuestions.addExpandListener(myExpandListener);
				
				expandBarNewQuestions.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
						true, true, 2, 1));
				
				int height = 0; 
				ExpandItem prevQuest = null;
//				System.out.println("NEW QUESTIONS: " + newQuestions);
				for(TreeNode<String> newQuestion:newQuestions){
					Composite questionComposite = new Composite(expandBarNewQuestions, SWT.NONE);
//					System.out.println("Added child, now: " + questionnaireValidation.getLevel());
					GridLayout layout1 = new GridLayout(1, true);
					questionComposite.setLayout(layout1);
					
					ExpandItem question = new ExpandItem(expandBarNewQuestions, SWT.NONE, 0);
					question.setText((String) newQuestion.data);
					TreeNode<String> dynamicQuestion = dynamicNode.addChild(newQuestion.data, true);
					
//					System.out.println("QUESTION INSERTED, TEXT:"+ question.getText());
					question.setControl(questionComposite);//Sets the control that is shown when the item is expanded.

					//new answers for each question
					List<TreeNode<String>> answers = newQuestion.children;
					for(TreeNode<String> answer:answers){
						Button label1 = new Button(questionComposite, SWT.RADIO);
						label1.setText((String) answer.data);
						label1.addSelectionListener(this);
						
						dynamicQuestion.addChild(answer.data, false);
					}
					question.setHeight(questionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					height += questionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
					questionComposite.setVisible(true);
					questionComposite.layout();
					questionsHolder.layout();
				}
				
				//we need to adjust the (parent) ExpandItem's height
				//first let's find it
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
					theOneWeNeed.setHeight(questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y); 

				}
				
				recallibrateHeight(height, oldOldHeight, questionsHolder);
				questionsHolder.layout();
				traverseQuestionnaire(dynamicQuestionnaire);
				System.out.println("\n");System.out.println("\n");
			}
			checkIfValid();
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
//			ExpandItem itemExpanded = (ExpandItem) e.item;
//			System.out.println("ItemExpanded: " + itemExpanded + "; height = " + itemExpanded.getHeight());
			ExpandItem theOneWeLookFor = null; 
			Composite compositeControlled = (Composite) itemExpanded.getControl();
			int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			while(itemExpanded!=null){
				theOneWeLookFor = null;
				ExpandBar itemParentBar = itemExpanded.getParent();
//				System.out.println("ItemParentBar: " + itemParentBar);
//				int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
				Composite compositeParent =itemParentBar.getParent();
				if(compositeParent!=null  && !itemParentBar.getParent().equals(parentLe)){
					ExpandBar expandBarSuperParent = (ExpandBar) compositeParent.getParent();
					if(expandBarSuperParent!=null){
						ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
						
						for(ExpandItem item:allItemsOfSuperParent){
							if(item.getControl().equals(compositeParent))
								theOneWeLookFor = item;
						}

						theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()+questionsHolder.computeSize(SWT.DEFAULT, SWT.DEFAULT).y - oldOldHeight);

						compositeParent.layout();
					
					}
					
				}
				itemExpanded = theOneWeLookFor; 

			}
		
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			widgetSelected(e);
		}
	}
	public TreeNode<String> questionnaire;
	public TreeNode<String> dynamicQuestionnaire;
	public static Label labelStartEvent, labelEndEvent;
	public static Text textStartEvent, textEndEvent;
	public static Label labelGraphicsHolder;
	public static Image scopeGraphical;

	private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
                sb.append(' ');
                sb.append(' ');
        }
        return sb.toString();
	}

	public Composite parentLe = null;
	protected QuestionTreePage(String pageName, String description, TreeNode<String> questionnaire){
		super(pageName);
		this.questionnaire = questionnaire;
		setTitle(pageName);
		setDescription(description);
		setPageComplete(false);
	}
	
	public void traverseQuestionnaire(TreeNode<String> questionnaireAman){
//		Iterator<TreeNode<String>> iter = this.questionnaire.iterator();
		System.out.println("TRAVERSAL-------");
		Iterator<TreeNode<String>> iter = dynamicQuestionnaire.iterator();
		while(iter.hasNext()){
			TreeNode<String> el = iter.next();			
			String ident = createIndent(el.getLevel());
			String hmmm = el.isQuestion?"Q: ":"A: ";
			
			System.out.println(el.getLevel()+ident+hmmm+el.data);
		}
	}
	
	public void checkIfValid(){
		boolean isValid = true;
		Iterator<TreeNode<String>> iter = dynamicQuestionnaire.iterator();
		while(iter.hasNext()){
			TreeNode<String> el = iter.next();
			if(el.isQuestion)
			if(el.children.size()!=1 && el.isQuestion){
				isValid = false;
				break;
			}
		}
		if(isValid)
			setPageComplete(true);
		else
			setPageComplete(false);
	}
	
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.FILL);
		parentLe = composite;

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		composite.setLayout(gridLayout); 

		setControl(composite);
		
		
		GridData gridData = new GridData();
		gridData.verticalSpan = 5;
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		ExpandBar root = new ExpandBar(composite, SWT.V_SCROLL | SWT.H_SCROLL);;
		root.setLayoutData(gridData);

		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		labelStartEvent = new Label(composite, SWT.NONE);
		labelStartEvent.setText("Start Event: ");
		FontData fontData = labelStartEvent.getFont().getFontData()[0];
		Font font = new Font(Display.getCurrent(), new FontData(fontData.getName(), fontData
		    .getHeight(), SWT.BOLD));
		labelStartEvent.setFont(font);
		labelStartEvent.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		textStartEvent = new Text(composite, SWT.FILL); // THESE SHOULD BE STATIC
		textStartEvent.setText("double click to select");
		textStartEvent.setEditable(false);
		textStartEvent.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		labelEndEvent = new Label(composite, SWT.NONE);
		labelEndEvent.setText("End Event: ");
		labelEndEvent.setFont(font);
		labelEndEvent.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		textEndEvent = new Text(composite, SWT.FILL);
		textEndEvent.setText("double-click to select");
		textEndEvent.setEditable(false);

		textEndEvent.setLayoutData(gridData);
		
		labelGraphicsHolder = new Label(composite, SWT.NONE);
		scopeGraphical = new Image(Display.getCurrent(),
				   "/home/daniela/git/PropertySpecification/ScopeTimelineView/legend.png");
		labelGraphicsHolder.setImage(scopeGraphical);
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		labelGraphicsHolder.setLayoutData(gridData);
		
		composite.pack();
		
		Listener operationListener = new Listener(){
//
			@Override
			public void handleEvent(Event event) {
				 UMLSelectExistingElementDialog dialogOperation = new
						 UMLSelectExistingElementDialog(getShell(),Collections.singletonList(UMLElementTypes.OPERATION));
				 dialogOperation.create();
				 
//				 dialogOperation.setElementSelectionScope(ElementSelectionScope.VISIBLE);
						if(dialogOperation.open()==Window.OK){
							List<Operation> selected = (List<Operation>) dialogOperation.getSelectedElements();
							System.out.println("Selected operation:"+ selected.get(0).getName()+" : "+selected.get(0).getQualifiedName());
							((Text)event.widget).setText(selected.get(0).getQualifiedName());
							((Text)event.widget).pack();
//							selectedOperation.setText(selected.get(0).getQualifiedName());
							dialogOperation.close();
						}
//
			}
//			
		};	
		
		textStartEvent.addListener(SWT.MouseDoubleClick,operationListener);
		textEndEvent.addListener(SWT.MouseDoubleClick,operationListener);

//		root.setLayoutData(formData);
		
//		FormData formData2 = new FormData();
//		formData2.top = new FormAttachment(root,SWT.TOP);
;
//		staticField.setLayoutData(formData);
//		staticField.setLayoutData(gridData);
//		root.setLayoutData(gridData);
		final MySelectionListener mySelectionListener = new MySelectionListener();
		final MyExpandListener myExpandListener = new MyExpandListener();
//		root.setBackgroundImage(new Image(display,"/home/daniela/Downloads/background.jpg"));
		ExpandItem question = new ExpandItem(root, SWT.NONE, 0);
		question.setText((String) questionnaire.data);
		 root.addExpandListener(myExpandListener);
		Composite answersContainer = new Composite(root, SWT.NONE);
		dynamicQuestionnaire = new TreeNode<String>(this.questionnaire.data, true);
		GridLayout layout1 = new GridLayout(1, true);
		answersContainer.setLayout(layout1);
		answersContainer.setVisible(true);
		question.setControl(answersContainer); //
		// now the answers
		List<TreeNode<String>> deca = this.questionnaire.children;
		for(TreeNode dete:deca){
			Button answer = new Button(answersContainer, SWT.RADIO);
			answer.setText((String) dete.data);
			
			answer.addSelectionListener(mySelectionListener);
			
			dynamicQuestionnaire.addChild((String)dete.data, false);
		}
		
		question.setHeight(answersContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		System.out.println("Dynamic QUESTIONNAIRE!!: ");
		traverseQuestionnaire(dynamicQuestionnaire);

	}
	
	class MyExpandListener implements ExpandListener{

		@Override
		public void itemCollapsed(ExpandEvent e) {
			// TODO Auto-generated method stub
			 // we need to propagate the height to all ExpandItem parents in the hierarchy
			
			ExpandItem itemExpanded = (ExpandItem) e.item;
			ExpandItem theOneWeLookFor = null; 
			Composite compositeControlled = (Composite) itemExpanded.getControl(); 
			int heightToRemove = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			while(itemExpanded!=null){
				theOneWeLookFor = null;
				
				ExpandBar itemParentBar = itemExpanded.getParent();
				compositeControlled = (Composite) itemExpanded.getControl();
				
				Composite compositeParent =itemParentBar.getParent();
				if(compositeParent!=null  && !itemParentBar.getParent().equals(parentLe)){
					ExpandBar expandBarSuperParent = (ExpandBar) compositeParent.getParent();
					if(expandBarSuperParent!=null){
						ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
						
						for(ExpandItem item:allItemsOfSuperParent){
							if(item.getControl().equals(compositeParent))
								theOneWeLookFor = item;
						}

						theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()-heightToRemove);

						compositeParent.layout();
						compositeControlled.layout();
					}
					
				}
				itemExpanded = theOneWeLookFor; 

			}
		}


		@Override
		public void itemExpanded(ExpandEvent e) {
	     // we need to propagate the height to all ExpandItem parents in the hierarchy
			ExpandItem itemExpanded = (ExpandItem) e.item;
			ExpandItem theOneWeLookFor = null; 
			Composite compositeControlled = (Composite) itemExpanded.getControl();
			int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
			while(itemExpanded!=null){
				theOneWeLookFor = null;
				ExpandBar itemParentBar = itemExpanded.getParent();
				compositeControlled = (Composite) itemExpanded.getControl();
				if(itemParentBar.getParent()!=null && !itemParentBar.getParent().equals(parentLe)){
					ExpandBar expandBarSuperParent = (ExpandBar) itemParentBar.getParent().getParent(); // BUG
					if(expandBarSuperParent!=null){
						ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
						
						for(ExpandItem item:allItemsOfSuperParent){
							if(item.getControl().equals(itemParentBar.getParent()))
								theOneWeLookFor = item;
						}

						theOneWeLookFor.setHeight(theOneWeLookFor.getHeight()+heightToAdd);

						itemParentBar.getParent().layout();
					}
					
				}
				itemExpanded = theOneWeLookFor; 

			}
		}
	}
}




