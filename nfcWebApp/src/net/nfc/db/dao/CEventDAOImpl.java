package net.nfc.db.dao;

import java.util.List;



import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.nfc.db.entity.CConstrains.eventStatus;
import net.nfc.db.entity.CEvent;
import net.nfc.web.forms.CSearchForm;

/**
 * @author Maciek24-06-2011
 *
 */
@Repository("CEventDAO")
public class CEventDAOImpl implements CEventDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<CEvent> list() throws AdException {
		try {
			return (List<CEvent>) sessionFactory.getCurrentSession().createCriteria(CEvent.class).addOrder(Order.desc("id")).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Events ",e);
		}
	}
	
	public void create(CEvent event) throws AdException {
		try {
			sessionFactory.getCurrentSession().save(event);
		}catch(HibernateException e) {
			throw new AdException("Could not add new Event : " + event.getId(), e);
		}
	}
	
	public CEvent get(long id) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CEvent where id=:id");
			q.setLong("id",id);
			CEvent event = (CEvent)q.uniqueResult();
			return event;
		}catch(HibernateException e) {
			throw new AdException("Could not get Event id: " +id , e);
		}
	}
	
	public CEvent get(String tagID) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CEvent where tagId=:tagID");
			q.setString("tagID",tagID);
			CEvent event = (CEvent)q.uniqueResult();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get Event tag: "+tagID, e);
		}
	}
	
	public CEvent getEventStatus(long userID) throws AdException{
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CEvent where status='EVENT_STATUS_STARTED' and user_id =:user_id");
			q.setLong("user_id",userID);
			CEvent event = (CEvent)q.uniqueResult();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get Event status for user_id : " + userID, e);
		}
	}
	
	
	public CEvent getCreatedEvent(String tagID) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CEvent where tagId=:tagID and status=:status");
			q.setString("tagID",tagID);
			q.setString("status",eventStatus.CREATED.getText());
			CEvent event = (CEvent)q.uniqueResult();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get Created Event tag: "+tagID, e);
		}
	}
	
	
	
	public CEvent getStartedEvent(String tagID, String token) throws AdException {
		try {
			
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CEvent.class);
			crit.add(Restrictions.eq("tagId", tagID));
			crit.add(Restrictions.eq("status", eventStatus.STARTED.getText()));
			if(token != null) crit.add(Restrictions.eq("token", token));
			CEvent event = (CEvent)crit.uniqueResult();
			return event;
		}catch (HibernateException e) {
			throw new AdException("Could not get started Event for tag: "+tagID, e);
		}
	}
	

	public void update(CEvent event) throws AdException {
		try {
			sessionFactory.getCurrentSession().update(event);
		}catch(HibernateException e) {
			throw new AdException("Could not update Event " +event.getId(),e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CEvent> searchEvent(CSearchForm form) throws AdException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CEvent.class);
			if(! form.getStatus().equals("null")) crit.add(Restrictions.eq("status", form.getStatus()));
			if(form.getLocation_id() != 0 ) crit.add(Restrictions.eq("location.id", form.getLocation_id()));
			
			return (List<CEvent>) crit.list();
			
		}catch (HibernateException e) {
			throw new AdException("Could not find event from search form",e);
		}
	}
}
