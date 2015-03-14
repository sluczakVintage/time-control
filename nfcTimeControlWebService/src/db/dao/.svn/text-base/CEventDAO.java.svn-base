/**
 * 
 */
package db.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CConstrains.eventStatus;
import db.entity.CEvent;

/**
 * @author Maciek24-06-2011
 *
 */
public interface CEventDAO {

	public List<CEvent> list() throws AdException ;

	public CEvent get(long id) throws AdException ;
	
	/**
	 * TODO zastanowiœæ siê czy to jest potrzebne
	 * @param tagID
	 * @return
	 * @throws AdException
	 */
	public CEvent get(String tagID) throws AdException ; 
	
	public CEvent getCreatedEvent(String tagID) throws AdException ;
	
	public CEvent getStartedEvent(String tagID, String token) throws AdException ; 
	
	public CEvent create(CEvent event) throws AdException ;
	
	public void update(CEvent event) throws AdException;
	
	public boolean isStartedEventFroUser(long userID) throws AdException;
}
