package info.remenska.myfirstplugin.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class BehaviorQuestionTreePage extends QuestionTreePage {

	protected BehaviorQuestionTreePage(String pageName, String description,
			TreeNode<String> questionnaire) {
		super(pageName, description, questionnaire);
		// TODO Auto-generated constructor stub
	}
	
	public void addEventSlots(Composite composite, ExpandBar root, Listener operationListener){
		
		GridData gridData = new GridData();
		gridData.verticalSpan = 7;
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		root.setLayoutData(gridData);
		
		 gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		labelEventA = new Label(composite, SWT.NONE);
		labelEventA.setText("Event A: ");
		FontData fontData = labelEventA.getFont().getFontData()[0];
		Font font = new Font(Display.getCurrent(), new FontData(fontData.getName(), fontData
		    .getHeight(), SWT.BOLD));
		labelEventA.setFont(font);
		labelEventA.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		textEventA = new Text(composite, SWT.FILL); // THESE SHOULD BE STATIC
		textEventA.setText("double click to select");
		textEventA.setEditable(false);
		textEventA.setLayoutData(gridData);
		textEventA.addListener(SWT.MouseDoubleClick, operationListener);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		labelEventB = new Label(composite, SWT.NONE);
		labelEventB.setText("Event B: ");
		labelEventB.setFont(font);
		labelEventB.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		textEventB = new Text(composite, SWT.FILL);
		textEventB.setText("double-click to select");
		textEventB.setEditable(false);
		textEventB.addListener(SWT.MouseDoubleClick, operationListener);
		
		

		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		labelEventC = new Label(composite, SWT.NONE);
		labelEventC.setText("Event C: ");
		labelEventC.setFont(font);
		labelEventC.setLayoutData(gridData);
		
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.verticalAlignment = SWT.TOP;
		textEventC = new Text(composite, SWT.FILL);
		textEventC.setText("double-click to select");
		textEventC.setEditable(false);

		textEventC.setLayoutData(gridData);
		textEventC.addListener(SWT.MouseDoubleClick, operationListener);
		
	}


}
