/**
 * 
 */
package db.dao;

import java.util.List;

import db.dao.AdException;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CDevices;

/**
 * @author Maciek24-06-2011
 *
 */
public class CDevicesDAOImpl extends DAO implements CDevicesDAO{

	public List<CDevices> list() throws AdException {
		try {
			begin();
			Query q =getSession().createQuery("from CDevices");
			List<CDevices> list = q.list();
			commit();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not list Devices ",e);
		}
	}

	public CDevices get(long id) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CDevices where id=:id");
			q.setLong("id",id);
			CDevices device = (CDevices)q.uniqueResult();
			commit();
			return device;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Devices id: " +id , e);
		}
	}
	
	public CDevices create(CDevices device) throws AdException {
		try {
			begin();
			getSession().save(device);
			commit();
			return device;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not add new device : " + device.getId(), e);
		}
	}
	
	public void update(CDevices device) throws AdException {
		try {
			begin();
			getSession().update(device);
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update Devices " +device.getId(),e);
		}
	}
	
	public CDevices getDeviceForEmployee(long empID) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CDevices where employee_id=:id");
			q.setLong("id",empID);
			CDevices device = (CDevices)q.uniqueResult();
			commit();
			return device;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Devices for employee id: " +empID, e);
		}
	}
	
}
