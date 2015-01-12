package de.budisantoso.wcd.wh.dto;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.budisantoso.wcd.wh.util.ModelConstants;

public final class ClubDTO implements Serializable {

	private static final long serialVersionUID = ModelConstants.DTO_SERIAL_VERSION_UID;

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

	/**
	 * Hash calculation is based only on the ID field of the ClubDTO. Two ClubDTO have the same hash code if their IDs
	 * are equal. This method is used by {@link PersonDTO} in the set of ClubDTOs.
	 * 
	 * @see Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * Equals calculation is based only on the ID field of the ClubDTO. Two ClubDTO are considered equal if they have
	 * the same ID. This method is used by {@link PersonDTO} in the set of ClubDTOs.
	 * 
	 * @see Object#hashCode()
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClubDTO other = (ClubDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
