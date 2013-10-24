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
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
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

public class ResponseChain2 extends PropertyPattern {
	Model m;

	public ResponseChain2(Model m) {
		this.m = m;
	}

	@Override
	public void draw(int scope) {
		switch (scope) {
		case PropertyPattern.BEFORE:
			draw_beforeRResponseChain2(m);
			break;
		case PropertyPattern.AFTER:
			draw_afterQResponseChain2(m);
			break;
		case PropertyPattern.AFTER_LAST:
			draw_afterLastQResponseChain2(m);
			break;
		case PropertyPattern.BETWEEN:
			draw_betweenQandRResponseChain2(m);
			break;

		case PropertyPattern.UNTIL:
			draw_UntilRResponseChain2(m);
			break;
			
		case PropertyPattern.AFTER_UNTIL:
			draw_afterQUntilRResponseChain2(m);
			break;

		case PropertyPattern.GLOBALLY:
			draw_globallyResponseChain2(m);
			break;
		}
	}

	private void draw_afterLastQResponseChain2(Model m2) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterLastQ_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterLastQ_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterLastQ(m, coll, inter);

		response_chain2(m, coll, inter);
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterLastQ_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterLastQ_ResponseChain2_Pattern_" + random);		
	}

	public void draw_UntilRResponseChain2(Model m2) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_UntilR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_UntilR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());

		response_chain2(m, coll, inter);
		untilR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_UntilR_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_UntilR_ResponseChain2_Pattern_" + random);
		
	}

	public void draw_beforeRResponseChain2(Model m) {

		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_BeforeR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_BeforeR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());

		response_chain2(m, coll, inter);
		beforeR(m, coll, inter);
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_BeforeR_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_BeforeR_ResponseChain2_Pattern_" + random);
	}

	public void draw_afterQUntilRResponseChain2(Model m) {

		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterQUntilR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterQUntilR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterQ(m, coll, inter);

		response_chain2(m, coll, inter);
		untilR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterQUntilR_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterQUntilR_ResponseChain2_Pattern_" + random);

	}

	public void draw_afterQResponseChain2(Model m) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_AfterQ_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_AfterQ_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterQ(m, coll, inter);

		response_chain2(m, coll, inter);
		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_AfterQ_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_AfterQ_ResponseChain2_Pattern_" + random);
	}

	public void draw_globallyResponseChain2(Model m) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_Globally_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_Globally_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());

		response_chain2(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_Globally_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_Globally_ResponseChain2_Pattern_" + random);
	}

	public void draw_betweenQandRResponseChain2(Model m) {
		int random = (int) (Math.random() * 5000 + 1);

		Collaboration coll = (Collaboration) m.createPackagedElement(
				"Collab_BetweenQandR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getCollaboration());
		Interaction inter = (Interaction) coll.createOwnedBehavior(
				"Inter_BetweenQandR_ResponseChain2_Pattern_" + random,
				UMLPackage.eINSTANCE.getInteraction());
		afterQ(m, coll, inter);
		response_chain2(m, coll, inter);
		beforeR(m, coll, inter);

		// and finally... create the diagrams
		// note slightly different syntax here
		Diagram d = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.SEQUENCE_LITERAL, inter);
		d.setName("Diag_SD_BetweenQandR_ResponseChain2_Pattern_" + random);
		UMLModeler.getUMLDiagramHelper().openDiagramEditor(d);

		Diagram cd = UMLModeler.getUMLDiagramHelper().createDiagram(inter,
				UMLDiagramKind.COMMUNICATION_LITERAL, inter);
		cd.setName("Diag_COM_BetweenQandR_ResponseChain2_Pattern_" + random);
	}

	public void response_chain2(Model m, Collaboration coll, Interaction inter) {

		regMessage(m, coll, inter, QuestionTreePage.textEventA);
		existence(m, coll, inter, QuestionTreePage.textEventB);

		// // /// //// // // //// / ///
		existence(m, coll, inter, QuestionTreePage.textEventC);

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

	public void bounded_existence(Model m, Collaboration coll,
			Interaction inter, Text textEvent) {
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

		CombinedFragment assertFragment = (CombinedFragment) inter
				.createFragment(null,
						UMLPackage.eINSTANCE.getCombinedFragment());

		assertFragment
				.setInteractionOperator(InteractionOperatorKind.ASSERT_LITERAL);
		InteractionOperand iop = assertFragment.createOperand("ASSERT_operand");
		assertFragment.getCovereds().add(lifeline1);
		assertFragment.getCovereds().add(lifeline2);

		CombinedFragment forFragment = (CombinedFragment) iop.createFragment(
				null, UMLPackage.eINSTANCE.getCombinedFragment());
		forFragment
				.setInteractionOperator(InteractionOperatorKind.LOOP_LITERAL);
		InteractionOperand iopLoop = forFragment.createOperand("LOOP_operand");
		InteractionConstraint loopGuard = iopLoop.createGuard("guard");
		// loopGuard.createMinint(name, type, eClass);

		//
		LiteralInteger minValue = (LiteralInteger) loopGuard
				.createMinint("minConstraint", null,
						UMLPackage.eINSTANCE.getLiteralInteger());
		minValue.setValue(0);

		LiteralUnlimitedNatural maxValue = (LiteralUnlimitedNatural) loopGuard
				.createMaxint("maxConstraint", null,
						UMLPackage.eINSTANCE.getLiteralUnlimitedNatural());
		maxValue.setValue(2);

		System.out.println("REACHED HERE...");

		// ((LiteralInteger) loopGuard.getMinint()).setValue(0);
		// ((LiteralUnlimitedNatural)loopGuard.getMaxint()).setValue(2);
		System.out.println("REACHED HERE...????");

		forFragment.getCovereds().add(lifeline1);
		forFragment.getCovereds().add(lifeline2);

		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) iopLoop
				.createFragment(null, UMLPackage.eINSTANCE
						.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1);
		// se.setName("se");// do we really need a name?
		se.getCovereds().add(lifeline1);

		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) iopLoop
				.createFragment(null, UMLPackage.eINSTANCE
						.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1);
		// re.setName("re");
		re.getCovereds().add(lifeline2);

		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification) iopLoop
				.createFragment(null, UMLPackage.eINSTANCE
						.getExecutionOccurrenceSpecification());
		// eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2);

		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) iopLoop
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

	public void regMessage(Model m, Collaboration coll, Interaction inter,
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

		Lifeline lifeline1B = inter.getLifeline(p1.getName(), false, true);
		Lifeline lifeline2B = inter.getLifeline(p2.getName(), false, true);

		lifeline1B.setRepresents(p1);
		lifeline2B.setRepresents(p2);
		Message m1B = inter.createMessage(originMessage.getName());
		m1B.setMessageSort(originMessage.getMessageSort());

		// here I need a combined fragment NEG
		Connector cn1B = inter.createOwnedConnector("connector1_" + random);
		cn1B.setKind(ConnectorKind.ASSEMBLY_LITERAL);

		// create the connector ends, assign the roles
		ConnectorEnd ce1B = cn1B.createEnd();
		ConnectorEnd ce2B = cn1B.createEnd();
		if (m1B.getMessageSort().getLiteral().equals("reply")) {
			ce1B.setRole(p2);
			ce2B.setRole(p1);
		} else {
			ce1B.setRole(p1);
			ce2B.setRole(p2);
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

		// CombinedFragment neg = (CombinedFragment) inter.createFragment(null,
		// UMLPackage.eINSTANCE.getCombinedFragment());

		// neg.setInteractionOperator(InteractionOperatorKind.ASSERT_LITERAL);
		// InteractionOperand iop = neg.createOperand("ASSERT_operand");
		MessageOccurrenceSpecification se = (MessageOccurrenceSpecification) inter
				.createFragment(null, UMLPackage.eINSTANCE
						.getMessageOccurrenceSpecification());
		se.setEvent(soe);
		se.setMessage(m1B);
		// se.setName("se");// do we really need a name?
		se.getCovereds().add(lifeline1B);

		MessageOccurrenceSpecification re = (MessageOccurrenceSpecification) inter
				.createFragment(null, UMLPackage.eINSTANCE
						.getMessageOccurrenceSpecification());
		re.setEvent(roe);
		re.setMessage(m1B);
		// re.setName("re");
		re.getCovereds().add(lifeline2B);

		ExecutionOccurrenceSpecification eos = (ExecutionOccurrenceSpecification) inter
				.createFragment(null, UMLPackage.eINSTANCE
						.getExecutionOccurrenceSpecification());
		// eos.setName("eos");
		eos.setEvent(ev1);
		eos.getCovereds().add(lifeline2B);

		BehaviorExecutionSpecification bes = (BehaviorExecutionSpecification) inter
				.createFragment(null, UMLPackage.eINSTANCE
						.getBehaviorExecutionSpecification());
		bes.setStart(re);
		bes.setFinish(eos);
		// bes.setName("bes");
		bes.getCovereds().add(lifeline2B);

		eos.setExecution(bes);

		// whew...

		// set the message properties
		m1B.setSendEvent(se);
		m1B.setReceiveEvent(re);
		m1B.setMessageSort(originMessage.getMessageSort());
		m1B.setConnector(cn1B);
		// END_ this is all for one message
	}

}
