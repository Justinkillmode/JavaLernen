package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db.Datenbank;
import objektorientierung.NutzerDaten;

public class NeuerEintrag {

	@SuppressWarnings({ "rawtypes" })
	public static JComboBox comboBoxSkillLevel;
	@SuppressWarnings({ "rawtypes" })
	public static JComboBox comboBoxSkill;
	@SuppressWarnings({ "rawtypes" })
	public static JComboBox comboBoxThema;

	public static void neuerEintrag() {

		final int max = 7;
		final int min = 1;

		JFrame neuerEintragFrame = new JFrame("Neuen Beitrag erstellen");
		JPanel panelAddSkills = new JPanel(new GridBagLayout());
		JLabel lblThema, lblSkill, lblLust, lblBeschreibung, lblUeberschrift, lblSkillLvl;
		final JSlider sliderMotivation = new JSlider(JSlider.HORIZONTAL, min, max, min);
		final JLabel emojies = new JLabel();
		neuerEintragFrame.setLocationRelativeTo(MainFrame.mainFrame);
		ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
		neuerEintragFrame.setIconImage(icon.getImage());

		emojies.setIcon(Icons.icon1);
		sliderMotivation.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				switch (sliderMotivation.getValue()) {
				case 1:
					emojies.setIcon(Icons.icon1);
					break;
				case 2:
					emojies.setIcon(Icons.icon2);
					break;
				case 3:
					emojies.setIcon(Icons.icon3);
					break;
				case 4:
					emojies.setIcon(Icons.icon4);
					break;
				case 5:
					emojies.setIcon(Icons.icon5);
					break;
				case 6:
					emojies.setIcon(Icons.icon6);
					break;
				case 7:
					emojies.setIcon(Icons.icon7);
					break;

				default:
					break;
				}

			}
		});

		final JTextArea taBeschreibung = new JTextArea(10, 34);
		taBeschreibung.setLineWrap(true);
		taBeschreibung.setWrapStyleWord(true);
		taBeschreibung.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		comboBoxSkillLevel = MainFrame.getSkillLevelComboBox(0);
		comboBoxSkill = MainFrame.getSkillComboBox(0);
		comboBoxThema = MainFrame.getThemaComboBox(0);

		lblUeberschrift = new JLabel("");
		Font schrift1 = (lblUeberschrift.getFont().deriveFont(Font.BOLD, 15));

		lblThema = new JLabel("Thema");

		lblSkill = new JLabel("Skill");

		lblSkillLvl = new JLabel("Skill Level");

		lblLust = new JLabel("Motivation");

		lblBeschreibung = new JLabel("Beschreibung");

		comboBoxSkill.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {

				if (comboBoxSkill.getSelectedItem() != null) {
					String sSkill = comboBoxSkill.getSelectedItem().toString();
					int skillID = Datenbank.loadSkillIdfromDB(sSkill);

					int ID = Datenbank.loadThemaIdfromDB(Datenbank.loadSkillfromDB(skillID).getThema().toString());

					if (ID < 4)
						comboBoxThema.setSelectedIndex(ID - 1);
					else
						comboBoxThema.setSelectedIndex(ID - 7);

				}
			}
		});

		comboBoxThema.addItemListener(new ItemListener() {
			int i = 0;

			@SuppressWarnings("unchecked")
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (comboBoxThema.getSelectedItem() != null) {
					comboBoxSkill.removeAllItems();
					ArrayList<String> ar = new ArrayList<>();

					String sThema = comboBoxThema.getSelectedItem().toString();
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

						if (ar.size() == 0 && i == 0) {

							JOptionPane.showMessageDialog(MainFrame.mainFrame,
									"Noch keine Skills zu diesem Thema vorhanden.");
							i++;

						} else if (ar.size() == 0 && i == 1) {

							i--;

						}
						@SuppressWarnings("rawtypes")
						DefaultComboBoxModel model = new DefaultComboBoxModel(ar.toArray());
						comboBoxSkill.setModel(model);
						rs.close();
						Datenbank.creatStatement().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		});

		JButton btnSenden = new JButton("Senden");
		btnSenden.setVisible(true);
		btnSenden.setFocusable(false);
		btnSenden.setBackground(Color.decode("#CCD9FD"));
		btnSenden.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		btnSenden.setFont(schrift1);
		btnSenden.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					String name = NutzerDaten.benutzer;
					String skill = comboBoxSkill.getSelectedItem().toString();
					String beschreibung = taBeschreibung.getText();
					int lust = sliderMotivation.getValue();
					String level = comboBoxSkillLevel.getSelectedItem().toString();

					if (beschreibung.length() < 1)
						JOptionPane.showMessageDialog(MainFrame.mainFrame, "Bitte geben sie eine Beschreibung ein");
					else {

						Datenbank.insterToDB(name, skill, beschreibung, lust, level);

						Datenbank.creatStatement().close();

						AllUserToTheDB.userDataChange();
						JOptionPane.showMessageDialog(MainFrame.mainFrame, "Skills gesendet.");
						taBeschreibung.setText("");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(MainFrame.mainFrame, "Skills konnten nicht gesendet werden.");
					e2.printStackTrace();
				}

			}
		});

		panelAddSkills.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		panelAddSkills.setBorder(BorderFactory.createLineBorder(Color.decode("#C5D3F8"), 15));

		panelAddSkills.setLayout(null);

		lblThema.setBounds(50, 55, 150, 30);
		comboBoxThema.setBounds(50, 80, 150, 30);
		lblSkill.setBounds(215, 55, 150, 30);
		comboBoxSkill.setBounds(215, 80, 150, 30);
		lblSkillLvl.setBounds(380, 55, 150, 30);
		comboBoxSkillLevel.setBounds(380, 80, 150, 30);
		lblBeschreibung.setBounds(50, 125, 480, 20);
		taBeschreibung.setBounds(50, 150, 480, 200);
		lblLust.setBounds(58, 365, 480, 20);
		sliderMotivation.setBounds(50, 380, 480, 30);
		emojies.setBounds(270, 420, 40, 40);
		btnSenden.setBounds(190, 490, 200, 50);

		panelAddSkills.add(btnSenden);
		panelAddSkills.add(taBeschreibung);
		panelAddSkills.add(lblBeschreibung);
		panelAddSkills.add(sliderMotivation);
		panelAddSkills.add(lblLust);
		panelAddSkills.add(emojies);
		panelAddSkills.add(lblThema);
		panelAddSkills.add(comboBoxThema);
		panelAddSkills.add(lblSkill);
		panelAddSkills.add(comboBoxSkill);
		panelAddSkills.add(lblSkillLvl);
		panelAddSkills.add(comboBoxSkillLevel);

		neuerEintragFrame.getContentPane().add(panelAddSkills, null);
		neuerEintragFrame.setSize(600, 700);
		neuerEintragFrame.setVisible(true);
		neuerEintragFrame.setLocationRelativeTo(MainFrame.mainFrame);
		neuerEintragFrame.setResizable(false);

	}

}