/**
 * 
 */
package db.dao;

import java.util.List;

import db.dao.AdException;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CConstrains.eventStatus;
import db.entity.CEvent;

/**
 * @author Maciek24-06-2011
 *
 */
public class CEventDAOImpl extends DAO implements CEventDAO{

	public List<CEvent> list() throws AdException {
		try {
			begin();
			Query q =getSession().createQuery("from CEvent");
			List<CEvent> list = q.list();
			commit();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not list Events ",e);
		}
	}

	public CEvent get(long id) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CEvent where id=:id");
			q.setLong("id",id);
			CEvent event = (CEvent)q.uniqueResult();
			commit();
			return event;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Event id: " +id , e);
		}
	}
	
	public CEvent get(String tagID) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CEvent where tagId=:tagID");
			q.setString("tagID",tagID);
			CEvent event = (CEvent)q.uniqueResult();
			commit();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get Event tag: "+tagID, e);
		}
	}
	
	public CEvent getCreatedEvent(String tagID) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CEvent where tagId=:tagID and status=:status");
			q.setString("tagID",tagID);
			q.setString("status",eventStatus.CREATED.getText());
			CEvent event = (CEvent)q.uniqueResult();
			commit();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get Created Event tag: "+tagID, e);
		}
	}
	
	
	
	public CEvent getStartedEvent(String tagID, String token) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CEvent where tagId=:tagID and token=:token and status=:status");
			q.setString("tagID",tagID);
			q.setString("token",token);
			q.setString("status", eventStatus.STARTED.getText());
			CEvent event = (CEvent)q.uniqueResult();
			commit();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get Event tag: "+tagID, e);
		}
	}
	
	public CEvent create(CEvent event) throws AdException {
		try {
			begin();
			getSession().save(event);
			commit();
			return event;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not add new Event : " + event.getId(), e);
		}
	}
	
	public void update(CEvent event) throws AdException {
		try {
			begin();
			getSession().update(event);
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update Event " +event.getId(),e);
		}
	}
	
	public CEvent getEventStatus(long userID) throws AdException{
		try {
			begin();
			Query q = getSession().createQuery("from CEvent where status='EVENT_STATUS_STARTED' and user_id =:user_id");
			q.setLong("user_id",userID);
			CEvent event = (CEvent)q.uniqueResult();
			commit();
			return event;
		}catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get Event status for user_id : " + userID, e);
		}
	}

	public boolean isStartedEventFroUser(long userID) throws AdException{
		try {
			begin();
			Query q = getSession().createQuery("from CEvent where status='EVENT_STATUS_STARTED' and user_id=:id");
			q.setLong("id",userID);
			CEvent event = (CEvent)q.uniqueResult();
			commit();
			if(event == null ) {
				return false;
			}
			else return true;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Event for user id: " +userID , e) ;
		}
	}
}
