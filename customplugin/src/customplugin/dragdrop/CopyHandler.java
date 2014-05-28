package customplugin.dragdrop;

import java.util.Collection;
import java.util.HashSet;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ResourceTransfer;

public class CopyHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Clipboard clipboard = new Clipboard(HandlerUtil.getActiveShell(event)
				.getDisplay());
		try {
			return execute(event, clipboard);
		} finally {
			clipboard.dispose();
		}
	}

	private Object execute(ExecutionEvent event, Clipboard clipboard) {
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection instanceof IStructuredSelection) {
			Object[] objects = ((IStructuredSelection) selection).toArray();
			if (objects.length > 0) {
				try {
					clipboard.setContents(new Object[] { asResources(objects),
							asText(objects), },
							new Transfer[] { ResourceTransfer.getInstance(),
									TextTransfer.getInstance(), });
				} catch (SWTError error) {
					// Copy to clipboard failed.
				}
			}
		}
		return null;
	}

	public static IResource[] asResources(Object[] objects) {
		Collection<IResource> resources = new HashSet<IResource>(objects.length);
		for (int i = 0; i < objects.length; i++) {
			Object each = objects[i];
			if (each instanceof IAdaptable) {
				IResource res = (IResource) ((IAdaptable) each)
						.getAdapter(IResource.class);
				if (res != null)
					resources.add(res);
			}
		}
		return resources.toArray(new IResource[resources.size()]);
	}
	
	public static String asText(Object[] objects) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < objects.length; i++) {
			Object each = objects[i];
			if (each instanceof Button) {
				buf.append((((Button) each).getText()));
			} else if (each != null)
				buf.append(each.toString());
			buf.append(System.getProperty("line.separator"));
		}
		return buf.toString();
	}

}
