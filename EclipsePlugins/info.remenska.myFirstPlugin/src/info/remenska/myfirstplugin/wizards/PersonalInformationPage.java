package info.remenska.myfirstplugin.wizards;

import info.remenska.myfirstplugin.views.PropertyManagerView;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PersonalInformationPage extends WizardPage {
	Text firstNameText;
	Text secondNameText;
	
	protected PersonalInformationPage(String pageName){
		super(pageName);
		setTitle("Personal Information");
		setDescription("Please enter your personal information");
		
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		new Label(composite,SWT.NONE).setText("First Name");
		firstNameText = new Text(composite,SWT.NONE);
		new Label(composite,SWT.NONE).setText("Last Name");
		secondNameText = new Text(composite,SWT.NONE);
		final Button button = new Button(composite,SWT.PUSH);
		button.setText("Click me");
		button.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				button.setText("You clicked me!");
				org.eclipse.ui.navigator.CommonNavigator comm = new org.eclipse.ui.navigator.CommonNavigator();
				comm.setFocus();
				//DanielaSelectionDialog dialog = new DanielaSelectionDialog(getShell());
				//dialog.setInitialPattern("a");
				 //dialog.open();
			}
			
		});
	}

}
