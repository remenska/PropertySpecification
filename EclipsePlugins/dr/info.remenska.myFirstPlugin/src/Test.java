
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
 


public class Test {
	
	private static TreeNode<String> questionnaire = new TreeNode<String>("Scope Question Tree View");
	public static void createQuestionnaire(){
	TreeNode<String> quest1 = questionnaire.addChild("Is the behavior only required to hold within the restricted interval(s) in the event sequence?");
		TreeNode<String> answ11 = quest1.addChild("Yes, the behavior is only required to hold within restricted interval(s) in the event sequence. ");
			TreeNode<String> quest111 = answ11.addChild("Which of the following choices best describes the restricted interval(s)?");
				TreeNode<String> answ1111 = quest111.addChild("There can be at most one restricted interval ");
				TreeNode<String> answ1112 = quest111.addChild("Can have both blabla ");
		TreeNode<String> answ12 = quest1.addChild("No, the behavior is required to hold throughout the entire event sequence");
			TreeNode<String> quest121 = answ11.addChild("Really are you sure? ");
				TreeNode<String> answ1211 = quest111.addChild("Blaaah yah1");
				TreeNode<String> answ1212 = quest111.addChild("Blaaah yah2");
	}
	
	
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
        // Création de l'ExpandBar
        final ExpandBar expandBarrePrincipale = new ExpandBar(shell, SWT.NONE);
 
        // Créé un composite qui sera stocké dans l'expandBar
        final Composite compositePrincipal = new Composite(expandBarrePrincipale, SWT.NONE);
        GridLayout layoutPrincipal = new GridLayout(1, true);
        compositePrincipal.setLayout(layoutPrincipal);
        Button label1 = new Button(compositePrincipal, SWT.RADIO);
        
//        label.setImage(display.getSystemImage(SWT.ICON_WARNING));
//        label = new Button(compositePrincipal, SWT.RADIO);
//        label.setText("SWT.ICON_WARNING");
        label1.setText("Yes, the behavior is only required to hold within restricted interval(s) in the event sequence");
      
//        label = new Button(compositePrincipal, SWT.RADIO);
//        label.setImage(display.getSystemImage(SWT.ICON_QUESTION));
        Button label2 = new Button(compositePrincipal, SWT.RADIO);
//        label.setText("SWT.ICON_QUESTION");
        label2.setText("No, the behavior is required to hold throughout the entire event sequence");
        
      
        
        final ExpandItem itemPrincipal = new ExpandItem(expandBarrePrincipale, SWT.NONE, 0);
        itemPrincipal.setText("Is the behavior only required to hold within the restricted interval(s) in the event sequence?   ");
        itemPrincipal.setHeight(compositePrincipal.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itemPrincipal.setControl(compositePrincipal);
 
        /**
         * ExpandBar secondaire
         */
        final ExpandBar expandBarreSecondaire = new ExpandBar(compositePrincipal, SWT.NONE);
        expandBarreSecondaire.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));
 
        final Composite compositeSecondaire = new Composite(expandBarreSecondaire, SWT.NONE);
        compositeSecondaire.setLayout(new GridLayout(2, true));
 
        Button labelSec = new Button(compositeSecondaire, SWT.RADIO);
//        labelSec.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
//        labelSec = new Label(compositeSecondaire, SWT.NONE);
        labelSec.setText("SWT.ICON_INFORMATION");
        labelSec = new Button(compositeSecondaire, SWT.RADIO);
        labelSec.setText("SWT.ICON_INFORMATION_1");
        
        
        final ExpandItem itemSecondaire = new ExpandItem(expandBarreSecondaire, SWT.NONE, 0);
//        itemSecondaire.setExpanded(false);
//        expandBarreSecondaire.setVisible(false);
        itemSecondaire.setText("How many events of primary interest are there in this behavior?    ");
        itemSecondaire.setControl(compositeSecondaire);
 
        final ExpandBar thirdExpandaBar = new ExpandBar(compositeSecondaire, SWT.NONE);
        thirdExpandaBar.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));
        final Composite thirdComposite = new Composite(thirdExpandaBar, SWT.NONE);
        thirdComposite.setLayout(new GridLayout(2, true));
        Button labelThird = new Button(thirdComposite, SWT.RADIO);
        labelThird.setText("Third level  ");
        labelThird = new Button(thirdComposite, SWT.RADIO);
        labelThird.setText("Third level yaah  ");
        final ExpandItem thirdExpandItem = new ExpandItem(thirdExpandaBar, SWT.NONE, 0);
        thirdExpandItem.setText("Yet another expand item");
        thirdExpandItem.setControl(thirdComposite);
        /**
         * Fin ExpandBar secondaire
         */
 
        itemPrincipal.setHeight(compositePrincipal.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + compositeSecondaire.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + thirdComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itemSecondaire.setHeight(compositeSecondaire.computeSize(SWT.DEFAULT, SWT.DEFAULT).y+ thirdComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

        thirdExpandItem.setHeight(thirdComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);

        // Fin d'implémentation du test
 
        label1.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isSelected = ((Button)e.getSource()).getSelection();
		          if(isSelected){     

//		                               System.out.println("Now this solved the problem");
		                               System.out.println(((Button)e.getSource()).getText());
		                               itemSecondaire.setText(((Button)e.getSource()).getText());
		                               expandBarreSecondaire.setVisible(true);
			}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        		
        });
        
        label2.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean isSelected = ((Button)e.getSource()).getSelection();
		          if(isSelected){     

                      System.out.println(((Button)e.getSource()).getText());
                      itemSecondaire.setText(((Button)e.getSource()).getText());
                      expandBarreSecondaire.setVisible(true);

			}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        		
        });
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}

