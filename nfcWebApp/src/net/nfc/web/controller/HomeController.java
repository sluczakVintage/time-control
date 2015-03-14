package net.nfc.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CConstrains.eventStatus;
import net.nfc.db.entity.CEvent;
import net.nfc.web.forms.CSearchForm;
import net.nfc.web.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/welcome")
public class HomeController {

	@Autowired
	private EventService eventService;
	
	@RequestMapping( method = RequestMethod.GET)
	public ModelAndView getHome() throws AdException{
		
		CSearchForm form = new CSearchForm();
				
		Map<String, Object> model = new HashMap<String, Object>();
		
		List<CEvent> lista = eventService.listEvents();
		
		int created = 0;
		int started = 0;
		int finished = 0;
		int closed = 0;
		
		for (CEvent cEvent : lista) {
			if(cEvent.getStatus().equals(eventStatus.CREATED.getText())){
				created++;
			}else if(cEvent.getStatus().equals(eventStatus.STARTED.getText())){
				started++;
			}else if(cEvent.getStatus().equals(eventStatus.FINISHED.getText())){
				finished++;
			}else if(cEvent.getStatus().equals(eventStatus.CLOSED.getText())){
				closed++;
			}
		}

		model.put("created",created);
		model.put("started",started);
		model.put("finished",finished);
		model.put("closed",closed);
		
//		form.setStatus(eventStatus.CREATED.getText());
//		model.put("created", eventService.searchEvent(form).size());
//		form.setStatus(eventStatus.STARTED.getText());
//		model.put("started", eventService.searchEvent(form).size());
//		form.setStatus(eventStatus.FINISHED.getText());
//		model.put("finished", eventService.searchEvent(form).size());
//		form.setStatus(eventStatus.CLOSED.getText());
//		model.put("closed", eventService.searchEvent(form).size());
//		
		return new ModelAndView("/welcome", model);
	}
	
}
