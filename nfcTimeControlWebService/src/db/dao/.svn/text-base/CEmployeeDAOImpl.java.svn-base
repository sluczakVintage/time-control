/**
 * 
 */
package db.dao;

import java.util.List;

import db.dao.AdException;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import db.entity.CEmployee;
/**
 * @author Maciek
 * 24-06-2011
 *
 */
public class CEmployeeDAOImpl extends DAO implements CEmployeeDAO{

	public List<CEmployee> list() throws AdException {
		try {
			begin();
			Query q =getSession().createQuery("from CEmployee");
			List<CEmployee> list = q.list();
			commit();
			return list;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not list Employees ",e);
		}
	}

	public CEmployee get(long id) throws AdException {
		try {
			begin();
			Query q = getSession().createQuery("from CEmployee where id=:id");
			q.setLong("id",id);
			CEmployee emp = (CEmployee)q.uniqueResult();
			commit();
			return emp;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Employee id: " +id , e);
		}
	}
	
	public CEmployee create(CEmployee emp) throws AdException {
		try {
			begin();
			getSession().save(emp);
			commit();
			return emp;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not add new Employee : " + emp.getName(), e);
		}
	}
	
	public void update(CEmployee emp) throws AdException {
		try {
			begin();
			getSession().update(emp);
			commit();
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not update employee " +emp.getName(),e);
		}
	}
	
	public CEmployee getEmployeeForUser(long userID) throws AdException {
		try {
			begin();
			Query q =  getSession().createQuery("from CEmployee where users_id=:id");
			q.setLong("id",userID);
			CEmployee emp = (CEmployee)q.uniqueResult();
			commit();
			return emp;
		}catch(HibernateException e) {
			rollback();
			throw new AdException("Could not get Employee for user id: " +userID , e);
		}
	}
	
}
