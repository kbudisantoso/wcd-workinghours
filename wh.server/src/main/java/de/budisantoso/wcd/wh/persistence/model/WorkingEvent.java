package de.budisantoso.wcd.wh.persistence.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import de.budisantoso.wcd.wh.dto.WorkingEventDTO;
import de.budisantoso.wcd.wh.util.ModelConstants;
import de.budisantoso.wcd.wh.util.PreCondition;

public class WorkingEvent {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorkingEvent.class);

	@Id
	private String id;

	private String name;
	private Date dateAndTime;

	@DBRef
	private Club club;

	public WorkingEvent(String name, Club club, Date dateAndTime) {
		checkName(name);
		checkClub(club);
		checkDateAndTime(dateAndTime);

		this.name = name;
		this.club = club;
		this.dateAndTime = dateAndTime;

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created WorkingEvent with name='{}', club={} and dateAndTime={}.", name, club, dateAndTime);
		}
	}

	public WorkingEvent(String name, Club club, Calendar calendar) {
		this(name, club, calendar.getTime());
	}

	public WorkingEvent(String name, Club club, int dayOfMonth, int month, int year, int hourOfDay, int minute) {
		this(name, club, new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute));
	}

	public WorkingEvent(String name, Club club) {
		this(name, club, GregorianCalendar.getInstance());
	}

	public WorkingEvent(WorkingEventDTO workingEvent) {
		this(workingEvent.getName(), new Club(workingEvent.getClub()), workingEvent.getDateAndTime());
		this.id = workingEvent.getId();
	}

	@SuppressWarnings("unused")
	private WorkingEvent() {
		// private default constructor to ensure serializability
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Created WorkingEvent by default constructor: name='{}', club={}, dateAndTime={}", name, club,
					dateAndTime);
		}
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Club getClub() {
		return club;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public WorkingEvent updateName(String name) {
		checkName(name);
		this.name = name;
		return this;
	}

	public WorkingEvent updateDateAndTime(Date dateAndTime) {
		checkDateAndTime(dateAndTime);
		this.dateAndTime = dateAndTime;
		return this;
	}

	private void checkName(String name) {
		PreCondition.notNull(name, "Name cannot be null!");
		PreCondition.notEmpty(name, "Name cannot be empty!");
		PreCondition.isTrue(name.length() <= ModelConstants.WORKING_EVENT_NAME_MAX_LENGTH,
				"Name cannot be longer than %d characters.", ModelConstants.WORKING_EVENT_NAME_MAX_LENGTH);
	}

	private void checkDateAndTime(Date dateAndTime) {
		PreCondition.notNull(dateAndTime, "DateAndTime cannot be null!");
	}

	private void checkClub(Club club) {
		PreCondition.notNull(club, "Club cannot be null!");
		PreCondition
				.isTrue(StringUtils.isNotEmpty(club.getId()),
						"Club cannot be unmanaged. Club has no Id which indicates that it is not persisted yet; persist Club first.");
	}

	public void update(String name, Club club, Date dateAndTime) {
		checkName(name);
		checkClub(club);
		checkDateAndTime(dateAndTime);

		this.name = name;
		this.club = club;
		this.dateAndTime = dateAndTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkingEvent other = (WorkingEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "WorkingEvent [id=" + id + ", name=" + name + ", dateAndTime=" + dateAndTime + ", club=" + club + "]";
	}

}
