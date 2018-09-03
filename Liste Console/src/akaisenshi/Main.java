package akaisenshi;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<String> list = new ArrayList<String>();

		File daten = new File("daten.txt");

		Scanner sca = new Scanner(daten);
		while (sca.hasNextLine()) {
			list.add(sca.nextLine());
		}

		StringBuilder stBuilder = new StringBuilder();
		stBuilder.append("Willkommen in der Anime Liste");
		stBuilder.append("\n");
		stBuilder.append("Waehlen sie einer dieser Funktionen");
		stBuilder.append("\n");
		stBuilder.append("1) Liste Anzeigen");
		stBuilder.append("\n");
		stBuilder.append("2) Anime Einf�gen");
		stBuilder.append("\n");
		stBuilder.append("3) Exit");

		while (true) {  //Programm geht nicht aus
		System.out.println();
		System.out.println(stBuilder);

		Scanner sc = new Scanner(System.in);

		String eingabe = "";
			eingabe = sc.nextLine();

			switch (eingabe) {
			case "1":
				for (String ele : list) {
					System.out.println(ele);

				}
				break;
			case "2":
				FileWriter file = new FileWriter(new File("daten.txt"));
				System.out.println("Geben sie einen Anime ein");
				eingabe = sc.nextLine();
				System.out.println("Anime geguckt? 1) Ja, 2) Nein");
				String eingabe2 = sc.nextLine();

				if (eingabe2.equals("1")) {
					list.add(eingabe + " geguck");
					Collections.sort(list);
				} else {
					list.add(eingabe + " nicht geguckt");
					Collections.sort(list);

				}

				for (String string : list) {
					file.append(string + "\n"); //Zeile hinzu
				}
				file.flush(); //erst in die File geschrieben
				file.close();
				break;
			case "3":
				System.exit(0);
				break;
			default:
				System.out.println("Error");

			}
		}
	}
}