package de.budisantoso.wcd.wh.exception;

public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String key, String value) {
		super(String.format("No person entry found with %s: <%s>", key, value));
	}
}
