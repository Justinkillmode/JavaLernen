package dialogEDV.Skill;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class btnAction implements ActionListener {

	final String hostname = "localhost";
	final String port = "3306";
	final String dbname = "dialogEDV";
	final String user = "root";
	final String password = "";

	Connection conn = null;

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == Gui.send) {

			try {
				String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
				conn = DriverManager.getConnection(url, user, password);
				Statement stm = conn.createStatement();
				String name = (String) Gui.user.getValueAt(0, 0);
				String skill = (String) Gui.user.getValueAt(0, 1);
				String lust = (String) Gui.user.getValueAt(0, 2);
				String beschreibung = (String) Gui.user.getValueAt(0, 3);
				if (name.length() < 1 || skill.length() < 1 || lust.length() < 1 || beschreibung.length() < 1)
					JOptionPane.showMessageDialog(Gui.frame, "Bitte geben sie was ein");
				else {
					stm.executeUpdate("INSERT INTO dialogEDV.mitarbeiter(Name, Skill, Beschreibung, Lust) VALUES('"
							+ name + "','" + skill + "','" + lust + "','" + beschreibung + "')");

					JOptionPane.showMessageDialog(Gui.frame, "Skills gesendet.");
					DatenbankInfo.inPut();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(Gui.frame, "Skills konnten nicht gesendet werden.");
				e2.printStackTrace();
			}
		}

	}

}
