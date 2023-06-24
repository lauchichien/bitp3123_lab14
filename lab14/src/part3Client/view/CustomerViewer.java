package part3Client.view;

import java.util.List;

import part3Model.Customer;

public class CustomerViewer {

	public void displayCustomers(List<Customer> customers) {

		// Some information about the record
		System.out.println("\tNumber of records: " + customers.size());
		System.out.println("\tCustomer Information:\n");

		// Print all customers on the console
		for (Customer customer : customers) {

			System.out.println("\tCustomer Id: " + customer.getCustomerId());
			System.out.println("\tName: " + customer.getName() + "\n");
		}

	}
}
