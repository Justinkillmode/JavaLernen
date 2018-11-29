package gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

class LableRenderer implements TableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable e, Object value, boolean arg2, boolean arg3, int arg4,
			int arg5) {

		TableColumn tc = MainFrame.db_Inhalt.getColumn("Motivation");
		tc.setMinWidth(100);
		tc.setMaxWidth(100);
		JLabel label = new JLabel("", SwingConstants.CENTER);
		label.setSize(40, 40);

		switch (value.toString()) {
		case "1":
			label.setIcon(Icons.icon1);
			break;
		case "2":
			label.setIcon(Icons.icon2);
			break;
		case "3":
			label.setIcon(Icons.icon3);
			break;
		case "4":
			label.setIcon(Icons.icon4);
			break;
		case "5":
			label.setIcon(Icons.icon5);
			break;
		case "6":
			label.setIcon(Icons.icon6);
			break;
		case "7":
			label.setIcon(Icons.icon7);
			break;
		}

		MainFrame.db_Inhalt.setRowHeight(40);
		return label;
	}

}