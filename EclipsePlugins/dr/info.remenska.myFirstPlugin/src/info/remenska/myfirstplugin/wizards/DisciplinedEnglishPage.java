package info.remenska.myfirstplugin.wizards;

import java.util.LinkedList;
import java.util.TreeMap;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

public class DisciplinedEnglishPage  extends WizardPage  {
	protected Composite aman;
	protected TreeMap<String,String> scope;
	protected TreeMap<String, String> behavior;
	static String eventA, eventB, eventC, eventX;
	static String startEvent, endEvent;
	static StyledText styledText;
	protected DisciplinedEnglishPage(String pageName, String description) {
		super(pageName);
		setTitle(pageName);
		setDescription(description);
//		setPageComplete(false);

		// TODO Auto-generated constructor stub
	}
	
	public void fillScope(){
		scope = new TreeMap<String,String>();
		 startEvent = QuestionTreePage.textStartEvent.getText() ;
		 endEvent = QuestionTreePage.textEndEvent.getText();
		scope.put("Globally", "The required behavior must hold throughout the entire system execution, i.e., from the start until the end of any event sequence.");
		scope.put("After Q", "The behavior must hold in a restricted interval in the event sequence, and this interval has a starting delimiter " + startEvent + ". The behavior must hold from the first occurrence of " + startEvent + " until the end of the system execution. \nEven if " + startEvent + " occurs more than once before the end of the event sequence, only the first occurrence begins the restricted interval; later occurrences of "+  startEvent + " do not have an effect." );
		scope.put("Before R", "The behavior must hold in a restricted interval in the event sequence, and this interval has a ending delimiter " + endEvent + ". The behavior is required to hold from the start of the event sequence through to the first occurrence of " + endEvent+", if it ever occurs. " + endEvent +" is not required to occur, and if it never occurs, then the behavior is not required to hold anywhere in the event sequence, i.e., system execution. ");
		scope.put("After Q until R", "The behavior must hold in a restricted interval in the event sequence, and this interval has both a starting delimiter, " + startEvent+ ", and an ending delimiter " + endEvent + ". The behavior must hold from the first occurrence of " + startEvent + ", if it ever occurs, throughout the first subsequent occurrence of " + endEvent + ", if it ever occurs. \nEven if " + startEvent + " occurs more than once before the first occurence of " + endEvent + ", only the first of those occurences of " + startEvent + " potentially  begins the restricted interval; later occurrences of "+  startEvent + " do not have an effect. If an occurrence of " + startEvent + " is not followed by occurrence of " + endEvent + ", then the behavior is required to hold until the end of the event sequence." );
		scope.put("Between Q and R", "The behavior must hold in a restricted interval in the event sequence, and this interval has both a starting delimiter, " + startEvent+ ", and an ending delimiter " + endEvent + ". The behavior must hold from the first occurrence of " + startEvent + ", if it ever occurs, throughout the first subsequent occurrence of " + endEvent + ", if it ever occurs. \nEven if " + startEvent + " occurs more than once before the first occurence of " + endEvent + ", only the first of those occurences of " + startEvent + " potentially  begins the restricted interval; later occurrences of "+  startEvent + " do not have an effect. If an occurrence of " + startEvent + " is not followed by occurrence of " + endEvent + ", then the behavior is not required to hold for the remainder of the event sequence." );
		
		
	}
	
