package de.budisantoso.wcd.wh.persistence.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import de.budisantoso.wcd.wh.dto.AccountDTO;
import de.budisantoso.wcd.wh.util.ModelConstants;
import de.budisantoso.wcd.wh.util.PasswordHashUtil;
import de.budisantoso.wcd.wh.util.PreCondition;

@Document
public class Account {

	private static final Logger LOGGER = LoggerFactory.getLogger(Account.class);
	private static final PasswordHashUtil PASSWORD_UTIL = new PasswordHashUtil();

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	private String passwordHash;

	@DBRef
	private Person person;

	@SuppressWarnings("unused")
	private Account() {
		// private default constructor for db
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Account by default constructor: name='{}', passwordHash='{}', person={}", username,
					passwordHash, person);
		}
	}

	public Account(String username, String password, Person person) {
		checkUsername(username);
		checkPassword(password);
		checkPerson(person);

		this.username = username;
		this.passwordHash = PASSWORD_UTIL.createHash(password);
		this.person = person;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Account with name='{}' and password=xxxxxxxx.", username);
		}
	}

	public Account(AccountDTO accountEntry) {
		this(accountEntry.getUsername(), accountEntry.getPassword(), new Person(accountEntry.getPerson()));
		this.id = accountEntry.getId();
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Person getPerson() {
		return person;
	}

	public boolean validatePassword(String password) {
		return PASSWORD_UTIL.validatePassword(password, passwordHash);
	}

	public Account update(String username, String password, Person person) {
		checkUsername(username);
		checkPassword(password);
		checkPerson(person);

		this.username = username;
		this.passwordHash = PASSWORD_UTIL.createHash(password);
		this.person = person;

		return this;
	}

	private void checkPerson(Person person) {
		PreCondition.notNull(person, "Person cannot be null!");
		PreCondition
				.isTrue(StringUtils.isNotEmpty(person.getId()),
						"Person cannot be unmanaged. Person has no Id which indicates that it is not persisted yet; persist Person first.");
	}

	private void checkUsername(String username) {
		PreCondition.notNull(username, "Username cannot be null!");
		PreCondition.notEmpty(username, "Username cannot be empty!");
		PreCondition.isTrue(username.length() <= ModelConstants.ACCOUNT_USERNAME_MAX_LENGTH,
				"Username cannot be longer than %d characters.", ModelConstants.ACCOUNT_USERNAME_MAX_LENGTH);
	}

	private void checkPassword(String password) {
		PreCondition.notNull(password, "Password cannot be null!");
		PreCondition.notEmpty(password, "Password cannot be empty!");
		PreCondition.isTrue(password.length() >= ModelConstants.ACCOUNT_PASSWORD_MIN_LENGTH,
				"Password must have at least %d characters.", ModelConstants.ACCOUNT_PASSWORD_MIN_LENGTH);
		PreCondition.isTrue(password.length() <= ModelConstants.ACCOUNT_PASSWORD_MAX_LENGTH,
				"Password cannot be longer than %d characters.", ModelConstants.ACCOUNT_PASSWORD_MAX_LENGTH);
		PreCondition.isTrue(password.matches(ModelConstants.ACCOUNT_PASSWORD_PATTERN),
				"Password must consist of at least one digit and one character.");
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
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", person=" + person + "]";
	}

}
