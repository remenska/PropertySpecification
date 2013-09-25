import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
//import org.eclipse.core.internal.utils.Queue;
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

public class Test2 {
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
		TreeNode<String> answ12 = questionnaire.addChild("No, the behavior is required to hold throughout the entire event sequence", false);
			TreeNode<String> quest121 = answ11.addChild("Really are you sure? ", true);
				TreeNode<String> answ1211 = quest121.addChild("Blaaah yah1", false);
				TreeNode<String> answ1212 = quest121.addChild("Blaaah yah2", false);
		   TreeNode<String> quest122 = answ11.addChild("Really are you sure sure? ", true);
				TreeNode<String> answ1221 = quest122.addChild("Blaaah blaah yah1", false);
				TreeNode<String> answ1222 = quest122.addChild("Blaaah blaah yah2", false);		
				aman = 1;
				
			    // traverse?
//		TreeNode<String > treeRoot = questionnaire;
//		for(TreeNode<String> node: treeRoot) {
//			String ident = createIndent(node.getLevel());
//			String hmmm = node.isQuestion?"Q: ":"A: ";
//			
////			System.out.println(ident+hmmm+node.data + " "+aman); // <-- so it's depth first
//			aman++;
//		}
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
				if (isSelected) {
					Control[] children = ((Button) e.getSource()).getParent()
							.getChildren();
					for (Control child : children) {
						child.setVisible(true);
						System.out.println(child + "  ");
						if (child instanceof ExpandBar) {
							ExpandItem[] theirExpandItems = ((ExpandBar) child)
									.getItems();
							for (ExpandItem theirChild : theirExpandItems) {
								System.out.print("Their children: "
										+ theirChild);
								theirChild.setExpanded(false);
							}
						}

					}
					System.out.println("\n\n");

				}
			}

		};

		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(questionnaire);
		while(!queue.isEmpty()){
			TreeNode node = (TreeNode) queue.remove();

			ExpandBar expandBar1= new ExpandBar(shell, SWT.NONE);;
			Composite composite1 = new Composite(expandBar1, SWT.NONE);;
			ExpandItem expandItem1;
			Button label1;
			if (node.isQuestion){
			
//				if(queue.isEmpty()){
					expandBar1 = new ExpandBar(shell, SWT.NONE);
					expandBar1.setLayoutData(new GridData(GridData.FILL, GridData.FILL,true, true, 2, 1));
					composite1 = new Composite(expandBar1, SWT.NONE);

//				}
				GridLayout layout1 = new GridLayout(1, true);
				composite1.setLayout(layout1);

				expandItem1 = new ExpandItem(expandBar1, SWT.NONE, 0);
				expandItem1.setText((String) node.data);
				expandItem1.setHeight(100);
				expandItem1.setControl(composite1);

				List<TreeNode<String>> deca = node.children;
				for(TreeNode dete:deca){
					label1 = new Button(composite1, SWT.RADIO);
					label1.setText((String) dete.data);
					label1.addSelectionListener(selectionListener);
					queue.addAll(dete.children);
				}
			} 

		}
		


		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	// heightItem_n = composite_n + heightItem_n+1
	public static int computeHeightItem(int n) {

		if (n == 0) {
			return 183;
		} else {
			return n + computeHeightItem(n - 1);
		}
	}

}
