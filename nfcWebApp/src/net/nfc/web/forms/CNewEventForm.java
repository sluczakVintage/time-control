package net.nfc.web.forms;

import java.sql.Timestamp;


public class CNewEventForm {

	private long id;
	private String eventType;
	private Timestamp eventCreationDate;
	private String comment;
	private long creator_id;
	private long location_id;
	private String status;
	
	public CNewEventForm() {}

	public CNewEventForm(long id, String eventType,
			Timestamp eventCreationDate, String comment,
			long creator_id, long location_id, String status) {
		super();
		this.id = id;
		this.eventType = eventType;
		this.eventCreationDate = eventCreationDate;
		this.comment = comment;
		this.creator_id = creator_id;
		this.location_id = location_id;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Timestamp getEventCreationDate() {
		return eventCreationDate;
	}

	public void setEventCreationDate(Timestamp eventCreationDate) {
		this.eventCreationDate = eventCreationDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getCreator_id() {
		return creator_id;
	}

	public void setCreator_id(long creator_id) {
		this.creator_id = creator_id;
	}

	public long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
		
}
