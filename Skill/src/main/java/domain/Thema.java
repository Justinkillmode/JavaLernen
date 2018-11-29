package domain;

public class Thema {

	private int m_id;
	private String m_name;

	public Thema() {

	}

	public Thema(String name, int id) {

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