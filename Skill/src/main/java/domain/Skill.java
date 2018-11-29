package domain;

public class Skill {

	private int m_id;
	private String m_name;
	private String m_beschreibung;
	private Thema m_thema;

	public Skill() {

	}

	public Skill(String name, String beschreibung, Thema thema, int id) {

		m_id = id;
		m_name = name;
		m_beschreibung = beschreibung;
		m_thema = thema;

	}

	public void setName(String name) {

		m_name = name;

	}

	public String getName() {

		return m_name;

	}

	public String getBechreibung() {

		return m_beschreibung;

	}

	public void setBechreibung(String beschreibung) {

		this.m_beschreibung = beschreibung;

	}

	public void setThema(Thema thema) {

		m_thema = thema;

	}

	public Thema getThema() {

		return m_thema;

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