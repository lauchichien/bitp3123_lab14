package part2Server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import part2SentenceProcessor.SentenceProcessor;

/**
 * An example of server-side application using UDP.
 * This program counts the number of vowels, consonants, and punctuations
 *     in a sentence received from the client and 
 *     sends the results back to the client.
 * 
 * How to run this application:
 * 1. Open terminal
 * 2. Change to directory /workspace-dad/udpdemo/bin
 * 3. java UDPServerSideApp
 * 4. Initial output: UDPServerSideApp: Demonstration of 
 *    UDP Server-Side Application.
 * 5. Final output: 
 *    Message received: I am Lau Chi Chien, I am a handsome guy !!!
 *    Number of vowels: 14, consonants: 15, punctuations: 3.
 *    
 * Author: Lau Chi Chien
 */
public class UDPServerSideApp {

	public static void main(String[] args) {
		
		System.out.println("UDPServerSideApp: Demonstration of "
				+ "UDP Server-Side Application.");
		
		// Permissible port for this application
		int portNo = 2222;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to receive data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object representing packet from the client
                DatagramPacket receivedPacket = new DatagramPacket(receivedData,
                		receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceProcessor processor = 
						new SentenceProcessor(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".");
				
				// Calculate number of vowels, consonants, and punctuations
				int vowelCount = processor.countVowels();
				int consonantCount = processor.countConsonants();
				int punctuationCount = processor.countPunctuations();
				
				// Create result message
				String resultMessage = "Number of vowels: " + vowelCount + 
						", consonants: " +
						consonantCount + ", punctuations: " + punctuationCount +
						".";
				
				// Convert result message to bytes
				byte[] resultData = resultMessage.getBytes();
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            int resultDataSize = resultData.length;
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket resultPacket = new DatagramPacket(resultData, 
	            		resultDataSize, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(resultPacket);
	            System.out.println("Result sent: " + resultMessage);
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDPServerSideApp: End of program.");
    }
}
