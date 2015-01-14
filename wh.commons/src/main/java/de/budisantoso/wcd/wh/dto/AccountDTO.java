package de.budisantoso.wcd.wh.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import de.budisantoso.wcd.wh.util.ModelConstants;

public class AccountDTO implements Serializable {

	private static final long serialVersionUID = ModelConstants.DTO_SERIAL_VERSION_UID;

	private String id;

	@NotEmpty
	@Size(max = ModelConstants.ACCOUNT_USERNAME_MAX_LENGTH)
	private String username;

	@NotEmpty
	@Size(min = ModelConstants.ACCOUNT_PASSWORD_MIN_LENGTH, max = ModelConstants.ACCOUNT_PASSWORD_MAX_LENGTH)
	@Pattern(regexp = ModelConstants.ACCOUNT_PASSWORD_PATTERN)
	private String password;

	private PersonDTO person;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUserame(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PersonDTO getPerson() {
		return person;
	}

	public void setPerson(PersonDTO person) {
		this.person = person;
	}

	@JsonIgnore
	public String getName() {
		if (null == person) {
			person = new PersonDTO();
			person.setName(username);
		}
		return person.getName();
	}

}
