package info.remenska.myfirstplugin.wizards;

import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.swt.widgets.Text;
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
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.UMLPackage;

import com.ibm.xtools.modeler.ui.UMLModeler;
import com.ibm.xtools.umlnotation.UMLDiagramKind;

public class Existence extends PropertyPattern {
	Model m;

	public Existence(Model m) {
		this.m = m;
	}

	@Override
	public void draw(int scope) {
		switch (scope) {
		case PropertyPattern.BEFORE:
			draw_beforeRExistence(m);
			break;
		case PropertyPattern.AFTER:
			draw_afterQExistence(m);
			break;
			
		case PropertyPattern.AFTER_LAST:
			draw_afterLastQExistence(m);
			break;

		case PropertyPattern.BETWEEN:
			draw_betweenQandRExistence(m);
			break;
			
		case PropertyPattern.UNTIL:
			draw_UntilRExistence(m);
			break;
		case PropertyPattern.AFTER_UNTIL:
			draw_afterQUntilRExistence(m);
			break;

		case PropertyPattern.GLOBALLY:
			draw_globallyExistence(m);
			break;
			

		case PropertyPattern.AFTER_LAST_UNTIL:
			draw_afterLastQUntilRExistence(m);
			break;			

		case PropertyPattern.BETWEEN_LAST:
			draw_betweenLastQandRExistence(m);
			break;
		}
	}

