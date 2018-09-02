package akaisenshi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Utils {

	File animeDB;
	String pathOfDB;
	public Properties states = new Properties();

	public static void test(String a) {
		System.out.println(a + " b");
	}

	public void load() {
		FileOutputStream out = null;
		FileInputStream in = null;
		setPathOfDB("C:\\Test\\animedb.properties");
		setAnimeDB(new File(getPathOfDB()));
		try {
			if (!getAnimeDB().exists()) {
				out = new FileOutputStream(getAnimeDB());

				states.setProperty("One Piece", "1");
				states.setProperty("Naruto", "1");
				states.setProperty("Attack on Titan", "2");
				states.setProperty("Fairy Tail", "1");

				try {
					states.store(out, "Datei Angelegt an");
				} finally {
					if (null != out) {
						out.close();
					}
				}
			}
			try {

				in = new FileInputStream(getAnimeDB());
				states.load(in);
			} finally {
				if (null != in) {
					in.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void store(String anime, String watched) throws IOException {
		FileOutputStream out = null;

		if (states != null) {
			try {

				out = new FileOutputStream(getAnimeDB());

				states.put(anime, watched);
				states.store(out, "");
			} finally {
				if (null != out) {
					out.close();
				}
			}
		}
	}

	public File getAnimeDB() {
		return animeDB;
	}

	public void setAnimeDB(File animeDB) {
		this.animeDB = animeDB;
	}

	public String getPathOfDB() {
		return pathOfDB;
	}

	public void setPathOfDB(String pathOfDB) {
		this.pathOfDB = pathOfDB;
	}

	public void startUtils() {
		load();
		while (true) {

			StringBuilder sb = new StringBuilder();
			sb.append("Willkommen in der Anime DB");
			sb.append("\n");
			sb.append("Optionen:");
			sb.append("\n\n");
			sb.append("1) Liste der Animes ausgeben\n");
			sb.append("2) Neuer Anime hinzufügen\n");
			sb.append("3) Beenden\n");
			sb.append("\n\n");
			sb.append("Auswahl:");
			System.out.println(sb.toString());
			try {

				Scanner scanner = new Scanner(System.in);
				String input = scanner.nextLine();

				switch (Integer.parseInt(input)) {
				case 1:
					states.list(System.out);

					break;
				case 2:
					System.out.println("Bitte neuen Anime name:\n");
					String anime = scanner.nextLine();
					System.out.println("Hast du ihn gesehn ? ( 1 für ja  | 2 für nein):\n");
					String watched = scanner.nextLine();
					store(anime, watched);
					break;
				case 3:
					System.exit(0);
					break;

				default:
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static void main(String[] args) {
		Utils start = new Utils();
	}

}
