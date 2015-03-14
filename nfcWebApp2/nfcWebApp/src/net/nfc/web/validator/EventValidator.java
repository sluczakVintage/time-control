package net.nfc.web.validator;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains.eventStatus;
import net.nfc.web.forms.CNewEventForm;
import net.nfc.web.service.EventService;
import net.nfc.web.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Repository("eventValidator")
public class EventValidator implements Validator {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private LocationService locationService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return CNewEventForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		CNewEventForm event = (CNewEventForm) target;
		
		if(event.getEventType().length() > 100) errors.rejectValue("eventType", "eventType.error");
		if(event.getComment().length() > 500)	errors.rejectValue("comment", "eventComment.error");
		if(event.getLocation_id() == 0) errors.rejectValue("location_id", "eventLocationID.required");
	
		 
		try {
			if(event.getStatus() != null && event.getLocation_id() != 0){
				if(event.getId() != 0) {
					if(event.getStatus().equals(eventStatus.STARTED.getText()) && eventService.getEvent(event.getId()).getId() != event.getId()) {
						if (eventService.getStartedEvent(locationService.getLocation(event.getLocation_id()).getTagID(), null) != null ) {
							errors.rejectValue("status", "eventStatusStarted.error");
						}
					}else if(event.getStatus().equals(eventStatus.CREATED.getText())&& eventService.getEvent(event.getId()).getId() != event.getId()) {
						if (eventService.getCreatedEvent(locationService.getLocation(event.getLocation_id()).getTagID()) != null ) {
							errors.rejectValue("status", "eventStatusCreated.error");
						}
					}
				}else {
					if(event.getStatus().equals(eventStatus.STARTED.getText()) ) {
						if (eventService.getStartedEvent(locationService.getLocation(event.getLocation_id()).getTagID(), null) != null ) {
							errors.rejectValue("status", "eventStatusStarted.error");
						}
					}else if(event.getStatus().equals(eventStatus.CREATED.getText())) {
						if (eventService.getCreatedEvent(locationService.getLocation(event.getLocation_id()).getTagID()) != null ) {
							errors.rejectValue("status", "eventStatusCreated.error");
						}
					}
				}
			}
			
		} catch (AdException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
