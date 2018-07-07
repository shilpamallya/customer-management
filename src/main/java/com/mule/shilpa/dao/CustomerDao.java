package com.mule.shilpa.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mule.module.apikit.exception.NotFoundException;

import com.mule.shilpa.model.Customer;

public class CustomerDao {

	private Map<Long, Customer> customerMap = new HashMap<Long, Customer>(); // this is a mock database
	private long idCounter;

	/**
	 * This method is used to get all customers
	 * 
	 * @return List<Customer>
	 * @throws NotFoundException
	 */
	public List<Customer> getAllCustomers() throws NotFoundException {
		if (customerMap.isEmpty()) {
			throw new NotFoundException("No customers are found.");
		}
		Collection<Customer> customers = customerMap.values();
		List<Customer> customerList = new ArrayList<Customer>(customers);
		return customerList;
	}

	/**
	 * This method is used to get a customer
	 * 
	 * @param id
	 * @return Customer
	 * @throws NotFoundException
	 */
	public Customer getCustomer(long id) throws NotFoundException {
		if (!customerMap.containsKey(id)) {
			throw new NotFoundException("Customer " + id + " does not exist.");
		}
		Customer customer = customerMap.get(id);
		return customer;
	}

	/**
	 * This method is used to create a new customer
	 * 
	 * @param customer
	 * @return Customer
	 */
	public Customer createCustomer(Customer customer) {
		idCounter++; // this is used as key in the map
		customer.setId(idCounter);
		customerMap.put(customer.getId(), customer);

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
		if (!customerMap.containsKey(id)) {
			throw new NotFoundException("Customer " + id + " does not exist.");
		}
		customer.setId(id);
		customerMap.put(id, customer);
		return customer;
	}

	/**
	 * This method is used to delete a customer
	 * 
	 * @param id
	 * @return String
	 * @throws NotFoundException
	 */
	public String deleteCustomer(long id) throws NotFoundException {

		if (!customerMap.containsKey(id)) {
			throw new NotFoundException("Customer " + id + " does not exist.");
		}
		customerMap.remove(id);
		return "Customer " + id + " has been successfully deleted.";
	}
}
