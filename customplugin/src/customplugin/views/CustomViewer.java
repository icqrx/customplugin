package customplugin.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.ViewPart;

import customplugin.view.properties.AdaptableList;
import customplugin.view.properties.ButtonElement;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class CustomViewer extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "customplugin.views.CustomViewer";

	private TableViewer viewer;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	class ViewContentProvider implements IStructuredContentProvider {
		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
		}

		public Object[] getElements(Object parent) {
			return new String[] {  };
		}
	}

	class ViewLabelProvider extends LabelProvider implements
			ITableLabelProvider {
		public String getColumnText(Object obj, int index) {
			return getText(obj);
		}

		public Image getColumnImage(Object obj, int index) {
			return getImage(obj);
		}

		public Image getImage(Object obj) {
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	class NameSorter extends ViewerSorter {
	}

	/**
	 * The constructor.
	 */
	public CustomViewer() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	private AdaptableList ctlList;
	private Group grp1;
	
	public void createPartControl(final Composite parent) {
		viewer = new TableViewer(parent, SWT.SINGLE );
		RowLayout rowLayout = new RowLayout();
		parent.setLayout(rowLayout);
		viewer.setContentProvider(new BaseWorkbenchContentProvider());
		viewer.setLabelProvider(new WorkbenchLabelProvider());
		// fill in the element		
//		grp1 = new Group (parent,SWT.NONE);
//		grp1.setText("Preview");		
//		grp1.setLayout(rowLayout);
		
//		Button btn = new Button(grp1,SWT.PUSH);
//		btn.setText("Hello");
//		Button btn1 = new Button(grp1,SWT.PUSH);
//		btn.setText("Hello111111111");
		ctlList = new AdaptableList();
//		ButtonElement btnEl = new ButtonElement(btn,"Button");
//		ButtonElement btnEl1 = new ButtonElement(btn1,"Button");
//		ctlList.add(btnEl);
//		ctlList.add(btnEl1);
//		
//		viewer.setInput(ctlList);
		
		//viewer.setInput(getViewSite());
		this.createDropTarget(parent, viewer);
		getSite().setSelectionProvider(viewer);
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem()
				.setHelp(viewer.getControl(), "customplugin.viewer");
		//makeActions();
		//hookContextMenu();
		//hookDoubleClickAction();
		//contributeToActionBars();
	}
	public void createDropTarget (final Composite parent, final TableViewer viewer) {
		//	// Allow data to be copied or moved to the drop target
		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
		DropTarget target = new DropTarget(viewer.getControl(), operations);
		
		// Receive data in Text or File format
		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		target.setTransfer(types);
		target.addDropListener(new DropTargetListener() {
			
			@Override
			public void dropAccept(DropTargetEvent event) {
			}
			
			@Override
			public void drop(DropTargetEvent event) {
				 String data = (String) event.data;
				 System.out.print(data);
				 
				if (data.equalsIgnoreCase("TextView")) {
					Label label = new Label(parent, SWT.BORDER);
					label.setText(data);
				} else if (data.equalsIgnoreCase("Button")) {
					Button btn = new Button(parent, SWT.BORDER);
					btn.setText(data);
					ButtonElement btnEl = new ButtonElement(btn, data);
					ctlList.add(btnEl);
					viewer.setInput(ctlList);
				}
				parent.pack();
				parent.layout(true);
		
			}
			
			@Override
			public void dragOver(DropTargetEvent event) {
			}
			
			@Override
			public void dragOperationChanged(DropTargetEvent event) {
			}
			
			@Override
			public void dragLeave(DropTargetEvent event) {
			}
			
			@Override
			public void dragEnter(DropTargetEvent event) {
			}
		});
	}
	

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				CustomViewer.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				showMessage("Double-click detected on " + obj.toString());
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"RmCRCView", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
}