package info.remenska.myfirstplugin.wizards;

import java.awt.Button;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.themes.WorkbenchPreview;
import org.eclipse.uml2.uml.BehaviorExecutionSpecification;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Collaboration;
import org.eclipse.uml2.uml.CombinedFragment;
import org.eclipse.uml2.uml.Connector;
import org.eclipse.uml2.uml.ConnectorEnd;
import org.eclipse.uml2.uml.ConnectorKind;
import org.eclipse.uml2.uml.ExecutionEvent;
import org.eclipse.uml2.uml.ExecutionOccurrenceSpecification;
import org.eclipse.uml2.uml.Interaction;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionOperatorKind;
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
import org.eclipse.uml2.uml.internal.impl.CollaborationImpl;

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
			final DisciplinedEnglishPage disciplinedEnglishPage = (DisciplinedEnglishPage) this.getPage("Disciplined English Summary: ");
			
			String undoLabel = "Create Diagrams";
			TransactionalEditingDomain editDomain = UMLModeler
					.getEditingDomain();
			editDomain.getCommandStack().execute(
					new RecordingCommand(editDomain, undoLabel) {
						protected void doExecute() {
							// code to modify the model goes here
							// Get selection
							final Collection<Model> models = UMLModeler.getOpenedModels();

							if (models.size() == 0) {
										System.out.println("Please open a UML model and select it. "); //$NON-NLS-1$
							} else{
		
							// Log each elements
							for (Model model:models) {
								
//								 System.out.println("Model NAMESPACE"+((Model) model).getNamespace());
								 System.out.println("Model qualified name: "+model.getQualifiedName());
								 System.out.println("Model name: "+model.getName());
								 System.out.println("Model namespace: "+model.getNamespace());
								 System.out.println("Model label: "+model.getLabel());


								 System.out.println("------------...");
								 //TODO: remove second condition?
								 if(disciplinedEnglishPage.textDirectoryFormula!=null && !model.getName().equalsIgnoreCase("UMLPrimitiveTypes")){
									 // property is monitorable, create an mCRL2 process
									 String path = disciplinedEnglishPage.textDirectoryFormula.getText();

										PrintWriter writer;
										try {
											String fullPath= path+"/monitor_" + (int )(Math.random() * 500 + 1) + ".mcrl2";
											writer = new PrintWriter(fullPath, "UTF-8");
											File f = new File(fullPath);

//											writer.println("The first line");
											writer.println("% " + disciplinedEnglishPage.textFormula.getText());
											writer.close();

											
										} catch (FileNotFoundException
												| UnsupportedEncodingException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										} 
										
								 }
								 
								 // next create an SD
								if(model.getName().equalsIgnoreCase("DanielaModel")){
//									createSequenceDiagram((Model) model); //$NON-NLS-1$
									 System.out.println("Creating a new SD...");
									 draw_afterQAbsence(model);
//									 draw_globallyAbsence(model);
									
								

								}
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

	public static Collection<org.eclipse.uml2.uml.Class> getClasses(
			org.eclipse.uml2.uml.Package rootPackage) {
		return EcoreUtil.getObjectsByType(rootPackage.eContents(),
				UMLPackage.Literals.CLASS);
			
	}

//	UMLPackage.Literals.CONNECTOR_END__ROLE;

	public static LinkedList<CollaborationImpl> getAllCollaborations(
			org.eclipse.uml2.uml.Package rootPackage) {
		TreeIterator<EObject> treeColab = rootPackage.eAllContents();
		LinkedList<CollaborationImpl> allColaborations = new LinkedList<CollaborationImpl>();
		while (treeColab.hasNext()) {
			EObject el = treeColab.next();
			if (el.getClass().equals(CollaborationImpl.class)) {
				allColaborations.add((CollaborationImpl) el);
//				getAllInteractionsForCollaboration((CollaborationImpl) el);
			}
		}
		if (allColaborations.size() == 0) {
			System.err
					.println("Strange:there are no collaborations (SDs) in the UML model.\nPlease check for possible problems.");
		}
		return allColaborations;
	}
	
	public void draw_afterQAbsence(Model m){
		
		//TODO: no need to create events!!!
		int random = (int )(Math.random() * 50 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement("Collab_AfterQ_Absence_Pattern_" + random, UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior("Inter_AfterQ_Absence_Pattern_" +random, UMLPackage.eINSTANCE.getInteraction());
	
		// stereotype needs to be applied
		Message originAfterQ = QuestionTreePage.traceLineMap.get(QuestionTreePage.textStartEvent).getOriginMessage();
		Class fromQ = (Class) ((MessageOccurrenceSpecification)originAfterQ.getSendEvent()).getCovereds().get(0).getRepresents().getType();
		Class toQ = (Class) ((MessageOccurrenceSpecification)originAfterQ.getReceiveEvent()).getCovereds().get(0).getRepresents().getType();
		
		Property p1Q = coll.createOwnedAttribute(((MessageOccurrenceSpecification)originAfterQ.getSendEvent()).getCovereds().get(0).getRepresents().getName(), fromQ);
		Property p2Q = coll.createOwnedAttribute(((MessageOccurrenceSpecification)originAfterQ.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), toQ);
		
		Lifeline lifeline1Q =  inter.createLifeline(p1Q.getName());
		Lifeline lifeline2Q = inter.createLifeline(p2Q.getName());
		
		lifeline1Q.setRepresents(p1Q);
		lifeline2Q.setRepresents(p2Q);
		Message eventQ = inter.createMessage(originAfterQ.getName());
		eventQ.setMessageSort(originAfterQ.getMessageSort());
		
		Connector cn1Q = inter.createOwnedConnector("connector1_"+(int )(Math.random() * 50 + 1));
		cn1Q.setKind(ConnectorKind.ASSEMBLY_LITERAL);
		
		// create the connector ends, assign the roles
		ConnectorEnd ce1Q = cn1Q.createEnd();
		ConnectorEnd ce2Q = cn1Q.createEnd();
		if (eventQ.getMessageSort().getLiteral().equals("reply"))
		{
			ce1Q.setRole(p2Q);		
			ce2Q.setRole(p1Q);
		} else
		{
			ce1Q.setRole(p1Q);		
			ce2Q.setRole(p2Q);
		}
	
		Operation op1Q = ((SendOperationEvent)((MessageOccurrenceSpecification) originAfterQ.getSendEvent()).getEvent()).getOperation();
		
		// NO NEED?
		ExecutionEvent ev1Q = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+(int )(Math.random() * 500 + 1), UMLPackage.eINSTANCE.getExecutionEvent()); //TODO: needs to be unique
		
		
		// can I deduce it?
		ReceiveOperationEvent roeQ  = (ReceiveOperationEvent) ((MessageOccurrenceSpecification)originAfterQ.getReceiveEvent()).getEvent();
		
//		ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
		roeQ.setOperation(op1Q); //need to find the operation correspondign to this class!
		
		// can I deduce it again?
		
		SendOperationEvent soeQ = (SendOperationEvent) ((MessageOccurrenceSpecification)originAfterQ.getSendEvent()).getEvent();
//		SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
		soeQ.setOperation(op1Q);

		
		MessageOccurrenceSpecification seQ = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		seQ.setEvent(soeQ);
		seQ.setMessage(eventQ);
//		se.setName("se");// do we really need a name?
		seQ.getCovereds().add(lifeline1Q);
		
		MessageOccurrenceSpecification reQ = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		reQ.setEvent(roeQ);
		reQ.setMessage(eventQ);
//		re.setName("re");
		reQ.getCovereds().add(lifeline2Q);
		
		ExecutionOccurrenceSpecification eosQ = (ExecutionOccurrenceSpecification)inter.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//		eos.setName("eos");
		eosQ.setEvent(ev1Q);
		eosQ.getCovereds().add(lifeline2Q);
		
		BehaviorExecutionSpecification besQ = (BehaviorExecutionSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
		besQ.setStart(reQ);
		besQ.setFinish(eosQ);
//		bes.setName("bes");
		besQ.getCovereds().add(lifeline2Q);
		
		eosQ.setExecution(besQ);

		// whew...
	
	// set the message properties
		eventQ.setSendEvent(seQ);
		eventQ.setReceiveEvent(reQ);
		eventQ.setConnector(cn1Q);
		
		
		// /// // /// /// / 
		Message originMessageA = QuestionTreePage.traceLineMap.get(QuestionTreePage.textEventA).getOriginMessage();
		Class from = (Class) ((MessageOccurrenceSpecification)originMessageA.getSendEvent()).getCovereds().get(0).getRepresents().getType();
		Class to = (Class) ((MessageOccurrenceSpecification)originMessageA.getReceiveEvent()).getCovereds().get(0).getRepresents().getType();
		
		// this is how
		//if(coll.getOwnedAttribute(name, type)!=null)
		Property p1, p2;
		if(coll.getOwnedAttribute(((MessageOccurrenceSpecification)originMessageA.getSendEvent()).getCovereds().get(0).getRepresents().getName(), from) != null)
			 p1 = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originMessageA.getSendEvent()).getCovereds().get(0).getRepresents().getName(), from);
		else		
			 p1 = coll.createOwnedAttribute(((MessageOccurrenceSpecification)originMessageA.getSendEvent()).getCovereds().get(0).getRepresents().getName(), from);
		if (coll.getOwnedAttribute(((MessageOccurrenceSpecification)originMessageA.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), to) != null)
			p2 = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originMessageA.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), to);
		else
			p2 = coll.createOwnedAttribute(((MessageOccurrenceSpecification)originMessageA.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), to);
		
		

		// NO, DON'T CREATE NEW LIFELINE IF THE PROPERTY IS THERE
		Lifeline lifeline1 =  inter.getLifeline(p1.getName(), false, true);
		Lifeline lifeline2 = inter.getLifeline(p2.getName(), false, true);
		lifeline1.setRepresents(p1);
		lifeline2.setRepresents(p2);
		Message m1 = inter.createMessage(originMessageA.getName());
		m1.setMessageSort(originMessageA.getMessageSort());
		// here I need a combined fragment NEG
		Connector cn1 = inter.createOwnedConnector("connector1_"+(int )(Math.random() * 50 + 1));
		cn1.setKind(ConnectorKind.ASSEMBLY_LITERAL);
		
		// create the connector ends, assign the roles
		ConnectorEnd ce1 = cn1.createEnd();
		ConnectorEnd ce2 = cn1.createEnd();
		if (m1.getMessageSort().getLiteral().equals("reply"))
		{
			ce1.setRole(p2);		
			ce2.setRole(p1);
		} else
		{
			ce1.setRole(p1);		
			ce2.setRole(p2);
		}
	
		
		
		Operation op1 = ((SendOperationEvent)((MessageOccurrenceSpecification) originMessageA.getSendEvent()).getEvent()).getOperation();
		
		// NO NEED?
		ExecutionEvent ev1 = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+(int )(Math.random() * 500 + 1), UMLPackage.eINSTANCE.getExecutionEvent());
		
		// can I deduce it?
		ReceiveOperationEvent roe  = (ReceiveOperationEvent) ((MessageOccurrenceSpecification)originMessageA.getReceiveEvent()).getEvent();
		
//		ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
		roe.setOperation(op1); //need to find the operation correspondign to this class!
		
		// can I deduce it again?
		
		SendOperationEvent soe = (SendOperationEvent) ((MessageOccurrenceSpecification)originMessageA.getSendEvent()).getEvent();
//		SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
		soe.setOperation(op1);

		CombinedFragment neg = (CombinedFragment) inter.createFragment(null, UMLPackage.eINSTANCE.getCombinedFragment());
		
		neg.setInteractionOperator(InteractionOperatorKind.NEG_LITERAL);
		InteractionOperand iop = neg.createOperand("NEG_operand");
		neg.getCovereds().add(lifeline1);
		neg.getCovereds().add(lifeline2);
		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) iop.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1);
//		se.setName("se");// do we really need a name?
		se.getCovereds().add(lifeline1);
		
		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) iop.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1);
