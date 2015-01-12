package de.budisantoso.wcd.wh.persistence.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkingEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkingEvent.class);

	private String name;
	private Club club;
	private Calendar dateAndTime;

	public WorkingEvent(String name, Club club, Calendar dateAndTime) {
		this.name = name;
		this.club = club;
		this.dateAndTime = dateAndTime;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created WorkingEvent with name='{}', club={} and dateAndTime={}.", name, club, dateAndTime);
		}
	}

	public WorkingEvent(String name, Club club, int dayOfMonth, int month, int year, int hourOfDay, int minute) {
		this(name, club, new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute));
	}

	public WorkingEvent(String name, Club club) {
		this(name, club, GregorianCalendar.getInstance());
	}

	@SuppressWarnings("unused")
	private WorkingEvent() {
		// private default constructor to ensure serializability
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created WorkingEvent by default constructor: name='{}', club={}, dateAndTime={}", name, club,
					dateAndTime);
		}
	}

	public String getName() {
		return name;
	}

	public Club getClub() {
		return club;
	}

	public Calendar getDateAndTime() {
		return dateAndTime;
	}

}
