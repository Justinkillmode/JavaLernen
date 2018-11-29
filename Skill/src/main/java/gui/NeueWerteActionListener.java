package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import db.Datenbank;
import objektorientierung.NutzerDaten;

public class NeueWerteActionListener implements ActionListener {
	public JButton btnSkillLevelNameAendern = new JButton("Erstellen");
	public JButton btnSkill = new JButton("Erstellen");
	public JFrame frameSkillLevelName = new JFrame();
	public JFrame frameSkillName = new JFrame();
	@SuppressWarnings("rawtypes")
	public JComboBox comboBoxThema, comboBoxSkillLevel;
	public boolean mehrSkillsEinfuegen = false;
	public JTextField tfSkillLevelName = new JTextField();
	public JTextField tfSkillName = new JTextField();
	public JTextArea taSkillBeschreibung = new JTextArea(5, 20);
	int wiederholungsStop = 0;

	JFrame passwordWechselnFrame = new JFrame("Password Ändern");
	JPasswordField altesPassword = new JPasswordField();
	JPasswordField neuesPassword = new JPasswordField();
	JLabel lblAltesPassword, lblNeuesPassword;
	JButton btnSenden = new JButton("Ändern");

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {

		tfSkillLevelName.setBounds(40, 90, 200, 20);
		tfSkillName.setBounds(40, 90, 200, 20);

		if (e.getSource() == MainFrame.neuerNutzer) {

			final JFrame loginFrame = new JFrame("Benutzer erstellen");
			final JTextField tfBenutzername = new JTextField();
			JLabel lblBenutzername = new JLabel("Benutzername");
			JLabel lblPassword = new JLabel("Password");
			JLabel lblPasswordWiederholen = new JLabel("Password Wiederholen");
			JButton btnLogin = new JButton("Erstellen");
			btnLogin.setBackground(Color.decode("#CCD9FD"));
			btnLogin.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			final JPasswordField passwordField = new JPasswordField(10);
			final JPasswordField passwordFieldWiederholen = new JPasswordField(10);
			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			loginFrame.setIconImage(icon.getImage());

			loginFrame.setLayout(null);
			loginFrame.setVisible(true);
			loginFrame.setSize(560, 320);
			loginFrame.setResizable(false);
			loginFrame.setLocationRelativeTo(MainFrame.mainFrame);

			tfBenutzername.setBounds(210, 40, 300, 30);
			lblBenutzername.setBounds(40, 40, 300, 30);
			passwordField.setBounds(210, 90, 300, 30);
			lblPassword.setBounds(40, 90, 100, 30);
			passwordFieldWiederholen.setBounds(210, 140, 300, 30);
			lblPasswordWiederholen.setBounds(40, 140, 300, 30);
			btnLogin.setBounds(210, 200, 100, 50);

			loginFrame.add(lblBenutzername);
			loginFrame.add(passwordField);
			loginFrame.add(tfBenutzername);
			loginFrame.add(lblPassword);
			loginFrame.add(btnLogin);
			loginFrame.add(passwordFieldWiederholen);
			loginFrame.add(lblPasswordWiederholen);

			tfBenutzername.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						char[] c = passwordField.getPassword();
						char[] cWiederholen = passwordFieldWiederholen.getPassword();
						String userPasswort = String.valueOf(c);
						String userPasswortWiederholen = String.valueOf(cWiederholen);
						String userName = tfBenutzername.getText();

						if (userName.length() > 1 || userPasswort.length() > 1
								|| userPasswortWiederholen.length() > 1) {

							if (userPasswort.equals(userPasswortWiederholen)) {
								Datenbank.saveNewUser(userName, userPasswort);
								loginFrame.dispose();
							} else {

								JOptionPane.showMessageDialog(loginFrame, "Passwörter stimmen nicht überein");

							}
						} else {

							JOptionPane.showMessageDialog(null, "Es fehlen noch benötigte Daten.");
						}

					} catch (

					Exception e2) {
						e2.printStackTrace();
					}
					tfBenutzername.removeActionListener(this);
				}
			});

			passwordFieldWiederholen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					try {
						char[] c = passwordField.getPassword();
						char[] cWiederholen = passwordFieldWiederholen.getPassword();
						String userPasswort = String.valueOf(c);
						String userPasswortWiederholen = String.valueOf(cWiederholen);
						String userName = tfBenutzername.getText();

						if (userName.length() > 1 || userPasswort.length() > 1
								|| userPasswortWiederholen.length() > 1) {

							if (userPasswort.equals(userPasswortWiederholen)) {
								Datenbank.saveNewUser(userName, userPasswort);
								loginFrame.dispose();
							} else {

								JOptionPane.showMessageDialog(loginFrame, "Passwörter stimmen nicht überein");

							}
						} else {

							JOptionPane.showMessageDialog(null, "Es fehlen noch benötigte Daten.");
						}

					} catch (

					Exception e2) {
						e2.printStackTrace();
					}
					passwordFieldWiederholen.removeActionListener(this);
				}
			});

			passwordField.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					try {
						char[] c = passwordField.getPassword();
						char[] cWiederholen = passwordFieldWiederholen.getPassword();
						String userPasswort = String.valueOf(c);
						String userPasswortWiederholen = String.valueOf(cWiederholen);
						String userName = tfBenutzername.getText();

						if (userName.length() > 1 || userPasswort.length() > 1
								|| userPasswortWiederholen.length() > 1) {

							if (userPasswort.equals(userPasswortWiederholen)) {
								Datenbank.saveNewUser(userName, userPasswort);
								loginFrame.dispose();
							} else {

								JOptionPane.showMessageDialog(loginFrame, "Passwörter stimmen nicht überein");

							}
						} else {

							JOptionPane.showMessageDialog(null, "Es fehlen noch benötigte Daten.");
						}

					} catch (

					Exception e2) {
						e2.printStackTrace();
					}
					passwordField.removeActionListener(this);
				}
			});

			btnLogin.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						char[] c = passwordField.getPassword();
						char[] cWiederholen = passwordFieldWiederholen.getPassword();
						String userPasswort = String.valueOf(c);
						String userPasswortWiederholen = String.valueOf(cWiederholen);
						String userName = tfBenutzername.getText();

						if (userName.length() > 1 || userPasswort.length() > 1
								|| userPasswortWiederholen.length() > 1) {

							if (userPasswort.equals(userPasswortWiederholen)) {
								Datenbank.saveNewUser(userName, userPasswort);
								loginFrame.dispose();
							} else {

								JOptionPane.showMessageDialog(loginFrame, "Passwörter stimmen nicht überein");

							}
						} else {

							JOptionPane.showMessageDialog(null, "Es fehlen noch benötigte Daten.");
						}

					} catch (

					Exception e2) {
						e2.printStackTrace();
					}
					btnLogin.removeActionListener(this);
				}
			});

		}
		if (e.getSource() == MainFrame.skill)

		{

			JLabel lblSkill, lblThema, lblBeschreibung;

			JCheckBox mehrSkillsEinfügen = new JCheckBox("Weitere Skills einfügen?");

			comboBoxThema = MainFrame.getThemaComboBox(0);
			comboBoxThema.setBounds(40, 30, 200, 40);

			mehrSkillsEinfügen.setBounds(40, 250, 200, 40);
			mehrSkillsEinfügen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (mehrSkillsEinfuegen == false) {

						mehrSkillsEinfuegen = true;

					} else {

						mehrSkillsEinfuegen = false;

					}
					mehrSkillsEinfügen.removeActionListener(this);
				}
			});

			lblSkill = new JLabel("Skill Name");
			lblSkill.setBounds(40, 70, 200, 20);

			lblThema = new JLabel("Thema");
			lblThema.setBounds(40, 10, 200, 20);

			lblBeschreibung = new JLabel("Beschreibung");
			lblBeschreibung.setBounds(40, 120, 200, 20);

			taSkillBeschreibung.setBounds(40, 140, 200, 100);
			taSkillBeschreibung.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			taSkillBeschreibung.setLineWrap(true);
			taSkillBeschreibung.setWrapStyleWord(true);

			btnSkill.setBounds(90, 300, 100, 50);
			btnSkill.setBackground(Color.decode("#CCD9FD"));
			btnSkill.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			btnSkill.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (tfSkillName.getText().length() < 1 || taSkillBeschreibung.getText().length() < 1) {

						if (wiederholungsStop == 0) {
							JOptionPane.showMessageDialog(frameSkillLevelName,
									"Bitte geben sie Skill Namen und eine Beschreibung ein.");
						}
					} else if (tfSkillName.getText().length() > 0 && taSkillBeschreibung.getText().length() > 0) {

						Datenbank.saveNewSkill(tfSkillName.getText(), taSkillBeschreibung.getText(),
								Datenbank.loadThemaIdfromDB(comboBoxThema.getSelectedItem().toString()));
						taSkillBeschreibung.setText("");
						tfSkillName.setText("");

						NeuerEintrag.comboBoxSkill.removeAllItems();
						ArrayList<String> ar = new ArrayList<>();

						String sThema = NeuerEintrag.comboBoxThema.getSelectedItem().toString();
						int id = Datenbank.loadThemaIdfromDB(sThema);
						try {
							ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);

							while (rs.next()) {

								if (rs.getInt(Datenbank.datenBankSpalte4) == id) {
									String name = Datenbank.loadSkillfromDB(rs.getInt(Datenbank.datenBankSpalteId))
											.toString();

									if (!ar.contains(name)) {
										ar.add(name);
									}
								}
							}
							@SuppressWarnings("rawtypes")
							DefaultComboBoxModel model = new DefaultComboBoxModel(ar.toArray());
							NeuerEintrag.comboBoxSkill.setModel(model);
							rs.close();
							Datenbank.creatStatement().close();
							if (!mehrSkillsEinfuegen) {

								frameSkillName.dispose();
								wiederholungsStop++;
								btnSkill.removeActionListener(this);

							}
						} catch (SQLException e) {
							e.printStackTrace();
						}

					} else {
						wiederholungsStop = 0;
					}
				}
			});

			frameSkillName.setSize(300, 400);
			frameSkillName.setVisible(true);
			frameSkillName.setLocationRelativeTo(MainFrame.mainFrame);
			frameSkillName.setResizable(false);
			frameSkillName.setLayout(null);
			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			frameSkillName.setIconImage(icon.getImage());

			frameSkillName.getContentPane().add(comboBoxThema);
			frameSkillName.getContentPane().add(tfSkillName);
			frameSkillName.getContentPane().add(lblThema);
			frameSkillName.getContentPane().add(lblSkill);
			frameSkillName.getContentPane().add(lblBeschreibung);
			frameSkillName.getContentPane().add(taSkillBeschreibung);
			frameSkillName.getContentPane().add(btnSkill);
			frameSkillName.getContentPane().add(mehrSkillsEinfügen);

		}
		if (e.getSource() == MainFrame.thema)

		{

			boolean checkName = false;
			while (checkName == false) {
				String neuesThema = JOptionPane.showInputDialog(MainFrame.mainFrame,
						"Geben sie den Names des Themas ein.");

				if (neuesThema != null) {
					if (neuesThema.length() < 1) {
						JOptionPane.showMessageDialog(MainFrame.mainFrame, "Kein Themenname angegeben.");
					} else {
						checkName = true;
						Datenbank.saveNewThema(neuesThema);

						NeuerEintrag.comboBoxThema.removeAllItems();
						ArrayList<String> ar = new ArrayList<>();

						try {
							ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromThema);

							while (rs.next()) {

								String thenaName = Datenbank.loadThemafromDB(rs.getInt(Datenbank.datenBankSpalteId))
										.toString();

								if (!ar.contains(thenaName)) {
									ar.add(thenaName);
								}
							}
							@SuppressWarnings("rawtypes")
							DefaultComboBoxModel model = new DefaultComboBoxModel(ar.toArray());
							NeuerEintrag.comboBoxThema.setModel(model);
							rs.close();
							Datenbank.creatStatement().close();
						} catch (SQLException ex) {
							ex.printStackTrace();
						}

					}
				} else {
					checkName = true;
				}
			}
		}
		if (e.getSource() == MainFrame.wechseln) {

			Anmelden.login();

		}
		if (e.getSource() == MainFrame.lustHinzufuegen) {

			boolean name = false;
			while (name == false) {
				String neueLust = JOptionPane.showInputDialog(MainFrame.mainFrame, "Geben sie den Names ein.");

				if (neueLust != null) {
					if (neueLust.length() < 1) {
						JOptionPane.showMessageDialog(MainFrame.mainFrame, "Kein Level angegeben.");
					} else {
						name = true;
						Datenbank.saveNewSkillLevel(neueLust);

					}
				} else {
					name = true;
				}

			}
		}
		if (e.getSource() == MainFrame.lustNamenAendern) {

			JLabel lblSkillLevel, lblLevel;

			JCheckBox mehrSkillLevelNamenÄndern = new JCheckBox("Weitere Namen ändern?");

			comboBoxSkillLevel = MainFrame.getSkillLevelComboBox(0);
			lblLevel = new JLabel("Name Auswählen");
			lblLevel.setBounds(40, 0, 200, 20);
			comboBoxSkillLevel.setBounds(40, 20, 200, 40);

			mehrSkillLevelNamenÄndern.setBounds(40, 110, 200, 40);
			mehrSkillLevelNamenÄndern.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (mehrSkillsEinfuegen == false) {

						mehrSkillsEinfuegen = true;

					} else {

						mehrSkillsEinfuegen = false;

					}

				}
			});

			lblSkillLevel = new JLabel("Neuer Level Name");
			lblSkillLevel.setBounds(40, 70, 200, 20);

			tfSkillLevelName.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (tfSkillLevelName.getText().length() < 1 && wiederholungsStop == 0) {

						JOptionPane.showMessageDialog(frameSkillLevelName, "Bitte geben sie noch ein Skill Level ein.");

					} else if (tfSkillLevelName.getText().length() > 0) {

						ArrayList<String> ar = new ArrayList<>();

						if (comboBoxSkillLevel.getSelectedItem() != null) {
							String sSkillLevel = comboBoxSkillLevel.getSelectedItem().toString();
							int id = Datenbank.loadSkillLevelIdfromDB(sSkillLevel);
							try {
								ResultSet rs = Datenbank.creatStatement()
										.executeQuery(Datenbank.querySelectAllFromSkillLevel);

								while (rs.next()) {

									if (rs.getInt(Datenbank.datenBankSpalteId) == id) {

										PreparedStatement preparedStmt = Datenbank.getConnection()
												.prepareStatement(Datenbank.querySkillLevelNameUpdate);
										preparedStmt.setString(1, tfSkillLevelName.getText());
										preparedStmt.setInt(2, id);
										preparedStmt.executeUpdate();
										break;

									}
								}

								rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkillLevel);

								while (rs.next()) {

									String skillLevelName = rs.getString(Datenbank.datenBankSpalteName);

									if (!ar.contains(skillLevelName)) {
										ar.add(skillLevelName);
									}
								}
								@SuppressWarnings("rawtypes")
								DefaultComboBoxModel model = new DefaultComboBoxModel(ar.toArray());
								comboBoxSkillLevel.setModel(model);
								rs.close();
								Datenbank.creatStatement().close();
								tfSkillLevelName.setText("");
								AllUserToTheDB.userDataChange();
								if (!mehrSkillsEinfuegen) {

									frameSkillLevelName.dispose();
									wiederholungsStop++;
									tfSkillLevelName.removeActionListener(this);

								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} else {

						wiederholungsStop = 0;

					}
				}
			});

			btnSkillLevelNameAendern.setBounds(90, 150, 100, 50);
			btnSkillLevelNameAendern.setBackground(Color.decode("#CCD9FD"));
			btnSkillLevelNameAendern.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			btnSkillLevelNameAendern.addActionListener(new ActionListener() {

				@SuppressWarnings("rawtypes")
				@Override
				public void actionPerformed(ActionEvent arg0) {

					if (tfSkillLevelName.getText().length() < 1 && wiederholungsStop == 0) {

						JOptionPane.showMessageDialog(frameSkillLevelName, "Bitte geben sie noch ein Skill Level ein.");

					} else if (tfSkillLevelName.getText().length() > 0) {

						ArrayList<String> ar = new ArrayList<>();

						if (comboBoxSkillLevel.getSelectedItem() != null) {
							String sSkillLevel = comboBoxSkillLevel.getSelectedItem().toString();
							int id = Datenbank.loadSkillLevelIdfromDB(sSkillLevel);
							try {
								ResultSet rs = Datenbank.creatStatement()
										.executeQuery(Datenbank.querySelectAllFromSkillLevel);

								while (rs.next()) {

									if (rs.getInt(Datenbank.datenBankSpalteId) == id) {

										PreparedStatement preparedStmt = Datenbank.getConnection()
												.prepareStatement(Datenbank.querySkillLevelNameUpdate);
										preparedStmt.setString(1, tfSkillLevelName.getText());
										preparedStmt.setInt(2, id);
										preparedStmt.executeUpdate();

										break;

									}
								}

								rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkillLevel);

								while (rs.next()) {

									String skillLevelName = rs.getString(Datenbank.datenBankSpalteName);

									if (!ar.contains(skillLevelName)) {
										ar.add(skillLevelName);
									}
								}
								DefaultComboBoxModel model = new DefaultComboBoxModel(ar.toArray());
								comboBoxSkillLevel.setModel(model);
								rs.close();
								Datenbank.creatStatement().close();
								tfSkillLevelName.setText("");
								AllUserToTheDB.userDataChange();
								if (!mehrSkillsEinfuegen) {

									frameSkillLevelName.dispose();
									wiederholungsStop++;
									btnSkillLevelNameAendern.removeActionListener(this);

								}
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
					} else {

						wiederholungsStop = 0;

					}
				}
			});

			frameSkillLevelName.setSize(300, 250);
			frameSkillLevelName.setVisible(true);
			frameSkillLevelName.setLocationRelativeTo(MainFrame.mainFrame);
			frameSkillLevelName.setResizable(false);
			frameSkillLevelName.setLayout(null);
			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			frameSkillLevelName.setIconImage(icon.getImage());

			frameSkillLevelName.getContentPane().add(comboBoxSkillLevel);
			frameSkillLevelName.getContentPane().add(tfSkillLevelName);
			frameSkillLevelName.getContentPane().add(lblSkillLevel);
			frameSkillLevelName.getContentPane().add(lblLevel);
			frameSkillLevelName.getContentPane().add(btnSkillLevelNameAendern);
			frameSkillLevelName.getContentPane().add(mehrSkillLevelNamenÄndern);

		}
		if (e.getSource() == MainFrame.dbNamenAendern) {

			final JFrame dbWerteAendernFrame = new JFrame("DatenBank Informationen");
			JButton btnFertig = new JButton("Ändern");
			final JTextField tfDBHostName = new JTextField();
			final JTextField tfDBBenutzer = new JTextField();
			final JPasswordField pfDBPassword = new JPasswordField();
			final JTextField tfDBName = new JTextField();
			final JTextField tfTabelleBenutzer = new JTextField();
			final JTextField tfTabelleLust = new JTextField();
			final JTextField tfTabelleSkill = new JTextField();
			final JTextField tfTabelleThema = new JTextField();
			final JTextField tfTabelleZuordnung = new JTextField();
			final JTextField tfTabelleLevel = new JTextField();

			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			dbWerteAendernFrame.setIconImage(icon.getImage());

			JLabel lblDBHostName = new JLabel("Host Name");
			JLabel lblDBName = new JLabel("DatenBank Name");
			JLabel lblDBBenutzer = new JLabel("Datenbank Benutzer Name");
			JLabel lblDBPassword = new JLabel("DatenBank Password");
			JLabel lblNutzer = new JLabel("Tabelle Nutzer");
			JLabel lblLust = new JLabel("Tabelle Motivation");
			JLabel lblSkill = new JLabel("Tabelle Skill");
			JLabel lblThema = new JLabel("Tabelle Thema");
			JLabel lblZuordnung = new JLabel("Tabelle Zuordnung");
			JLabel lblLevel = new JLabel("Tabelle Level");

			dbWerteAendernFrame.setVisible(true);
			dbWerteAendernFrame.setSize(720, 550);
			dbWerteAendernFrame.setLayout(null);
			dbWerteAendernFrame.setResizable(false);
			dbWerteAendernFrame.setLocationRelativeTo(MainFrame.mainFrame);

			tfDBHostName.setText(Datenbank.hostname);
			tfDBName.setText(Datenbank.dbname);
			tfDBBenutzer.setText(Datenbank.user);
			pfDBPassword.setText(Datenbank.password);
			tfTabelleZuordnung.setText(Datenbank.tabelleZuordnung);
			tfTabelleBenutzer.setText(Datenbank.tabelleBenutzer);
			tfTabelleSkill.setText(Datenbank.tabelleSkill);
			tfTabelleThema.setText(Datenbank.tabelleThema);
			tfTabelleLust.setText(Datenbank.tabelleLust);
			tfTabelleLevel.setText(Datenbank.tabelleSkillLevel);

			lblDBHostName.setBounds(100, 25, 200, 25);
			tfDBHostName.setBounds(100, 50, 200, 25);
			lblDBName.setBounds(400, 25, 200, 25);
			tfDBName.setBounds(400, 50, 200, 25);
			lblDBBenutzer.setBounds(100, 100, 200, 25);
			tfDBBenutzer.setBounds(100, 125, 200, 25);
			lblDBPassword.setBounds(400, 100, 200, 25);
			pfDBPassword.setBounds(400, 125, 200, 25);
			lblZuordnung.setBounds(100, 175, 200, 25);
			tfTabelleZuordnung.setBounds(100, 200, 200, 25);
			lblNutzer.setBounds(400, 175, 200, 25);
			tfTabelleBenutzer.setBounds(400, 200, 200, 25);
			lblLust.setBounds(100, 250, 200, 25);
			tfTabelleLust.setBounds(100, 275, 200, 25);
			lblSkill.setBounds(400, 250, 200, 25);
			tfTabelleSkill.setBounds(400, 275, 200, 25);
			lblThema.setBounds(100, 325, 200, 25);
			tfTabelleThema.setBounds(100, 350, 200, 25);
			lblLevel.setBounds(400, 325, 200, 25);
			tfTabelleLevel.setBounds(400, 350, 200, 25);

			btnFertig.setBounds(295, 400, 100, 50);
			btnFertig.setBackground(Color.decode("#CCD9FD"));
			btnFertig.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			btnFertig.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					char[] n = pfDBPassword.getPassword();
					String stringDBPassword = String.valueOf(n);

					File datei = new File("datenBank.txt");
					try {
						FileWriter writer = new FileWriter(datei, false);
						writer.write(tfDBHostName.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfDBName.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfDBBenutzer.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(stringDBPassword);
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleZuordnung.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleBenutzer.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleSkill.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleThema.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleLust.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleLevel.getText());

						writer.flush();
						writer.close();
						dbWerteAendernFrame.dispose();

						btnFertig.removeActionListener(this);

						JOptionPane.showMessageDialog(MainFrame.mainFrame,
								"Bitte starten sie das Programm neu um auf die neue Datenbank zuzugreifen.");

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

			tfTabelleThema.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					char[] n = pfDBPassword.getPassword();
					String stringDBPassword = String.valueOf(n);

					File datei = new File("datenBank.txt");
					try {
						FileWriter writer = new FileWriter(datei, false);
						writer.write(tfDBHostName.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfDBName.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfDBBenutzer.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(stringDBPassword);
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleZuordnung.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleBenutzer.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleSkill.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleThema.getText());
						writer.write(System.getProperty("line.separator"));
						writer.write(tfTabelleLust.getText());

						writer.flush();
						writer.close();
						dbWerteAendernFrame.dispose();

						tfTabelleThema.removeActionListener(this);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});

			dbWerteAendernFrame.add(tfDBHostName);
			dbWerteAendernFrame.add(lblDBHostName);
			dbWerteAendernFrame.add(tfDBName);
			dbWerteAendernFrame.add(lblDBName);
			dbWerteAendernFrame.add(lblDBBenutzer);
			dbWerteAendernFrame.add(tfDBBenutzer);
			dbWerteAendernFrame.add(lblDBPassword);
			dbWerteAendernFrame.add(pfDBPassword);
			dbWerteAendernFrame.add(lblZuordnung);
			dbWerteAendernFrame.add(tfTabelleZuordnung);
			dbWerteAendernFrame.add(lblNutzer);
			dbWerteAendernFrame.add(tfTabelleBenutzer);
			dbWerteAendernFrame.add(lblLust);
			dbWerteAendernFrame.add(tfTabelleLust);
			dbWerteAendernFrame.add(lblSkill);
			dbWerteAendernFrame.add(tfTabelleSkill);
			dbWerteAendernFrame.add(lblThema);
			dbWerteAendernFrame.add(tfTabelleThema);
			dbWerteAendernFrame.add(lblLevel);
			dbWerteAendernFrame.add(tfTabelleLevel);
			dbWerteAendernFrame.add(btnFertig);

		}
		if (e.getSource() == MainFrame.eintragHinzufuegen) {
			NeuerEintrag.neuerEintrag();

		}

		if (e.getSource() == MainFrame.passwordAendern) {

			passwordWechselnFrame.setSize(300, 200);
			passwordWechselnFrame.setVisible(true);
			passwordWechselnFrame.setResizable(false);
			passwordWechselnFrame.setLocationRelativeTo(MainFrame.mainFrame);
			passwordWechselnFrame.setLayout(null);

			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			passwordWechselnFrame.setIconImage(icon.getImage());

			lblAltesPassword = new JLabel("Altes Password");
			lblNeuesPassword = new JLabel("Neues Password");

			btnSenden.setBackground(Color.decode("#CCD9FD"));
			btnSenden.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			lblAltesPassword.setBounds(25, 10, 225, 25);
			altesPassword.setBounds(25, 30, 225, 25);
			lblNeuesPassword.setBounds(25, 55, 225, 25);
			neuesPassword.setBounds(25, 75, 225, 25);
			btnSenden.setBounds(90, 105, 100, 50);

			btnSenden.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					passWordAendern();
					btnSenden.removeActionListener(this);

				}
			});
			altesPassword.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					passWordAendern();
					altesPassword.removeActionListener(this);

				}
			});
			neuesPassword.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					passWordAendern();
					neuesPassword.removeActionListener(this);

				}
			});
			passwordWechselnFrame.add(lblAltesPassword);
			passwordWechselnFrame.add(altesPassword);
			passwordWechselnFrame.add(lblNeuesPassword);
			passwordWechselnFrame.add(neuesPassword);
			passwordWechselnFrame.add(btnSenden);

		}
	}

	public void passWordAendern() {
		boolean geaendert = false;
		char[] a = altesPassword.getPassword();
		String altesUserPasswort = String.valueOf(a);
		char[] n = neuesPassword.getPassword();
		String neuesUserPasswort = String.valueOf(n);

		ResultSet rs;
		try {
			rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);

			while (rs.next()) {

				if (rs.getString(Datenbank.datenBankSpalte3).equals(HashPassword.sha1(altesUserPasswort))) {

					PreparedStatement preparedStmt = Datenbank.getConnection()
							.prepareStatement(Datenbank.queryPasswordUpdate);
					preparedStmt.setString(1, HashPassword.sha1(neuesUserPasswort));
					preparedStmt.setInt(2, NutzerDaten.Id);
					preparedStmt.executeUpdate();
					geaendert = true;
					altesPassword.setText("");
					neuesPassword.setText("");
					rs.close();
					passwordWechselnFrame.dispose();
					JOptionPane.showMessageDialog(MainFrame.mainFrame, "Password erfolgreich geändert.");
					break;

				}
			}

			if (!geaendert) {

				JOptionPane.showMessageDialog(MainFrame.mainFrame,
						"Password konnte nicht geändert werden. Überprüfen Sie nochmal Ihr altes Password.");

			}

		} catch (SQLException | NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}