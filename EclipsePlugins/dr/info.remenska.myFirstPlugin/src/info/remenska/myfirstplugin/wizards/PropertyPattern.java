package info.remenska.myfirstplugin.wizards;

import org.eclipse.emf.common.util.EList;
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
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;

public abstract class PropertyPattern {
	public static int BEFORE = 1;
	public static int AFTER = 2;
	public static int BETWEEN = 3;
	public static int UNTIL = 4;
	public static int GLOBALLY = 5;
	public void draw(int scope){
		
	}
	
	public void afterQ(Model m, Collaboration coll, Interaction inter){
		Message originAfterQ = QuestionTreePage.traceLineMap.get(QuestionTreePage.textStartEvent).getOriginMessage();
		Class fromQ = (Class) ((MessageOccurrenceSpecification)originAfterQ.getSendEvent()).getCovereds().get(0).getRepresents().getType();
		Class toQ = (Class) ((MessageOccurrenceSpecification)originAfterQ.getReceiveEvent()).getCovereds().get(0).getRepresents().getType();
		
		
		Property p1Q = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originAfterQ.getSendEvent()).getCovereds().get(0).getRepresents().getName(), fromQ, false, UMLPackage.Literals.PROPERTY, true);
		Property p2Q = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originAfterQ.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), toQ, false, UMLPackage.Literals.PROPERTY, true);
		
		
		Lifeline lifeline1Q =  inter.getLifeline(p1Q.getName(), false, true);
		Lifeline lifeline2Q = inter.getLifeline(p2Q.getName(), false, true);
		
		lifeline1Q.setRepresents(p1Q);
		lifeline2Q.setRepresents(p2Q);
		Message eventQ = inter.createMessage(originAfterQ.getName());
		
		// apply stereotype
				EList<Stereotype> applicableStereotypes = eventQ.getApplicableStereotypes();
				for(Stereotype stereotype:applicableStereotypes){
					if(stereotype.getName().equals("after"))
						eventQ.applyStereotype(stereotype);
				}
				
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
				ExecutionEvent ev1Q = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+(int )(Math.random() * 5000 + 1), UMLPackage.eINSTANCE.getExecutionEvent()); 
				
				
				// can I deduce it?
				ReceiveOperationEvent roeQ  = (ReceiveOperationEvent) ((MessageOccurrenceSpecification)originAfterQ.getReceiveEvent()).getEvent();
				
//				ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
				roeQ.setOperation(op1Q); //need to find the operation correspondign to this class!
				
				// can I deduce it again?
				
				SendOperationEvent soeQ = (SendOperationEvent) ((MessageOccurrenceSpecification)originAfterQ.getSendEvent()).getEvent();
//				SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
				soeQ.setOperation(op1Q);

				
				MessageOccurrenceSpecification seQ = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
				seQ.setEvent(soeQ);
				seQ.setMessage(eventQ);
				
//				se.setName("se");// do we really need a name?
				seQ.getCovereds().add(lifeline1Q);
				
				MessageOccurrenceSpecification reQ = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
				reQ.setEvent(roeQ);
				reQ.setMessage(eventQ);
//				re.setName("re");
				reQ.getCovereds().add(lifeline2Q);
				
				ExecutionOccurrenceSpecification eosQ = (ExecutionOccurrenceSpecification)inter.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//				eos.setName("eos");
				eosQ.setEvent(ev1Q);
				eosQ.getCovereds().add(lifeline2Q);
				
				BehaviorExecutionSpecification besQ = (BehaviorExecutionSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
				besQ.setStart(reQ);
				besQ.setFinish(eosQ);
//				bes.setName("bes");
				besQ.getCovereds().add(lifeline2Q);
				
				eosQ.setExecution(besQ);

				// whew...
			
			// set the message properties
				eventQ.setSendEvent(seQ);
				eventQ.setReceiveEvent(reQ);
				eventQ.setConnector(cn1Q);
	}
	
