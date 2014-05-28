package customplugin.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class CustomEditor extends EditorPart {

	public static String CUSTOMEDITOR_ID = "customplugin.editorID";
	
	public CustomEditor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
//		 IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(); 
//    	 page.openEditor(new CustomEditor().getEditorInput(), CustomEditor.CUSTOMEDITOR_ID, false); 
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	private Label contents;
	@Override
	public void createPartControl(Composite parent) {
	    contents = new Label(parent, SWT.NONE);
        contents.setText("Minimal Editor");
	}

	@Override
	public void setFocus() {
		if (contents != null) {
			contents.setFocus();
		}
	}

}
