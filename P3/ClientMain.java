
import java.util.Scanner;
import javax.swing.JOptionPane;


public class ClientMain{
	private static int count = 0;    
	public static void main(String[] args){                
		MySocket mSocket = new MySocket("localhost", 1234);
		String nick = JOptionPane.showInputDialog("Enter your nick:");
		SwingClient client = new SwingClient(nick, mSocket);						
		client.createAndShowGUI(nick);
		
		// Output Thread
		Thread OnlineThread = new Thread(new Runnable() {
			public void run(){                
				while (Thread.activeCount() >= 0) {
					count++;
					System.out.println("Counter: " + count);
					if(!client.isConnected() ) {
						mSocket.writeString(nick+": exit");
						break;       
					                        
					}
					try {
						Thread.sleep(1000);            
					} catch (InterruptedException e) {
						e.printStackTrace();                                                                        
					}
					if (count % 5 == 0 && client.isConnected()) {                        
						mSocket.writeString(nick+": connection");                        
					}
					if (count == 60) {
						mSocket.writeString(nick+": disconnection");                        
						count = 0;
						client.disconnect();                                                                                                                                               
					}
				}
				// Close the socket and exit the program when SwingClient is closed
				mSocket.close();
				System.exit(0);		                                 
			}            
		});
		Thread OutputThread = new Thread(new Runnable() {
			public void run(){
				String message;    
				while((message = mSocket.readString()) != null){                
					client.addMessage(message);					                                                                       
				}										
			}
		});
		OutputThread.start();
		OnlineThread.start();		
		
		try {
			OutputThread.join();
			OnlineThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}        	      
	}
}

