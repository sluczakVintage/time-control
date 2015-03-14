package db.entity;

public class CConstrains {
	
	
	public enum eventStatus {
		  STARTED("EVENT_STATUS_STARTED"),
		  FINISHED("EVENT_STATUS_FINISHED"),
		  CREATED("EVENT_STATUS_CREATED"),
		  SUSPENDED("EVENT_STATUS_SUSPENDED"),
		  CLOSED("EVENT_STATUS_CLOSED");

		  private String text;

		  eventStatus(String text) {
		    this.text = text;
		  }

		  public String getText() {
		    return this.text;
		  }

	}
	public enum locationStatus {
		  ACTIVE("LOCATION_STATUS_ACTIVE"),
		  INACTIVE("LOCATION_STATUS_INACTIVE"),
		  ERROR("LOCATION_STATUS_ERROR"),
		  CREATED("LOCATION_STATUS_CREATED");

		  private String text;

		  locationStatus(String text) {
		    this.text = text;
		  }

		  public String getText() {
		    return this.text;
		  }

	}
	public enum devicesStatus {

		  ACTIVE("DEVICE_STATUS_ACTIVE"),
		  INACTIVE("DEVICE_STATUS_INACTIVE"),
		  CREATED("DEVICE_STATUS_CREATED");

		  private String text;

		 devicesStatus(String text) {
		    this.text = text;
		  }

		  public String getText() {
		    return this.text;
		  }

	}
	
	public enum responseStatus {
		EVENT_FINISHED("EVENT_FINISHED"),
		EVENT_STARTED("EVENT_STARTED"),
		CREATED_AND_STARTED("NEW_EVENT_CREATED_AND_STARTED"),
		USER_NOT_FOUND("USER_NOT_FOUND_ERROR"),
		WRONG_PASS("WRONG_PASSWORD_ERROR"),
		ACCESS_DENIED("ACCESS_DENIED_ERROR"),
		ACCESS_GRANTED("ACCESS_GRANTED"),
		LOCATION_CREATED_COMPANY_CREATED("LOCATION_CREATED_COMPANY_CREATED_SUCCESS"),
		LOCATION_CREATED("LOCATION_CREATED_SUCCESS"),
		TAG_ALREADY_EXISTS("TAG_ALREADY_EXISTS_ERROR"),
		SERVER_ERROR("SERVER_ERROR"),
		EVENT_ALREADY_STARTED("EVENT_ALREADY_STARTED_ERROR"),
		TAG_NOT_FOUND("TAG_NOT_FOUND_ERROR");
		
		private String text;

		 responseStatus(String text) {
		    this.text = text;
		  }

		  public String getText() {
		    return this.text;
		  }
	}

	public enum userPrivileges {
		
		USER("ROLE_USER"),
		SUPER_USER("ROLE_SUPER_USER"),
		ADMIN("ROLE_ADMIN");
		
		private String i;

		 private userPrivileges(String i) {
		    this.i = i;
		  }

		  public String getText() {
		    return this.i;
		  }
	}
	
	
}

	
	
	
	

