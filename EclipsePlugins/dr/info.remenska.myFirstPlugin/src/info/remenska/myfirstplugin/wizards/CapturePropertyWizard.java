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
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Text;
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
import org.eclipse.uml2.uml.InteractionConstraint;
import org.eclipse.uml2.uml.InteractionOperand;
import org.eclipse.uml2.uml.InteractionOperatorKind;
import org.eclipse.uml2.uml.Lifeline;
import org.eclipse.uml2.uml.LiteralInteger;
import org.eclipse.uml2.uml.LiteralUnlimitedNatural;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.MessageOccurrenceSpecification;
import org.eclipse.uml2.uml.MessageSort;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.ReceiveOperationEvent;
import org.eclipse.uml2.uml.SendOperationEvent;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.ValueSpecification;
import org.eclipse.uml2.uml.internal.impl.CollaborationImpl;
import org.eclipse.uml2.uml.Profile;
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
											e1.printStackTrace();
										} 
										
								 }
								 
								 // next create an SD
								if(model.getName().equalsIgnoreCase("DanielaModel")){
									 System.out.println("Creating a new SD...");

									 PropertyPattern propPattern = null;
									 if(QuestionTreePage.behavior.equals("Absence"))
											 propPattern = new Absence(model);
									 else if(QuestionTreePage.behavior.equals("Existence"))
										 	propPattern = new Existence(model);
									 else if(QuestionTreePage.behavior.equals("Bounded Existence"))
										 	propPattern = new BoundedExistence(model);
									 else if(QuestionTreePage.behavior.equals("Precedence"))
										 	propPattern = new Precedence(model);
									 else if(QuestionTreePage.behavior.equals("Precedence variant"))
										 	propPattern = new Precedence(model);
									 else if(QuestionTreePage.behavior.equals("Precedence Chain 1"))
											 propPattern = new PrecedenceChain(model);
									 else if(QuestionTreePage.behavior.equals("Precedence Chain 2"))
										 	 propPattern = new PrecedenceChain2(model);
									 else if(QuestionTreePage.behavior.equals("Response"))
										 	propPattern = new Response(model);
									 else if(QuestionTreePage.behavior.equals("Response variant"))
										 	propPattern = new ResponseVariant(model);
									 else if(QuestionTreePage.behavior.equals("Response Chain 1"))
										   propPattern = new ResponseChain(model);
									 else if(QuestionTreePage.behavior.equals("Response Chain 2"))
										 propPattern = new ResponseChain2(model);
									 else if(QuestionTreePage.behavior.equals("Constrained Response Chain 2"))
										 propPattern = new ConstrainedChainPattern(model);
									 
									 if(QuestionTreePage.scope.equals("Before R"))
										 propPattern.draw(PropertyPattern.BEFORE);
									 else if(QuestionTreePage.scope.equals("After Q"))
										 propPattern.draw(PropertyPattern.AFTER);
									 else if(QuestionTreePage.scope.equals("After Q variant"))
										 propPattern.draw(PropertyPattern.AFTER_LAST); 
									 else if(QuestionTreePage.scope.equals("Between Q and R"))
										 propPattern.draw(PropertyPattern.BETWEEN);
									 else if(QuestionTreePage.scope.equals("After Q until R"))
										 propPattern.draw(PropertyPattern.AFTER_UNTIL);
									 else if(QuestionTreePage.scope.equals("Globally"))
										 propPattern.draw(PropertyPattern.GLOBALLY);
									 else if(QuestionTreePage.scope.equals("Before R variant"))
										 propPattern.draw(PropertyPattern.UNTIL);
									 else if(QuestionTreePage.scope.equals("Between Q and R variant"))
										 propPattern.draw(PropertyPattern.BETWEEN_LAST);
									 else if(QuestionTreePage.scope.equals("After Q until R variant"))
										 propPattern.draw(PropertyPattern.AFTER_LAST_UNTIL);
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
	
	
}