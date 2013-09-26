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
	

	public static void main(String[] args) {
		createQuestionnaire();
		final Display display = new Display();
		shell= new Shell(display);
		shell.setLayout(new FillLayout());
		final MySelectionListener mySelectionListener = new MySelectionListener();
		final MyExpandListener myExpandListener = new MyExpandListener();
		
		// we know first one(root) is a question	
		ExpandBar root = new ExpandBar(shell, SWT.NONE);;
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
		System.out.println("ItemExpanded: " + itemExpanded + "; height = " + itemExpanded.getHeight());
		ExpandItem theOneWeLookFor = null; 
		while(itemExpanded!=null){
			theOneWeLookFor = null;
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
			Composite compositeControlled = (Composite) itemExpanded.getControl();
			int heightToRemove = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
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
		while(itemExpanded!=null){
			theOneWeLookFor = null;
			ExpandBar itemParentBar = itemExpanded.getParent();
			System.out.println("ItemParentBar: " + itemParentBar);
			Composite compositeControlled = (Composite) itemExpanded.getControl();
			int heightToAdd = compositeControlled.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
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
		System.out.println("Trying to find a TreeNode, it's children are..: "+ Test4.questionnaire.findTreeNode(((Button) e.getSource()).getText()).children);
		if (isSelected) {
			
			List<TreeNode<String>> newQuestions = Test4.questionnaire.findTreeNode(((Button) e.getSource()).getText()).children;
			Composite questionsHolder = ((Button) e.getSource()).getParent();
//			questionsHolder.redraw();
//			questionsHolder.update();
			int oldOldHeight = 0;
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
			ExpandBar expandBarParent = (ExpandBar) questionsHolder.getParent();
			ExpandItem[] expandItems = expandBarParent.getItems();
			ExpandItem theOneWeNeed = null;
			for(ExpandItem expandItem:expandItems){
				if(expandItem.getControl().equals(questionsHolder))
					theOneWeNeed = expandItem;
			}
			//recalculate the height
			if(theOneWeNeed!=null){
				int oldHeight = theOneWeNeed.getHeight();
				theOneWeNeed.setHeight(oldHeight+height); // <-- this is fishy
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
