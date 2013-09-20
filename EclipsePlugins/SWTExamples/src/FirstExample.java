import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
public class FirstExample {
	
	public static void main(String[] args){
		Display firstDisplay = new Display();
//		Shell firstShell = new Shell(firstDisplay);
//		firstShell.setText("First Example");
//		firstShell.setSize(200,100);
//		firstShell.open();
//		while(!firstShell.isDisposed()){
//			if(!firstDisplay.readAndDispatch())
//				firstDisplay.sleep();
//		}
//		firstDisplay.dispose();
		
		final Shell myShell = new Shell(firstDisplay);
		myShell.setText("This is a label");
//		myShell.setBounds(100,100,800,800);
		
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		
		myShell.setLayout(gridLayout);
		Label label = new Label(myShell,SWT.CENTER);
		label.setText("Hello World");
		Color red = new Color(firstDisplay,255,0,0);
		Color blue = new Color(firstDisplay,0,0,255);
		label.setForeground(red);
		label.setBackground(blue);
		
//		Composite composite = new Composite(myShell, SWT.BORDER);
//		composite.setBounds(35,35,800,820);
		
		final Button button = new Button(myShell,SWT.PUSH);
		button.setBounds(30, 30, 120, 120);
		button.setText("Click me");
		button.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				button.setText("You clicked me!");
			}
			
		});
		
		
		final Text text = new Text(myShell, SWT.MULTI);
		final Combo myCombo = new Combo(myShell,SWT.READ_ONLY);
		myCombo.setItems(new String[] {"option1", "option2", "option3", "option4"});
		myCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event){
				System.out.println("You selected me: " + myCombo.getText());
			}
			
		});
		
//		final Table myTable = new Table(myShell,SWT.SINGLE | SWT.FULL_SELECTION);
//		TableColumn col1 = new TableColumn(myTable, SWT.NULL);
//		col1.setText("First column");
//		col1.pack();
//		
//		TableColumn col2 = new TableColumn(myTable, SWT.NULL);
//		col2.setText("Second column");
//		col2.pack();
//		
//		TableItem tableItem1 = new TableItem(myTable, SWT.NULL);
//		tableItem1.setText(new String[] {"A1", "A2"}); // sets the text for multiple columns in the table
//		TableItem tableItem2 = new TableItem(myTable, SWT.NULL);
//		tableItem2.setText(new String[] {"B1", "B2"});
//		
//		final Tree tree = new Tree(myShell, SWT.SINGLE);
//		for (int i = 1; i<4; i++){
//			TreeItem parent1 = new TreeItem(tree,0);
//			parent1.setText("Parent1 - " + i);
//			for (int j = 1; j < 4; j++){
//				TreeItem parent2 = new TreeItem(parent1,0);
//				parent2.setText("Parent2 - " + j);
//				for (int k = 1; k < 4; k++){
//					TreeItem child = new TreeItem(parent2,0);
//					child.setText("Child - " + k);
//				}
//			}
//		}
		
		  Menu myBar = new Menu(myShell, SWT.BAR);
	      myShell.setMenuBar(myBar);
	      MenuItem fileMenuItem = new MenuItem(myBar, SWT.CASCADE);
	      fileMenuItem.setText("&This is my Menu");
	      Menu subMenuItem = new Menu(myShell, SWT.DROP_DOWN);
	      fileMenuItem.setMenu(subMenuItem);
	      MenuItem selectMenuItem = new MenuItem(
	         subMenuItem, SWT.NULL);
	      selectMenuItem.setText("&Hello\tCtrl+S");
	      selectMenuItem.setAccelerator(SWT.CTRL + 'S');
	      selectMenuItem.addSelectionListener(
	         new SelectionAdapter() {
	         public void widgetSelected(SelectionEvent event) {
	            System.out.println("Hello Selected!");
	         }
	      });
	      MenuItem thisIsSeperator = new MenuItem(subMenuItem, SWT.SEPARATOR);
	      MenuItem exitMenuItem = new MenuItem(subMenuItem, SWT.NULL);
	      exitMenuItem.setText("&Bye");
	      exitMenuItem.addSelectionListener(new SelectionAdapter(){
	         public void widgetSelected(SelectionEvent event) {
	            myShell.dispose();
	         }
	      });
		
		myShell.open();
		while(!myShell.isDisposed()){
			if(!firstDisplay.readAndDispatch()) firstDisplay.sleep();
		}
		
		red.dispose();
		firstDisplay.dispose();
	}

}
