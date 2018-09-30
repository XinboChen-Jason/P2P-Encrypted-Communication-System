import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
 
/*
 * Client
 */

public class ClientDemo {
	public static void testClient(){
		System.out.println("Connecting...");
		Socket socket = null;
		Scanner keyboardScanner = null;
		Scanner inScanner = null;
		PrintWriter pwtoserver = null;
		try {
			socket = new Socket("192.168.0.195", 10450);	//ip address is your local ip under LAN, same port with server
			inScanner = new Scanner(socket.getInputStream()); 	//read information from server
			System.out.println(inScanner.nextLine());
			pwtoserver = new PrintWriter(socket.getOutputStream());
			System.out.print("Me(Client): ");
			keyboardScanner = new Scanner(System.in);	//read keyboard input
			//block program to wait user input
			while(keyboardScanner.hasNextLine()){
				String keyboardData = keyboardScanner.nextLine();
		
				pwtoserver.println(keyboardData);
				pwtoserver.flush();
	
				String indata = inScanner.nextLine();
				System.out.println("Server: "+indata);
				System.out.print("Me(Client): ");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			keyboardScanner.close();
			pwtoserver.close();
			inScanner.close();
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	public static void main(String[] args) {
		testClient();
	}
}