/**
 * 
 */
package net.nfc.db.dao;

import java.util.List;

import net.nfc.db.entity.CEmployee;
import net.nfc.web.forms.CSearchForm;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Maciek
 * 24-06-2011
 *
 */
@Repository("CEmployeeDAO")
public class CEmployeeDAOImpl implements CEmployeeDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<CEmployee> list() throws AdException {
		try {
			return (List<CEmployee>) sessionFactory.getCurrentSession().createCriteria(CEmployee.class).addOrder(Order.asc("lastName")).list() ;
		}catch(HibernateException e) {
			throw new AdException("Could not list Employees ",e);
		}
	}

	public CEmployee get(long id) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CEmployee where id=:id");
			q.setLong("id",id);
			CEmployee emp = (CEmployee)q.uniqueResult();
			return emp;
		}catch(HibernateException e) {
			throw new AdException("Could not get Employee id: " +id , e);
		}
	}
	
	public CEmployee getEmployeeForUser(long userID) throws AdException {
		try {
			Query q = sessionFactory.getCurrentSession().createQuery("from CEmployee where users_id=:id");
			q.setLong("id",userID);
			CEmployee emp = (CEmployee)q.uniqueResult();
			return emp;
		}catch(HibernateException e) {
			throw new AdException("Could not get Employee for user id: " +userID , e);
		}
	}
	
	
	public CEmployee create(CEmployee emp) throws AdException {
		try {
			sessionFactory.getCurrentSession().save(emp);
			return emp;
		}catch(HibernateException e) {
			throw new AdException("Could not add new Employee : " + emp.getName(), e);
		}
	}
	
	public void update(CEmployee emp) throws AdException {
		try {
			sessionFactory.getCurrentSession().update(emp);
		}catch(HibernateException e) {
			throw new AdException("Could not update employee " +emp.getName(),e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<CEmployee> searchEmployee(CSearchForm form)
			throws AdException {
		try {
			Criteria crit = sessionFactory.getCurrentSession().createCriteria(CEmployee.class);
			
			System.out.println("*" +form.getName()  + "*" + form.getUserName() + "*");
			
			String[] tab = form.getName().split(" ");
			
			if(! form.getName().equals("")){ crit.add(Restrictions.ilike("name", "%" + tab[0] + "%")); }
			if(tab.length == 2) {
			if(! form.getName().equals("")) crit.add(Restrictions.ilike("lastName", "%" + tab[1] + "%"));
			}
			//if(! form.getUserName().equals("") ) { crit.add(Restrictions.ilike("user.userName", "%" + form.getUserName() + "%")); }
			if(! form.getUserName().equals("") ) { crit.createAlias("user", "user").add(Restrictions.ilike("user.userName", "%" + form.getUserName() + "%")); }
			
			
			return (List<CEmployee>) crit.list();
			
		}catch (HibernateException e) {
			throw new AdException("Could not find employee from search form",e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CEmployee> listEmployeesWithUsers() {
		Query q = sessionFactory.getCurrentSession().createQuery("from CEmployee where users_id is not null order by lastName asc");
		return (List<CEmployee>)q.list();
	}
	
	
}
