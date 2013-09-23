package info.remenska.myfirstplugin.wizards;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.Workbench;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;

public class AddressInformationPage extends WizardPage {
	Text street;
	Text city;
	Text state;
	
	protected AddressInformationPage(String pageName){
		super(pageName);
		setTitle("Address Information");
		setDescription("Please enter your address information");
		setPageComplete(false);
	}
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);
		setControl(composite);
		new Label(composite, SWT.NONE).setText("Street");
		street = new Text(composite, SWT.NONE);
		new Label(composite, SWT.NONE).setText("City");
		city = new Text(composite, SWT.NONE);	
		new Label(composite, SWT.NONE).setText("State");
		state = new Text(composite, SWT.NONE);	
		createChangeAddressPagePage(composite);
	}
	
	private void createChangeAddressPagePage(final Composite container1) {
		Composite container = new Composite(container1, SWT.NONE);
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		container.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		ExpandBar expandBar = new ExpandBar(container, SWT.NONE);
		final ExpandItem expandItem1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem1.setText("Scope Timeline View");
		new ExpandItem(expandBar, SWT.NONE).setText("Scope Question Tree View"); /* expandItem2 */
		final ExpandItem expandItem2 = new ExpandItem(expandBar, SWT.NONE);
		expandItem2.setText("Behavior Question Tree View");
		
		final StyledText text = new StyledText(expandBar, SWT.MULTI | SWT.WRAP);
		expandItem1.setControl(text);
		text.setText("initial text that will wrap if it's long enough");
		/* update the item's height if needed in response to changes in the text's size */ 
		final int TRIAL_WIDTH = 100;
		final int trimWidth = text.computeTrim(0, 0, TRIAL_WIDTH, 100).width - TRIAL_WIDTH;
		text.addListener(SWT.Modify, new Listener() {
			public void handleEvent(Event event) {
				Point size = text.computeSize(text.getSize().x - trimWidth, SWT.DEFAULT);
				if (expandItem1.getHeight() != size.y) {
					expandItem1.setHeight(size.y);
				}
			}
		});

		expandBar.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event event) {
				/* 
				 * The following is done asynchronously to allow the Text's width
				 * to be changed before re-calculating its preferred height. 
				 */
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						if (text.isDisposed()) return;
						Point size = text.computeSize(text.getSize().x - trimWidth, SWT.DEFAULT);
						if (expandItem1.getHeight() != size.y) {
							expandItem1.setHeight(size.y);
						}
					}
				});
			}
		});
		
		Point size = text.computeSize(expandBar.getClientArea().width, SWT.DEFAULT);
		expandItem1.setHeight(400);
		expandItem1.setExpanded(true);

	}
}
