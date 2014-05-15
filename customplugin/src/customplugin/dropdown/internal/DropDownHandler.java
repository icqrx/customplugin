package customplugin.dropdown.internal;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;

/**
 * This class support make pull down button
 * @author QUOC NGUYEN
 * 
 */
public class DropDownHandler extends AbstractHandler implements IHandler {
	private static final String PARM_MSG = "customplugin.dropdown.msg";

	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		String msg = event.getParameter(PARM_MSG);

		if (msg == null) {
			System.out.println("No message");
		} else {
			System.out.println("msg: " + msg);
			System.out.println("Building...");

			Process p;
			// building app using cordova
			try {
				p = Runtime.getRuntime().exec("cmd.exe /C START E:\\run.bat");
				p.waitFor();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isHandled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

}
