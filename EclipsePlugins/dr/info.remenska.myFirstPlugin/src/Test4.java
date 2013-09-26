import org.eclipse.swt.graphics.Image;

import info.remenska.myfirstplugin.wizards.TreeNode;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


public class Test4 {
	private static int DEPTH_TREE = 6;
	private static int[] heightItem = new int[DEPTH_TREE];
	public static TreeNode<String> questionnaire = new TreeNode<String>("Is the behavior only required to hold within the restricted interval(s) in the event sequence?", true);
	public static TreeNode<String> questionnaire1 = new TreeNode<String>("How many events of primary interest are there in this behavior?", true);
	public static Shell shell;

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
			TreeNode<String> answ1111 = quest111.addChild("There can be at most one restricted interval in the event sequence and it has a \n starting delimiter, START: the behavior is required to hold from an occurence of START \n through to the end of the event sequence. ", false);
				TreeNode<String> quest11111 = answ1111.addChild("What if there are multiple occurences of START before the end of the event sequence?", true);
					TreeNode<String> answ111111 = quest11111.addChild("Only the first occurence of START starts the restricted interval; later occurences of START do not have an effect.", false);
					TreeNode<String> answ111112 = quest11111.addChild("Only the last occurence of START starts the restricted interval; each occurence of START resets the beginning of a restricted interval. ", false);
			TreeNode<String> answ1112 = quest111.addChild("There can be at most one restricted interval in the event sequence and it has an ending \n delimiter, END: the behavior is required to hold from the start of the event sequence \n through to the first occurence of END. ", false);
				TreeNode<String> quest11121 = answ1112.addChild("if END does not occur, is the behavior still required to hold, until the end of the event sequence?", true);
					TreeNode<String> answ111211 = quest11121.addChild("Yes, if END does not occur, the behavior is required to hold throughout the entire event sequence.", false);
					TreeNode<String> answ111212 = quest11121.addChild("No, if END does not occur, the behavior not required to hold anywhere in the event sequence.", false);
	
			TreeNode<String> answ1113 = quest111.addChild("A restricted interval in the event sequence can have both a starting delimiter, START, and an \n ending delimiter, END. The behavior is required to hold from an occurence of START \n through to the end of that restricted interval. ", false);
				TreeNode<String> quest11131 = answ1113.addChild("What happens if there are multiple occurences of START without an occurence of END in between them?", true);
					TreeNode<String> answ11311 = quest11131.addChild("Only the first of those occurences of START potentially starts a restricted interval; later occurences of START within that restricted interval do not have an effect.", false);
					TreeNode<String> answ11312 = quest11131.addChild("Only the last of those occurences of START potentially starts a restricted interval; each of those occurences of START resets the beginning of that restricted interval. ", false);
				TreeNode<String> quest11132 = 	answ1113.addChild("If an occurence of START is not followed by an occurence of END, is the behavior still required to hold, until the end of the event sequence?", true);
					TreeNode<String> answ111321 = quest11132.addChild("Yes, if END does not occur subsequently, then the behavior is required to hold until the end of the event sequence.", false);
					TreeNode<String> answ111322 = quest11132.addChild("No, if END does not occur subsequently, then the behavior is not required to hold for the remainder of the event sequence.", false);
				TreeNode<String> quest11133 =  answ1113.addChild("What happens if START occurs after the end of a restricted interval?", true);
					TreeNode<String> answ111331 = quest11133.addChild("That new occurence of START would potentially start a new restricted interval. The situation would thus be the same \n as after the first occurence of START, meaning that the restrictions described on any \n events that take place after that first occurence would again apply.", false);
					TreeNode<String> answ111332 = quest11133.addChild("The new occurence of START does not have an effect; no new occurence of START will ever start a new restricted interval. ", false);
			TreeNode<String> quest112 = answ11.addChild("Are there any exceptional events such that if any of them occurs the property behavior may not be required to hold? ", true);
				TreeNode<String> answ1121 = quest112.addChild("No.", false);
				TreeNode<String> answ1122 = quest112.addChild("Yes, if exceptional event X occurs, the property behavior may not be required to hold", false);
					TreeNode<String> quest11221 = answ1122.addChild("Please select one of the following options related to the scope:", true);
						TreeNode<String> answ112211 = quest11221.addChild("If the exceptional event occurs outside the designated scope, it will have no effect. It only matters \n when the exceptional event occurs inside the scope.", false);
						TreeNode<String> answ112212 = quest11221.addChild("No matter where the exceptional event occurs, outside or inside the designated scope, it may have an effect", false);
			
