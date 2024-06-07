package P3;

import java.lang.Thread;

public class MainCliente {
    private static final int SERVER_PORT = 12345;

	
	public static void main(String[] args){
		MySocket mysocket = new MySocket("localhost", SERVER_PORT);
		SwingCliente scliente = new SwingCliente( mysocket);
		scliente.GUI();

		new Thread(){ 
			public void run(){
				String message;
				while((message=mysocket.readString()) != null){
					scliente.addMessage(message);
				}
			}
		}.start();
	}
    
}
