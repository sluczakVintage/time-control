package net.nfc.web.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.event.EventDirContext;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.*;
import net.nfc.db.entity.CConstrains.devicesStatus;
import net.nfc.db.entity.CConstrains.eventStatus;
import net.nfc.web.forms.CNewEventForm;
import net.nfc.web.forms.CObjectForm;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.mailservice.MailService;
import net.nfc.web.service.EventService;
import net.nfc.web.service.LocationService;
import net.nfc.web.service.UserService;
import net.nfc.web.validator.EventValidator;

import org.hibernate.annotations.CollectionOfElements;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.TimeZone;

/**
 * 
 * @author Maciek
 *
 */
@Controller
@RequestMapping("/events")
public class eventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private EventValidator eventValidator;
	
	@Autowired
	private MailService mailService;
	
	/**
	 * Zapisywanie nowo utworzonego eventu
	 * @param event
	 * @param result
	 * @return
	 * @throws AdException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveEvent(@ModelAttribute("newEventForm") CNewEventForm  eventForm,
			BindingResult result) throws AdException{
		eventForm.setStatus(eventStatus.CREATED.getText());
		eventValidator.validate(eventForm, result);
		if (result.hasErrors()) {
			return addEvent(eventForm, result);
		} else {
			CEvent event = new CEvent();
			/**
			 * TODO zmieniæ ustawianie creatora. pobieraæ go z sesji (?)
			 */
			event.setCreator(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName()));
			event.setEventCreationDate(new Timestamp(new java.util.Date().getTime()));
			event.setStatus(eventStatus.CREATED.getText());
			event.setComment(eventForm.getComment());
			event.setLocation(locationService.getLocation(eventForm.getLocation_id()));
			event.setTagId(locationService.getLocation(eventForm.getLocation_id()).getTagID());
			event.setEventType(eventForm.getEventType());
			
			 eventService.addEvent( event);
			 
			return new ModelAndView("redirect:/events.do");
			//return listEvents(new CSearchForm(), new CObjectForm());
		}
	}

	/**
	 * Domyœlne dzia³anie kontrolera - listowanie wszystkich CEvents
	 * @return
	 * @throws AdException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listEvents(@ModelAttribute("eventSearchForm")CSearchForm searchForm, @ModelAttribute("list") CObjectForm objectForm) throws AdException {
		Map<String, Object> model = new HashMap<String, Object>();
		if(objectForm.getObjectList() == null) {		
			model.put("events",  eventService.listEvents());
		}
		else {
			model.put("events", objectForm.getObjectList());
		}
		
		System.out.println();
		
		model.put("eventStatus", CConstrains.getEventMap());
		model.put("locations", locationService.listLocations());
		
		return new ModelAndView("event/eventsList", model);
	}

	
	@RequestMapping(value="/search", method= RequestMethod.POST)
	public ModelAndView searchEvent(@ModelAttribute("eventSearchForm") CSearchForm searchForm) throws AdException {
		
		//je¿eli user nie wprowadzi³ ¿adnych kryteriów to odeœlij go do poprzedniego widoku
		if(searchForm.getLocation_id() == 0 && searchForm.getStatus().equals("null")) {
			return listEvents(searchForm, new CObjectForm());
		}
		else {
			List<CEvent> eventList = eventService.searchEvent(searchForm);
			CObjectForm objectForm = new CObjectForm();
			objectForm.setObjectList(eventList);
			
			ModelAndView mav = listEvents(searchForm, objectForm);
			return mav;	
		}
	}
	
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEvent(@ModelAttribute("newEventForm") CNewEventForm eventForm,
			BindingResult result) throws AdException{
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("locations",  locationService.listActiveLocations());
		
		return new ModelAndView("event/addEvent", model);
	}

	/**
	 * Wyœwietlanie szczegó³ów danego eventu
	 * @param id
	 * @return
	 * @throws AdException
	 */
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public ModelAndView getEvent(@PathVariable("id") long id) throws AdException {
		
		CEvent event = eventService.getEvent(id);

		long t;
		
		if(event.getEventFinishDate() == null ) {
			t = 0;
		}
		else  t = event.getEventFinishDate().getTime() - event.getEventStartDate().getTime(); 
		
		SimpleDateFormat format= new SimpleDateFormat("HH:mm:ss");
		format.setTimeZone(TimeZone.getTimeZone("GMT"));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("event",  event );
		model.put("time", format.format(t));
		
		return new ModelAndView("event/eventsDetails", model);
	}

	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEvent(@PathVariable("id") long id, @ModelAttribute("editEventForm") CNewEventForm eventForm,
			BindingResult result) throws AdException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("event",  eventService.getEvent(id));
		model.put("locations",  locationService.listActiveLocations());
		
		return new ModelAndView("event/editEvent", model);
	}
	
	@RequestMapping(value = "/update/{eventID}", method = RequestMethod.POST)
	public ModelAndView updateEvent(@PathVariable("eventID") long id ,@ModelAttribute("editEventForm") CNewEventForm  eventForm,
			BindingResult result) throws AdException{
		CEvent event = eventService.getEvent(eventForm.getId());
		eventForm.setStatus(event.getStatus());
		eventValidator.validate(eventForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = editEvent(id, eventForm, result);
			return mav ;//new ModelAndView("company/editCompany");
		} else {
			event.setComment(eventForm.getComment());
			event.setEventType(eventForm.getEventType());
			
			event.setLocation(locationService.getLocation(eventForm.getLocation_id()));
			event.setTagId(locationService.getLocation(eventForm.getLocation_id()).getTagID());

			eventService.updateEvent( event);
			ModelAndView mav = getEvent(id);
			return new ModelAndView("redirect:/events/show/"+id);//mav;
		}
	}

	@RequestMapping(value="/close/{id}", method = RequestMethod.GET)
	public ModelAndView closeEvent(@PathVariable("id") long id, @ModelAttribute("editEventForm") CNewEventForm eventForm,
			BindingResult result) throws AdException {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("event",  eventService.getEvent(id));
		
		return new ModelAndView("event/closeEvent", model);
	}
	
	@RequestMapping(value = "/saveClosed/{eventID}", method = RequestMethod.POST)
	public ModelAndView saveClosedEvent(@PathVariable("eventID") long id ,@ModelAttribute("editEventForm") CNewEventForm  eventForm,
			BindingResult result) throws AdException{
		CEvent event = eventService.getEvent(eventForm.getId());
		eventForm.setStatus(event.getStatus());
		eventValidator.validate(eventForm, result);
		if (result.hasErrors()) {
			ModelAndView mav = closeEvent(id, eventForm, result);
			return mav ;//new ModelAndView("company/editCompany");
		} else {
			
			String creatorComment = event.getComment();
			String userComment = eventForm.getComment();
			if(! creatorComment.equals(userComment)) {
				event.setComment("poczatkowy : " + creatorComment + " | \nkoncowy : " + userComment);
			}	
			
			event.setEventType(eventForm.getEventType());
			event.setStatus(eventStatus.CLOSED.getText());
			
			eventService.updateEvent( event);
			ModelAndView mav = getEvent(id);
			return mav;
		}
	}
	
	
	
	@RequestMapping(value="/show/pdf/{eventID}", method = RequestMethod.GET)
	public ModelAndView listEventsPDF(@PathVariable("eventID") long id) throws AdException {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("event", eventService.getEvent(id));
		
		return new ModelAndView("pdf", model);
	}
	


}
