package de.budisantoso.wcd.wh.persistence.model;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkingLog implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkingLog.class);
	
	private Person worker;
	private WorkingEvent event;
	private float hours;

	private String comment = "";

	public WorkingLog(Person worker, WorkingEvent event, float hours, String comment) {
		this.worker = worker;
		this.event = event;
		this.comment = comment;
		this.hours = hours;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created WorkingLog with worker={}, event={}, hours={} and comment='{}'.", worker, event,
					hours, comment);
		}
	}

	public WorkingLog(Person worker, WorkingEvent event, float hours) {
		this(worker, event, hours, "");
	}

	public WorkingLog(Person worker, WorkingEvent event, int hours, int minutes, String comment) {
		this(worker, event, minutes / 60.0f + hours, "");
	}

	public WorkingLog(Person worker, WorkingEvent event, int hours, int minutes) {
		this(worker, event, hours, minutes, "");
	}

	@SuppressWarnings("unused")
	private WorkingLog() {
		// private default constructor to ensure serializability
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created WorkingLog by default constructor: worker={}, event={}, hours={}, comment='{}'",
					worker, event, hours, comment);
		}
	}

	public String getName() {
		return comment;
	}

	public Person getWorker() {
		return worker;
	}

	public float getHours() {
		return hours;
	}

	public String getComment() {
		return comment;
	}

}
