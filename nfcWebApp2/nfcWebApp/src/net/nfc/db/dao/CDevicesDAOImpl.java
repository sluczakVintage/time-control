/**
 * 
 */
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

import net.nfc.db.entity.CDevices;
import net.nfc.web.forms.CSearchForm;


/**
 * @author Maciek24-06-2011
 *
 */
@Repository("CDevicesDAO")
public class CDevicesDAOImpl implements CDevicesDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<CDevices> list() throws AdException {
		try {
			return (List<CDevices>) sessionFactory.getCurrentSession().createCriteria(CDevices.class).addOrder(Order.asc("deviceType")).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Devices ",e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public List<CDevices> getDevicesForEmployee(long id) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CDevices where employee_id=:id" );
			q.setLong("id", id);
			List<CDevices> lista  = q.list();
			return lista;
		}catch(HibernateException e) {
			throw new AdException("Could not list Devices for employee id :  " + id,e);
		}
	}
	
	
	public CDevices get(long id) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CDevices where id=:id");
			q.setLong("id",id);
			CDevices device = (CDevices)q.uniqueResult();
			return device;
		}catch(HibernateException e) {
			throw new AdException("Could not get Devices id: " +id , e);
		}
	}
	
	
	
	public CDevices create(CDevices device) throws AdException {
		try {
			sessionFactory.getCurrentSession().save(device);
			return device;
		}catch(HibernateException e) {
			throw new AdException("Could not add new device : " + device.getId(), e);
		}
	}
	
	public void update(CDevices device) throws AdException {
		try {
			sessionFactory.getCurrentSession().update(device);
		}catch(HibernateException e) {
			throw new AdException("Could not update Devices " +device.getId(),e);
		}
	}

	/**
	 * TODO b³¹d logiczny = mo¿e byæ wiele devices na jednego emp
	 */
	
	public CDevices getDeviceForEmployee(long empID) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CDevices where employee_id=:id");
			q.setLong("id",empID);
			CDevices device = (CDevices)q.uniqueResult();
			return device;
		}catch(HibernateException e) {
			throw new AdException("Could not get Devices for employee id: " +empID, e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CDevices> searchDevice(CSearchForm form)
			throws AdException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CDevices.class);
			
			String[] tab = form.getName().split(" ");
			
			if(! form.getStatus().equals("null")) crit.add(Restrictions.eq("deviceStatus", form.getStatus()));
			if(! form.getName().equals("") ) crit.createAlias("employee", "employee").add(Restrictions.ilike("employee.name", "%" + tab[0] + "%"));
			if(tab.length==2) {
			if(! form.getName().equals("") ) crit.createAlias("employee", "employee").add(Restrictions.ilike("employee.lastName", "%" + tab[1] + "%")); }
			
			
			return (List<CDevices>) crit.list();
			
		}catch (HibernateException e) {
			throw new AdException("Could not find device from search form",e);
		}
	}


	@SuppressWarnings("unchecked")
	public List<CDevices> getFreeDevices() throws AdException {
		try {
			
			Query q = sessionFactory.getCurrentSession().createQuery("from CDevices where employee_id is null and deviceStatus = 'DEVICE_STATUS_ACTIVE'");
			return (List<CDevices>) q.list();
			
		}catch(HibernateException e) {
			throw new AdException("Could not get list of free devices" ,e);
		}
	}
}
