import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
public class MyFormLayout {
	public static void main(String[] args){
		Display myDisplay = new Display();
		final Shell myShell = new Shell(myDisplay);
		myShell.setText("My FormLayout");
		myShell.setBounds(120,120,240,200);
		myShell.setLayout(new FormLayout());
		
		Button myCancelButton = new Button(myShell, SWT.PUSH);
		myCancelButton.setText("This is Cancel Button");
		FormData myFormData = new FormData();
		myFormData.right = new FormAttachment(100,-10);
		myFormData.bottom = new FormAttachment(100,-10);
		myCancelButton.setLayoutData(myFormData);
		
		Button myOKButton = new Button(myShell, SWT.PUSH);
		myOKButton.setText("This is OK Button");
		myFormData = new FormData();
		myFormData.right = new FormAttachment(myCancelButton,-10);
		myFormData.bottom = new FormAttachment(100,-10);
		myOKButton.setLayoutData(myFormData);
		Text myTextBox = new Text(myShell, SWT.MULTI | SWT.BORDER);
        myFormData = new FormData();
        myFormData.top = new FormAttachment(0,10);
        myFormData.bottom = new FormAttachment(
        myCancelButton,-10);
        myFormData.left = new FormAttachment(0,10);
        myFormData.right = new FormAttachment(100,-10);
        myTextBox.setLayoutData(myFormData);
		myShell.open();
		 while (!myShell.isDisposed()) {
	         if (!myDisplay.readAndDispatch()) myDisplay.sleep();
	      }
	      myDisplay.dispose();
		
	}
}
