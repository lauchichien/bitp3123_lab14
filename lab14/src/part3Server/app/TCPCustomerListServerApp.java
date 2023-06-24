package part3Server.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import part3Model.Customer;
import part3Server.controller.CustomerDataManager;

/**
 * This class represents a TCP server application for handling 
 *    customer list requests.
 * It listens on a specified port, accepts client connections, 
 *    retrieves the list of customers from the CustomerDataManager, 
 *    and sends it back to the client.
 * 
 * The server continuously runs and waits for client requests.
 * When a request is received, it retrieves the list of customers from the 
 *    CustomerDataManager and sends it back to the client.
 * 
 * The server uses a TCP socket connection for communication.
 * 
 * Exercise 9: Getting a List of Customers from the Server-Side
 * 1. Create a TCP server-side application that will return a list of 
 *    customers to the client.
 * 2. The server will log all operations and interactions with the client.
 * 
 * @author Lau Chi Chien
 */
public class TCPCustomerListServerApp {

	public static void main(String[] args) {
		
		int portNo = 6666;
		
		CustomerDataManager manager = new CustomerDataManager();
		
		System.out.println("\n\tExecuting TCPCustomerListServerApp");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server needs to continually run to listen to requests
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
								
				// Get customer list
				List<Customer> customers = manager.getAllCustomers();
				
				// 4. Respond to client
				OutputStream os = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(customers);
				System.out.println("\tSending: " + customers.size() 
					+ " customers");
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}

}