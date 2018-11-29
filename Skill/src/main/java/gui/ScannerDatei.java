package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerDatei {

	public static String hostName;
	public static String dbName;
	public static String user;
	public static String password;
	public static String tabelleZuordnung;
	public static String tabelleBenutzer;
	public static String tabelleSkill;
	public static String tabelleThema;
	public static String tabelleLust;
	public static String tabelleSkillLevel;

	public static void scannFile() {

		File datei = new File("datenBank.txt");
		try {
			Scanner sca = new Scanner(datei);
			int i = 0;

			while (sca.hasNextLine()) {

				switch (i) {

				case 0:
					hostName = sca.nextLine();
					break;
				case 1:
					dbName = sca.nextLine();
					break;
				case 2:
					user = sca.nextLine();
					break;
				case 3:
					password = sca.nextLine();
					break;
				case 4:
					tabelleZuordnung = sca.nextLine();
					break;
				case 5:
					tabelleBenutzer = sca.nextLine();
					break;
				case 6:
					tabelleSkill = sca.nextLine();
					break;
				case 7:
					tabelleThema = sca.nextLine();
					break;
				case 8:
					tabelleLust = sca.nextLine();
					break;
				case 9:
					tabelleSkillLevel = sca.nextLine();
					break;
				}
				i++;

			}
			sca.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}