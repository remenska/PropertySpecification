import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Test3 {
	private static int DEPTH_TREE = 6;
	private static int[] heightItem = new int[DEPTH_TREE];
	public static TreeNode<String> questionnaire = new TreeNode<String>("Is the behavior only required to hold within the restricted interval(s) in the event sequence?", true);
	public static int aman = 0;


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
				TreeNode<String> answ1122 = quest112.addChild("Blaaah yah2", false);
			TreeNode<String> quest113 = answ11.addChild("Really are you sure sure? ", true);
				TreeNode<String> answ1131 = quest113.addChild("Blaaah blaah yah1", false);
				TreeNode<String> answ1132 = quest113.addChild("Blaaah blaah yah2", false);		
		TreeNode<String> answ12 = questionnaire.addChild("No, the behavior is required to hold throughout the entire event sequence", false);

				aman = 1;
		
				
			    // traverse?

				traverseBFS(questionnaire);
	}
	
	public static void traverseBFS(TreeNode root){
		root = questionnaire;
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			TreeNode node = (TreeNode) queue.remove();
			String ident = createIndent(node.getLevel());
			String hmmm = node.isQuestion?"Q: ":"A: ";
			queue.addAll(node.children);
//			queue.add(node.children);
			System.out.println(ident+hmmm+node.data + " "+aman); // <-- so it's depth first
			aman++;
		}
		
	}
	
	public static void main(String[] args) {
		createQuestionnaire();
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		SelectionListener selectionListener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// ExpandBar contains: ExpandItem and Composite(contains:
				// Buttons))
				boolean isSelected = ((Button) e.getSource()).getSelection();
				System.out.println("-----------");
				System.out.println("Selected: "+  e.getSource());
				if (isSelected) {
					Control[] children = ((Button) e.getSource()).getParent()
							.getChildren();
					System.out.println("Parent: " +  ((Control) e.getSource()).getParent());
					System.out.println("Parent's children: ");
					for (Control child : children) {
						child.setVisible(true);
						System.out.println("\t \t child: "+ child + "  ");
						if (child instanceof ExpandBar) {
							ExpandItem[] theirExpandItems = ((ExpandBar) child).getItems();
							Control[] restOfChildren = ((ExpandBar) child).getChildren();
							for (ExpandItem theirChild : theirExpandItems) {
								System.out.println("\t \t \t Their children ExpandItems: "
										+ theirChild);
								theirChild.setExpanded(false);
							}
							
							for (Control theirChild : restOfChildren) {
								System.out.println("\t \t \t Their children (rest): "
										+ theirChild);
//								theirChild.setExpanded(false);
							}
						}


					}
					System.out.println("\n\n");

				}
			}

		};

		LinkedList<LinkedList<TreeNode>> queue = new LinkedList<LinkedList<TreeNode>>();
		LinkedList<TreeNode> grrrrr = new LinkedList<TreeNode>();
		grrrrr.add(questionnaire);
		queue.add(grrrrr);
		System.out.println("QUEUE: " + queue);

		//beginning manual cycle:
		ExpandBar questions= new ExpandBar(shell, SWT.NONE);;
		ExpandItem[] root = questions.getItems();
		// keep a list of Composites and ExpandItems so we can re-calculate the height
		LinkedList<Composite> composites = new LinkedList<Composite>();
		LinkedList<ExpandItem> expandItems = new LinkedList<ExpandItem>();
		LinkedList<ExpandBar> expandBars = new LinkedList<ExpandBar>(); 
		expandBars.add(questions);
		// here dequeue, we know first one is a question(s)
		LinkedList<TreeNode> nodes =  queue.remove();
		// we need to dequeue all questions and put them in the same ExpandBar, different expandItems and Composite
		Composite answers = null; 
		ExpandItem question = null;
		Button answer = null;
		for(TreeNode node:nodes){
			answers = new Composite(questions, SWT.NONE);
			composites.add(answers);

			GridLayout layout1 = new GridLayout(1, true);
			answers.setLayout(layout1);
			answers.setVisible(true);
			
			question = new ExpandItem(questions, SWT.NONE, 0);
			question.setText((String) node.data);
			question.setHeight(answers.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
			question.setControl(answers); //		Sets the control that is shown when the item is expanded.
			expandItems.add(question);
			// these kids are ANSWERS
			List<TreeNode<String>> deca = node.children;
//			System.out.println("KIDS: " + deca);
			for(TreeNode dete:deca){
				answer = new Button(answers, SWT.RADIO);
				answer.setText((String) dete.data);
				answer.addSelectionListener(selectionListener);
				if(!dete.children.isEmpty())
				queue.add((LinkedList<TreeNode>) dete.children); // there should be separate lists of questions for each answer, not just a single queue 
											// with all the "next" questions. Because one Answer (dete) may need to show multiple questions
											// so a queue-of-lists?  and in every dequeue, all questions from a list are in the same ExpandBar
			}
			
//			int heightItem1 = composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//			expandItem1.setHeight(heightItem1);
			System.out.println("QUEUE: " + queue);
		}
		
		//end manual cycle:

		while(!queue.isEmpty()){
			nodes =  queue.remove();
			questions= new ExpandBar(answers, SWT.NONE);
			expandBars.add(questions);
			questions.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
					true, true, 2, 1));
			questions.setVisible(false);
			
			for(TreeNode node:nodes){
				answers = new Composite(questions, SWT.NONE);;
				composites.add(answers);
				GridLayout layout1 = new GridLayout(1, true);
				answers.setLayout(layout1);
				answers.setVisible(true);
				
				question = new ExpandItem(questions, SWT.NONE, 0);
				expandItems.add(question);
				question.setText((String) node.data);
				question.setHeight(answers.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
				question.setControl(answers); //		Sets the control that is shown when the item is expanded.

				// these kids are ANSWERS
				List<TreeNode<String>> deca = node.children;
//				System.out.println("KIDS: " + deca);
				for(TreeNode dete:deca){
					answer = new Button(answers, SWT.RADIO);
					answer.setText((String) dete.data);
					answer.addSelectionListener(selectionListener);
					if(!dete.children.isEmpty())
					queue.add((LinkedList<TreeNode>) dete.children); // there should be separate lists of questions for each answer, not just a single queue 
												// with all the "next" questions. Because one Answer (dete) may need to show multiple questions
												// so a queue-of-lists?  and in every dequeue, all questions from a list are in the same ExpandBar
				}
				
//				int heightItem1 = composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//				expandItem1.setHeight(heightItem1);
						
				System.out.println("QUEUE: " + queue);
			}

		}
		

//		int heightItem3 = composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//		int heightItem21 = composite21.computeSize(SWT.DEFAULT, SWT.DEFAULT).y
//				;
//		int heightItem22 = composite22.computeSize(SWT.DEFAULT, SWT.DEFAULT).y
//				+ heightItem3 + heightItem21;
//		int heightItem1 = composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y
//				+ heightItem21 + heightItem22;
//		
////		System.out.println(heightItem1 + "  " + heightItem2 + "  "
////				+ heightItem3);
//		expandItem1.setHeight(heightItem1);
//
//		expandItem21.setHeight(heightItem21);
//		expandItem22.setHeight(heightItem22);
//
//		expandItem3.setHeight(heightItem3);
		
		

		
		
		
//		int height = composites.removeLast().computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//		expandItems.removeLast().setHeight(height);
//		while(!composites.isEmpty()){
//			height = height + composites.removeLast().computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
//			expandItems.removeLast().setHeight(height);
//		}
		
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}


}