//		re.setName("re");
		re.getCovereds().add(lifeline2);
		
		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification)iop.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//		eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2);
		
		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) iop.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
		bes.setStart(re);
		bes.setFinish(eos);
//		bes.setName("bes");
		bes.getCovereds().add(lifeline2);
		
		eos.setExecution(bes);

		// whew...
	
	// set the message properties
		m1.setSendEvent(se);
		m1.setReceiveEvent(re);
		m1.setMessageSort(originMessageA.getMessageSort());
		m1.setConnector(cn1);
		// END_ this is all for one message: START_EVENT
	
		
		
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.SEQUENCE_LITERAL,inter);
		d.setName("Diag_SD_AfterQ_Absence_Pattern_"+random);		
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);		
	
		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.COMMUNICATION_LITERAL,inter);
		cd.setName("Diag_COM_AfterQ_Absence_Pattern_"+random);		
	}
	
	public void draw_globallyAbsence(Model m){
		//TODO: no need to create events!!!
		int random = (int )(Math.random() * 50 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement("Collab_Globally_Absence_Pattern_" + random, UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior("Inter_Globally_Absence_Pattern_" +random, UMLPackage.eINSTANCE.getInteraction());
	
		Message originMessageA = QuestionTreePage.traceLineMap.get(QuestionTreePage.textEventA).getOriginMessage();
		Class from = (Class) originMessageA.getConnector().getEnds().get(0).getRole().getType();
		Class to = (Class) originMessageA.getConnector().getEnds().get(1).getRole().getType();
		Property p1 = coll.createOwnedAttribute(originMessageA.getConnector().getEnds().get(0).getRole().getName(), from);
		Property p2 = coll.createOwnedAttribute(originMessageA.getConnector().getEnds().get(1).getRole().getName(), to);
		
		Lifeline lifeline1 =  inter.createLifeline(p1.getName());
		Lifeline lifeline2 = inter.createLifeline(p2.getName());
		lifeline1.setRepresents(p1);
		lifeline2.setRepresents(p2);
		Message m1 = inter.createMessage(originMessageA.getName());
		m1.setMessageSort(originMessageA.getMessageSort());
		// here I need a combined fragment NEG
		Connector cn1 = inter.createOwnedConnector("connector1_"+random);
		cn1.setKind(ConnectorKind.ASSEMBLY_LITERAL);
		
		// create the connector ends, assign the roles
		ConnectorEnd ce1 = cn1.createEnd();
		ConnectorEnd ce2 = cn1.createEnd();
		if (m1.getMessageSort().getLiteral().equals("reply"))
		{
			ce1.setRole(p2);		
			ce2.setRole(p1);
		} else
		{
			ce1.setRole(p1);		
			ce2.setRole(p2);
		}
	
		
		
		Operation op1 = ((SendOperationEvent)((MessageOccurrenceSpecification) originMessageA.getSendEvent()).getEvent()).getOperation();
		
		// NO NEED?
		ExecutionEvent ev1 = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+random, UMLPackage.eINSTANCE.getExecutionEvent());
		
		// can I deduce it?
		ReceiveOperationEvent roe  = (ReceiveOperationEvent) ((MessageOccurrenceSpecification)originMessageA.getReceiveEvent()).getEvent();
		
//		ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
		roe.setOperation(op1); //need to find the operation correspondign to this class!
		
		// can I deduce it again?
		
		SendOperationEvent soe = (SendOperationEvent) ((MessageOccurrenceSpecification)originMessageA.getSendEvent()).getEvent();
//		SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
		soe.setOperation(op1);

		CombinedFragment neg = (CombinedFragment) inter.createFragment(null, UMLPackage.eINSTANCE.getCombinedFragment());
		
		neg.setInteractionOperator(InteractionOperatorKind.NEG_LITERAL);
		InteractionOperand iop = neg.createOperand("NEG_operand");
		neg.getCovereds().add(lifeline1);
		neg.getCovereds().add(lifeline2);
		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) iop.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1);
