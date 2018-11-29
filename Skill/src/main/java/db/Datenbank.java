package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import domain.Benutzer;
import domain.Lust;
import domain.Skill;
import domain.Thema;
import domain.Zuordnung;
import gui.HashPassword;
import gui.MainFrame;
import gui.ScannerDatei;

public class Datenbank {

	static Connection conn;
	public static String hostname = ScannerDatei.hostName;
	public static String port = "3306";
	public static String dbname = ScannerDatei.dbName;
	public static String user = ScannerDatei.user;
	public static String password = ScannerDatei.password;
	public static String tabelleZuordnung = ScannerDatei.tabelleZuordnung;
	public static String tabelleBenutzer = ScannerDatei.tabelleBenutzer;
	public static String tabelleSkill = ScannerDatei.tabelleSkill;
	public static String tabelleThema = ScannerDatei.tabelleThema;
	public static String tabelleLust = ScannerDatei.tabelleLust;
	public static String tabelleSkillLevel = ScannerDatei.tabelleSkillLevel;
	public static final String querySelectAllFromSkill = "SELECT * FROM " + dbname + "." + tabelleSkill;
	public static final String querySelectAllFromLust = "SELECT * FROM " + dbname + "." + tabelleLust;
	public static final String querySelectAllFromZuordnung = "SELECT * FROM " + dbname + "." + tabelleZuordnung;
	public static final String queryBeschreibungUpdate = "update " + tabelleZuordnung
			+ " set Beschreibung = ? where ID = ?";
	public static final String querySkillUpdate = "update " + tabelleZuordnung + " set Skill = ? where ID = ?";
	public static final String querySkillLevelUpdate = "update " + tabelleZuordnung
			+ " set SkillLevel = ? where ID = ?";
	public static final String queryPasswordUpdate = "update " + tabelleBenutzer + " set passwort = ? where ID = ?";
	public static final String queryLustUpdate = "update " + tabelleZuordnung + " set Lust = ? where ID = ?";
	public static final String querySkillLevelNameUpdate = "update " + tabelleSkillLevel + " set Name = ? where ID = ?";
	public static final String querySelectAllFromBenutzer = "SELECT * FROM " + dbname + "." + tabelleBenutzer;
	public static final String querySelectAllFromThema = "SELECT * FROM " + dbname + "." + tabelleThema;
	public static final String querySelectAllFromSkillLevel = "SELECT * FROM " + dbname + "." + tabelleSkillLevel;
	public static final String deleteFromBenutzer = "DELETE FROM " + tabelleBenutzer + " WHERE ID = ?";
	public static final String deleteBenutzerFromZuordnung = "DELETE FROM " + tabelleZuordnung + " WHERE Name = ?";
	public static final String deleteFromSkill = "DELETE FROM " + tabelleSkill + " WHERE ID = ?";
	public static final String deleteSkillFromZuordnung = "DELETE FROM " + tabelleZuordnung + " WHERE Skill = ?";
	public static final String deleteFromThema = "DELETE FROM " + tabelleThema + " WHERE ID = ?";
	public static final String deleteThemaFromSkill = "DELETE FROM " + tabelleSkill + " WHERE Thema = ?";
	public static final String deleteFromLevel = "DELETE FROM " + tabelleSkillLevel + " WHERE ID = ?";
	public static final String deleteLevelFromZuordnung = "DELETE FROM " + tabelleZuordnung + " WHERE SkillLevel = ?";
	public static final int datenBankSpalteId = 1;
	public static final int datenBankSpalteName = 2;
	public static final int datenBankSpalte3 = 3;
	public static final int datenBankSpalte4 = 4;
	public static final int datenBankSpalte5 = 5;

	public static Benutzer loadNutzerfromDB(int id) {

		Benutzer benutzer = new Benutzer();
		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);

