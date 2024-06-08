
import java.util.Scanner;


public class ClientMain{
	private static int count = 0;		

	public static void main(String[] args){
                
		MySocket mSocket = new MySocket("localhost", 1234);
                Scanner sc= new Scanner(System.in); 
                System.out.print("Introdueix el teu nom: ");
                String nick= sc.nextLine();  
 
		SwingClient client = new SwingClient(nick, mSocket);
		sc.close();		
		client.createAndShowGUI(nick);


		// Output Thread
		new Thread(){
			public void run(){			
									
				
				while (Thread.activeCount() >= 0) {
					count++;
					System.out.println("Counter: " + count);					
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
				
					 			 
			}	
			
		}.start();

	    new Thread(){
			public void run(){
			String message;	
			while((message = mSocket.readString()) != null){
				
				client.addMessage(message);
				count = 0;				
																	
			}
		}
		}.start();
		
		
	}
}