//		se.setName("se");// do we really need a name?
		se.getCovereds().add(lifeline1);
		
		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) iop.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1);
//		re.setName("re");
		re.getCovereds().add(lifeline2);
		
		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification)iop.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//		eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2);
		
		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) iop.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
		bes.setStart(re);
		bes.setFinish(eos);
//		bes.setName("bes");
		bes.getCovereds().add(lifeline2);
		
		eos.setExecution(bes);

		// whew...
	
	// set the message properties
		m1.setSendEvent(se);
		m1.setReceiveEvent(re);
		m1.setMessageSort(originMessageA.getMessageSort());
		m1.setConnector(cn1);
		// END_ this is all for one message: START_EVENT
	
		
		
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.SEQUENCE_LITERAL,inter);
		d.setName("Diag_SD_Globally_Absence_Pattern_"+random);		
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);		
	
		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.COMMUNICATION_LITERAL,inter);
		cd.setName("Diag_COM_Globally_Absence_Pattern_"+random);		
	}
	
	public void testMe(Model m){
		// not part of the sequence diagram, but we'll be using these
		Collection<org.eclipse.uml2.uml.Class> classes = getClasses(m);
		Iterator<org.eclipse.uml2.uml.Class> classes_iterator = classes.iterator();
		// Loop UML classes
		while (classes_iterator.hasNext()) {
			org.eclipse.uml2.uml.Class classFromCollection = (org.eclipse.uml2.uml.Class) classes_iterator
					.next();
			System.out.println("Found class: " + classFromCollection.getName());
		}
		
		// this is all for one message: START_EVENT
		int random = (int )(Math.random() * 50 + 1);
		Collaboration coll = (Collaboration) m.createPackagedElement("PropertySpecCollaboration_" + random, UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior("PropertySpecInteraction_" +random, UMLPackage.eINSTANCE.getInteraction());
		Message originMessage = QuestionTreePage.traceLineMap.get(QuestionTreePage.textStartEvent).getOriginMessage();
		Class from = (Class) originMessage.getConnector().getEnds().get(0).getRole().getType();
		System.out.println("Class FROM: "+ from );
		//		Class from = (Class) QuestionTreePage.trStartEvent.getOriginMessage().getConnector().getEnds().get(0).getRole().getType();
		Class to = (Class) originMessage.getConnector().getEnds().get(1).getRole().getType();
		System.out.println(" Class TO: "+ to);

		Property p1 = coll.createOwnedAttribute(originMessage.getConnector().getEnds().get(0).getRole().getName(), from);
		Property p2 = coll.createOwnedAttribute(originMessage.getConnector().getEnds().get(1).getRole().getName(), to);
		
		Lifeline lifeline1 =  inter.createLifeline(p1.getName());
		Lifeline lifeline2 = inter.createLifeline(p2.getName());
		lifeline1.setRepresents(p1);
		lifeline2.setRepresents(p2);
		Message m1 = inter.createMessage(originMessage.getName());
		
		Connector cn1 = inter.createOwnedConnector("connector1_"+random);
		cn1.setKind(ConnectorKind.ASSEMBLY_LITERAL);
		
		// create the connector ends, assign the roles
		ConnectorEnd ce1 = cn1.createEnd();
		ce1.setRole(p1);		
		ConnectorEnd ce2 = cn1.createEnd();
		ce2.setRole(p2);
		
		Operation op1 = ((SendOperationEvent)((MessageOccurrenceSpecification) originMessage.getSendEvent()).getEvent()).getOperation();
		ExecutionEvent ev1 = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+random, UMLPackage.eINSTANCE.getExecutionEvent());
		ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
		roe.setOperation(op1); //need to find the operation correspondign to this class!
		SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
		soe.setOperation(op1);

		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1);