	public void fillBehavior(){
		behavior = new TreeMap<String, String>();
		 eventA = QuestionTreePage.textEventA.getText();
		 eventB = QuestionTreePage.textEventB.getText();
		 eventC = QuestionTreePage.textEventC.getText();
		 eventX = QuestionTreePage.textEventX.getText();
		behavior.put("Absence", "The event of interest for the required behavior is " + eventA + ". " + eventA + " must never occur. ");
		behavior.put("Existence", "The event of interest for the required behavior is " +  eventA + ". " + eventA + " is required to occur one or more times. ");
		behavior.put("Bounded Existence", "The event of interest for the required behavior is " +  eventA + ". " + eventA + " is required at most one time. ");
		behavior.put("Precedence", "The events of interest for the required behavior are " + eventA + " and " + eventB + ". " + eventB + " is not allowed to occur until after " + eventA + " occurs." + eventA + " is not required to occur and, if it does not occur, " + eventB + " is never allowed to occur.");
		behavior.put("Response", "The events of interest for the required behavior are " + eventA + " and " + eventB + ". If " + eventA + " occurs, " + eventB +" is required to occur subsequently. Event " + eventA + "is not required to occur. ");
		behavior.put("Precedence Chain 1", "The events of interest for the required behavior are " + " " + eventA + ", " + eventB + " and " + eventC + ". Event " + eventC + " is not allowed to occur until after " + eventA +" followed by event " + eventB + " occurs. The event chain " + eventA+" followed by " + eventB + ", is not required to occur, and, if it does not occur, " + eventC + " is never allowed to occur.");
		behavior.put("Precedence Chain 2", "The events of interest for the required behavior are " + " " + eventA + ", " + eventB + " and " + eventC + ". Event " + eventB + "followed by " + eventC + " is not allowed to occur, until " + eventA  + " occurs. The event  " + eventA+ " is not  required to occur, and, if does not occur, " + " the event chain: " + eventB + " followed by " + eventC + ", is never allowed to occur.");
		behavior.put("Response Chain 1", "The events of interest for the required behavior are " + " " + eventA + ", " + eventB + " and " + eventC + ". If " + eventA + "occurs, then " + eventB +" followed by " + eventC + " is required to occur subsequently. Event " + eventA + "is not required to occur. ");
		behavior.put("Response Chain 2", "The events of interest for the required behavior are " + " " + eventA + ", " + eventB + " and " + eventC + ". If " + eventA + " followed by " + eventB + " occurs, then " + eventC + "is required to occur subsequently. The event chain " + eventA +" followed by " + eventB + "is not required to occur. ");
		behavior.put("Constrained Response Chain 2", "The events of interest for the required behavior are " + " " + eventA + ", " + eventB + "," + eventC + ", and the exceptional event " + eventX +". If " + eventA + " followed by " + eventB + " occurs, without the event " + eventX + " occurring inbetween,  then " + eventC + "is required to occur subsequently. The event chain " + eventA +" followed by " + eventB + "is not required to occur. ");
//		behavior.put("Universality", "The event of interest for the required behavior is " + eventA + ". " + eventA + " must always be possible to occur. ");
	}
	
	
	public void setVisible(boolean visible) {

	    super.setVisible(visible);

	    if (visible) {

	        onEnterPage();
	    }
	}
	
	void onEnterPage()
	{
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		gridData.verticalSpan = 5;
		gridData.heightHint = 450;
		gridData.widthHint = 550;
//		gridData.horizontalAlignment = GridData.GRAB_HORIZONTAL;
//		gridData.verticalAlignment = GridData.GRAB_VERTICAL;
//		gridData.se
		StyledText styledText = new StyledText(aman, SWT.WRAP);
		styledText.setLineJustify(0, 1, true);
		styledText.setEditable(false);
//		styledText.setLineAlignment(6, 1, SWT.RIGHT);
		styledText.setWordWrap(true);
		styledText.setLayoutData(gridData); 
		styledText.setText(((BehaviorQuestionTreePage)this.getPreviousPage()).textEventA.getText());
	}

	public static void boldify(String originalText, String event, int style){
		String newOrig = originalText;

		int index = newOrig.indexOf(event);
		int prevIndex = 0;
		String orig = newOrig; 
		while (index !=-1 ){
			orig = newOrig;
			
			StyleRange styleRange = new StyleRange();
			styleRange.start =  prevIndex + index;
			styleRange.length = event.length() ;
			styleRange.fontStyle = style;
			styledText.setStyleRange(styleRange);
			
			newOrig = orig.substring(index+event.length(), orig.length());
			prevIndex = prevIndex + index + event.length();
			index = newOrig.indexOf(event);
		}

	}
	
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		System.out.println("NOW WE'RE IN!");
		aman = composite;
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		gridData.verticalSpan = 5;
		gridData.heightHint = 350;
		gridData.widthHint = 850;

		styledText = new StyledText(aman, SWT.WRAP);
	
		styledText.setLineJustify(0, 1, true);
		styledText.setEditable(false);
		styledText.setWordWrap(true);
		styledText.setLayoutData(gridData); 
		fillScope();
		fillBehavior();
		String fullText = "SCOPE: \n" + scope.get(QuestionTreePage.scope) + "\n\nBEHAVIOR: \n" + behavior.get(QuestionTreePage.behavior);
		String pleaseReview = "\n\nClicking \"Finish\" will generate the Sequence Diagram, mu-calculus formula, as well as the monitoring process (when applicable) matching the elucidated property. ";
		styledText.setText(fullText + pleaseReview);

		boldify(fullText, startEvent, SWT.BOLD);
		boldify(fullText, endEvent, SWT.BOLD);
		boldify(fullText, eventA, SWT.BOLD);
		boldify(fullText, eventB,SWT.BOLD);
		boldify(fullText, eventC, SWT.BOLD);
		boldify(fullText, eventX, SWT.BOLD);
		boldify(fullText, "SCOPE", SWT.BOLD); boldify(fullText, "BEHAVIOR", SWT.BOLD);
		boldify(fullText + pleaseReview, pleaseReview, SWT.ITALIC);
		
	}

}
