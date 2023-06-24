package part3Client.app;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;
import java.util.Collections;

import part3Client.view.CustomerViewer;
import part3Model.Customer;

/**
 * This is a TCP client class that receives a list of customers from the server
 * and displays the details of the clients in alphabetical order.
 * 
 * Exercise 10: Receiving a List of Customers at the Client-Side
 * 1. Create a TCP client-side application that will receive a list of customers 
 *    from the server.
 * 2. Display the details of the client in alphabetical order.
 * 
 * @author Lau Chi Chien
 */
public class TCPCustomerListClientApp {

	public static void main(String[] args) {

		try {

			// Server information
			int serverPortNo = 6666;
			InetAddress serverAddress = InetAddress.getLocalHost();

			// 1. Connect to the remote machine
			Socket socket = new Socket(serverAddress, serverPortNo);

			// Create stream to receive response from the server
			InputStream is = socket.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);

			// 3. Read response from the server - cast the object
			List<Customer> customers = (List<Customer>) ois.readObject();

			// 4. Process response - display the customers in alphabetical order
			Collections.sort(customers, (c1, c2) -> 
				c1.getName().compareToIgnoreCase(c2.getName()));
			
			CustomerViewer customerViewer = new CustomerViewer();
			customerViewer.displayCustomers(customers);

		} catch (Exception ex) {

			ex.printStackTrace();

		}

	}
}
