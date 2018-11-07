package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Benutzer")
public class User {

	private String name;
	@Id
	private Long ID;

	public Long getId() {

		return this.ID;

	}

}