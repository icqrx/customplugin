package customplugin.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.ide.IDE;

/**
 * 
 * @author QUOC NGUYEN
 * 
 */
public class CustomProjectNewSchemaFile extends Wizard implements INewWizard {

	private IWorkbench _workbench;
	private IStructuredSelection _selection;
	private WizardNewFileCreationPage _pageOneFile;

	public CustomProjectNewSchemaFile() {
		setWindowTitle("New RmCRC Schema File");
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		_workbench = workbench;
		_selection = selection;
	}

	@Override
	public boolean performFinish() {
		boolean result = false;
		IFile file = _pageOneFile.createNewFile();
		result = file != null;

		if (result) {
			try {
				IDE.openEditor(_workbench.getActiveWorkbenchWindow().getActivePage(), file);
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		} // else no file created...result == false

		return result;
	}

	@Override
	public void addPages() {
		super.addPages();
		_pageOneFile = new WizardSchemaNewFileCreationPage(_selection);

		addPage(_pageOneFile);
	}

}
