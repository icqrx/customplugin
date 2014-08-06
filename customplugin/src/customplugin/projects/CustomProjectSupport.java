package customplugin.projects;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

import customplugin.natures.ProjectNature;

public class CustomProjectSupport {
	
	/**
     * For this marvelous project we need to:
     * - create the default Eclipse project
     * - add the custom project nature
     * - create the folder structure
     *
     * @param projectName
     * @param location
     * @param natureId
     * @return
     */
	
    public static IProject createProject(String projectName, URI location) {
        Assert.isNotNull(projectName);
        Assert.isTrue(projectName.trim().length() > 0);
 
        IProject project = createBaseProject(projectName, location);
        try {
            addNature(project);
            String[] paths = {
                    "source",
                    "design",
                    "bin"
                    };
            addToProjectStructure(project, paths);
            // open brower to load maqetta
            loadBrowser();
        } catch (Exception e) {
            e.printStackTrace();
            project = null;
        }
 
        return project;
    }
    /**
     * @throws MalformedURLException 
     * @throws PartInitException 
     * 
     */
    public static void loadBrowser() throws PartInitException, MalformedURLException {
    	   IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
           browserSupport.getExternalBrowser().openURL(new URL("http://163.180.117.219:50000/"));
    }
    /**
     * Just do the basics: create a basic project.
     *
     * @param location
     * @param projectName
     */
    private static IProject createBaseProject(String projectName, URI location) {
        // it is acceptable to use the ResourcesPlugin class
        IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
 
        if (!newProject.exists()) {
            URI projectLocation = location;
            IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
            if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
                projectLocation = null;
            }
 
            desc.setLocationURI(projectLocation);
            try {
                newProject.create(desc, null);
                if (!newProject.isOpen()) {
                    newProject.open(null);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
 
        return newProject;
    }
    
    /**
     * 
     * @param folder
     * @throws CoreException
     */
    private static void createFolder(IFolder folder) throws CoreException {
        IContainer parent = folder.getParent();
        if (parent instanceof IFolder) {
            createFolder((IFolder) parent);
        }
        if (!folder.exists()) {
            folder.create(false, true, null);
        }
    }
 
    /**
     * Create a folder structure with a parent root, overlay, and a few child
     * folders.
     *
     * @param newProject
     * @param paths
     * @throws CoreException
     * @throws MalformedURLException 
     */
    private static void addToProjectStructure(IProject newProject, String[] paths) throws CoreException, MalformedURLException {
        for (String path : paths) {
            IFolder etcFolders = newProject.getFolder(path);
            createFolder(etcFolders);
            //create file in design folder
            if(path.equalsIgnoreCase("design")) {
            	createDesignFile(etcFolders);
            }
        }
    }
    
    /**
     * Create design file layout
     * @param etcFolders
     * @throws MalformedURLException 
     */
    private static void createDesignFile(IFolder etcFolders) throws MalformedURLException {
		IFile designFile = etcFolders.getFile("gui.html");
		if (!designFile.exists()) {
		    try {
				byte[] bytes = "File contents".getBytes();
				InputStream source = new ByteArrayInputStream(bytes);
				designFile.create(source, IResource.NONE, null);
//				IEditorInput editorInput = new FileEditorInput(designFile);
//				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
//			    IWorkbenchPage page = window.getActivePage();
//			    IDE.openEditor(page, designFile, "org.eclipse.ui.browser.editor");
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
	}

	/**
     * 
     * @param project
     * @throws CoreException
     */
    private static void addNature(IProject project) throws CoreException {
        if (!project.hasNature(ProjectNature.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures = description.getNatureIds();
            String[] newNatures = new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length] = ProjectNature.NATURE_ID;
            description.setNatureIds(newNatures);
 
            IProgressMonitor monitor = null;
            project.setDescription(description, monitor);
        }
    }
}
