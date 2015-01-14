package de.budisantoso.wcd.wh.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import de.budisantoso.wcd.wh.util.ModelConstants;

public class WorkingEventDTO implements Serializable {

	private static final long serialVersionUID = ModelConstants.DTO_SERIAL_VERSION_UID;

	private String id;

	@NotNull
	private Date dateAndTime;

	@NotEmpty
	@Size(max = ModelConstants.WORKING_EVENT_NAME_MAX_LENGTH)
	private String name;
	
	@NotNull
	private ClubDTO club;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public ClubDTO getClub() {
		return club;
	}

	public void setClub(ClubDTO club) {
		this.club = club;
	}

}
