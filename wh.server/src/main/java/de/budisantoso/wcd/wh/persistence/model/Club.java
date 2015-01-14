package de.budisantoso.wcd.wh.persistence.model;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import de.budisantoso.wcd.wh.dto.ClubDTO;
import de.budisantoso.wcd.wh.util.ModelConstants;
import de.budisantoso.wcd.wh.util.PreCondition;

@Document
public class Club {

	private static final Logger LOGGER = LoggerFactory.getLogger(Club.class);

	@Id
	private String id;

	@Indexed(unique = true)
	private String name;

	@SuppressWarnings("unused")
	private Club() {
		// private default constructor for db
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Club by default constructor: name='{}'", name);
		}
	}

	public Club(String name) {
		checkName(name);
		this.name = name;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Club with name='{}'.", name);
		}
	}

	public Club(ClubDTO club) {
		this(club.getName());
		this.id = club.getId();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Club update(String name) {
		checkName(name);
		this.name = name;
		return this;
	}

	private void checkName(String name) {
		PreCondition.notNull(name, "Name cannot be null!");
		PreCondition.notEmpty(name, "Name cannot be empty!");
		PreCondition.isTrue(name.length() <= ModelConstants.CLUB_NAME_MAX_LENGTH, "Name cannot be longer than %d characters",
				ModelConstants.CLUB_NAME_MAX_LENGTH);
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Club other = (Club) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Club [id=" + id + ", name=" + name + "]";
	}

	public static Set<Club> createClubSet(Set<ClubDTO> clubDTOs) {
		Set<Club> clubs = new HashSet<Club>(clubDTOs.size());
		for (ClubDTO club : clubDTOs) {
			clubs.add(new Club(club));
		}
		return clubs;
	}

}
