package gui;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import db.Datenbank;
import objektorientierung.NutzerDaten;

public class AllUserToTheDB {

	public static boolean mainLogin = false;

	public static void userDataChange() {

		DefaultTableModel dtm = (DefaultTableModel) MainFrame.db_Inhalt.getModel();

		if (mainLogin == false) {

			try {

				dtm.setRowCount(0);
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

				while (rs.next()) {

					Object[] rowData = new Object[6];
					int i = 3;
					rowData[i] = rs.getObject(Datenbank.datenBankSpalte4);
					i = 4;
					rowData[i] = rs.getObject(Datenbank.datenBankSpalte5);

					int benutzerId = rs.getInt(Datenbank.datenBankSpalteName);
					ResultSet rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);
					while (rsn.next()) {

						if (rsn.getInt(Datenbank.datenBankSpalteId) == benutzerId) {

							i = 0;
							rowData[i] = " " + rsn.getObject(Datenbank.datenBankSpalteName);
							Datenbank.creatStatement().close();
							break;

						}

					}
					rsn.close();
					int skillId = rs.getInt(Datenbank.datenBankSpalte3);
					rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);
					while (rsn.next()) {

						if (rsn.getInt(Datenbank.datenBankSpalteId) == skillId) {

							int themenId = rsn.getInt(4);
							ResultSet rst = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromThema);
							while (rst.next()) {

								if (rst.getInt(Datenbank.datenBankSpalteId) == themenId) {

									i = 1;
									rowData[i] = rst.getObject(Datenbank.datenBankSpalteName);
									Datenbank.creatStatement().close();
									break;

								}
							}
						}

					}
					rsn.close();
					rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);
					while (rsn.next()) {

						if (rsn.getInt(Datenbank.datenBankSpalteId) == skillId) {

							i = 2;
							rowData[i] = rsn.getObject(Datenbank.datenBankSpalteName);
							Datenbank.creatStatement().close();
							break;

						}

					}
					int lustId = rs.getInt(5);
					rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromLust);
					while (rsn.next()) {

						if (rsn.getInt(Datenbank.datenBankSpalteId) == lustId) {

							i = 4;
							rowData[i] = rsn.getInt(Datenbank.datenBankSpalteId);
							Datenbank.creatStatement().close();

						}

					}
					int levelId = rs.getInt(6);
					rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkillLevel);
					while (rsn.next()) {

						if (rsn.getInt(Datenbank.datenBankSpalteId) == levelId) {

							i = 5;
							rowData[i] = rsn.getString(Datenbank.datenBankSpalteName);
							Datenbank.creatStatement().close();
							break;

						}

					}
					rsn.close();

					dtm.addRow(rowData);
					MainFrame.db_Inhalt.getColumn("Motivation").setCellRenderer(new LableRenderer());
				}
				Datenbank.creatStatement().close();
				rs.close();
			} catch (

			SQLException e) {
				e.printStackTrace();
			}
		} else {

			try {

				dtm.setRowCount(0);
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromZuordnung);

				while (rs.next()) {
					if (Datenbank.loadNameIdfromDB(NutzerDaten.benutzer) == rs.getInt(Datenbank.datenBankSpalteName)) {
						Object[] rowData = new Object[6];

						int i = 3;
						rowData[i] = rs.getObject(Datenbank.datenBankSpalte4);

						ResultSet rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);
						while (rsn.next()) {

							if (rsn.getString(Datenbank.datenBankSpalteName).equals(NutzerDaten.benutzer)) {

								i = 0;
								rowData[i] = " " + rsn.getObject(Datenbank.datenBankSpalteName);
								Datenbank.creatStatement().close();
								break;

							}

						}
						rsn.close();
						int skillId = rs.getInt(Datenbank.datenBankSpalte3);
						rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);
						while (rsn.next()) {

							if (rsn.getInt(Datenbank.datenBankSpalteId) == skillId) {

								int themenId = rsn.getInt(Datenbank.datenBankSpalte4);
								ResultSet rst = Datenbank.creatStatement()
										.executeQuery(Datenbank.querySelectAllFromThema);
								while (rst.next()) {

									if (rst.getInt(Datenbank.datenBankSpalteId) == themenId) {

										i = 1;
										rowData[i] = rst.getObject(2);
										Datenbank.creatStatement().close();
										break;

									}
								}
							}

						}
						rsn.close();
						rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);
						while (rsn.next()) {

							if (rsn.getInt(Datenbank.datenBankSpalteId) == skillId) {

								i = 2;
								rowData[i] = rsn.getObject(Datenbank.datenBankSpalteName);
								Datenbank.creatStatement().close();
								break;

							}

						}
						int lustId = rs.getInt(Datenbank.datenBankSpalte5);
						rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromLust);
						while (rsn.next()) {

							if (rsn.getInt(Datenbank.datenBankSpalteId) == lustId) {

								i = 4;
								rowData[i] = rsn.getInt(Datenbank.datenBankSpalteId);
								Datenbank.creatStatement().close();

							}

						}
						int levelId = rs.getInt(6);
						rsn = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkillLevel);
						while (rsn.next()) {

							if (rsn.getInt(Datenbank.datenBankSpalteId) == levelId) {

								i = 5;
								rowData[i] = rsn.getString(Datenbank.datenBankSpalteName);
								Datenbank.creatStatement().close();
								break;

							}

						}

						dtm.addRow(rowData);
						MainFrame.db_Inhalt.getColumn("Motivation").setCellRenderer(new LableRenderer());

					}

				}
				Datenbank.creatStatement().close();
				rs.close();

			} catch (

			SQLException e) {
				e.printStackTrace();
			}

		}
	}

}
