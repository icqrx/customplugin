package customplugin.view;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

/**
 * This class support custom view (make tool box)
 * @author QUOC NGUYEN
 *
 */
public class CustomToolsBox extends ViewPart {

	public CustomToolsBox() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite colorComposite = new Composite(parent, SWT.NULL);
		colorComposite.setLayout(new GridLayout());
		GridData gd = new GridData();
		gd.horizontalSpan = 1;
		colorComposite.setLayoutData(gd);

		Group Layouts = new Group(colorComposite, SWT.NONE);
		Layouts.setBounds(10, 42, 202, 97);
		
		Button btnNewButton = new Button(Layouts, SWT.NONE);
		btnNewButton.setBounds(10, 21, 86, 25);
		btnNewButton.setText("Linear Layout");
		
		Button button = new Button(Layouts, SWT.NONE);
		button.setText("Frame Layout");
		button.setBounds(113, 21, 79, 25);
		
		Button button_1 = new Button(Layouts, SWT.NONE);
		button_1.setText("Relative Layout");
		button_1.setBounds(10, 62, 86, 25);
		
		Button button_2 = new Button(Layouts, SWT.NONE);
		button_2.setText("Table Layout");
		button_2.setBounds(113, 62, 79, 25);
		
		
		Label label = new Label(colorComposite, SWT.NONE);
		label.setText("Layouts");
		label.setBounds(10, 146, 55, 15);
		
		Group group = new Group(colorComposite, SWT.NONE);
		group.setBounds(10, 169, 202, 94);
		
		Button button_3 = new Button(group, SWT.NONE);
		button_3.setText("Label");
		button_3.setBounds(10, 21, 83, 25);
		
		Button button_4 = new Button(group, SWT.NONE);
		button_4.setText("TextView");
		button_4.setBounds(117, 21, 75, 25);
		
		Button button_5 = new Button(group, SWT.NONE);
		button_5.setText("Button");
		button_5.setBounds(10, 52, 83, 25);
		
		Button button_6 = new Button(group, SWT.NONE);
		button_6.setText("Combobox");
		button_6.setBounds(117, 52, 75, 25);
		
		Label lblLayouts = new Label(colorComposite, SWT.NONE);
		lblLayouts.setBounds(10, 21, 55, 15);
		lblLayouts.setText("Controls");
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
