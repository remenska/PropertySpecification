package info.remenska.myfirstplugin;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveFactory3 implements IPerspectiveFactory {
	private static final String VIEW_ID = "info.remenska.myFirstPlugin.ResourceManagerView";
	
	private static final String BOTTOM = "bottom";
	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.addView(IPageLayout.ID_OUTLINE, IPageLayout.LEFT, 0.30f, layout.getEditorArea());
		
		IFolderLayout bot = layout.createFolder(BOTTOM, IPageLayout.BOTTOM, 0.76f, layout.getEditorArea());
		bot.addView(VIEW_ID);
	}

}
