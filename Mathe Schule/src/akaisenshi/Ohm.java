package akaisenshi;

import java.util.Scanner;

public class Ohm {

	public static void ohm() {
		Scanner sc = new Scanner(System.in);
		Scanner sca = new Scanner(System.in);
		float eingabe1;
		float eingabe2;
		float loesung;

		System.out.println("Geben sie den Wert von U ein.");
		eingabe1 = sc.nextFloat();
		System.out.println("\n" + "Geben sie ein ob ihr Wert k, M, G T");
		System.out.println("oder m, �, n beinhaltet oder nichts wenns nichts von den ist.");
		String groese1 = "";
		groese1 = sca.nextLine();

		System.out.println("\n" + "Geben sie den Wert von I ein");
		eingabe2 = sc.nextFloat();
		System.out.println("\n" + "Geben sie ein ob ihr Wert k, M, G T");
		System.out.println("oder m, �, n beinhaltet oder nichts wenns nichts von den ist.");
		String groese2 = "";
		groese2 = sca.nextLine();

		switch (groese1) {

		case "k":
		case "K":
			eingabe1 = eingabe1 * 1000;
			break;
		case "M":
			eingabe1 = eingabe1 * 1000000;
			break;
		case "G":
		case "g":
			eingabe1 = eingabe1 * 1000000000;
			break;
		case "T":
		case "t":
			eingabe1 = eingabe1 * (1000000 * 1000000);
			break;
		case "m":
			eingabe1 = eingabe1 / 1000;
			break;
		case "�":
			eingabe1 = eingabe1 / 1000000;
			break;
		case "n":
		case "N":
			eingabe1 = eingabe1 / 1000000000;
			break;
		default:

			break;

		}
		switch (groese2) {

		case "k":
		case "K":
			eingabe2 = eingabe2 * 1000;
			break;
		case "M":
			eingabe2 = eingabe2 * 1000000;
			break;
		case "G":
		case "g":
			eingabe2 = eingabe2 * 1000000000;
			break;
		case "T":
		case "t":
			eingabe2 = eingabe2 * (1000000 * 1000000);
			break;
		case "m":
			eingabe2 = eingabe2 / 1000;
			break;
		case "�":
			eingabe2 = eingabe2 / 1000000;
			break;
		case "n":
		case "N":
			eingabe2 = eingabe2 / 1000000000;
			break;
		default:

			break;

		}

		loesung = eingabe1 / eingabe2;
		System.out.println("\n" + "Die L�sung ist " + loesung + " Ohm");

	}
	
}