	TreeNode<String> answ12 = questionnaire.addChild("No, the behavior is required to hold throughout the entire event sequence", false);
		TreeNode<String> quest121 = answ12.addChild("Are there any exceptional events such that if any of them occurs the property behavior may not be required to hold?", true);	
			TreeNode<String> answ1211 = quest121.addChild("No.", false);
			TreeNode<String> answ1212 = quest121.addChild("Yes, if exceptional event X occurs, the property behavior may not be required to hold", false);
			    
	//////
	TreeNode<String> aansw11 = questionnaire1.addChild("One event.", false);
		TreeNode<String> qquest111 = aansw11.addChild("Which of the following choices best describes the restriction on A?", true);
			TreeNode<String> aansw1111 = qquest111.addChild("A is never allowed to occur", false);
				TreeNode<String> qquest1111 = aansw1111.addChild("Are there any exceptional events such that if any of them occurs, the property \n behavior may not be required to hold?", true);
					TreeNode<String> aansw11111 = qquest1111.addChild("No.", false);
					TreeNode<String> aansw11112 = qquest1111.addChild("Yes, if exceptional event X occurs when A has not yet occured, the behavior is not required to hold.", false);
						TreeNode<String> qquest111121 = aansw11112.addChild("What if the exceptional event occurs after a behavior violation has been found (A has occured)?", true);
							TreeNode<String> aansw1111211 = qquest111121.addChild("In that case, the event sequence is not acceptable.", false);
							TreeNode<String> aansw1111212 = qquest111121.addChild("Even in that case, the event sequence is still considered acceptable", false);
			TreeNode<String> aansw1112 = qquest111.addChild("A is allowed to occur at least once.", false);				
				TreeNode<String> qquest11121 = aansw1112.addChild("Are there any exceptional events such that if any of them occurs, the property \n behavior may not be required to hold?", true);
					TreeNode<String> aansw111211 = qquest11121.addChild("No.", false);
					TreeNode<String> aansw111212 = qquest11121.addChild("Yes, if exceptional event X occurs, A is not required to occur.", false);
			
			TreeNode<String> aansw1113 = qquest111.addChild("A is allowed to occur exactly once.", false);
				TreeNode<String> qquest11131 = aansw1113.addChild("Are there any exceptional events such that if any of them occurs, the property \n behavior may not be required to hold?", true);
					TreeNode<String> aansw111311 = qquest11131.addChild("No.", false);
					TreeNode<String> aansw111312 = qquest11131.addChild("Yes, if exceptional event X occurs when A has not yet occured, the behavior is not required to hold.", false);
						TreeNode<String> qquest1113121 = aansw111312.addChild("What if the exceptional event occurs after the violation has been found (A has occured more then once)?", true);
							TreeNode<String> aansw11121211 = qquest1113121.addChild("In that case, the event sequence is not acceptable.", false);
							TreeNode<String> aansw11121212 = qquest1113121.addChild("Even in that case, the event sequence is still considered acceptable.", false);
						TreeNode<String> qquest1113122 = aansw111312.addChild("What if the exceptional event occurs even \n while the behavioral requirement has not been fulfilled (i.e., A has not yet occured)?", true);
							TreeNode<String> aansw11131221 = qquest1113122.addChild("In that case, the behavior is not required to hold.", false);
							TreeNode<String> aansw11131222 = qquest1113122.addChild("In that case, the behavior is still required to hold", false);
						
