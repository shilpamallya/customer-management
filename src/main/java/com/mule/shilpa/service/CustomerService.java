package com.mule.shilpa.service;

import java.util.List;

import org.mule.module.apikit.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import com.mule.shilpa.dao.CustomerDao;
import com.mule.shilpa.model.Customer;

public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	/**
	 * This method is used to get all customers
	 * 
	 * @return List<Customer>
	 * @throws NotFoundException
	 */
	public List<Customer> getAllCustomers() throws NotFoundException {
		List<Customer> customers = customerDao.getAllCustomers();
		return customers;
	}

	/**
	 * This method is used to get a customer
	 * 
	 * @param id
	 * @return Customer
	 * @throws NotFoundException
	 */
	public Customer getCustomer(long id) throws NotFoundException {
		Customer customer = customerDao.getCustomer(id);
		return customer;
	}

	/**
	 * This method is used to create a new customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer createCustomer(Customer cust) {
		Customer customer = customerDao.createCustomer(cust);
		return customer;
	}

	/**
	 * This method is used to update a customer
	 * 
	 * @param id
	 * @param customer
	 * @return Customer
	 * @throws NotFoundException
	 */
	public Customer updateCustomer(long id, Customer customer) throws NotFoundException {
		Customer cust = customerDao.updateCustomer(id, customer);
		return cust;

	}

	/**
	 * This method is used to delete a customer
	 * 
	 * @param id
	 * @return String
	 * @throws NotFoundException
	 */
	public String deleteCustomer(long id) throws NotFoundException {
		return customerDao.deleteCustomer(id);
	}

}
