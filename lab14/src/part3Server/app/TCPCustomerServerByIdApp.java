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
 *    requests, and sends back the corresponding customer information.
 * 
 * The server continuously runs and waits for client requests.
 * When a request is received, it retrieves the customer based on the provided 
 *    customer ID from the CustomerDataManager and sends it back to the client.
 * 
 * The server uses a TCP socket connection for communication.
 * 
 * Exercise 6: Finding a Customer at the Server-Side by Customer ID
 * 1. Create a TCP server-side application to process requests from TCP client.
 * 2. The server will receive integer data from the client that represents 
 *    a customer ID.
 * 3. The server will search for the customer based on the customer ID and 
 *    return a customer object to the client.
 * 4. The server will log all its operations and interactions with the clients.
 * 
 * @author Lau Chi Chien
 */
public class TCPCustomerServerByIdApp {

    public static void main(String[] args) {
        
        int portNo = 5555;
        
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
                // Request - customer ID:int
                InputStream is = clientSocket.getInputStream();
                DataInputStream dis = new DataInputStream(is);
                
                // Read customer ID from client
                int customerId = dis.readInt();
                System.out.println("\tRequest for customer ID: " + customerId);
                
                // Get customer
                Customer customer = manager.findCustomerById(customerId);
                
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
