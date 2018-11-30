package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

class TabelSearch implements ActionListener, DocumentListener {

	private void filter(String query) {

		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(MainFrame.datenbankTabelModel);
		MainFrame.db_Inhalt.setRowSorter(tr);

		tr.setRowFilter(RowFilter.regexFilter(query));

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		String query = MainFrame.search.getText();
		filter(query);

		if (MainFrame.search.getText().length() > 0) {

			MainFrame.db_Inhalt.setEnabled(false);

		} else
			MainFrame.db_Inhalt.setEnabled(true);

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		String query = MainFrame.search.getText();
		filter(query);

		if (MainFrame.search.getText().length() > 0) {

			MainFrame.db_Inhalt.setEnabled(false);

		} else
			MainFrame.db_Inhalt.setEnabled(true);
	}

}