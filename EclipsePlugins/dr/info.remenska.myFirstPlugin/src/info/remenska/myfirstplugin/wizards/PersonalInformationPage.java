package info.remenska.myfirstplugin.wizards;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import info.remenska.myfirstplugin.views.PropertyManagerView;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.uml.type.UMLElementTypes;
import com.ibm.xtools.uml.ui.internal.dialogs.UMLSelectExistingElementDialog;
import com.ibm.xtools.uml.ui.internal.dialogs.UMLSelectElementDialog;
import com.ibm.xtools.uml.ui.internal.dialogs.selectelement.SelectElementDialog;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.search.ecore.common.ui.dialogs.ModelSearchFilteredEClassifierSelectionDialog;
import org.eclipse.gmf.runtime.common.ui.services.elementselection.ElementSelectionScope;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.uml2.search.common.ui.dialogs.ModelSearchFilteredUMLClassSelectionDialog;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

//import org.eclipse.emf.search.ecore.common.ui.dialogs.ModelSearchFilteredEClassifierSelectionDialog;

public class PersonalInformationPage extends WizardPage {
	Text firstNameText;
	Text secondNameText;

	protected PersonalInformationPage(String pageName) {
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
		new Label(composite, SWT.NONE).setText("First Name");
		firstNameText = new Text(composite, SWT.NONE);
		new Label(composite, SWT.NONE).setText("Last Name");
		secondNameText = new Text(composite, SWT.NONE);
		final Button button = new Button(composite, SWT.PUSH);
		button.setText("Click me");
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				button.setText("You clicked me!");
				// org.eclipse.ui.navigator.CommonNavigator comm = new
				// org.eclipse.ui.navigator.CommonNavigator();
				// comm.setFocus();
				// ModelSearchFilteredUMLClassSelectionDialog dialog = new
//				// ModelSearchFilteredUMLClassSelectionDialog(Display.getDefault().getActiveShell(),false);
//				EclassName:Class : B
//				EclassName:Class : C
//				EclassName:Operation : getAnotherValue
				
				Collection<Model> models = UMLModeler.getOpenedModels();
				ResourceSet resourceSet = UMLModeler.getEditingDomain().getResourceSet();

				List selectedElements = UMLModeler.getUMLUIHelper().getSelectedElements();
				for (Iterator iter = selectedElements.iterator(); iter.hasNext();) {
					
					EObject eObject = (EObject) iter.next();
					String eClassName = eObject.eClass().getName();
					System.out.print("EclassName:"+eClassName + " : ");

					if (eObject instanceof Diagram) {
						System.out.println(((Diagram) eObject).getName());

					} else if (eObject instanceof View) {
						View view = (View) eObject;
						String viewType = view.getType();
						if (viewType.trim().length() > 0) {
							System.out.print("(" + view.getType() + ")");
						}

						EObject element = view.getElement();
						if (null != element) {
							System.out.print(" of " + element);
						}
						System.out.println();

					} else if (eObject instanceof Element) {
						if (eObject instanceof NamedElement) {
							System.out.println(((NamedElement) eObject).getName());
						} else {
							System.out.println(eObject);
						}
					}
				}
				
//				UMLSelectExistingElementDialog dialog = new UMLSelectExistingElementDialog(
//						new ArrayList(models));
				ArrayList aman = new ArrayList();
//				aman.add(org.eclipse.uml2.uml.Operation.getType());
				 UMLSelectExistingElementDialog dialog = new
				 UMLSelectExistingElementDialog(Collections.singletonList(UMLElementTypes.OPERATION));
//				 List<?> elements = dialog.getSelectedElements();
//				 System.out.println("Selected stuff: " + elements.toString());
				//
				// ModelSearchFilteredUMLClassSelectionDialog dialog = new
				// ModelSearchFilteredUMLClassSelectionDialog(Display.getDefault().getActiveShell(),
				// true);
				// dialog.setTitle("Select Existing Operation YAH!");
				// dialog.setMessage("Enter operation name prefix or pattern (* or ?):");
				// dialog.setInitialPattern("?");
				// dialog.refresh();
				 dialog.setBlockOnOpen(true);
				 dialog.create();
//				int returnCode = dialog.open();
				// dialog.getResult();
//				System.out.println(returnCode);
				if(dialog.open()==Window.OK){
					List<Operation> selected  = (List<Operation>) dialog.getSelectedElements();
					System.out.println(selected.get(0).getName()+" : "+selected.get(0).getQualifiedName());
					
					System.out.println(selected.get(0).getOwner().eClass().getName());
				}
//				new Label(composite, SWT.NONE).setText();
			}

		});

	}

}
