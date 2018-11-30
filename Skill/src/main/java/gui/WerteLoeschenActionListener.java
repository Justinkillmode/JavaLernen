package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import db.Datenbank;
import domain.Benutzer;

public class WerteLoeschenActionListener implements ActionListener {

	JComboBox<Benutzer> benutzerComboBox = new JComboBox<>();

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == MainFrame.benutzerLoeschen) {

			JFrame frame = new JFrame("Benutzer Löschen");
			JButton btnLoeschen = new JButton("Löschen");

			benutzerComboBox = MainFrame.getBenutzerComboBox();
			btnLoeschen.setBackground(Color.decode("#CCD9FD"));
			btnLoeschen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			frame.setIconImage(icon.getImage());
			frame.setSize(280, 210);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.setResizable(false);
			frame.setLocationRelativeTo(MainFrame.mainFrame);

			benutzerComboBox.setBounds(32, 20, 200, 50);
			btnLoeschen.setBounds(55, 90, 150, 50);

			frame.add(benutzerComboBox);
			frame.add(btnLoeschen);

			btnLoeschen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					Datenbank.deleteBenutzer(benutzerComboBox.getSelectedItem().toString());
					frame.dispose();

				}
			});

		}
		if (e.getSource() == MainFrame.skillLoeschen) {

			JFrame frame = new JFrame("Skill Löschen");
			JButton btnLoeschen = new JButton("Löschen");

			MainFrame.comboBox2bTabelSkill = MainFrame.getSkillComboBox(0);
			btnLoeschen.setBackground(Color.decode("#CCD9FD"));
			btnLoeschen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			frame.setIconImage(icon.getImage());
			frame.setSize(280, 210);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.setResizable(false);
			frame.setLocationRelativeTo(MainFrame.mainFrame);

			MainFrame.comboBox2bTabelSkill.setBounds(32, 20, 200, 50);
			btnLoeschen.setBounds(55, 90, 150, 50);

			frame.add(MainFrame.comboBox2bTabelSkill);
			frame.add(btnLoeschen);

			btnLoeschen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					Datenbank.deleteSkill(MainFrame.comboBox2bTabelSkill.getSelectedItem().toString());
					frame.dispose();
					MainFrame.comboBox2bTabelSkill = MainFrame.getSkillComboBox(0);

				}
			});

		}
		if (e.getSource() == MainFrame.themaLoeschen) {

			JFrame frame = new JFrame("Thema Löschen");
			JButton btnLoeschen = new JButton("Löschen");

			MainFrame.comboBoxTabelThema = MainFrame.getThemaComboBox(0);
			btnLoeschen.setBackground(Color.decode("#CCD9FD"));
			btnLoeschen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			frame.setIconImage(icon.getImage());
			frame.setSize(280, 210);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.setResizable(false);
			frame.setLocationRelativeTo(MainFrame.mainFrame);

			MainFrame.comboBoxTabelThema.setBounds(32, 20, 200, 50);
			btnLoeschen.setBounds(55, 90, 150, 50);

			frame.add(MainFrame.comboBoxTabelThema);
			frame.add(btnLoeschen);

			btnLoeschen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					Datenbank.deleteThema(MainFrame.comboBoxTabelThema.getSelectedItem().toString());
					frame.dispose();
					MainFrame.comboBoxTabelThema = MainFrame.getThemaComboBox(0);

				}
			});

		}
		if (e.getSource() == MainFrame.levelLoeschen) {

			JFrame frame = new JFrame("Level Löschen");
			JButton btnLoeschen = new JButton("Löschen");

			MainFrame.comboBoxTabelLevel = MainFrame.getSkillLevelComboBox(0);
			btnLoeschen.setBackground(Color.decode("#CCD9FD"));
			btnLoeschen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

			ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
			frame.setIconImage(icon.getImage());
			frame.setSize(280, 210);
			frame.setVisible(true);
			frame.setLayout(null);
			frame.setResizable(false);
			frame.setLocationRelativeTo(MainFrame.mainFrame);

			MainFrame.comboBoxTabelLevel.setBounds(32, 20, 200, 50);
			btnLoeschen.setBounds(55, 90, 150, 50);

			frame.add(MainFrame.comboBoxTabelLevel);
			frame.add(btnLoeschen);

			btnLoeschen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {

					Datenbank.deleteLevel(MainFrame.comboBoxTabelLevel.getSelectedItem().toString());
					frame.dispose();
					MainFrame.comboBoxTabelLevel = MainFrame.getSkillLevelComboBox(0);

				}
			});

		}

	}

}