public void beforeR(Model m, Collaboration coll, Interaction inter){
		
		// // //// /// / //// / /// 
		
				// stereotype needs to be applied
				Message originBeforeR = QuestionTreePage.traceLineMap.get(QuestionTreePage.textEndEvent).getOriginMessage();
				Class fromR = (Class) ((MessageOccurrenceSpecification)originBeforeR.getSendEvent()).getCovereds().get(0).getRepresents().getType();
				Class toR = (Class) ((MessageOccurrenceSpecification)originBeforeR.getReceiveEvent()).getCovereds().get(0).getRepresents().getType();
					
				Property p1R = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originBeforeR.getSendEvent()).getCovereds().get(0).getRepresents().getName(), fromR, false, UMLPackage.Literals.PROPERTY, true);
				Property p2R = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originBeforeR.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), toR, false, UMLPackage.Literals.PROPERTY, true);
				
				// NO, DON'T CREATE NEW LIFELINE IF THE PROPERTY IS THERE
				Lifeline lifeline1R =  inter.getLifeline(p1R.getName(), false, true);
				Lifeline lifeline2R = inter.getLifeline(p2R.getName(), false, true);
				
				lifeline1R.setRepresents(p1R);
				lifeline2R.setRepresents(p2R);
				Message eventR = inter.createMessage(originBeforeR.getName());
				eventR.setMessageSort(originBeforeR.getMessageSort());
				
				// apply stereotype
				EList<Stereotype> applicableStereotypes = eventR.getApplicableStereotypes();
				for(Stereotype stereotype:applicableStereotypes){
					if(stereotype.getName().equals("before"))
						eventR.applyStereotype(stereotype);
				}
				
				
				Connector cn1R = inter.createOwnedConnector("connector1_"+(int )(Math.random() * 50 + 1));
				cn1R.setKind(ConnectorKind.ASSEMBLY_LITERAL);
				
				// create the connector ends, assign the roles
				ConnectorEnd ce1R = cn1R.createEnd();
				ConnectorEnd ce2R = cn1R.createEnd();
				if (eventR.getMessageSort().getLiteral().equals("reply"))
				{
					ce1R.setRole(p2R);		
					ce2R.setRole(p1R);
				} else
				{
					ce1R.setRole(p1R);		
					ce2R.setRole(p2R);
				}
			
				Operation op1R = ((SendOperationEvent)((MessageOccurrenceSpecification) originBeforeR.getSendEvent()).getEvent()).getOperation();
				
				ExecutionEvent ev1R = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+(int )(Math.random() * 5000 + 1), UMLPackage.eINSTANCE.getExecutionEvent()); 
				
				
				// can I deduce it?
				ReceiveOperationEvent roeR  = (ReceiveOperationEvent) ((MessageOccurrenceSpecification)originBeforeR.getReceiveEvent()).getEvent();
				
//				ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
				roeR.setOperation(op1R); //need to find the operation correspondign to this class!
				
				// can I deduce it again?
				
				SendOperationEvent soeR = (SendOperationEvent) ((MessageOccurrenceSpecification)originBeforeR.getSendEvent()).getEvent();
//				SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
				soeR.setOperation(op1R);

				
				MessageOccurrenceSpecification seR = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
				seR.setEvent(soeR);
				seR.setMessage(eventR);
//				se.setName("se");// do we really need a name?
				seR.getCovereds().add(lifeline1R);
				
				MessageOccurrenceSpecification reR = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
				reR.setEvent(roeR);
				reR.setMessage(eventR);
//				re.setName("re");
				reR.getCovereds().add(lifeline2R);
				
				ExecutionOccurrenceSpecification eosR = (ExecutionOccurrenceSpecification)inter.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//				eos.setName("eos");
				eosR.setEvent(ev1R);
				eosR.getCovereds().add(lifeline2R);
				
				BehaviorExecutionSpecification besR = (BehaviorExecutionSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
				besR.setStart(reR);
				besR.setFinish(eosR);
//				bes.setName("bes");
				besR.getCovereds().add(lifeline2R);
				
				eosR.setExecution(besR);

				// whew...
			
			// set the message properties
				eventR.setSendEvent(seR);
				eventR.setReceiveEvent(reR);
				eventR.setConnector(cn1R);
		
	}
	
	
