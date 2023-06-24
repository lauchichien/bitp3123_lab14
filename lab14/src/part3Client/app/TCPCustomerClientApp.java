package part3Client.app;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import part3Model.Customer;

/**
 * This is a TCP client class that processes customer names and receives
 * customer objects from the server.
 * 
 * Exercise 5: Finding a Customer at the Client-Side by Customer Name
 * 1. Create a TCP client-side application to send several customer names 
 *    to the server.
 * 2. The application will also receive a customer object from the server.
 * 3. Your solution shall demonstrate sending the customer's full name and 
 *    several partial names.
 * 4. Your solution shall also demonstrate non-existing customers.
 * 5. Display the details of the object.
 * 
 * @author Lau Chi Chien
 */
public class TCPCustomerClientApp {
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("\tExecuting TCPCustomerClientApp");
			
			// Server information
			int serverPortNo = 3333;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// 1. Connect to the remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);
			
			// Create stream to send request
			OutputStream os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			// 2. Send request to the server
			String name = "Abu";
			dos.writeUTF(name);
			System.out.println("\tRequesting customer with name: " + name);
				
			// Create stream to receive response from the server
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
				
			// 3. Read response from the server - cast object
			Customer customer = (Customer) ois.readObject();
				
			// 4. Process response - display the object details
			System.out.println("\n\tCustomer Information (From the server)");
			if (customer != null) {
				System.out.println("\tCustomer Id: " + customer.getCustomerId());
				System.out.println("\tName: " + customer.getName());
			} else {
				System.out.println("\tCustomer not found");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
