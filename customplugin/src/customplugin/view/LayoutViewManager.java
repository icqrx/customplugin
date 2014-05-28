package customplugin.view;

import javax.inject.Inject;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.model.AdaptableList;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.ViewPart;

import customplugin.view.properties.ButtonElement;
/**
 * This class support custom layout view
 * @author QUOC NGUYEN
 *
 */
public class LayoutViewManager extends ViewPart {
	
	public static String LAYOUT_VIEW_ID = "customplugin.views.layoutView";
    private Label label;
    private Group grp1;
    private AdaptableList ctlList;
	private ListViewer viewer;
	public LayoutViewManager() {
		super();
	}
	
	@Override
	public void createPartControl(final Composite parent) {
		//final Composite container = new Composite(parent, SWT.NONE);
//		viewer = new ListViewer(parent, SWT.SINGLE );	
		RowLayout rowLayout = new RowLayout();
		parent.setLayout(rowLayout);
		createDropTarget(parent);
//		

//		Button btn = new Button(grp1,SWT.PUSH);
//		btn.setText("Hello");
//		Button btn1 = new Button(grp1,SWT.PUSH);
//		btn.setText("Hello111111111");
		
		// fill in the element		
//		AdaptableList ctlList = new AdaptableList();
//		ButtonElement btnEl = new ButtonElement(btn,"Button");
//		ButtonElement btnEl1 = new ButtonElement(btn1,"Button");
//		ctlList.add(btnEl);
//		ctlList.add(btnEl1);
		
//		viewer.setContentProvider(new BaseWorkbenchContentProvider());
//		viewer.setLabelProvider(new WorkbenchLabelProvider());			
//		viewer.setInput(ctlList);

		
	}
//	  private static final class SelProvider implements ISelectionProvider {
//	        protected IStructuredSelection projectSelection = StructuredSelection.EMPTY;
//	        private ISelectionProvider selProvider;
//
//	        public void addSelectionChangedListener(
//	                ISelectionChangedListener listener) {
//	            // do nothing
//	        }
//
//	        public ISelection getSelection() {
////	            return new StructuredSelection(new Button(, 0));
//	        }
//
//	        public void removeSelectionChangedListener(
//	                ISelectionChangedListener listener) {
//	            // do nothing
//	        }
//
//	        public void setSelection(ISelection selection) {
//	            // do nothing
//	        }
//	    }

	/**
	 * create drop target
	 * @param parent
	 */
	public void createDropTarget (final Composite parent) {
		//	// Allow data to be copied or moved to the drop target
		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
		DropTarget target = new DropTarget(parent, operations);
		
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
//					ButtonElement btnEl = new ButtonElement(btn,data);
//					ctlList.add(btnEl);
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
	@Override
	public Object getAdapter(Class adapter) {
		return super.getAdapter(adapter);
	}
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	@Override
	public void dispose() {
		super.dispose();
	}

}
