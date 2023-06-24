package part3Server.app;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import part3Model.Product;
import part3Server.controller.ProductDataManager;

/**
 * This class represents a TCP server application for handling product 
 *     list requests.
 * It listens on a specified port, accepts client connections, retrieves 
 *     the list of products
 * from the ProductDataManager, and sends it back to the client.
 * 
 * The server continuously runs and waits for client requests.
 * When a request is received, it retrieves the list of products from 
 *     the ProductDataManager and sends it back to the client.
 * 
 * The server uses a TCP socket connection for communication.
 * 
 * @author Lau Chi Chien
 */
public class TCPProductsServerApp {

	public static void main(String[] args) {
		
		int portNo = 9999;
		
		ProductDataManager manager = new ProductDataManager();
		
		System.out.println("\n\tExecuting TCPProductsServerApp");
		
		try {
			
			System.out.println("\tWaiting for next request");
			
			// 1. Bind to a port
			ServerSocket serverSocket = new ServerSocket(portNo); 
			
			// 2. Server need to continually run to listen to request
			while (true) {
				
				// 3. Accept request from client
				Socket clientSocket = serverSocket.accept();
								
				// Get product
				List<Product> products = manager.getProducts();
				
				// 4. Respond to client
				OutputStream os  = clientSocket.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				oos.writeObject(products);
				System.out.println("\tSending : " + products.size() 
					+ " products");
				
			}
			
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}

	}

}

