package de.budisantoso.wcd.wh.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.budisantoso.wcd.wh.util.ModelConstants;

public final class ClubDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	@NotEmpty
	@Size(max = ModelConstants.MAX_LENGTH_CLUB_NAME)
	private String name;

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

}
