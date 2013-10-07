package info.remenska.myfirstplugin.wizards;

import java.awt.Button;
import java.util.Collection;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.themes.WorkbenchPreview;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.ConnectorKind;
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

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.umlnotation.UMLDiagramKind;

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
//		disciplinedEnglishPage = new DisciplinedEnglishPage("Disciplined English Summary: ", "Please review the collected information regarding the property.");
//		addPage(disciplinedEnglishPage);
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
		// here is where we generate the SD
		
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
								
//								 System.out.println("Model NAMESPACE"+((Model) model).getNamespace());
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
		
		this.getShell().setFocus();
//		this.dispose();
		return true;
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