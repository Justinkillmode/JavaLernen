package gui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultCellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import db.Datenbank;
import objektorientierung.NutzerDaten;

public class MainTableActionListener implements CellEditorListener, TableModelListener {

	@Override
	public void editingCanceled(ChangeEvent arg0) {

	}

	@Override
	public void editingStopped(ChangeEvent e) {

		if (e.getSource() == MainFrame.editorTabelThema) {
			try {

				MainFrame.comboBox2bTabelSkill = MainFrame.getSkillComboBox(0);
				MainFrame.editorTabelSkill = new DefaultCellEditor(MainFrame.comboBox2bTabelSkill);
				MainFrame.db_Inhalt.getColumnModel().getColumn(2).setCellEditor(MainFrame.editorTabelSkill);
				MainFrame.editorTabelSkill.addCellEditorListener(new MainTableActionListener());

				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

				int spalte = 0;
				while (rs.next()) {

					if (Datenbank.loadSkillfromDB(rs.getInt(3)).getThema().toString()
							.equals(MainFrame.db_Inhalt.getValueAt(spalte, 1).toString())
							&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
						spalte++;

					} else if (!Datenbank.loadSkillfromDB(rs.getInt(3)).getThema().toString()
							.equals(MainFrame.db_Inhalt.getValueAt(spalte, 1).toString())
							&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {

						MainFrame.comboBox2bTabelSkill = MainFrame.getSkillComboBox(
								Datenbank.loadThemaIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 1).toString()));
						MainFrame.editorTabelSkill = new DefaultCellEditor(MainFrame.comboBox2bTabelSkill);
						MainFrame.db_Inhalt.getColumnModel().getColumn(2).setCellEditor(MainFrame.editorTabelSkill);
						MainFrame.editorTabelSkill.addCellEditorListener(new MainTableActionListener());
						Datenbank.creatStatement().close();
						rs.close();
						break;

					}
					if (MainFrame.db_Inhalt.getRowCount() <= spalte) {

						break;
					}
				}
				Datenbank.creatStatement().close();
				rs.close();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == MainFrame.editorTabelSkill) {

			try {
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

				int spalte = 0;
				while (rs.next()) {

					try {
						if (rs.getInt(Datenbank.datenBankSpalte3) == Datenbank
								.loadSkillIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 2).toString())
								&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
							spalte++;
						} else if (rs.getInt(Datenbank.datenBankSpalte3) != Datenbank
								.loadSkillIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 2).toString())
								&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
							PreparedStatement preparedStmt = Datenbank.getConnection()
									.prepareStatement(Datenbank.querySkillUpdate);

							preparedStmt.setInt(1,
									Datenbank.loadSkillIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 2).toString()));
							preparedStmt.setInt(2, rs.getInt(Datenbank.datenBankSpalteId));
							preparedStmt.executeUpdate();

							AllUserToTheDB.userDataChange();
							Datenbank.creatStatement().close();
							rs.close();
							break;
						}
						if (MainFrame.db_Inhalt.getRowCount() <= spalte) {

							break;
						}
					} catch (Exception e2) {

					}

				}
				Datenbank.creatStatement().close();
				rs.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == MainFrame.editorTabelLust)

		{

			try {
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

				int spalte = 0;
				while (rs.next()) {

					if (rs.getInt(Datenbank.datenBankSpalte5) == Integer
							.parseInt(MainFrame.db_Inhalt.getValueAt(spalte, 4).toString())
							&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
						spalte++;
					} else if (rs.getInt(Datenbank.datenBankSpalte5) != Integer
							.parseInt(MainFrame.db_Inhalt.getValueAt(spalte, 4).toString())
							&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
						PreparedStatement preparedStmt = Datenbank.getConnection()
								.prepareStatement(Datenbank.queryLustUpdate);
						preparedStmt.setInt(1, Integer.parseInt(MainFrame.db_Inhalt.getValueAt(spalte, 4).toString()));
						preparedStmt.setInt(2, rs.getInt(Datenbank.datenBankSpalteId));
						preparedStmt.executeUpdate();
						AllUserToTheDB.userDataChange();
						break;

					}
					if (MainFrame.db_Inhalt.getRowCount() <= spalte) {

						break;
					}

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == MainFrame.editorTabelLevel)

		{

			try {
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

				int spalte = 0;
				while (rs.next()) {

					if (MainFrame.db_Inhalt.getValueAt(spalte, 5) != null) {
						if (rs.getInt(6) == Datenbank
								.loadSkillLevelIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 5).toString())
								&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
							spalte++;
						} else if (rs.getInt(6) != Datenbank
								.loadSkillLevelIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 5).toString())
								&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
							PreparedStatement preparedStmt = Datenbank.getConnection()
									.prepareStatement(Datenbank.querySkillLevelUpdate);
							preparedStmt.setInt(1, Datenbank
									.loadSkillLevelIdfromDB(MainFrame.db_Inhalt.getValueAt(spalte, 5).toString()));
							preparedStmt.setInt(2, rs.getInt(Datenbank.datenBankSpalteId));
							preparedStmt.executeUpdate();
							AllUserToTheDB.userDataChange();
							break;

						}
						if (MainFrame.db_Inhalt.getRowCount() <= spalte) {

							break;
						}
					}
				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}

	}

	@Override
	public void tableChanged(TableModelEvent e) {

		if (e.getType() == TableModelEvent.UPDATE) {

			if (e.getColumn() == 3) {

				try {
					ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

					int spalte = 0;
					while (rs.next()) {

						if (rs.getString(Datenbank.datenBankSpalte4)
								.equals(MainFrame.db_Inhalt.getValueAt(spalte, 3).toString())
								&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
							spalte++;
						} else if (!rs.getString(Datenbank.datenBankSpalte4)
								.equals(MainFrame.db_Inhalt.getValueAt(spalte, 3).toString())
								&& rs.getInt(2) == Datenbank.loadNameIdfromDB(NutzerDaten.benutzer)) {
							PreparedStatement preparedStmt = Datenbank.getConnection()
									.prepareStatement(Datenbank.queryBeschreibungUpdate);
							preparedStmt.setString(1, MainFrame.db_Inhalt.getValueAt(spalte, 3).toString());
							preparedStmt.setInt(2, rs.getInt(Datenbank.datenBankSpalteId));
							preparedStmt.executeUpdate();
							break;

						}
						if (MainFrame.db_Inhalt.getRowCount() <= spalte) {

							break;
						}

					}
					Datenbank.creatStatement().close();
				} catch (SQLException e1) {
				}

			}

		}

	}

}