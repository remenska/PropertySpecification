package info.remenska.myfirstplugin.wizards;

import info.remenska.myfirstplugin.Activator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

public class DanielaSelectionDialog extends FilteredItemsSelectionDialog {
	private static ArrayList resources = new ArrayList();

	static {
		generateRescourcesTestCases('A', 'C', 8, ""); //$NON-NLS-1$
		generateRescourcesTestCases('a', 'c', 4, ""); //$NON-NLS-1$
	}

	private static void generateRescourcesTestCases(char startChar,
			char endChar, int length, String resource) {
		for (char ch = startChar; ch <= endChar; ch++) {
			String res = resource + String.valueOf(ch);
			if (length == res.length())
				resources.add(res);
			else if ((res.trim().length() % 2) == 0)
				generateRescourcesTestCases(
						Character.toUpperCase((char) (startChar + 1)),
						Character.toUpperCase((char) (endChar + 1)), length,
						res);
			else
				generateRescourcesTestCases(
						Character.toLowerCase((char) (startChar + 1)),
						Character.toLowerCase((char) (endChar + 1)), length,
						res);
		}
	}

	public DanielaSelectionDialog(Shell shell) {
		super(shell);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Control createExtendedContentArea(Composite parent) {
		return null;
	}

	private static final String DIALOG_SETTINGS = "FilteredResourcesSelectionDialogExampleSettings";	
	
	   protected IDialogSettings getDialogSettings() {
	      	IDialogSettings settings = Activator.getDefault().getDialogSettings()
					.getSection(DIALOG_SETTINGS);
			if (settings == null) {
				settings = Activator.getDefault().getDialogSettings()
						.addNewSection(DIALOG_SETTINGS);
			}
			return settings;
	   }

	@Override
	protected IStatus validateItem(Object item) {
		return Status.OK_STATUS;
	}

	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter() {
			public boolean matchItem(Object item) {
				return matches(item.toString());
			}

			public boolean isConsistentItem(Object item) {
				return true;
			}
		};
	}

	@Override
	protected Comparator getItemsComparator() {
		return new Comparator() {
			public int compare(Object arg0, Object arg1) {
				return arg0.toString().compareTo(arg1.toString());
			}
		};
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter itemsFilter, IProgressMonitor progressMonitor)
			throws CoreException {
		progressMonitor.beginTask("Searching", resources.size()); //$NON-NLS-1$
		for (Iterator iter = (Iterator) resources.iterator(); ((java.util.Iterator) iter)
				.hasNext();) {
			contentProvider.add(iter.next(), itemsFilter);
			progressMonitor.worked(1);
		}
		progressMonitor.done();
	}

	@Override
	public String getElementName(Object item) {
		// TODO Auto-generated method stub
		return item.toString();
	}

}
