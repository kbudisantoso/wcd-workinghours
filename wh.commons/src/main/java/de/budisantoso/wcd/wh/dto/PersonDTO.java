package de.budisantoso.wcd.wh.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.budisantoso.wcd.wh.util.ModelConstants;

public class PersonDTO implements Serializable {

	private static final long serialVersionUID = ModelConstants.DTO_SERIAL_VERSION_UID;

	private String id;

	@NotEmpty
	@Size(max = ModelConstants.PERSON_NAME_MAX_LENGTH)
	private String name;
	
	@NotNull
	private Set<ClubDTO> clubs = new HashSet<ClubDTO>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ClubDTO> getClubs() {
		return clubs;
	}

	public void setClubs(Set<ClubDTO> clubs) {
		this.clubs = clubs;
	}
}
