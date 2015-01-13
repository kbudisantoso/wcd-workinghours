package de.budisantoso.wcd.wh.exception;

public class ClubNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClubNotFoundException(String key, String value) {
		super(String.format("No club entry found with %s: <%s>", key, value));
	}
}
