package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import db.Datenbank;
import objektorientierung.NutzerDaten;

public class Anmelden {
	static boolean mainLogin = false;
	public static JFrame loginFrame = new JFrame("Login");
	public static JTextField tfBenutzername = new JTextField();
	public static JLabel lblBenutzername = new JLabel("Benutzername");
	public static JLabel lblPassword = new JLabel("Password");
	public static JButton btnLogin = new JButton("Login");
	final static JPasswordField passwordField = new JPasswordField(10);

	public static void login() {

		ImageIcon icon = new ImageIcon("src/main/icon/icon.png");
		loginFrame.setIconImage(icon.getImage());

		btnLogin.setBackground(Color.decode("#CCD9FD"));
		btnLogin.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		loginFrame.setLayout(null);
		loginFrame.setVisible(true);
		loginFrame.setSize(500, 300);
		loginFrame.setResizable(false);
		loginFrame.setLocationRelativeTo(null);
		if (!mainLogin)
			loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tfBenutzername.setBounds(120, 50, 300, 30);
		lblBenutzername.setBounds(30, 50, 300, 30);
		passwordField.setBounds(120, 100, 300, 30);
		lblPassword.setBounds(30, 100, 100, 30);
		btnLogin.setBounds(190, 170, 100, 50);

		loginFrame.add(lblBenutzername);
		loginFrame.add(passwordField);
		loginFrame.add(tfBenutzername);
		loginFrame.add(lblPassword);
		loginFrame.add(btnLogin);

		tfBenutzername.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (anmelden())
					tfBenutzername.removeActionListener(this);

			}
		});

		passwordField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (anmelden())
					passwordField.removeActionListener(this);

			}
		});

		btnLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (anmelden())
					btnLogin.removeActionListener(this);

			}
		});

	}

	public static boolean anmelden() {

		boolean login = false;

		try {
			char[] c = passwordField.getPassword();
			String userPasswort = String.valueOf(c);
			String userName = tfBenutzername.getText();

			ResultSet rs = Datenbank.creatStatement().executeQuery(Datenbank.querySelectAllFromBenutzer);

			if (userName.length() > 1 || userPasswort.length() > 1) {

				while (rs.next()) {
					if (rs.getString(Datenbank.datenBankSpalteName).equals(userName)
							&& rs.getString(Datenbank.datenBankSpalte3).equals(HashPassword.sha1(userPasswort))) {

						NutzerDaten.Id = rs.getInt(Datenbank.datenBankSpalteId);
						passwordField.setText("");
						tfBenutzername.setText("");
						login = true;
						rs.close();
						Datenbank.creatStatement().close();
						break;

					}
				}
				if (!login) {

					JOptionPane.showMessageDialog(null, "Benutzername oder Password sind falsch.");

				} else {

					NutzerDaten.benutzer = userName;
					Datenbank.creatStatement().close();
					MainFrame.mainFrame.setVisible(true);
					MainFrame.mainFrame.validate();
					MainFrame.mainFrame.setTitle("Skill-Level: Angemeldet als " + NutzerDaten.benutzer);
					mainLogin = true;
					loginFrame.dispose();
					AllUserToTheDB.mainLogin = true;
					AllUserToTheDB.userDataChange();

				}

			} else {

				JOptionPane.showMessageDialog(null, "Sie haben keine Daten angegeben.");

			}

		} catch (

		Exception e2) {
			e2.printStackTrace();
		}
		return login;

	}
}
