package part3Server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import part3Model.Customer;
import part3Server.controller.CustomerDataManager;

/**
 * The class represents a TCP server application for handling customer requests.
 * It listens on a specified port, accepts client connections, processes client 
 *     requests, and sends back the corresponding customer information.
 * 
 * The server continuously runs and waits for client requests.
 * When a request is received, it retrieves the customer based on the provided 
 *     name from the CustomerDataManager and sends it back to the client.
 * 
 * The server uses a TCP socket connection for communication.
 * 
 * Exercise 4: Finding a Customer at the Server-Side by Customer Name
 * 1. Create a TCP server-side application to process requests from TCP client.
 * 2. The server will receive a string of data that represents a customer from 
 *    a client.
 * 3. The server will find the customer based on the name and return a 
 *    customer object to the client.
 * 4. The server will log all its operations and interactions with the clients.
 * 
 * @author Lau Chi Chien
 */
public class TCPCustomerServerApp {

	public static void main(String[] args) {
		
		int portNo = 3333;
		
		CustomerDataManager manager = new CustomerDataManager();
		
		System.out.println("\n\tExecuting TCPCustomerServerApp");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server needs to continually run to listen to requests
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
				
				// 4. Process request - create input stream to read request
				// Request - customer name:string
				InputStream is = clientSocket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				
				// Read customer name from client
				String customerName = dis.readUTF();
				System.out.println("\tRequest for customer name: " 
						+ customerName);
				
				// Get customer
				Customer customer = manager.findCustomerByName(customerName);
				
				// 5. Respond to client
				OutputStream os = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(customer);
				
				System.out.print("\tSending customer: ");
				if (customer != null) {
					System.out.println(customer.getCustomerId() + " " 
							+ customer.getName() + "\n");
				} else {
					System.out.println("Customer not found \n");
				}
				
				// 6. Close the client socket
				clientSocket.close();
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}

}