//		se.setName("se");// do we really need a name?
		se.getCovereds().add(lifeline1);
		
		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1);
//		re.setName("re");
		re.getCovereds().add(lifeline2);
		
		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification)inter.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//		eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2);
		
		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
		bes.setStart(re);
		bes.setFinish(eos);
//		bes.setName("bes");
		bes.getCovereds().add(lifeline2);
		
		eos.setExecution(bes);

		// whew...
	
	// set the message properties
		m1.setSendEvent(se);
		m1.setReceiveEvent(re);
		m1.setMessageSort(originMessage.getMessageSort());
		m1.setConnector(cn1);
		// END_ this is all for one message: START_EVENT
	
		
		
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.SEQUENCE_LITERAL,inter);
		d.setName("My Sequence diagram_"+random);		
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);		
	
		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter, UMLDiagramKind.COMMUNICATION_LITERAL,inter);
		cd.setName("My Communication diagram_"+random);		
	}
	
	private void createSequenceDiagram(Model m) {
		
	
		int random = (int )(Math.random() * 50 + 1);
		Collaboration coll = (Collaboration) m.createPackagedElement("PropertySpecCollaboration_" + random, UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior("PropertySpecInteraction_" +random, UMLPackage.eINSTANCE.getInteraction());
		
	
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