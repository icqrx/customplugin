package customplugin.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import customplugin.uitls.SWTResourceManager;

/**
 * This class support custom view (make tool box)
 * 
 * @author QUOC NGUYEN
 * 
 */
public class CustomToolsBox extends ViewPart {
	Label btnTextview;
	Button button;
	DragSource ds;
	public CustomToolsBox() {
		// TODO Auto-generated constructor stub
	}

	public void setStyle(Device mDevice) {

	}

	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		Label lblNewLabel = new Label(container, SWT.BORDER | SWT.WRAP
				| SWT.SHADOW_NONE);
		lblNewLabel.setBackground(SWTResourceManager
				.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD
				| SWT.ITALIC));
		lblNewLabel.setBounds(0, 0, 594, 22);
		lblNewLabel.setText("Form Widgets");
		{
			Label label = new Label(container, SWT.BORDER | SWT.WRAP
					| SWT.SHADOW_NONE);
			label.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
			label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD
					| SWT.ITALIC));
			label.setText("Layouts");
			label.setBounds(0, 100, 594, 22);
		}
		{
			Label label = new Label(container, SWT.BORDER | SWT.WRAP
					| SWT.SHADOW_NONE);
			label.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
			label.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD
					| SWT.ITALIC));
			label.setText("Composite");
			label.setBounds(0, 190, 594, 22);
		}

		btnTextview = new Label(container, SWT.NONE);
		btnTextview.setBounds(10, 28, 75, 25);
		btnTextview.setText("TextView");
		
		button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		button.setText("Button");
		button.setBounds(91, 28, 75, 25);
		
		{
			Combo button_1 = new Combo(container, SWT.NONE);
			button_1.setEnabled(true);
			button_1.setText("Combobox");
			button_1.setBounds(10, 59, 75, 25);
		}
		{
			Button button_1 = new Button(container, SWT.RADIO);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("Radio");
			button_1.setBounds(91, 59, 75, 25);
		}
		{
			Button button_1 = new Button(container, SWT.NONE);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("FrameLayout");
			button_1.setBounds(10, 128, 102, 25);
		}
		{
			Button button_1 = new Button(container, SWT.NONE);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("TableLayout");
			button_1.setBounds(118, 128, 93, 25);
		}
		{
			Button button_1 = new Button(container, SWT.NONE);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("RelativeLayout");
			button_1.setBounds(118, 159, 93, 25);
		}
		{
			Button button_1 = new Button(container, SWT.NONE);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("LinearLayout");
			button_1.setBounds(10, 159, 102, 25);
		}
		{
			Button button_1 = new Button(container, SWT.NONE);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("ListView");
			button_1.setBounds(10, 222, 102, 25);
		}
		{
			Button button_1 = new Button(container, SWT.NONE);
			button_1.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
				}
			});
			button_1.setText("WebView");
			button_1.setBounds(118, 222, 93, 25);
		}
		// set drag listener for widget
		createDragSource(btnTextview.getText());
		createDragSource(button.getText());
	}
	
	/**
	 * 
	 */
	public void createDragSource (final String type) {
	
		// Allow data to be copied or moved from the drag source
				int operationsTranfer = DND.DROP_MOVE | DND.DROP_COPY;
				if (type.equalsIgnoreCase("TextView")) {
					 ds = new DragSource(btnTextview, operationsTranfer);
				} else {
					if(type.equalsIgnoreCase("Button")) {
						ds = new DragSource(button, operationsTranfer);
					}
				
				}
				
				// Provide data in Text format
				Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
				ds.setTransfer(types);
				
				ds.addDragListener(new DragSourceListener() {

					@Override
					public void dragStart(DragSourceEvent event) {
						// Only start the drag if there is actually text in the
						// label - this text will be what is dropped on the target
						if (button.getText().length() == 0) {
							event.doit = false;
						}
						System.out.print("DRAG START");
					}

					@Override
					public void dragSetData(DragSourceEvent event) {
						// Provide the data of the requested type.
						if (type.equalsIgnoreCase("TextView")) {
							event.data = btnTextview.getText();
						} else {
							if(type.equalsIgnoreCase("Button")) {
								event.data = button.getText();
							}
						
						}
						
					}

					@Override
					public void dragFinished(DragSourceEvent event) {
						// If a move operation has been performed, remove the data
						// from the source
//						if (event.detail == DND.DROP_MOVE) {
//							btnTextview.setText("");
//						}
					}
					
				});
	}
	
	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
