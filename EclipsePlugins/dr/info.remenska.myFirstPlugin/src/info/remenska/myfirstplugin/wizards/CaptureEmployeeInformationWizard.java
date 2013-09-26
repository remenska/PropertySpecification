package info.remenska.myfirstplugin.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.themes.WorkbenchPreview;

public class CaptureEmployeeInformationWizard extends Wizard {
	PersonalInformationPage personalInfoPage;
	AddressInformationPage addressInfoPage;
	AddressInformationPage newPage;
	public void addPages(){
		personalInfoPage = new PersonalInformationPage("Personal Information Page");
		addPage(personalInfoPage);
		addressInfoPage = new AddressInformationPage("Scope Question Tree View", "Please answer the following questions regarding the scope of the property:", Questionnaire.scopeQuestionTree);
		addPage(addressInfoPage);
		newPage = new AddressInformationPage("Behavior Question Tree View", "Please answer the following questions regarding the behavior of the property:", Questionnaire.behaviorQuestionTree);
		addPage(newPage);
		this.getShell().setMaximized(true);
		this.getShell().setFocus();
		this.getShell().setMaximized(true);
		Rectangle clientArea = this.getShell().getClientArea ();
		System.out.println("Client Area: "+ clientArea + " AAAA" + getShell().getMaximized());
		this.getShell().setSize(clientArea.width, clientArea.height);
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		this.getShell().setFocus();
//		this.dispose();
		return true;
	}

}
