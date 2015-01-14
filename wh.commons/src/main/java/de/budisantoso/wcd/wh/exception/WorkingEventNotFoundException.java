package de.budisantoso.wcd.wh.exception;

public class WorkingEventNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public WorkingEventNotFoundException(String key, String value) {
		super(String.format("No working event entry found with %s: <%s>", key, value));
	}
}
