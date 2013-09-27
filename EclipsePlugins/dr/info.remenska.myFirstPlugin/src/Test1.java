import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.ExpandListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
public class Test1 {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
 
        // Création de l'ExpandBar
        final ExpandBar expandBarrePrincipale = new ExpandBar(shell, SWT.NONE);
 
        // Créé un composite qui sera stocké dans l'expandBar
        final Composite compositePrincipal = new Composite(expandBarrePrincipale, SWT.NONE);
        GridLayout layoutPrincipal = new GridLayout(2, true);
        compositePrincipal.setLayout(layoutPrincipal);
        Label label = new Label(compositePrincipal, SWT.NONE);
        label.setImage(display.getSystemImage(SWT.ICON_WARNING));
        label = new Label(compositePrincipal, SWT.NONE);
        label.setText("SWT.ICON_WARNING");
        /**
         * ExpandBar secondaire
         */
        final ExpandBar expandBarreSecondaire = new ExpandBar(compositePrincipal, SWT.NONE);
        expandBarreSecondaire.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true, 2, 1));
 
        final Composite compositeSecondaire = new Composite(expandBarreSecondaire, SWT.NONE);
        compositeSecondaire.setLayout(new GridLayout(2, true));
 
        Label labelSec = new Label(compositeSecondaire, SWT.NONE);
        labelSec.setImage(display.getSystemImage(SWT.ICON_INFORMATION));
        labelSec = new Label(compositeSecondaire, SWT.NONE);
        labelSec.setText("SWT.ICON_INFORMATION");
 
        final ExpandItem itemPrincipal = new ExpandItem(expandBarrePrincipale, SWT.NONE, 0);
        itemPrincipal.setText("TRIGGER");
        itemPrincipal.setControl(compositePrincipal);
 
 
        final ExpandItem itemSecondaire = new ExpandItem(expandBarreSecondaire, SWT.NONE, 0);
        itemSecondaire.setText("TRIGGER 2(le retour)");
        itemSecondaire.setHeight(compositeSecondaire.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
        itemSecondaire.setControl(compositeSecondaire);
 
 
        /**
         * Fin ExpandBar secondaire
         */
        label = new Label(compositePrincipal, SWT.NONE);
        label.setImage(display.getSystemImage(SWT.ICON_QUESTION));
        label = new Label(compositePrincipal, SWT.NONE);
        label.setText("SWT.ICON_QUESTION");
 
        final int Tprincipal = compositePrincipal.computeSize(SWT.DEFAULT, SWT.DEFAULT).y;
        itemPrincipal.setHeight(Tprincipal);
        expandBarreSecondaire.addExpandListener(new ExpandListener() {
			@Override
        	public void itemCollapsed(ExpandEvent event) {
                   System.out.println(event.item);
                   System.out.println("avant retour à la normale, Taille = " + Tprincipal);
                   itemPrincipal.setHeight(Tprincipal);
                   System.out.println("aprèsretour à la normale");
 
               }
			@Override
               public void itemExpanded(ExpandEvent event){
                   System.out.println(event.item);
                   itemPrincipal.setHeight(compositePrincipal.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + compositeSecondaire.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
               }
		
            });
 
        // Fin d'implémentation du test
 
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }
}