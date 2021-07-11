package com.simmi.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.simmi.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//need to inject the Session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	//@Transactional //remove this for service layer, because service layer will manage it
	
	public List<Customer> getCustomers() {
      
		//get the Current hibernatr session
		Session currentSession= sessionFactory.getCurrentSession();
		
		//create a query .. sort by last name
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",
						                   Customer.class );
		
		
		//execute query and get the result list
		List<Customer> customers=theQuery.getResultList();
		
		//return result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
 
		//get current hibernate session
		Session currentSession =  sessionFactory.getCurrentSession();
		//save/update the customer ... finally LOL
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomers(int theId) {

		//get the current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
		//now retrive/read from database using primary key
		Customer theCustomer=currentSession.get(Customer.class,theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		//get the current hibernate session
				Session currentSession = sessionFactory.getCurrentSession();
				
				//delete object with primary key
			    Query theQuery= currentSession.createQuery("delete from Customer where id=:customerId");
			    
			    theQuery.setParameter("customerId", theId);
			    
			    theQuery.executeUpdate();
		
	}

}
