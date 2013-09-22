package info.remenska.myfirstplugin;

import info.remenska.myfirstplugin.wizards.CaptureEmployeeInformationWizard;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

public class ResourceManagerViewActionDelgate implements IViewActionDelegate {

	private IViewPart view;
	@Override
	public void run(IAction action) {
		
//		
//		MessageBox box = new MessageBox(view.getSite().getShell(),SWT.ICON_INFORMATION);
//		box.setMessage("Hello you! You clicked view action!");
//		box.open();
		
//		InputDialog dialog = new InputDialog(view.getSite().getShell(),"Let's try!", 
//				"Please enter your name","",null);
//		if (dialog.open() == IStatus.OK){ 
//			String value = dialog.getValue();
//			MessageBox box = new MessageBox(view.getSite().getShell(),SWT.ICON_INFORMATION);
//			box.setMessage("Hey there! You entered: " + value);
//			box.open();
//			
//		} else{
//			
//			MessageBox box = new MessageBox(view.getSite().getShell(),SWT.ICON_INFORMATION);
//			box.setMessage("Bye!");
//			box.open();
//			
//		}
		
	CaptureEmployeeInformationWizard wizard = new CaptureEmployeeInformationWizard();
	WizardDialog dialog = new WizardDialog(view.getSite().getShell(), wizard);
	dialog.create();
	dialog.open();
	
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		this.view = view; //cache the view part, this will be used in run action 
						// to fetch the parent shell for dialog
	}

}
