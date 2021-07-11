package com.simmi.springdemo.service;

import java.util.List;

import com.simmi.springdemo.entity.Customer;

public interface CustomerService {

	List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);


}