	TreeNode<String> aansw12 = questionnaire1.addChild("Two events.", false);
		TreeNode<String> qquest121 = aansw12.addChild("Which of the following best describes how A and B interact?", true);
			TreeNode<String> aansw1211 = qquest121.addChild("If A occurs, B is required to occur subsequently", false);
				TreeNode<String> qquest12111 = aansw1211.addChild("Is A required to occur?", true);
					TreeNode<String> aansw121111 = qquest12111.addChild("Yes, A is required to occur.", false);
					TreeNode<String> aansw121112 = qquest12111.addChild("No, A is not required to occur.", false);
			TreeNode<String> aansw1212 = qquest121.addChild("B is not allowed to occur until after A occurs.", false);
				TreeNode<String> qquest12121 = aansw1212.addChild("Is A required to occur, whether or not B eventually occurs?", true);
					TreeNode<String> aansw121211 = qquest12121.addChild("Yes, A is required to occur, whether or not B eventually occurs.", false);
					TreeNode<String> aansw121212 = qquest12121.addChild("No, A is not required to occur and, if it does not occur, B is never allowed to occur.", false);
			TreeNode<String> aansw1213 = qquest121.addChild("Both statements describe how A and B interact: if A occurs, B is required to occur subsequently, and B is not allowed to occur until after A occurs.", false);
				TreeNode<String> qquest12131 = aansw1213.addChild("Is A required to occur?", true);
					TreeNode<String> aansw121311 = qquest12131.addChild("Yes, A is required to occur.", false);
					TreeNode<String> aansw121312 = qquest12131.addChild("No, A is not required to occur.", false);
		
		TreeNode<String> quest122 = aansw12.addChild("After A occurs, is A allowed to occur again before the first subsequent B occurs?", true);
			TreeNode<String> aansw1221 = quest122.addChild("Yes, A is allowed to occur again, zero or more times, before the first subsequent B occurs", false);
			TreeNode<String> aansw1222 = quest122.addChild("No, A is not allowed to occur again before the first subsequent B occurs.", false);
		TreeNode<String> quest123 = aansw12.addChild("After A and the first subsequent B occur, is A allowed to occur again?", true);
			TreeNode<String> aansw1232 = quest123.addChild("No, A is not allowed to occur again.", false);
				TreeNode<String> qquest12321 = aansw1232.addChild("Is B allowed to occur again?", true);
					TreeNode<String> aansw123211 = qquest12321.addChild("Yes, B is allowed to occur again zero or more times.", false);
					TreeNode<String> aansw123212 = qquest12321.addChild("No, B is not allowed to occur again.", false);
			TreeNode<String> aansw1231 = quest123.addChild("Yes, A is allowed to occur again.", false);
				TreeNode<String> qquest12311 = aansw1231.addChild("Is B allowed to occur again?", true);
					TreeNode<String> aansw123111 = qquest12311.addChild("No, B is not allowed to occur again.", false);
					TreeNode<String> aansw123112 = qquest12311.addChild("Yes, B is allowed to occur again, but not until after another A occurs. \n If another A does occur, the situation is the same as when the first A occured, meaning that \n the restrictions described on any events that take place after that occurence would again apply", false);
					TreeNode<String> aansw123113 = qquest12311.addChild("Yes, B is allowed to occur again, zero or more times, whether or not another A occurs.", false);
						TreeNode<String> qquest1231131 = aansw123113.addChild("If another A does occur, is the situation the same as when the first A occured, \n meaning that the restrictions described on any events that take place after that occurence would again apply?", true);
							TreeNode<String> aansw12311311 = qquest1231131.addChild("Yes, if A does occur again, the restrictions described on those events would again apply.", false);
							TreeNode<String> aansw12311312 = qquest1231131.addChild("No, even if A does not occur again, there are no restrictions on the occurences of any future events.", false);
			
