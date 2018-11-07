package dialogEDV.Skill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class DatenbankInfo {

	static final String hostname = "localhost";
	static final String port = "3306";
	static final String dbname = "dialogEDV";
	static final String user = "root";
	static final String password = "";

	static Connection conn = null;

	public static void inPut() {

		DefaultTableModel dtm = (DefaultTableModel) Gui.tabel.getModel();

		try {
			dtm.setRowCount(0);
			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
			conn = DriverManager.getConnection(url, user, password);
			String query = "SELECT Name, Skill, Beschreibung, Lust FROM " + "dialogEDV.mitarbeiter";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				Object[] rowData = new Object[4];
				for (int i = 0; i < rowData.length; ++i) {
					rowData[i] = rs.getObject(i + 1);
				}
				dtm.addRow(rowData);
			}
			dtm.fireTableDataChanged();
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
