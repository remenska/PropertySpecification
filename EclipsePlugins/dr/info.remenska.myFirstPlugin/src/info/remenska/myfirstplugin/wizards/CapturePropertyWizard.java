package info.remenska.myfirstplugin.wizards;

import java.awt.Button;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.themes.WorkbenchPreview;

public class CapturePropertyWizard extends Wizard {
	PropertySpecificationScopes propertyScopes;
	ModelSelectionPage modelSelectionPage;
	QuestionTreePage addressInfoPage;
	QuestionTreePage questionTreePage;
	DisciplinedEnglishPage disciplinedEnglishPage;
	public void addPages(){
		propertyScopes = new PropertySpecificationScopes("Introduction", "Property Specification Scopes");
		addPage(propertyScopes);
//		getShell().setMaximized(true);
//		getShell().setFullScreen(true);
//		modelSelectionPage = new ModelSelectionPage("Personal Information Page");
//		addPage(modelSelectionPage);
		addressInfoPage = new ScopeQuestionTreePage("Scope Question Tree View", "Please answer the following questions regarding the scope of the property:", Questionnaire.scopeQuestionTree);
		addPage(addressInfoPage);
		questionTreePage = new BehaviorQuestionTreePage("Behavior Question Tree View", "Please answer the following questions regarding the behavior of the property:", Questionnaire.behaviorQuestionTree);
		addPage(questionTreePage);
		disciplinedEnglishPage = new DisciplinedEnglishPage("Disciplined English Summary: ", "Please review the collected information regarding the property.");
		addPage(disciplinedEnglishPage);
		this.getShell().setMaximized(true);
		this.getShell().setFocus();
//		this.getShell().pack();
//		this.getShell().setMaximized(true);
		Rectangle clientArea = this.getShell().getClientArea ();
		System.out.println("Client Area: "+ clientArea + " AAAA" + getShell().getMaximized());
		this.getShell().setSize(clientArea.width, clientArea.height);
//		this.getShell().pack();
	}
	@Override
	public boolean performFinish() {
		// TODO Auto-generated method stub
		this.getShell().setFocus();
//		this.dispose();
		return true;
	}

}