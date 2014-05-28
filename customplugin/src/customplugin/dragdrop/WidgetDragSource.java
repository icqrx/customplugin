package customplugin.dragdrop;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.part.ResourceTransfer;

public class WidgetDragSource implements DragSourceListener {
	private final Button viewer;

	public WidgetDragSource(Button viewer) {
		this.viewer = viewer;
		DragSource source = new DragSource(viewer, DND.DROP_COPY);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance(), ResourceTransfer.getInstance() });
		source.addDragListener(this);
	}

	@Override
	public void dragStart(DragSourceEvent event) {
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		Object[] objects = ((IStructuredSelection) viewer).toArray();
		if (ResourceTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = CopyHandler.asResources(objects);
		} else if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = CopyHandler.asText(objects);
		}
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		// TODO Auto-generated method stub

	}

}
