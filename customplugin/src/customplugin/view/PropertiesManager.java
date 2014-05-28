package customplugin.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.ViewPart;

import customplugin.uitls.SWTResourceManager;

public class PropertiesManager extends ViewPart {
	private Table table;
	public PropertiesManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(0, 23, 594, 169);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Propertiy");
		{
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText("Width");
		}
		{
			TableItem tableItem = new TableItem(table, SWT.NONE);
			tableItem.setText("Height");
		}
		
		
		TableColumn tblclmnValue = new TableColumn(table, SWT.NONE);
		tblclmnValue.setWidth(488);
		tblclmnValue.setText("Value");
		
					Label label_1 = new Label(container, SWT.BORDER | SWT.SHADOW_NONE);
					label_1.setBounds(0, 0, 594, 22);
					label_1.setBackground(SWTResourceManager
							.getColor(SWT.COLOR_LIST_SELECTION));
					label_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD
							| SWT.ITALIC));
					label_1.setText("Properties");

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
