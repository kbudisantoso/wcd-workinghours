package de.budisantoso.wcd.wh.util;

public interface ModelConstants {

	public static final long DTO_SERIAL_VERSION_UID = 1L;

	public static final int CLUB_NAME_MAX_LENGTH = 80;
	public static final int PERSON_NAME_MAX_LENGTH = 80;
	public static final int ROLE_NAME_MAX_LENGTH = 40;
	public static final int ACCOUNT_USERNAME_MAX_LENGTH = 20;
	public static final int ACCOUNT_PASSWORD_MIN_LENGTH = 8;
	public static final int ACCOUNT_PASSWORD_MAX_LENGTH = 80;
	public static final int WORKING_EVENT_NAME_MAX_LENGTH = 40;

	public static final String ACCOUNT_PASSWORD_PATTERN = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*).*$";


}
