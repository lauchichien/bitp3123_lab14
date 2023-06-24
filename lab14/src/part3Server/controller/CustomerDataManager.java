package part3Server.controller;

import java.util.ArrayList;
import java.util.List;

import part3Model.Customer;

/**
 * This class manages customer data.
 * Exercise 3: Creating New Controller Class
 * 1. Create a controller class to manage the customer data in the 
 *    package server.controller. Name this class appropriately.
 * 2. Define an object that represents a list of customers. This object 
 *    shall be private.
 * 3. This class shall have four methods. The description of the methods is 
 *    shown in Table 1
 * 
 * Method 1 This method creates a list of samples of customer 
 *          data. The list will contain 10 customers. This is a 
 *          private method.
 * 
 * Method 2 This method searches a customer based on the 
 *          customer’s name from a list of customers. This 
 *          method will receive a parameter that represents a 
 *          customer’s name. The customer’s name will be 
 *          either full or partial name. The method will return a 
 *          Customer’s object if the name exists. Otherwise, it 
 *          will return null. This is a public method.
 * 
 * Method 3 This method searches a customer based on the 
 *          customer’s id from a list of customers. This method 
 *          will receive a parameter that represents a 
 *          customer’s id. This method will return a 
 *          Customer’s object if the name exists. Otherwise, it 
 *          will return null. This is a public method.
 * 
 * Method 4 This method will return a list of customers. This is a 
 *          public method.
 * 
 * Constructor 1 This constructor will call Method 1.
 * 
 * 4. Implement all the methods. Name the methods appropriately.
 * 5. Additional methods might be needed. Add those methods 
 *    accordingly.
 * 
 * @author Lau Chi Chien
 */
public class CustomerDataManager {

	private List<Customer> customers;

	/**
	 * Constructor that initializes the list of customers.
	 */
	public CustomerDataManager() {
		customers = new ArrayList<>();
		createSampleCustomers();
	}

	/**
	 * Method that creates a list of sample customers (Method 1).
	 * This is a private method.
	 */
	private void createSampleCustomers() {
	    customers = new ArrayList<>();

	    int ids[] = {1001, 1002, 1003, 1004, 1005, 1006, 
	    		1007, 1008, 1009, 1010};
	    String names[] = {"Lim Zhao Hong", "See Zhao Wei", "Macaurel", 
	    		"Jason Lam", "Darrick", "Ng Wei Hen", "Nicole Tan", 
	    		"Lim Jian Wei", "Justin","terence Ong"};

	    for (int i = 0; i < ids.length; i++) {
	        Customer customer = new Customer();
	        customer.setCustomerId(ids[i]);
	        customer.setName(names[i]);
	        customers.add(customer);
	    }
	}

	/**
	 * Method that searches a customer based on the customer's name (Method 2).
	 * This is a public method.
	 * 
	 * @param name the name of the customer (full or partial)
	 * @return the Customer object if the name exists, otherwise null
	 */
	public Customer findCustomerByName(String name) {
		// Search for the customer by name
		for (Customer customer : customers) {
			if (customer.getName().contains(name)) {
				return customer;
			}
		}
		return null; // Customer not found
	}

	/**
	 * Method that searches a customer based on the customer's id (Method 3).
	 * This is a public method.
	 * 
	 * @param id the id of the customer
	 * @return the Customer object if the id exists, otherwise null
	 */
	public Customer findCustomerById(int id) {
		// Search for the customer by id
		for (Customer customer : customers) {
			if (customer.getCustomerId() == id) {
				return customer;
			}
		}
		return null; // Customer not found
	}

	/**
	 * Method that returns a list of customers (Method 4).
	 * This is a public method.
	 * 
	 * @return the list of customers
	 */
	public List<Customer> getAllCustomers() {
		return customers;
	}
}
