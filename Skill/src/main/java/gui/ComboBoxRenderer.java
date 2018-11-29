package gui;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

@SuppressWarnings("rawtypes")
public class ComboBoxRenderer implements ListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

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

		return label;
	}

}