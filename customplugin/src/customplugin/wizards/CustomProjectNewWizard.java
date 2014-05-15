package customplugin.wizards;

import java.net.URI;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

import customplugin.projects.CustomProjectSupport;
/**
 * 
 * @author QUOC NGUYEN
 *
 */
public class CustomProjectNewWizard extends Wizard implements INewWizard, IExecutableExtension {

	private static final String WIZARD_NAME = NewWizardMessages.Custom_Plugin_Project;
	private static final String PAGE_NAME = NewWizardMessages.Create_something_custom;
	private WizardNewProjectCreationPage _pageOneProject;
	private WizardNewFileCreationPage _pageOneFile;
	private IConfigurationElement _configurationElement;
	private IWorkbench _workbench;
	private IStructuredSelection _selectionFile;
	
	public CustomProjectNewWizard() {
		setWindowTitle(WIZARD_NAME);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		_workbench = workbench;
		_selectionFile = selection;
	}

	@Override
	public boolean performFinish() {
		  String name = _pageOneProject.getProjectName();
		    URI location = null;
		    if (!_pageOneProject.useDefaults()) {
		        location = _pageOneProject.getLocationURI();
		    } // else location == null
		 
		    CustomProjectSupport.createProject(name, location);
		    // Add this
		    BasicNewProjectResourceWizard.updatePerspective(_configurationElement);
		    
//		    // add schema file when the first create project
//		    boolean result = false;
//		    _pageOneFile = new WizardSchemaNewFileCreationPage(_selectionFile);
//			IFile file = _pageOneFile.createNewFile();
//			result = file != null;
//
//			if (result) {
//				try {
//					IDE.openEditor(_workbench.getActiveWorkbenchWindow().getActivePage(), file);
//				} catch (PartInitException e) {
//					e.printStackTrace();
//				}
//			} // else no file created...result == false

		    
		    return true;
	}

	@Override
	public void addPages() {
		super.addPages();
		// add page of project when create
		_pageOneProject = new WizardNewProjectCreationPage(PAGE_NAME);
		_pageOneProject.setTitle(NewWizardMessages.CustomProjectNewWizard_2);
		_pageOneProject.setDescription(NewWizardMessages.CustomProjectNewWizard_3);

		// add workbench for create page
		addPage(_pageOneProject);
		// add workbench for create file
//		_pageOneFile = new WizardSchemaNewFileCreationPage(_selectionFile);
//		addPage(_pageOneFile);
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		_configurationElement = config;		
	}
}
