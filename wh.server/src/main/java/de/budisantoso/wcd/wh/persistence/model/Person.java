package de.budisantoso.wcd.wh.persistence.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import de.budisantoso.wcd.wh.util.ModelConstants;
import de.budisantoso.wcd.wh.util.PreCondition;

@Document
public class Person {

	private static final Logger LOGGER = LoggerFactory.getLogger(Person.class);

	@Id
	private String id;

	private String name;

	@DBRef
	private Set<Club> clubs = new HashSet<Club>();

	@SuppressWarnings("unused")
	private Person() {
		// private default constructor for db
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Person by default constructor: name='{}'", name);
		}
	}

	public Person(String name, Set<Club> clubs) {
		checkName(name);
		checkClubs(clubs);

		this.name = name;
		this.clubs.addAll(clubs);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Person with name='{}' and clubs={}.", name, clubs);
		}
	}

	public Person(String name) {
		this(name, Collections.<Club> emptySet());
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Club> getClubs() {
		return clubs;
	}

	protected void setClubs(Set<Club> clubs) {
		checkClubs(clubs);
		this.clubs.clear();
		this.clubs.addAll(clubs);
	}

	public Person update(String name, Set<Club> clubs) {
		checkName(name);
		this.name = name;
		setClubs(clubs);
		return this;
	}

	public Person joinClub(Club club) {
		checkClub(club);
		clubs.add(club);
		return this;
	}

	private void checkName(String name) {
		PreCondition.notNull(name, "Name cannot be null!");
		PreCondition.notEmpty(name, "Name cannot be empty!");
		PreCondition.isTrue(name.length() <= ModelConstants.PERSON_NAME_MAX_LENGTH,
				"Name cannot be longer than %d characters", ModelConstants.PERSON_NAME_MAX_LENGTH);
	}

	private void checkClub(Club club) {
		PreCondition.notNull(club, "Club cannot be null!");
		PreCondition
				.isTrue(StringUtils.isNotEmpty(club.getId()),
						"Club cannot be unmanaged. Club has no Id which indicates that it is not persisted yet; persist Club first.");
	}

	private void checkClubs(Collection<Club> clubs) {
		for (Club club : clubs) {
			checkClub(club);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clubs == null) ? 0 : clubs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Person other = (Person) obj;
		if (clubs == null) {
			if (other.clubs != null)
				return false;
		} else if (!clubs.equals(other.clubs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", clubs=" + clubs + "]";
	}

}
