package de.budisantoso.wcd.wh.exception;

public class ReferencedClubDeleteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReferencedClubDeleteException(String key, String value) {
		super(String.format("Club (%s: '%s') cannot be deleted because it is still referenced by WorkingEvents. "
				+ "Delete the referencing WorkingEvents first.", key, value));
	}
}