		TreeNode<String> qquest124 = aansw12.addChild("Are there any exceptional events such that if any of them occurs the property behavior may not be required to hold?", true);
			TreeNode<String> aansw1241 = qquest124.addChild("No.", false);
			TreeNode<String> aansw1242 = qquest124.addChild("Yes, if exceptional event X occurs, the event sequence may be considered as acceptable.", false);
				TreeNode<String> qquest12421 = aansw1242.addChild("What if the exceptional event occurs after a behavior violation has been found?", true);
					TreeNode<String> aansw124211 = qquest12421.addChild("In that case, the event sequence is not acceptable.", false);
					TreeNode<String> aansw124212 = qquest12421.addChild("Even in that case, the event sequence is still considered acceptable.", false);
			
					
			
			// traverse for debugging purposes?
		TreeNode<String > treeRoot = questionnaire;
		for(TreeNode<String> node: treeRoot) {
			String ident = createIndent(node.getLevel());
			String hmmm = node.isQuestion?"Q: ":"A: ";
			
			System.out.println(ident+hmmm+node.data);
		}
	}
	

	public static void main(String[] args) {
		createQuestionnaire();
		final Display display = new Display();
		shell= new Shell(display);
		shell.setLayout(new FillLayout());
		final MySelectionListener mySelectionListener = new MySelectionListener();
		final MyExpandListener myExpandListener = new MyExpandListener();
		
		// we know first one(root) is a question	
		ExpandBar root = new ExpandBar(shell, SWT.V_SCROLL);;
		
		root.setBackground(display.getSystemColor(SWT.COLOR_BLUE));
		root.setBackgroundImage(new Image(display,"/home/daniela/Downloads/background1.jpg"));
		ExpandItem question = new ExpandItem(root, SWT.NONE, 0);
		question.setText((String) questionnaire.data);

//		 final int Tprincipal = compositePrincipal.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//		 root.setHeight(Tprincipal);
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
//			answer.setLayoutData(new GridData(SWT.WRAP, SWT.WRAP,false,  true));

			answer.addSelectionListener(mySelectionListener);
		}
		
		question.setHeight(answersContainer.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
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
		Composite compositeControlled = (Composite) itemExpanded.getControl();
		int heightToRemove = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		while(itemExpanded!=null){
			theOneWeLookFor = null;
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
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
		System.out.println("Trying to find a TreeNode, it's children are..: "+ Test4.questionnaire.findTreeNode(((Button) e.getSource()).getText()).children);
		if (isSelected) {
			
			List<TreeNode<String>> newQuestions = Test4.questionnaire.findTreeNode(((Button) e.getSource()).getText()).children;
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
			expandBarNewQuestions.setBackgroundImage(new Image(Display.getCurrent(),"/home/daniela/Downloads/background2.jpg"));
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
//					label1.setLayoutData(new GridData(SWT.WRAP, SWT.WRAP,false,  true));
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

	private void recallibrateHeight(int height, int oldOldHeight, Composite questionsHolder) {
		// we need to propagate the height to all ExpandItem parents in the hierarchy
				ExpandBar barHeigher = (ExpandBar) questionsHolder.getParent();
				ExpandItem[] items = barHeigher.getItems();
				ExpandItem itemExpanded = null;
				for(ExpandItem item:items){
					if(item.getControl().equals(questionsHolder))
						itemExpanded = item;
				}
//				ExpandItem itemExpanded = (ExpandItem) e.item;
				System.out.println("ItemExpanded: " + itemExpanded + "; height = " + itemExpanded.getHeight());
				ExpandItem theOneWeLookFor = null; 
				Composite compositeControlled = (Composite) itemExpanded.getControl();
				int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
				while(itemExpanded!=null){
					theOneWeLookFor = null;
					ExpandBar itemParentBar = itemExpanded.getParent();
					System.out.println("ItemParentBar: " + itemParentBar);
//					int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
					Composite compositeParent =itemParentBar.getParent();
					if(compositeParent!=null){
						ExpandBar expandBarSuperParent = (ExpandBar) compositeParent.getParent();
						if(expandBarSuperParent!=null){
							ExpandItem[] allItemsOfSuperParent = expandBarSuperParent.getItems();
							
							for(ExpandItem item:allItemsOfSuperParent){
								if(item.getControl().equals(compositeParent))
									theOneWeLookFor = item;
							}
//							heightToAdd = compositeParent.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
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
