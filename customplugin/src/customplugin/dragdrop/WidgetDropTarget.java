package customplugin.dragdrop;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;

public class WidgetDropTarget extends DropTargetAdapter {
	public WidgetDropTarget(TableViewer viewer) {
		DropTarget target = new DropTarget(viewer.getControl(), DND.DROP_MOVE | DND.DROP_COPY);
		//target.setTransfer(new Transfer[] { ResourceTransfer.getInstance(), JavaUI.getJavaElementClipboardTransfer() });
		target.addDropListener(this);
	}
	@Override
	public void dragEnter(DropTargetEvent event) {
		// TODO Auto-generated method stub
		super.dragEnter(event);
	}
	@Override
	public void dragLeave(DropTargetEvent event) {
		// TODO Auto-generated method stub
		super.dragLeave(event);
	}
}
