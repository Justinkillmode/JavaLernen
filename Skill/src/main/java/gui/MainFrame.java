package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;

import db.Datenbank;
import domain.Benutzer;
import domain.Skill;
import domain.Thema;

public class MainFrame {

	public static JFrame mainFrame = new JFrame();
	static String tabelHeader[] = { "Name", "Thema", "Skills", "Beschreibung", "Motivation", "Skill Level" };
	static DefaultTableModel datenbankTabelModel = new DefaultTableModel(null, tabelHeader);
	static JTable db_Inhalt = new JTable(datenbankTabelModel);

	@SuppressWarnings("rawtypes")
	static JComboBox comboBoxTabelLust, comboBox2bTabelSkill, comboBoxTabelThema, comboBoxTabelLevel, comboBoxFilter;
	static TableCellEditor editorLust, editorSkill, editorThema, editorTabelLust, editorTabelSkill, editorTabelThema,
			editorTabelBeschreibung, editorTabelLevel;

	static JTextField search = new JTextField();
	public static JMenuItem neuerNutzer, skill, thema, wechseln, lustHinzufuegen, lustNamenAendern, eintragHinzufuegen,
			dbNamenAendern, bilderWechseln, passwordAendern, benutzerLoeschen, skillLoeschen, themaLoeschen,
			levelLoeschen;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
		mainFrame.setIconImage(icon.getImage());

		ScannerDatei.scannFile();
		ScannerForEmojies.emojieScanner();

		Anmelden.login();

		AllUserToTheDB.userDataChange();

		db_Inhalt.getTableHeader().setFont(new Font("Agenda", 1, 15));
		db_Inhalt.setFont(new Font("Agenda", 0, 15));

