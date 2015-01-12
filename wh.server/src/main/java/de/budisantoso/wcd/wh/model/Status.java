package de.budisantoso.wcd.wh.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Status implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Status.class);

	private long id = 0;
	private String message = "red";

	public Status(long id, String message) {
		this.id = id;
		this.message = message;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Status with id={} and message='{}'.", id, message);
		}
	}

	@SuppressWarnings("unused")
	private Status() {
		// private default constructor to ensure serializability
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created Status by default constructor: id={}, message='{}'", id, message);
		}
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}
}
