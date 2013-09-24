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

public class Test {
	private static int DEPTH_TREE = 6;
	private static int[] heightItem = new int[DEPTH_TREE];
	private static TreeNode<String> questionnaire = new TreeNode<String>(
			"Scope Question Tree View", false);

	private static String createIndent(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) {
                sb.append(' ');
                sb.append(' ');
        }
        return sb.toString();
}
	public static void createQuestionnaire(){
	TreeNode<String> quest1 = questionnaire.addChild("Is the behavior only required to hold within the restricted interval(s) in the event sequence?", true);
		TreeNode<String> answ11 = quest1.addChild("Yes, the behavior is only required to hold within restricted interval(s) in the event sequence. ", false);
			TreeNode<String> quest111 = answ11.addChild("Which of the following choices best describes the restricted interval(s)?", true);
				TreeNode<String> answ1111 = quest111.addChild("There can be at most one restricted interval ", false);
				TreeNode<String> answ1112 = quest111.addChild("Can have both blabla ", false);
		TreeNode<String> answ12 = quest1.addChild("No, the behavior is required to hold throughout the entire event sequence", false);
			TreeNode<String> quest121 = answ11.addChild("Really are you sure? ", true);
				TreeNode<String> answ1211 = quest121.addChild("Blaaah yah1", false);
				TreeNode<String> answ1212 = quest121.addChild("Blaaah yah2", false);
				
				
				
			    // traverse?
		TreeNode<String > treeRoot = questionnaire;
		for(TreeNode<String> node: treeRoot) {
			String ident = createIndent(node.getLevel());
			String hmmm = node.isQuestion?"Q: ":"A: ";
			
			System.out.println(ident+hmmm+node.data);
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
		final ExpandBar expandBar1 = new ExpandBar(shell, SWT.NONE);

		final Composite composite1 = new Composite(expandBar1, SWT.NONE);
		GridLayout layout1 = new GridLayout(1, true);
		composite1.setLayout(layout1);
		composite1.setVisible(true);
		Button label1 = new Button(composite1, SWT.RADIO);
		label1.setText("Yes, the behavior is only required to hold within restricted interval(s) in the event sequence");
		Button label2 = new Button(composite1, SWT.RADIO);
		label2.setText("No, the behavior is required to hold throughout the entire event sequence");

		final ExpandItem expandItem1 = new ExpandItem(expandBar1, SWT.NONE, 0);
		expandItem1
				.setText("Is the behavior only required to hold within the restricted interval(s) in the event sequence?   ");
		expandItem1
				.setHeight(composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		expandItem1.setControl(composite1);

		/**
		 * ExpandBar secondaire
		 */
		final ExpandBar expandBar2 = new ExpandBar(composite1, SWT.NONE);
		expandBar2.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 2, 1));

		final Composite composite2 = new Composite(expandBar2, SWT.NONE);
		composite2.setLayout(new GridLayout(2, true));
		expandBar2.setVisible(false);

		Button labelSec = new Button(composite2, SWT.RADIO);
		labelSec.setText("SWT.ICON_INFORMATION");
		labelSec.addSelectionListener(selectionListener);
		labelSec = new Button(composite2, SWT.RADIO);
		labelSec.setText("SWT.ICON_INFORMATION_1");
		labelSec.addSelectionListener(selectionListener);

		final ExpandItem expandItem2 = new ExpandItem(expandBar2, SWT.NONE, 0);
		expandItem2
				.setText("How many events of primary interest are there in this behavior?    ");
		expandItem2.setControl(composite2);
		expandItem2.setExpanded(false);
		final ExpandBar expandBar3 = new ExpandBar(composite2, SWT.NONE);
		expandBar3.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 2, 1));
		final Composite composite3 = new Composite(expandBar3, SWT.NONE);
		composite3.setLayout(new GridLayout(2, true));
		expandBar3.setVisible(false);
		Button labelThird = new Button(composite3, SWT.RADIO);
		labelThird.addSelectionListener(selectionListener);

		labelThird.setText("Third level  ");
		labelThird = new Button(composite3, SWT.RADIO);
		labelThird.setText("Third level yaah  ");
		labelThird.addSelectionListener(selectionListener);

		final ExpandItem expandItem3 = new ExpandItem(expandBar3, SWT.NONE, 0);
		expandItem3.setText("Yet another expand item");
		expandItem3.setControl(composite3);
		expandItem3.setExpanded(false);

		int heightItem3 = composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
		int heightItem2 = composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT).y
				+ heightItem3;
		int heightItem1 = composite1.computeSize(SWT.DEFAULT, SWT.DEFAULT).y
				+ heightItem2;
		System.out.println(heightItem1 + "  " + heightItem2 + "  "
				+ heightItem3);
		expandItem1.setHeight(heightItem1);

		expandItem2.setHeight(heightItem2);
		expandItem3.setHeight(heightItem3);

		// Fin d'implémentation du test

		label1.addSelectionListener(selectionListener);
		label2.addSelectionListener(selectionListener);

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	// heightItem_n = composite_n + heightItem_n+1
	public static int computeHeightItem(int n) {

		if (n == DEPTH_TREE) {
			return n;
		} else {
			return n + computeHeightItem(n + 1);
		}
	}

}