	public void draw_betweenLastQandRExistence(Model m2) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_BetweenLastQandR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_BetweenLastQandR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterLastQ(m, coll, inter);
		existence(m, coll, inter, QuestionTreePage.textEventA);
		beforeR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_BetweenLastQandR_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_BetweenLastQandR_Existence_Pattern_" + random);
		
	}

	public void draw_afterLastQUntilRExistence(Model m2) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterLastQUntilR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterLastQUntilR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterLastQ(m, coll, inter);

		existence(m, coll, inter, QuestionTreePage.textEventA);
		untilR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterLastQUntilR_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterLastQUntilR_Existence_Pattern_" + random);
	}

	public void draw_afterLastQExistence(Model m2) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterLastQ_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterLastQ_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterLastQ(m, coll, inter);

		existence(m, coll, inter, QuestionTreePage.textEventA);
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterLastQ_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterLastQ_Existence_Pattern_" + random);		
	}

	public void draw_UntilRExistence(Model m2) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_UntilR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_UntilR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());

		existence(m, coll, inter, QuestionTreePage.textEventA);
		untilR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_UntilR_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_UntilR_Existence_Pattern_" + random);
		
	}

	public void draw_beforeRExistence(Model m) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_BeforeR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_BeforeR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());

		existence(m, coll, inter, QuestionTreePage.textEventA);
		beforeR(m, coll, inter);
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_BeforeR_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_BeforeR_Existence_Pattern_" + random);

	}

	public void draw_afterQUntilRExistence(Model m) {

		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterQUntilR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterQUntilR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterQ(m, coll, inter);

		existence(m, coll, inter, QuestionTreePage.textEventA);
		untilR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterQUntilR_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterQUntilR_Existence_Pattern_" + random);

	}

	public void draw_afterQExistence(Model m) {

		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterQ_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterQ_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterQ(m, coll, inter);

		existence(m, coll, inter, QuestionTreePage.textEventA);
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterQ_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterQ_Existence_Pattern_" + random);
	}

	public void draw_globallyExistence(Model m) {

		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_Globally_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_Globally_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());

		existence(m, coll, inter, QuestionTreePage.textEventA);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_Globally_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_Globally_Existence_Pattern_" + random);
	}

	public void draw_betweenQandRExistence(Model m) {

		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_BetweenQandR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_BetweenQandR_Existence_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterQ(m, coll, inter);
		existence(m, coll, inter, QuestionTreePage.textEventA);
		beforeR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_BetweenQandR_Existence_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_BetweenQandR_Existence_Pattern_" + random);

	}

	public void existence(Model m, Collaboration coll, Interaction inter,
			Text textEvent) {
		int random = (int) (Math.random() * 5000 + 1);

		Message originMessage = QuestionTreePage.traceLineMap.get(textEvent)
				.getOriginMessage();

		Class from = (Class) ((MessageOccurrenceSpecification) originMessage
				.getSendEvent()).getCovereds().get(0).getRepresents().getType();
		Class to = (Class) ((MessageOccurrenceSpecification) originMessage
				.getReceiveEvent()).getCovereds().get(0).getRepresents()
				.getType();

		Property p1 = coll.getOwnedAttribute(
				((MessageOccurrenceSpecification) originMessage.getSendEvent())
						.getCovereds().get(0).getRepresents().getName(), from,
				false, UMLPackage.Literals.PROPERTY, true);
		Property p2 = coll.getOwnedAttribute(
				((MessageOccurrenceSpecification) originMessage
						.getReceiveEvent()).getCovereds().get(0)
						.getRepresents().getName(), to, false,
				UMLPackage.Literals.PROPERTY, true);

		Lifeline lifeline1 = inter.getLifeline(p1.getName(), false, true);
		Lifeline lifeline2 = inter.getLifeline(p2.getName(), false, true);

		lifeline1.setRepresents(p1);
		lifeline2.setRepresents(p2);
		Message m1 = inter.createMessage(originMessage.getName());
		m1.setMessageSort(originMessage.getMessageSort());

		// here I need a combined fragment NEG
		Connector cn1 = inter.createOwnedConnector("connector1_" + random);
		cn1.setKind(ConnectorKind.ASSEMBLY_LITERAL);

		// create the connector ends, assign the roles
		ConnectorEnd ce1 = cn1.createEnd();
		ConnectorEnd ce2 = cn1.createEnd();
		if (m1.getMessageSort().getLiteral().equals("reply")) {
			ce1.setRole(p2);
			ce2.setRole(p1);
		} else {
			ce1.setRole(p1);
			ce2.setRole(p2);
		}

		Operation op1 = ((SendOperationEvent) ((MessageOccurrenceSpecification) originMessage
				.getSendEvent()).getEvent()).getOperation();

		// NO NEED?
		ExecutionEvent ev1 = (ExecutionEvent) m.createPackagedElement(
				"ExecutionEvent1_" + (int) (Math.random() * 5000 + 1),
				UMLPackage.eINSTANCE.getExecutionEvent());

		// can I deduce it?
		ReceiveOperationEvent roe = (ReceiveOperationEvent) ((MessageOccurrenceSpecification) originMessage
				.getReceiveEvent()).getEvent();

		// ReceiveOperationEvent roe = (ReceiveOperationEvent)
		// m.createPackagedElement("ReceiveOperationEvent1_"+random,
		// UMLPackage.eINSTANCE.getReceiveOperationEvent());
		roe.setOperation(op1); // need to find the operation correspondign to
								// this class!

		// can I deduce it again?

		SendOperationEvent soe = (SendOperationEvent) ((MessageOccurrenceSpecification) originMessage
				.getSendEvent()).getEvent();
		// SendOperationEvent soe = (SendOperationEvent)
		// m.createPackagedElement("SendOperationEvent1_"+random,
		// UMLPackage.eINSTANCE.getSendOperationEvent());
		soe.setOperation(op1);

		CombinedFragment asssertFragment = (CombinedFragment) inter
				.getFragment("assertFragment", false,
						UMLPackage.eINSTANCE.getCombinedFragment(), true);
		asssertFragment
				.setInteractionOperator(InteractionOperatorKind.ASSERT_LITERAL);
		InteractionOperand iop = asssertFragment.getOperand("ASSERT_operand",
				false, true);
		// InteractionOperand iop =
		// asssertFragment.createOperand("ASSERT_operand");
		asssertFragment.getCovereds().add(lifeline1);
		asssertFragment.getCovereds().add(lifeline2);
		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) iop
				.createFragment(null, UMLPackage.eINSTANCE
						.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1);
		// se.setName("se");// do we really need a name?
		se.getCovereds().add(lifeline1);

		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) iop
				.createFragment(null, UMLPackage.eINSTANCE
						.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1);
		// re.setName("re");
		re.getCovereds().add(lifeline2);

		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification) iop
				.createFragment(null, UMLPackage.eINSTANCE
						.getExecutionOccurrenceSpecification());
		// eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2);

		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) iop
				.createFragment(null, UMLPackage.eINSTANCE
						.getBehaviorExecutionSpecification());
		bes.setStart(re);
		bes.setFinish(eos);
		// bes.setName("bes");
		bes.getCovereds().add(lifeline2);

		eos.setExecution(bes);

		// whew...

		// set the message properties
		m1.setSendEvent(se);
		m1.setReceiveEvent(re);
		m1.setMessageSort(originMessage.getMessageSort());
		m1.setConnector(cn1);
		// END_ this is all for one message
	}
}