		mainFrame.setVisible(false);
		mainFrame.setSize(1200, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setLayout(new GridBagLayout());

		search.setUI(new HintTextField("Search", Color.gray));
		search.setText("Search");
		search.setForeground(Color.GRAY);
		search.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		Font schrift1 = (search.getFont().deriveFont(Font.BOLD, 15));
		search.setFont(schrift1);
		search.getDocument().addDocumentListener(new TabelSearch());
		search.addMouseListener(new MouseListener() {

			int i = 0;

			@Override
			public void mouseReleased(MouseEvent arg0) {

			}

			@Override
			public void mousePressed(MouseEvent arg0) {

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {

				if (i == 0) {
					search.setText("");
					search.setForeground(Color.BLACK);
					i++;
				}
			}
		});

		final JTextArea taBeschreibung = new JTextArea(30, 30);
		taBeschreibung.setLineWrap(true);
		taBeschreibung.setWrapStyleWord(true);
		taBeschreibung.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JMenuBar mb = new JMenuBar();
		JMenu einstellungen, hinzufügen, loeschen;

		hinzufügen = new JMenu();
		hinzufügen.setIcon(new ImageIcon("src/main/icon/add.png"));
		einstellungen = new JMenu();
		einstellungen.setIcon(new ImageIcon("src/main/icon/einstellung.png"));
		loeschen = new JMenu();
		loeschen.setIcon(new ImageIcon("src/main/icon/trash.png"));
		neuerNutzer = new JMenuItem("Neuer Benutzer");
		skill = new JMenuItem("Neuer Skill");
		thema = new JMenuItem("Neues Thema");
		wechseln = new JMenuItem("Benutzer wechseln");
		lustNamenAendern = new JMenuItem("Level Namen ändern");
		lustHinzufuegen = new JMenuItem("Level Arten Hinzufügen");
		dbNamenAendern = new JMenuItem("DatenBank Informationen ändern");
		eintragHinzufuegen = new JMenuItem("Eintrag Hinzufügen");
		bilderWechseln = new JMenuItem("Emojie Bilder wechseln");
		passwordAendern = new JMenuItem("Password Ändern");
		benutzerLoeschen = new JMenuItem("Benutzer Löschen");
		skillLoeschen = new JMenuItem("Skill Löschen");
		themaLoeschen = new JMenuItem("Thema Löschen");
		levelLoeschen = new JMenuItem("Level Löschen");
		neuerNutzer.addActionListener(new NeueWerteActionListener());
		skill.addActionListener(new NeueWerteActionListener());
		thema.addActionListener(new NeueWerteActionListener());
		lustHinzufuegen.addActionListener(new NeueWerteActionListener());
		wechseln.addActionListener(new NeueWerteActionListener());
		lustNamenAendern.addActionListener(new NeueWerteActionListener());
		dbNamenAendern.addActionListener(new NeueWerteActionListener());
		eintragHinzufuegen.addActionListener(new NeueWerteActionListener());
		passwordAendern.addActionListener(new NeueWerteActionListener());
		bilderWechseln.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				JCheckBox mehrEinfügen = new JCheckBox("Mehr Einfügen?");
				JFrame frame = new JFrame("Emojies Ändern");
				JButton btnAendern = new JButton("Ändern");
				JButton choiceFile = new JButton("Bild Auswählen");
				frame.setVisible(true);
				frame.setSize(300, 300);
				frame.setResizable(false);
				frame.setLocationRelativeTo(mainFrame);
				frame.setLayout(null);

				choiceFile.setVisible(true);
				choiceFile.setFocusable(false);
				choiceFile.setBackground(Color.decode("#CCD9FD"));
				choiceFile.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				choiceFile.setFont(schrift1);
				btnAendern.setVisible(true);
				btnAendern.setFocusable(false);
				btnAendern.setBackground(Color.decode("#CCD9FD"));
				btnAendern.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
				btnAendern.setFont(schrift1);

				choiceFile.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						OpenFile.thoseImage();

						switch (comboBoxTabelLust.getSelectedItem().toString()) {
						case "1":
							ScannerForEmojies.icon1 = OpenFile.path;
							break;
						case "2":
							ScannerForEmojies.icon2 = OpenFile.path;
							break;
						case "3":
							ScannerForEmojies.icon3 = OpenFile.path;
							break;
						case "4":
							ScannerForEmojies.icon4 = OpenFile.path;
							break;
						case "5":
							ScannerForEmojies.icon5 = OpenFile.path;
							break;
						case "6":
							ScannerForEmojies.icon6 = OpenFile.path;
							break;
						case "7":
							ScannerForEmojies.icon7 = OpenFile.path;
							break;

						}

					}
				});

				btnAendern.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {

						File datei = new File("emojies.txt");
						try {
							FileWriter writer = new FileWriter(datei, false);
							writer.write(ScannerForEmojies.icon1);
							writer.write(System.getProperty("line.separator"));
							writer.write(ScannerForEmojies.icon2);
							writer.write(System.getProperty("line.separator"));
							writer.write(ScannerForEmojies.icon3);
							writer.write(System.getProperty("line.separator"));
							writer.write(ScannerForEmojies.icon4);
							writer.write(System.getProperty("line.separator"));
							writer.write(ScannerForEmojies.icon5);
							writer.write(System.getProperty("line.separator"));
							writer.write(ScannerForEmojies.icon6);
							writer.write(System.getProperty("line.separator"));
							writer.write(ScannerForEmojies.icon7);
							Icons.icon1 = new ImageIcon(ScannerForEmojies.icon1);
							Icons.icon2 = new ImageIcon(ScannerForEmojies.icon2);
							Icons.icon3 = new ImageIcon(ScannerForEmojies.icon3);
							Icons.icon4 = new ImageIcon(ScannerForEmojies.icon4);
							Icons.icon5 = new ImageIcon(ScannerForEmojies.icon5);
							Icons.icon6 = new ImageIcon(ScannerForEmojies.icon6);
							Icons.icon7 = new ImageIcon(ScannerForEmojies.icon7);

							writer.flush();
							writer.close();
							if (!mehrEinfügen.isSelected()) {
								frame.dispose();
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});

				comboBoxTabelLust.setBounds(42, 20, 200, 50);
				choiceFile.setBounds(42, 90, 200, 50);
				mehrEinfügen.setBounds(42, 155, 200, 20);
				btnAendern.setBounds(42, 190, 200, 50);

				frame.add(comboBoxTabelLust);
				frame.add(choiceFile);
				frame.add(mehrEinfügen);
				frame.add(btnAendern);

			}
		});
		benutzerLoeschen.addActionListener(new WerteLoeschenActionListener());
		skillLoeschen.addActionListener(new WerteLoeschenActionListener());
		themaLoeschen.addActionListener(new WerteLoeschenActionListener());
		levelLoeschen.addActionListener(new WerteLoeschenActionListener());
		hinzufügen.add(neuerNutzer);
		hinzufügen.add(skill);
		hinzufügen.add(thema);
		hinzufügen.add(lustHinzufuegen);
		hinzufügen.add(eintragHinzufuegen);
		einstellungen.add(wechseln);
		einstellungen.add(dbNamenAendern);
		einstellungen.add(lustNamenAendern);
		einstellungen.add(bilderWechseln);
		einstellungen.add(passwordAendern);
		loeschen.add(benutzerLoeschen);
		loeschen.add(skillLoeschen);
		loeschen.add(themaLoeschen);
		loeschen.add(levelLoeschen);
		mb.add(einstellungen);
		mb.add(hinzufügen);
		mb.add(loeschen);
		mainFrame.setJMenuBar(mb);

		mainFrame.getContentPane().setBackground(Color.decode("#BACBF8"));

		comboBoxFilter = getSkillLevelComboBoxForFiltering();
		comboBoxFilter.setBackground(Color.lightGray);
		comboBoxFilter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				String query = comboBoxFilter.getSelectedItem().toString();
				filter(query);

				if (comboBoxFilter.getSelectedItem().toString().equals("")) {
					db_Inhalt.setEnabled(true);
					comboBoxFilter.setBackground(Color.lightGray);

				} else {
					db_Inhalt.setEnabled(false);
					comboBoxFilter.setBackground(Color.white);
				}
			}

			private void filter(String query) {

				TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(
						MainFrame.datenbankTabelModel);
				MainFrame.db_Inhalt.setRowSorter(tr);

				tr.setRowFilter(RowFilter.regexFilter(query, 2));

			}

		});

		db_Inhalt.setEnabled(true);
		db_Inhalt.setVisible(true);
		comboBoxTabelLust = getLustComboBox(0);
		editorTabelLust = new DefaultCellEditor(comboBoxTabelLust);
		db_Inhalt.getColumnModel().getColumn(4).setCellEditor(editorTabelLust);
		comboBox2bTabelSkill = getSkillComboBox(0);
		editorTabelSkill = new DefaultCellEditor(comboBox2bTabelSkill);
		db_Inhalt.getColumnModel().getColumn(2).setCellEditor(editorTabelSkill);
		comboBoxTabelThema = getThemaComboBox(0);
		editorTabelThema = new DefaultCellEditor(comboBoxTabelThema);
		comboBoxTabelLevel = getSkillLevelComboBox(0);
		editorTabelLevel = new DefaultCellEditor(comboBoxTabelLevel);
		db_Inhalt.getColumnModel().getColumn(1).setCellEditor(editorTabelThema);
		db_Inhalt.getColumnModel().getColumn(3).setCellEditor(editorTabelBeschreibung);
		db_Inhalt.getColumnModel().getColumn(5).setCellEditor(editorTabelLevel);
		db_Inhalt.getTableHeader().setReorderingAllowed(false);

		db_Inhalt.getModel().addTableModelListener(new MainTableActionListener());

		editorTabelLust.addCellEditorListener(new MainTableActionListener());
		editorTabelSkill.addCellEditorListener(new MainTableActionListener());
		editorTabelThema.addCellEditorListener(new MainTableActionListener());
		editorTabelLevel.addCellEditorListener(new MainTableActionListener());

		JScrollPane sp_Inhalt = new JScrollPane(db_Inhalt, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		GridBagConstraints p1 = new GridBagConstraints();
		JPanel panelShowUserSkills = new JPanel(new GridBagLayout());

		final JCheckBox alleAnzeigen = new JCheckBox("Alle Anzeigen?");
		alleAnzeigen.addKeyListener(new NeuerEintragAction());
		alleAnzeigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (AllUserToTheDB.mainLogin == true) {
					db_Inhalt.setEnabled(false);
					AllUserToTheDB.mainLogin = false;
					AllUserToTheDB.userDataChange();
				} else {
					AllUserToTheDB.mainLogin = true;
					AllUserToTheDB.userDataChange();
					db_Inhalt.setEnabled(true);
				}
			}
		});

		JLabel lblSortBySkill = new JLabel("Skill Filtern  ");
		JPanel panelPlatzhalter = new JPanel();

		comboBoxFilter.addKeyListener(new NeuerEintragAction());
		panelShowUserSkills.setBackground(Color.decode("#C5D3F8"));
		alleAnzeigen.setBackground(Color.decode("#C5D3F8"));

		search.setPreferredSize(new Dimension(300, 25));

		p1.weightx = 0;

		p1.weighty = 0.1;
		p1.gridy = 0;
		p1.gridwidth = 1;
		p1.gridx = 3;
		panelShowUserSkills.add(lblSortBySkill, p1);
		p1.gridwidth = 1;
		p1.gridx = 4;
		panelShowUserSkills.add(comboBoxFilter, p1);
		p1.gridwidth = 1;
		p1.weightx = 0;
		p1.gridy = 0;
		p1.gridx = 1;
		panelShowUserSkills.add(alleAnzeigen, p1);
		p1.gridwidth = 1;
		p1.weightx = 10;
		p1.gridy = 0;
		p1.gridx = 2;
		panelShowUserSkills.add(panelPlatzhalter, p1);
		p1.weighty = 0.1;
		p1.weightx = 0;
		p1.gridy = 0;
		p1.gridx = 0;
		p1.gridwidth = 0;
		panelShowUserSkills.add(search, p1);
		p1.fill = GridBagConstraints.BOTH;
		p1.weightx = 3;
		p1.weighty = 1;
		p1.gridy = 2;
		p1.gridx = 0;
		p1.gridwidth = 5;
		panelShowUserSkills.add(sp_Inhalt, p1);

		panelShowUserSkills.setVisible(true);

		GridBagConstraints c = new GridBagConstraints();

		c.weightx = 10;

		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 2;
		c.ipady = 40;
		mainFrame.setLayout(new GridLayout());
		panelShowUserSkills.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		mainFrame.getContentPane().add(panelShowUserSkills);

		panelShowUserSkills.validate();
		mainFrame.validate();

		search.addKeyListener(new NeuerEintragAction());
		db_Inhalt.addKeyListener(new NeuerEintragAction());

		comboBoxTabelLust.setRenderer(new ComboBoxRenderer());

	}

	public static JComboBox<Skill> getSkillComboBox(int themenId) {

		JComboBox<Skill> cb = new JComboBox<>();

		cb.removeAllItems();

		if (themenId == 0) {
			for (Skill s : Datenbank.loadAllSkills()) {

				cb.addItem(s);

			}
		} else {
			try {
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);

				while (rs.next()) {

					if (rs.getInt(Datenbank.datenBankSpalte4) == themenId) {
						Skill name = Datenbank.loadSkillfromDB(rs.getInt(Datenbank.datenBankSpalteId));
						cb.addItem(name);
					}
				}
				rs.close();
				Datenbank.creatStatement().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return cb;
	}

	public static JComboBox<Thema> getThemaComboBox(int skillId) {

		JComboBox<Thema> cb = new JComboBox<>();

		if (skillId == 0) {
			for (Thema t : Datenbank.loadAllThemen()) {

				cb.addItem(t);

			}
		} else {

			try {
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkill);

				while (rs.next()) {

					if (rs.getInt(Datenbank.datenBankSpalteId) == skillId) {
						Thema name = Datenbank.loadThemafromDB(rs.getInt(Datenbank.datenBankSpalte4));
						NeuerEintrag.comboBoxThema.setSelectedItem(name);
						cb.addItem(name);
					}
				}
				Datenbank.creatStatement().close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return cb;
	}

	public static JComboBox<String> getSkillLevelComboBox(int LevelId) {

		JComboBox<String> cb = new JComboBox<>();

		if (LevelId == 0) {
			for (String t : Datenbank.loadAllSkillLevel()) {

				cb.addItem(t);

			}
		} else {

			try {
				ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromSkillLevel);

				while (rs.next()) {

					if (rs.getInt(Datenbank.datenBankSpalteId) == LevelId) {
						String name = Datenbank.loadSkillLevelfromDB(rs.getInt(Datenbank.datenBankSpalteName));
						NeuerEintrag.comboBoxThema.setSelectedItem(name);
						cb.addItem(name);
					}
				}
				Datenbank.creatStatement().close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return cb;
	}

	public static JComboBox<String> getSkillLevelComboBoxForFiltering() {

		JComboBox<String> cb = new JComboBox<>();

		cb.addItem("");
		for (String s : Datenbank.loadAllSkillsForFiltering()) {

			cb.addItem(s);

		}
		return cb;
	}

	public static JComboBox<Benutzer> getBenutzerComboBox() {

		JComboBox<Benutzer> cb = new JComboBox<>();

		for (Benutzer s : Datenbank.loadAllBenutzer()) {

			cb.addItem(s);

		}
		return cb;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static JComboBox<Object> getLustComboBox(int lustId) {

		JComboBox cb = new JComboBox();

		try {
			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromLust);

			while (rs.next()) {

				int id = rs.getInt(Datenbank.datenBankSpalteId);
				cb.addItem(id);
			}
			Datenbank.creatStatement().close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cb;
	}

}