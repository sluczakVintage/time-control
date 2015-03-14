package net.nfc.web.service;

import net.nfc.db.dao.AdException;
import net.nfc.db.entity.CEvent;
import net.nfc.web.forms.CSearchForm;

import java.util.List;

/**
 * 
 * @author Maciek
 *
 */

public interface EventService   {
	
	public void addEvent(CEvent event ) throws AdException;
	public List<CEvent> listEvents() throws AdException;
	public CEvent getEvent(long id) throws AdException;
	public void updateEvent(CEvent event) throws AdException;
	public List<CEvent> searchEvent(CSearchForm form) throws AdException;
	public CEvent getCreatedEvent(String tagID) throws AdException ;
	public CEvent getStartedEvent(String tagID, String token) throws AdException ;
}
