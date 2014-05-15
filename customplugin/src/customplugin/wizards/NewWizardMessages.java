package customplugin.wizards;

import org.eclipse.osgi.util.NLS;
/**
 * 
 * @author QUOC NGUYEN
 *
 */
public class NewWizardMessages extends NLS {
	private static final String BUNDLE_NAME = "customplugin.wizards.messages"; //$NON-NLS-1$
	public static String Create_something_custom;
	public static String Custom_Plugin_Project;
	public static String CustomProjectNewWizard_2;
	public static String CustomProjectNewWizard_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, NewWizardMessages.class);
	}

	private NewWizardMessages() {
	}
}
