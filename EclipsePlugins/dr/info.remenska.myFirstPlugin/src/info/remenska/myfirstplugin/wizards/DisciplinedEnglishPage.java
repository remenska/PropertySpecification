package info.remenska.myfirstplugin.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

public class DisciplinedEnglishPage  extends WizardPage  {

	protected DisciplinedEnglishPage(String pageName, String description) {
		super(pageName);
		setTitle(pageName);
		setDescription(description);
		setPageComplete(false);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
	}

}
