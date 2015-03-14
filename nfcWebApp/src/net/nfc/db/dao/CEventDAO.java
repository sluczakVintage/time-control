package net.nfc.db.dao;

import java.util.List;



import net.nfc.db.entity.CEvent;
import net.nfc.web.forms.CSearchForm;
/**
 * 
 * @author Maciek
 *
 */
public interface CEventDAO {
	
	public List<CEvent> list() throws AdException ;

	public CEvent get(long id) throws AdException ;
	
	public CEvent get(String tagID) throws AdException ;
	
	public CEvent getEventStatus(long userID) throws AdException ;
	
	public CEvent getCreatedEvent(String tagID) throws AdException ;
	
	public CEvent getStartedEvent(String tagID, String token) throws AdException ;
	
	public void create(CEvent event) throws AdException ;
	
	public void update(CEvent event) throws AdException ;
	
	public List<CEvent> searchEvent(CSearchForm searchForm) throws AdException;
	
	
}
