package domain;

public class Benutzer {

	private int m_id; // Atribute
	private String m_name;

	public Benutzer() { // Methode //Konstruktor

	}

	public Benutzer(String name, int id) {

		m_name = name;
		m_id = id;

	}

	public void setName(String name) {

		m_name = name;

	}

	public String getName() {

		return m_name;

	}

	public void setId(int id) {

		m_id = id;

	}

	public void setId(String id) {

		m_id = Integer.parseInt(id);

	}

	public int getId() {

		return m_id;

	}

	@Override
	public String toString() {

		return m_name;

	}

}