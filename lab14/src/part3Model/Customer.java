package part3Model;

import java.io.Serializable;

/**
 * This class represents a Customer.
 * Exercise 2: Creating New Entity Class
 * 1. Create an entity class to represent a customer in the package model.
 * 2. The class shall contain the customer id and name as the attributes. 
 *    These attributes are private.
 * 3. Add getters and setters for all attributes.
 * 
 * @author Lau Chi Chien
 */
public class Customer implements Serializable {
	
	private int customerId;
	private String name;
	
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
