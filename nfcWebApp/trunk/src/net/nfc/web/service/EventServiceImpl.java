package net.nfc.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.nfc.db.dao.AdException;
import net.nfc.db.dao.CEventDAO;
import net.nfc.db.entity.CEvent;
import net.nfc.web.forms.CSearchForm;

/**
 * 
 * @author Maciek
 *
 */

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private CEventDAO eventDAO;
	
	public EventServiceImpl() {}
	
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addEvent(CEvent event) throws AdException {
		eventDAO.create(event);
	}

	@Transactional
	public List<CEvent> listEvents() throws AdException {
		return eventDAO.list();
	}
	
	@Transactional
	public CEvent getEvent(long id) throws AdException {
		return eventDAO.get(id);
	}
	
	@Transactional
	public void updateEvent(CEvent event) throws AdException {
		eventDAO.update(event);
	}

	@Transactional
	public List<CEvent> searchEvent(CSearchForm form) throws AdException {
		return eventDAO.searchEvent(form);
	}

	@Override
	public CEvent getCreatedEvent(String tagID) throws AdException {
		return eventDAO.getCreatedEvent(tagID);
	}

	@Override
	public CEvent getStartedEvent(String tagID, String token) throws AdException {
		return eventDAO.getStartedEvent(tagID, token);
	}
		
}
