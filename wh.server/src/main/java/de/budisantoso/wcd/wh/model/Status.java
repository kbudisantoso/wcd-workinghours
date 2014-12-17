package de.budisantoso.wcd.wh.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Status {

	private static final Logger LOGGER = LoggerFactory.getLogger(Status.class);

	private final long id;
	private final String message;

	public Status(long id, String message) {
		this.id = id;
		this.message = message;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Status with id={} and message={}.", id, message);
		}
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}
}
