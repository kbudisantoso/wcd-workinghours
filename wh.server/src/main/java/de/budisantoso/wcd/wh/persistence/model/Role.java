package de.budisantoso.wcd.wh.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import de.budisantoso.wcd.wh.util.ModelConstants;
import de.budisantoso.wcd.wh.util.PreCondition;

@Document
public class Role {

	private static final Logger LOGGER = LoggerFactory.getLogger(Role.class);

	@Id
	private String id;

	private String name;

	private boolean allowedToManageClubs;
	private boolean allowedToManagePersons;
	private boolean allowedToAssignAccounts;

	@SuppressWarnings("PMD")
	public Role() {
	}

	public Role(String name) {
		checkName(name);

		this.name = name;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Role with name='{}'.", name);
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Role update(String name) {
		checkName(name);
		this.name = name;
		return this;
	}

	private void checkName(String name) {
		PreCondition.notNull(name, "Name cannot be null!");
		PreCondition.notEmpty(name, "Name cannot be empty!");
		PreCondition.isTrue(name.length() <= ModelConstants.ROLE_NAME_MAX_LENGTH,
				"Name cannot be longer than %d characters", ModelConstants.ROLE_NAME_MAX_LENGTH);
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
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
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

}
