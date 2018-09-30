import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
 
/*
 * Server
 * A socket communication based on TCP 
 */

public class ServerDemo {
	public static void testServer(){
		System.out.println("waiting for client...");
		PrintWriter pwToClient = null;
		Scanner keyboardScanner = null;
		Scanner inScanner = null;
		ServerSocket ss = null;
		
		try {
			ss = new ServerSocket(10450);	//new socket object, same port with client
			Socket socket = ss.accept();	//invoking accept() to start monitoring
			System.out.println(socket.getInetAddress()+"has connectted");
			pwToClient = new PrintWriter(socket.getOutputStream());
			pwToClient.println("You are alredy connected to the Server"+"\t"+"please key-in");
			pwToClient.flush();
			keyboardScanner = new Scanner(System.in);	//read keyboard input
			inScanner = new Scanner(socket.getInputStream());
			//block program to wait user input
			while(inScanner.hasNextLine()){
				String inData = inScanner.nextLine();
				System.out.println("Client: "+inData);
				System.out.print("Me(Server): ");
				String keyboardData = keyboardScanner.nextLine();
				
				pwToClient.println(keyboardData);
				pwToClient.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			pwToClient.close();
			keyboardScanner.close();
			inScanner.close();
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		testServer();
	}
}