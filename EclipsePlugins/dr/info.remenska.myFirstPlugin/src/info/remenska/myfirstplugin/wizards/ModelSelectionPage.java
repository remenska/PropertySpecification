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
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.LayoutType;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.FillStyle;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Style;
import org.eclipse.uml2.uml.Activity;
import org.eclipse.uml2.uml.ActivityPartition;
import org.eclipse.uml2.uml.Actor;
import org.eclipse.uml2.uml.AggregationKind;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.CallBehaviorAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.ControlFlow;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.UseCase;

import com.ibm.xtools.modeler.ui.UMLModeler;
//import com.ibm.xtools.pluglets.Pluglet;
import com.ibm.xtools.umlnotation.UMLDiagramKind;

//import org.eclipse.emf.search.ecore.common.ui.dialogs.ModelSearchFilteredEClassifierSelectionDialog;

public class ModelSelectionPage extends WizardPage {
	Text firstNameText;
	Text secondNameText;

	protected ModelSelectionPage(String pageName) {
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
//		new Label(composite, SWT.NONE).setText("First Name");
//		firstNameText = new Text(composite, SWT.NONE);
//		new Label(composite, SWT.NONE).setText("Last Name");
//		secondNameText = new Text(composite, SWT.NONE);
//		final Button button = new Button(composite, SWT.PUSH);
//		button.setText("Click me");
		new Label(composite, SWT.NONE).setText("Select the operation ");
		final Text selectedOperation = new Text(composite, SWT.NONE);
		new Label(composite, SWT.NONE).setText("Select the working model: ");
		final Text selectedModel = new Text(composite, SWT.NONE);
		
		selectedModel.setEditable(false);
		
		selectedOperation.setEditable(false);
		Listener operationListener = new Listener(){
//
			@Override
			public void handleEvent(Event event) {
				 UMLSelectExistingElementDialog dialogOperation = new
						 UMLSelectExistingElementDialog(getShell(),Collections.singletonList(UMLElementTypes.OPERATION));
				 dialogOperation.create();
						if(dialogOperation.open()==Window.OK){
							List<Operation> selected = (List<Operation>) dialogOperation.getSelectedElements();
							System.out.println("Selected operation:"+ selected.get(0).getName()+" : "+selected.get(0).getQualifiedName());
							selectedOperation.setText(selected.get(0).getQualifiedName());
							dialogOperation.close();
						}
//
			}
//			
		};
		
	
		Listener modelListener = new Listener() {
			@Override
			public void handleEvent(Event event) {

				
//				Collection<Model> models = UMLModeler.getOpenedModels();
				
				/* Perform remaining work within a Runnable */
				try {

					String undoLabel = "Create Diagrams";
					TransactionalEditingDomain editDomain = UMLModeler
							.getEditingDomain();
					editDomain.getCommandStack().execute(
							new RecordingCommand(editDomain, undoLabel) {
								protected void doExecute() {
									// code to modify the model goes here
									// Get selection
									final Collection<Model> elements = UMLModeler.getOpenedModels();

									if (elements.size() == 0) {
												System.out.println("Cannot perform enumeration on current selection.\nPlease select a UML Element from the Project Explorer or\nselect a Notation element from a diagram."); //$NON-NLS-1$
									}

									// Log each elements
									for (Model model:elements) {
										
//										 System.out.println("Model NAMESPACE"+((Model) model).getNamespace());
										 System.out.println("Model qualified name: "+model.getQualifiedName());
										 System.out.println("Model name: "+model.getName());
										 System.out.println("Model namespace: "+model.getNamespace());
										 System.out.println("Model label: "+model.getLabel());


										 System.out.println("------------...");
										if(model.getName().equalsIgnoreCase("DanielaModel")){
											createSequenceDiagram((Model) model); //$NON-NLS-1$
											 System.out.println("Creating a new SD...");


										}
									}
								}
							});

				} catch (Exception e) {
					System.out.println("The operation was interrupted"); //$NON-NLS-1$
					System.out.println("StackTrace: "+ e);
				}	
				
//				 System.out.println("OpenModelRoots:"+UMLModeler.getOpenModelRoots());
//				 for(Model model:models){
//		
//					 createSequenceDiagram(model);
//
//				 }
//				 UMLSelectExistingElementDialog dialog = new
//				 UMLSelectExistingElementDialog(getShell(),Collections.singletonList(UMLElementTypes.OPERATION));
//				 dialog.create();
//				if(dialog.open()==Window.OK){
//					List<Operation> selected = (List<Operation>) dialog.getSelectedElements();
//					System.out.println(selected.get(0).getName()+" : "+selected.get(0).getQualifiedName());
////					
//					System.out.println(selected.get(0).getOwner().eClass().getName());
//					selectedOperation.setText(selected.get(0).getName());
//					dialog.close();
//				}

			}

		};
		selectedModel.addListener(SWT.MouseDoubleClick,modelListener);
		selectedOperation.addListener(SWT.MouseDoubleClick,operationListener);
//		selectedModel.addListener(SWT.KeyDown,modelListener);



//		button.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent event) {
//			
//			}
//
//		});
		
		
	}
	
private void createSequenceDiagram(Model m) {
		int random = (int )(Math.random() * 50 + 1);
		Collaboration coll = (Collaboration) m.createPackagedElement("PropertySpecCollaboration_" + random, UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior("PropertySpecInteraction_" +random, UMLPackage.eINSTANCE.getInteraction());
		
		// not part of the sequence diagram, but we'll be using these
		Class c1 = (Class) m.createPackagedElement("C1_"+random, UMLPackage.eINSTANCE.getClass_());
		Class c2 = (Class) m.createPackagedElement("C2_"+random, UMLPackage.eINSTANCE.getClass_());
		
		// create a class to use as the operation parameter type
		Class c3 = (Class) m.createPackagedElement("C3_"+random, UMLPackage.eINSTANCE.getClass_());
		
		// create an operation on c2
		EList ownedParameterNames = new BasicEList();
		EList ownedParameterTypes = new BasicEList();
		ownedParameterNames.add("Param1_"+random);
		ownedParameterTypes.add(c3);
		Operation op1 = c2.createOwnedOperation("open_"+random, ownedParameterNames, ownedParameterTypes);

		// create the properties of the collaboration (we'll need these for the lifelines)
		Property p1 = coll.createOwnedAttribute("c1_"+random, c1);
		Property p2 = coll.createOwnedAttribute("c2_"+random, c2);
		
		//
		// start populating the interaction
		//
		
		// create the lifelines
		Lifeline lifeline1 =  inter.createLifeline("k1_"+random);
		Lifeline lifeline2 = inter.createLifeline("k2_"+random);
		lifeline1.setRepresents(p1);
		lifeline2.setRepresents(p2);
		
		// create a message from k1 to k2
		Message m1 = inter.createMessage("open_"+random);
		
		// every message needs a connector
		Connector cn1 = inter.createOwnedConnector("connector1_"+random);
		cn1.setKind(ConnectorKind.ASSEMBLY_LITERAL);
		
		// create the connector ends, assign the roles
		ConnectorEnd ce1 = cn1.createEnd();
		ce1.setRole(p1);		
		ConnectorEnd ce2 = cn1.createEnd();
		ce2.setRole(p2);
		
		ExecutionEvent ev1 = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+random, UMLPackage.eINSTANCE.getExecutionEvent());
		ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
		roe.setOperation(op1);
		SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
		soe.setOperation(op1);

		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1);
		se.setName("se");
		se.getCovereds().add(lifeline1);
		
		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1);
		re.setName("re");
		re.getCovereds().add(lifeline2);
		
		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification)inter.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
		eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2);
		
		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
		bes.setStart(re);
		bes.setFinish(eos);
		bes.setName("bes");
		bes.getCovereds().add(lifeline2);
		
		eos.setExecution(bes);

		// whew...
		
		// set the message properties
		m1.setSendEvent(se);
		m1.setReceiveEvent(re);
		m1.setMessageSort(MessageSort.ASYNCH_CALL_LITERAL);
		m1.setConnector(cn1);
		
		
		
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.SEQUENCE_LITERAL,inter);
		d.setName("My Sequence diagram_"+random);		
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);		

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.COMMUNICATION_LITERAL,inter);
		cd.setName("My Communication diagram_"+random);		
//		UMLModeler.getUMLDiagramHelper().openDiagramEditor(cd);
	}

}