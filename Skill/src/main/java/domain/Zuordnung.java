package domain;

public class Zuordnung {

	private int m_id;
	private Benutzer benutzer;
	private Skill skill;
	private String m_lust;

	public Zuordnung() {

	}

	public Zuordnung(Benutzer benutzer, Skill skill, String lust, int id) {

		this.benutzer = benutzer;
		this.skill = skill;
		m_lust = lust;
		m_id = id;

	}

	public Benutzer getBenutzer() {

		return benutzer;

	}

	public void setBenutzer(Benutzer benutzer) {

		this.benutzer = benutzer;

	}

	public Skill getSkill() {

		return skill;

	}

	public void setSkill(Skill skill) {

		this.skill = skill;

	}

	public String getLust() {

		return m_lust;

	}

	public void setLust(String lust) {

		m_lust = lust;

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

		return m_id + ": " + benutzer.getName() + " || " + skill.getName() + " || " + skill.getBechreibung() + " || "
				+ m_lust;

	}

}