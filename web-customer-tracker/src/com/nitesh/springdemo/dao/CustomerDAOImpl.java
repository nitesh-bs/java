package com.nitesh.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nitesh.springdemo.entity.Customer;
import com.nitesh.springdemo.util.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
//	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		
		/*
		 * Session session = sessionFactory.getCurrentSession();
		 * 
		 * Query<Customer> query =
		 * session.createQuery("from Customer order by lastName",Customer.class);
		 * 
		 * List<Customer> customers = query.getResultList();
		 */
		
		Session session = sessionFactory.getCurrentSession();
		
		// determine sort field
		String theFieldName = null;
		
		switch (theSortField) {
			case SortUtils.FIRST_NAME: 
				theFieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				theFieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				theFieldName = "lastName";
		}
		
		// create a query  
		String queryString = "from Customer order by " + theFieldName;
		Query<Customer> theQuery = 
				session.createQuery(queryString, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {
		
		Session session = sessionFactory.getCurrentSession();
		
//		session.save(customer);
		
		// if primary key id is empty then save otherwise update
		session.saveOrUpdate(customer);
		
	}

	@Override
	public Customer getCustomer(int custId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		return session.get(Customer.class, custId);
	}

	@Override
	public void deleteCustomer(int custId) {
		Session session = sessionFactory.getCurrentSession();
		
		/*
		 * Customer customer = session.get(Customer.class, custId);
		 * session.delete(customer);
		 */
		
		Query query = session.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId", custId);
		query.executeUpdate();
		
	}
	
	 @Override
	    public List<Customer> searchCustomers(String theSearchName) {
	        
	        Session currentSession = sessionFactory.getCurrentSession();
	        
	        Query theQuery = null;
	        
	        //
	        // only search by name if theSearchName is not empty
	        //
	        if (theSearchName != null && theSearchName.trim().length() > 0) {
	            // search for firstName or lastName ... case insensitive
	            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
	            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
	        }
	        else {
	            // theSearchName is empty ... so just get all customers
	            theQuery =currentSession.createQuery("from Customer", Customer.class);            
	        }
	        
	        // execute query and get result list
	        List<Customer> customers = theQuery.getResultList();
	                
	        // return the results        
	        return customers;
	        
	    }

}
