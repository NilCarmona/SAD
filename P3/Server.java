package P3;

import java.lang.Thread;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server{
	private static Lock lock = new ReentrantLock();
	private static HashMap<String, MySocket> users = new HashMap<>();
	private static final int SERVER_PORT = 12345;

	public static void main(String[] args){
		MyServerSocket socket = new MyServerSocket(SERVER_PORT);
		System.out.println("Servidor Arrancado.\nEsperando Usuarios...");

		while(true){
			MySocket client = socket.accept();

			new Thread(){
				public void run(){
					client.writeString("SERVIDOR: (Información) Si quiere cerrar el chat escriba *EXIT*.");
					client.writeString("SERVIDOR: Escriba su nombre: ");
					String user = client.readString();
					addUser(user, client);
					String message;
					
					while((message = client.readString()) != null){
						send(message,user);
						System.out.println(user + ": "+ message);
						
						if(message.equals("EXIT")){
							break;
						}
					}
					remove_user(user);
					client.close();
				}
			}.start();
		}
	}
	public static void send(String message, String user){
		lock.lock();
		for(Map.Entry<String, MySocket> entry : users.entrySet()){
			MySocket mysocket = entry.getValue();
			if(!user.equals(entry.getKey())){
				mysocket.writeString(user+": "+message);
			}
		}
		lock.unlock();
	}

	public static void addUser(String user, MySocket mysocket){
		lock.lock();
		users.put(user, mysocket);
		System.out.println( user + " entro en el chat.");
		lock.unlock();
	}

	public static void remove_user(String user){
		lock.lock();
		users.remove(user);
		System.out.println(user + " salio del chat.");
		lock.unlock();
	}
}