package info.remenska.myfirstplugin.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PropertySpecificationScopes extends WizardPage {

	protected PropertySpecificationScopes(String pageName, String description) {
		super(pageName);
		setTitle(pageName);
		setDescription(description);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControl(Composite parent) {
		String explanation = " Each pattern has a scope, which is the extent of the program execution "
		+ "over which the pattern must hold. There are five basic kinds of scopes: global (the entire program execution), "
		+ "before (the execution up to a given state/event), after (the execution after a given state/event),"
		+ "between (any part of the execution from one given state/event to another given state/event) "
		+ "and after-until (like between but the designated part of the execution continues even if the second state/event does not occur)."
		+ "The scope is determined by specifying a starting and an ending state/event for the pattern: "
		+ "the scope consists of all states/events beginning with the starting state/event and up to but not including the ending state/event.";

		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
//		layout.set
		setControl(composite);

		GridData gridData = new GridData();
//		gridData.heightHint = 300;
//		gridData.widthHint = 700;
//		gridData.horizontalAlignment = GridData.GRAB_HORIZONTAL;
//		gridData.verticalAlignment = GridData.GRAB_VERTICAL;
//		gridData.se
		StyledText styledText = new StyledText(composite, SWT.WRAP | SWT.BORDER);
		styledText.setLineJustify(0, 1, true);
		styledText.setEditable(false);
//		styledText.setLineAlignment(6, 1, SWT.RIGHT);
		styledText.setWordWrap(true);
		styledText.setLayoutData(gridData); 
//		styledText.setBounds(10,10,100,100);
		styledText.setText(explanation);
//		Label labelExplanation = new Label(composite, SWT.FILL);
//		labelExplanation.setText(explanation);

	}

}
