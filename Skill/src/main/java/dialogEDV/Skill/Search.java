package dialogEDV.Skill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Search implements ActionListener {

	private void filter(String query) {

		TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(Gui.model);
		Gui.tabel.setRowSorter(tr);

		tr.setRowFilter(RowFilter.regexFilter(query));

	}

	public void actionPerformed(ActionEvent arg0) {

		String query = Gui.search.getText();
		filter(query);

	}

}