public void untilR(Model m, Collaboration coll, Interaction inter){
		
	// // //// /// / //// / /// 
	
	// stereotype needs to be applied
	Message originBeforeR = QuestionTreePage.traceLineMap.get(QuestionTreePage.textEndEvent).getOriginMessage();
	Class fromR = (Class) ((MessageOccurrenceSpecification)originBeforeR.getSendEvent()).getCovereds().get(0).getRepresents().getType();
	Class toR = (Class) ((MessageOccurrenceSpecification)originBeforeR.getReceiveEvent()).getCovereds().get(0).getRepresents().getType();
		
	Property p1R = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originBeforeR.getSendEvent()).getCovereds().get(0).getRepresents().getName(), fromR, false, UMLPackage.Literals.PROPERTY, true);
	Property p2R = coll.getOwnedAttribute(((MessageOccurrenceSpecification)originBeforeR.getReceiveEvent()).getCovereds().get(0).getRepresents().getName(), toR, false, UMLPackage.Literals.PROPERTY, true);
	
	// NO, DON'T CREATE NEW LIFELINE IF THE PROPERTY IS THERE
	Lifeline lifeline1R =  inter.getLifeline(p1R.getName(), false, true);
	Lifeline lifeline2R = inter.getLifeline(p2R.getName(), false, true);
	
	lifeline1R.setRepresents(p1R);
	lifeline2R.setRepresents(p2R);
	Message eventR = inter.createMessage(originBeforeR.getName());
	eventR.setMessageSort(originBeforeR.getMessageSort());
	
	// apply stereotype
	EList<Stereotype> applicableStereotypes = eventR.getApplicableStereotypes();
	for(Stereotype stereotype:applicableStereotypes){
		if(stereotype.getName().equals("until"))
			eventR.applyStereotype(stereotype);
	}
	
	
	Connector cn1R = inter.createOwnedConnector("connector1_"+(int )(Math.random() * 50 + 1));
	cn1R.setKind(ConnectorKind.ASSEMBLY_LITERAL);
	
	// create the connector ends, assign the roles
	ConnectorEnd ce1R = cn1R.createEnd();
	ConnectorEnd ce2R = cn1R.createEnd();
	if (eventR.getMessageSort().getLiteral().equals("reply"))
	{
		ce1R.setRole(p2R);		
		ce2R.setRole(p1R);
	} else
	{
		ce1R.setRole(p1R);		
		ce2R.setRole(p2R);
	}

	Operation op1R = ((SendOperationEvent)((MessageOccurrenceSpecification) originBeforeR.getSendEvent()).getEvent()).getOperation();
	
	ExecutionEvent ev1R = (ExecutionEvent) m.createPackagedElement("ExecutionEvent1_"+(int )(Math.random() * 5000 + 1), UMLPackage.eINSTANCE.getExecutionEvent()); 
	
	
	// can I deduce it?
	ReceiveOperationEvent roeR  = (ReceiveOperationEvent) ((MessageOccurrenceSpecification)originBeforeR.getReceiveEvent()).getEvent();
	
//	ReceiveOperationEvent roe = (ReceiveOperationEvent) m.createPackagedElement("ReceiveOperationEvent1_"+random, UMLPackage.eINSTANCE.getReceiveOperationEvent());
	roeR.setOperation(op1R); //need to find the operation correspondign to this class!
	
	// can I deduce it again?
	
	SendOperationEvent soeR = (SendOperationEvent) ((MessageOccurrenceSpecification)originBeforeR.getSendEvent()).getEvent();
//	SendOperationEvent soe = (SendOperationEvent) m.createPackagedElement("SendOperationEvent1_"+random, UMLPackage.eINSTANCE.getSendOperationEvent());
	soeR.setOperation(op1R);

	
	MessageOccurrenceSpecification seR = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
	seR.setEvent(soeR);
	seR.setMessage(eventR);
//	se.setName("se");// do we really need a name?
	seR.getCovereds().add(lifeline1R);
	
	MessageOccurrenceSpecification reR = (MessageOccurrenceSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getMessageOccurrenceSpecification());
	reR.setEvent(roeR);
	reR.setMessage(eventR);
//	re.setName("re");
	reR.getCovereds().add(lifeline2R);
	
	ExecutionOccurrenceSpecification eosR = (ExecutionOccurrenceSpecification)inter.createFragment(null, UMLPackage.eINSTANCE.getExecutionOccurrenceSpecification());
//	eos.setName("eos");
	eosR.setEvent(ev1R);
	eosR.getCovereds().add(lifeline2R);
	
	BehaviorExecutionSpecification besR = (BehaviorExecutionSpecification) inter.createFragment(null, UMLPackage.eINSTANCE.getBehaviorExecutionSpecification());
	besR.setStart(reR);
	besR.setFinish(eosR);
//	bes.setName("bes");
	besR.getCovereds().add(lifeline2R);
	
	eosR.setExecution(besR);

	// whew...

// set the message properties
	eventR.setSendEvent(seR);
	eventR.setReceiveEvent(reR);
	eventR.setConnector(cn1R);
		
}
	
}
