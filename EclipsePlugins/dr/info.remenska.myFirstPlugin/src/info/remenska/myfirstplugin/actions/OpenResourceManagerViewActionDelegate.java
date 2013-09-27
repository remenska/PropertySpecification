package info.remenska.myfirstplugin.actions;

import info.remenska.myfirstplugin.wizards.CapturePropertyWizard;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

public class OpenResourceManagerViewActionDelegate implements
		IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	public static final String ID = "info.remenska.myfirstplugin.views.ResourceManagerView";

	@Override
	public void run(IAction action) {
//		
//		IWorkbenchPage page = window.getActivePage();
//		
//		try {
//			page.showView(ID);
//			
//		} catch (PartInitException e){
//			
//		}
// Hmmm let's change it to show the wizard instead of the view :)
		
		CapturePropertyWizard wizard = new CapturePropertyWizard();
		WizardDialog dialog = new WizardDialog(window.getShell(), wizard);

		dialog.create();
		dialog.open();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window; // cache the window object in which action delegate is operating
	}

}
