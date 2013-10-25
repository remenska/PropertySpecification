package info.remenska.myfirstplugin.wizards;

import info.remenska.myfirstplugin.Activator;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.emf.search.ecore.common.ui.dialogs.ModelSearchFilteredEClassifierSelectionDialog;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.uml2.uml.resource.*;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Message;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.NamedElement;

import com.ibm.xtools.modeler.ui.UMLModeler;
//import org.eclipse.emf.common.ui.dialogs.*;
//search.ecore.common.ui.dialogs.;
public class DanielaSelectionDialog extends FilteredItemsSelectionDialog {
	private static ArrayList resources = new ArrayList();

	static {
		generateRescourcesTestCases('A', 'C', 8, ""); //$NON-NLS-1$
		//	generateRescourcesTestCases('a', 'c', 4, ""); //$NON-NLS-1$
	}

	private static void generateRescourcesTestCases(char startChar,
			char endChar, int length, String resource) {
		
		try {
			UMLModeler.getEditingDomain().runExclusive(new Runnable() {

				@SuppressWarnings("deprecation")
				public void run() {

					List selectedElements = UMLModeler.getUMLUIHelper().getSelectedElements();
					Collection<Model> models = UMLModeler.getOpenedModels();
					for (Iterator iter = models.iterator(); iter.hasNext();) {
						Model model = (Model) iter.next();
						
						System.out.println("MODEL: "+model.getQualifiedName());
						for (Iterator iter1 = model.getOwnedElements().iterator(); iter1.hasNext();) {
							EObject eObject = (EObject) iter1.next();
							String eClassName = eObject.eClass().getName();
							System.out.print(eClassName + " : ");

							if (eObject instanceof Diagram) {
								System.out.println(((Diagram) eObject).getName());

							} else if (eObject instanceof View) {
								View view = (View) eObject;
								String viewType = view.getType();
								if (viewType.trim().length() > 0) {
									System.out.print("(" + view.getType() + ")");
								}

								EObject element = view.getElement();
								if (null != element) {
									System.out.print(" of " + element);
								}
								System.out.println();

							} else if (eObject instanceof Element) {
								if (eObject instanceof NamedElement) {
									System.out.println(((NamedElement) eObject).getName());
									resources.add(new String(eObject.eClass().getName()+":"+ ((NamedElement) eObject).getName()));

								} else {
									System.out.println(eObject);
								}
							}
						}
					}
					
			
				}
			});
		} catch (InterruptedException e) {
			System.out.println("The operation was interrupted");
		}
		//EObject object = null;
//		EObject eObject = (EObject) object; 
//		org.eclipse.emf.common.util.URI uri = EcoreUtil.getURI(eObject); 
//		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile((IPath) new Path(uri.toPlatformString(true)));
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IProject[] projects = workspace.getRoot().getProjects();
		for (int i = 0; i < projects.length; i++) {

			try {
				IResource[] folderResources = projects[i].members();
				System.out.println(projects[i].getName().toString());
				for (int j = 0; j < folderResources.length; j++) {

					if (folderResources[j] instanceof IFolder) {
						IFolder resource1 = (IFolder) folderResources[j];

						IResource[] fileResources = resource1.members();
						for (int k = 0; k < fileResources.length; k++) {

							//resources.add(fileResources[k].getName());

						}
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}

		}
		System.out.println(resources.toString());

		//
		// for (char ch = startChar; ch <= endChar; ch++) {
		// String res = resource + String.valueOf(ch);
		// if (length == res.length())
		// resources.add(res);
		// else if ((res.trim().length() % 2) == 0)
		// generateRescourcesTestCases(
		// Character.toUpperCase((char) (startChar + 1)),
		// Character.toUpperCase((char) (endChar + 1)), length,
		// res);
		// else
		// generateRescourcesTestCases(
		// Character.toLowerCase((char) (startChar + 1)),
		// Character.toLowerCase((char) (endChar + 1)), length,
		// res);
		// }
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