			while (rs.next()) {

				if (rs.getInt(datenBankSpalteId) == id) {
					benutzer.setName(rs.getString(datenBankSpalteName));
					benutzer.setId(id);
					break;
				}
			}
			rs.close();
			Datenbank.creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return benutzer;

	}

	public static Thema loadThemafromDB(int id) {

		Thema thema = new Thema();
		try {
			ResultSet rs = creatStatement().executeQuery(Datenbank.querySelectAllFromThema);

			while (rs.next()) {

				if (rs.getInt(datenBankSpalteId) == id) {
					thema.setName(rs.getString(datenBankSpalteName));
					thema.setId(id);
					rs.close();
					creatStatement().close();
					break;
				}
			}
			rs.close();
			creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return thema;

	}

	public static String loadSkillLevelfromDB(int id) {

		String thema = null;
		try {
			ResultSet rs = creatStatement().executeQuery(querySelectAllFromSkillLevel);

			while (rs.next()) {

				if (rs.getInt(datenBankSpalteId) == id) {
					thema = (rs.getString(datenBankSpalteName));
					rs.close();
					creatStatement().close();
					break;
				}
			}
			rs.close();
			creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return thema;

	}

	public static String loadLustfromDB(int id) {

		String lust = null;
		try {
			ResultSet rs = creatStatement().executeQuery(Datenbank.querySelectAllFromLust);

			while (rs.next()) {

				if (rs.getInt(datenBankSpalteId) == id) {
					lust = rs.getString(datenBankSpalteName);
					rs.close();
					creatStatement().close();
					break;
				}
			}
			rs.close();
			creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lust;

	}

	public static Integer loadThemaIdfromDB(String name) {

		Thema thema = new Thema();
		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromThema);

			while (rs.next()) {

				if (rs.getString(datenBankSpalteName).equals(name)) {
					thema.setName(name);
					thema.setId(rs.getInt(datenBankSpalteId));
					break;
				}
			}
			rs.close();
			Datenbank.creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return thema.getId();

	}

	public static Integer loadSkillLevelIdfromDB(String name) {

		int id = 0;
		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkillLevel);

			while (rs.next()) {

				if (rs.getString(datenBankSpalteName).equals(name)) {

					id = rs.getInt(datenBankSpalteId);
					break;
				}
			}
			rs.close();
			Datenbank.creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return id;

	}

	public static Integer loadLustIdfromDB(String name) {

		Lust lust = new Lust();

		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromLust);

			while (rs.next()) {

				if (rs.getString(datenBankSpalteName).equals(name)) {
					lust.setId(rs.getInt(datenBankSpalteId));
					break;
				}
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lust.getId();

	}

	public static Integer loadNameIdfromDB(String name) {

		Benutzer benutzer = new Benutzer();
		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);

			while (rs.next()) {

				if (rs.getString(datenBankSpalteName).equals(name)) {
					benutzer.setName(name);
					benutzer.setId(rs.getInt(datenBankSpalteId));
					break;
				}
			}
			rs.close();
			Datenbank.creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return benutzer.getId();

	}

	public static Skill loadSkillfromDB(int id) {

		Skill skill = new Skill();
		try {
			ResultSet rs = creatStatement().executeQuery(querySelectAllFromSkill);

			while (rs.next()) {

				if (rs.getInt(datenBankSpalteId) == id) {
					Thema thema = loadThemafromDB(rs.getInt(datenBankSpalte4));
					skill.setThema(thema);
					skill.setBechreibung(rs.getString(datenBankSpalte3));
					skill.setName(rs.getString(datenBankSpalteName));
					skill.setId(id);
					rs.close();
					creatStatement().close();
					break;
				}
			}
			rs.close();
			creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return skill;

	}

	public static Integer loadSkillIdfromDB(String name) {

		Skill skill = new Skill();
		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);

			while (rs.next()) {

				if (rs.getString(datenBankSpalteName).equals(name)) {
					Thema thema = loadThemafromDB(rs.getInt(datenBankSpalte4));
					skill.setThema(thema);
					skill.setBechreibung(rs.getString(datenBankSpalte3));
					skill.setName(name);
					skill.setId(rs.getInt(datenBankSpalteId));
					break;
				}
			}
			rs.close();
			Datenbank.creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return skill.getId();

	}

	public static ArrayList<Skill> loadAllSkills() {

		ArrayList<Skill> liste = new ArrayList<>();
		ArrayList<String> check = new ArrayList<>();

		ResultSet rs;
		try {
			rs = creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);
			while (rs.next()) {
				if (!check.contains(rs.getString(datenBankSpalteName))) {
					check.add(rs.getString(datenBankSpalteName));
					Skill skill = new Skill();
					Thema thema = loadThemafromDB(rs.getInt(datenBankSpalte4));
					skill.setThema(thema);
					skill.setBechreibung(rs.getString(datenBankSpalte3));
					skill.setName(rs.getString(datenBankSpalteName));
					skill.setId(rs.getInt(datenBankSpalteId));
					liste.add(skill);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<Benutzer> loadAllBenutzer() {

		ArrayList<Benutzer> liste = new ArrayList<>();
		ArrayList<String> check = new ArrayList<>();

		ResultSet rs;
		try {
			rs = creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);
			while (rs.next()) {
				if (!check.contains(rs.getString(datenBankSpalteName))) {
					check.add(rs.getString(datenBankSpalteName));
					Benutzer benutzer = new Benutzer();
					benutzer.setName(rs.getString(datenBankSpalteName));
					benutzer.setId(rs.getInt(datenBankSpalteId));
					liste.add(benutzer);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<String> loadAllSkillsForFiltering() {

		ArrayList<String> liste = new ArrayList<>();
		ArrayList<String> check = new ArrayList<>();

		ResultSet rs;
		try {
			rs = creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);
			while (rs.next()) {
				if (!check.contains(rs.getString(datenBankSpalteName))) {
					check.add(rs.getString(datenBankSpalteName));
					liste.add(rs.getString(datenBankSpalteName));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<Thema> loadAllThemen() {

		ArrayList<Thema> liste = new ArrayList<>();

		ResultSet rs;
		try {
			rs = creatStatement().executeQuery(querySelectAllFromThema);
			while (rs.next()) {
				Thema thema = new Thema();
				thema.setName(rs.getString(datenBankSpalteName));
				liste.add(thema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<String> loadAllSkillLevel() {

		ArrayList<String> liste = new ArrayList<>();

		ResultSet rs;
		try {
			rs = creatStatement().executeQuery(querySelectAllFromSkillLevel);
			while (rs.next()) {
				String thema;
				thema = (rs.getString(datenBankSpalteName));
				liste.add(thema);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}

	public static ArrayList<Integer> loadAllLust() {

		ArrayList<Integer> liste = new ArrayList<>();
		int lust;

		ResultSet rs;
		try {
			rs = creatStatement().executeQuery(querySelectAllFromLust);
			while (rs.next()) {
				lust = rs.getInt(datenBankSpalteId);
				liste.add(lust);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return liste;
	}

	public static Zuordnung loadZuordnungfromDB(int id) {

		Zuordnung zuordnung = new Zuordnung();
		try {
			ResultSet rs = creatStatement().executeQuery(querySelectAllFromZuordnung);

			while (rs.next()) {

				if (rs.getInt(datenBankSpalteId) == id) {
					Skill skill = loadSkillfromDB(rs.getInt(datenBankSpalteName));
					Skill beschreibung = loadSkillfromDB(rs.getInt(datenBankSpalte3));
					Benutzer benutzer = loadNutzerfromDB(rs.getInt(datenBankSpalteName));
					zuordnung.setSkill(skill);
					zuordnung.setId(id);
					zuordnung.setLust(rs.getString(datenBankSpalte5));
					zuordnung.setSkill(beschreibung);
					zuordnung.setBenutzer(benutzer);
					break;
				}
			}
			rs.close();
			creatStatement().close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return zuordnung;

	}

	public static void saveNewUser(String name, String password) {

		try {

			creatStatement().executeUpdate("INSERT INTO " + dbname + "." + tabelleBenutzer + "(Name, passwort) VALUES('"
					+ name + "','" + HashPassword.sha1(password) + "')");
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Nutzer wurde angelegt.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Neuer Nutzer konnte nicht erstellt werden.");
			e2.printStackTrace();

		}
	}

	public static void saveNewSkill(String name, String beschreibung, int thema) {

		try {
			tabelleSkill = "skill";
			creatStatement()
					.executeUpdate("INSERT INTO " + dbname + "." + tabelleSkill + "(Name, Beschreibung, Thema) VALUES('"
							+ " " + name + "','" + beschreibung + "','" + thema + "')");
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Skill wurde angelegt.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Skill konnte nicht erstellt werden.");
			e2.printStackTrace();

		}
	}

	public static void saveNewThema(String name) {

		try {

			creatStatement().executeUpdate(
					"INSERT INTO " + dbname + "." + tabelleThema + "(Name) VALUES('" + " " + name + "')");
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Thema wurde angelegt.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Thema konnte nicht erstellt werden.");
			e2.printStackTrace();

		}
	}

	public static void saveNewSkillLevel(String name) {

		try {

			creatStatement().executeUpdate(
					"INSERT INTO " + dbname + "." + tabelleSkillLevel + "(Name) VALUES('" + " " + name + "')");
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Level wurde angelegt.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Level konnte nicht erstellt werden.");
			e2.printStackTrace();

		}
	}

	public static void deleteBenutzer(String name) {

		try {
			PreparedStatement preparedStmt = getConnection().prepareStatement(deleteBenutzerFromZuordnung);
			preparedStmt.setInt(1, loadNameIdfromDB(name));
			preparedStmt.executeUpdate();
			preparedStmt = getConnection().prepareStatement(deleteFromBenutzer);
			preparedStmt.setInt(1, loadNameIdfromDB(name));
			preparedStmt.executeUpdate();
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Benutzer wurde Gelöscht.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Benutzer konnte nicht gelöscht werden.");
			e2.printStackTrace();

		}
	}

	public static void deleteSkill(String name) {

		try {
			PreparedStatement preparedStmt = getConnection().prepareStatement(deleteSkillFromZuordnung);
			preparedStmt.setInt(1, loadSkillIdfromDB(name));
			preparedStmt.executeUpdate();
			preparedStmt = getConnection().prepareStatement(deleteFromSkill);
			preparedStmt.setInt(1, loadSkillIdfromDB(name));
			preparedStmt.executeUpdate();
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Skill wurde Gelöscht.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Skill konnte nicht gelöscht werden.");
			e2.printStackTrace();

		}
	}

	public static void deleteThema(String name) {

		try {
			PreparedStatement preparedStmt = getConnection().prepareStatement(deleteThemaFromSkill);
			preparedStmt.setInt(1, loadThemaIdfromDB(name));
			preparedStmt.executeUpdate();
			preparedStmt = getConnection().prepareStatement(deleteFromThema);
			preparedStmt.setInt(1, loadThemaIdfromDB(name));
			preparedStmt.executeUpdate();
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Thema wurde Gelöscht.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Thema konnte nicht gelöscht werden.");
			e2.printStackTrace();

		}
	}

	public static void deleteLevel(String name) {

		try {
			PreparedStatement preparedStmt = getConnection().prepareStatement(deleteLevelFromZuordnung);
			preparedStmt.setInt(1, loadSkillLevelIdfromDB(name));
			preparedStmt.executeUpdate();
			preparedStmt = getConnection().prepareStatement(deleteFromLevel);
			preparedStmt.setInt(1, loadSkillLevelIdfromDB(name));
			preparedStmt.executeUpdate();
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Level wurde Gelöscht.");

		} catch (Exception e2) {

			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Level konnte nicht gelöscht werden.");
			e2.printStackTrace();

		}
	}

	public static Connection getConnection() {

		if (conn == null) {
			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
			try {
				conn = DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return conn;

	}

	public static Statement creatStatement() {

		Statement stm = null;

		try {
			stm = getConnection().createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(MainFrame.mainFrame, "Auf die DatenBank konnte nicht zugegriffen werden.");
			System.exit(0);
		}

		return stm;
	}

	public static PreparedStatement preparedStatement() {

		PreparedStatement preparedStmt = null;

		try {
			preparedStmt = Datenbank.getConnection().prepareStatement(querySkillUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return preparedStmt;

	}

	public static void insterToDB(String name, String skill, String beschreibung, int lust, String level) {

		try {
			Datenbank.creatStatement()
					.executeUpdate("INSERT INTO " + dbname + "." + tabelleZuordnung
							+ "(Name, Skill, Beschreibung, Lust, SkillLevel) VALUES('"
							+ Datenbank.loadNameIdfromDB(name) + "','" + Datenbank.loadSkillIdfromDB(skill) + "','"
							+ " " + beschreibung + "','" + lust + "','" + loadSkillLevelIdfromDB(level) + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}