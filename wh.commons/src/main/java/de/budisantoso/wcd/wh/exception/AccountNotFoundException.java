package de.budisantoso.wcd.wh.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String key, String value) {
		super(String.format("No account entry found with %s: <%s>", key, value));
	}
}
