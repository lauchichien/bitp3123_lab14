package part2Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * An example of client-side application using UDP.
 * This program sends a sentence to the UDP server and 
 *     receives the analysis result.
 * 
 * Author: Lau Chi Chien
 */
public class UDPClientSideApp {

	public static void main(String[] args) {
		
		System.out.println("UDPClientSideApp: Demonstration of "
				+ "UDP Client-Side Application.");
		
		try {
			
			// 1. Define server port number and address
			int portNo = 2222;
			InetAddress ip = InetAddress.getLocalHost();
			
			// 2. Prepare and transform data into bytes
			String text = "I am Lau Chi Chien, I am a handsome guy !!!";
			byte[] buf = text.getBytes();

			// 3. Wrap data in datagram packet
			DatagramPacket outPacket = new DatagramPacket(buf, buf.length, 
					ip, portNo);
			
			// 4. Create the socket object to transmit the data
			DatagramSocket datagramSocket = new DatagramSocket();

			// 5. Send datagram packet
			datagramSocket.send(outPacket);
			System.out.println("Sending '" + text + "' (" + text.length() + ") "
					+ "out on the network.");
			
			// 6. New buffer to extract the received data
			byte[] inData = new byte[65535];
			
			// 7. Packet to receive data
			DatagramPacket inPacket = new DatagramPacket(inData, inData.length);
			
			// 8. Receive data
			datagramSocket.receive(inPacket);
			
			// 9. Extract data
			inData = inPacket.getData();
			
			// 10. Further processing
			String resultMessage = new String(inData, 0, inPacket.getLength());
			
			// Display the data received from the server
			System.out.println("Analysis result: " + resultMessage);
			
			datagramSocket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("UDPClientSideApp: End of program.");

	}
}
