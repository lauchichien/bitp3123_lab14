package part3Server.app;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import part3Model.Product;
import part3Server.controller.ProductDataManager;

/**
 * This class represents a TCP server application for handling product requests.
 * It listens on a specified port, accepts client connections, processes client 
 *     requests, and sends back the corresponding product information.
 * 
 * The server continuously runs and waits for client requests.
 * When a request is received, it retrieves the product based on the provided 
 *     ID from the ProductDataManager and sends it back to the client.
 * 
 * The server uses a TCP socket connection for communication.
 * 
 * @author Lau Chi Chien
 */
public class TCPProductServerApp {

	public static void main(String[] args) {
		
		int portNo = 8888;
		
		ProductDataManager manager = new ProductDataManager();
		
		System.out.println("\n\tExecuting TCPProductServerApp");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
				
				// 4. Process request - create input stream to read request
				// Request - product id:int
				InputStream is = clientSocket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				
				// Read product id from client
				int productId = dis.readInt();
				System.out.println("\tRequest for product id: " + productId);
				
				// Get product
				Product product = manager.getProduct(productId);
				
				// 5. Respond to client
				OutputStream os  = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(product);
				System.out.print("\tSending pruduct: " + product.getProductId()
						+ " " + product.getName());
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
	}
	

}
