package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerForEmojies {

	public static String icon1;
	public static String icon2;
	public static String icon3;
	public static String icon4;
	public static String icon5;
	public static String icon6;
	public static String icon7;

	public static void emojieScanner() {

		File datei = new File("emojies.txt");
		try {
			Scanner sca = new Scanner(datei);
			int i = 0;

			while (sca.hasNextLine()) {

				switch (i) {

				case 0:
					icon1 = sca.nextLine();
					break;
				case 1:
					icon2 = sca.nextLine();
					break;
				case 2:
					icon3 = sca.nextLine();
					break;
				case 3:
					icon4 = sca.nextLine();
					break;
				case 4:
					icon5 = sca.nextLine();
					break;
				case 5:
					icon6 = sca.nextLine();
					break;
				case 6:
					icon7 = sca.nextLine();
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