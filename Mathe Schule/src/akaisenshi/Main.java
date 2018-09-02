package akaisenshi;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Volt r = new Volt();
		Ampere a = new Ampere();
		Ohm o = new Ohm();
		Scanner sc = new Scanner(System.in);

		String eingabe = "";
		while (true) {
			System.out.println("\n" + "Was wollen sie berechnen?");
			System.out.println("U für Volt");
			System.out.println("I für Ampere");
			System.out.println("R für Ohm");

			eingabe = sc.nextLine();
			switch (eingabe) {
			case "U":
			case "u":
				r.volt();
				break;
			case "i":
			case "I":
				a.ampere();
				break;
			case "r":
			case "R":
				o.ohm();
				break;
			}
		}
	}
}
