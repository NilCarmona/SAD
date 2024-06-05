package P2;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class Server {
    private static final Map<String, MySocket> clients = new HashMap<>();
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) {

        System.out.println("Server is starting...");
        MyServerSocket serverSocket = new MyServerSocket(12345);
        System.out.println("Server is listening on port 12345...");
        System.out.println("Server is ready to accept clients...");

        while (true) {
            MySocket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    private static class ClientHandler implements Runnable {
        private MySocket clientSocket;
        private String nick;

        public ClientHandler(MySocket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                nick = clientSocket.readString();
                if (nick != null) {
                    addClient(nick, clientSocket);
                    System.out.println(nick + " has joined the chat.");

                    String message;
                    while ((message = clientSocket.readString()) != null && !message.equals("exit")) {
                        broadcastMessage(nick, message);
                    }
                }
            } finally {
                System.out.println(nick + " has left the chat.");
                broadcastMessage(nick, nick + " has left the chat.");
                removeClient(nick);
                clientSocket.close();
                
            }
        }

        private void addClient(String nick, MySocket socket) {
            lock.writeLock().lock();
            try {
                clients.put(nick, socket);
                broadcastMessage(nick,"first message");
            } finally {
                lock.writeLock().unlock();
            }
        }

        private void removeClient(String nick) {
            lock.writeLock().lock();
            try {
                clients.remove(nick);
            } finally {
                lock.writeLock().unlock();
            }
        }

        private void broadcastMessage(String senderNick, String message) {
            lock.readLock().lock();
            try {
                for (Map.Entry<String, MySocket> entry : clients.entrySet()) {
                    if (!entry.getKey().equals(senderNick)) {
                        if(message.equals("first message")){
                            entry.getValue().writeString(senderNick + " has joined the chat.");
                        }else{                            
                        entry.getValue().writeString(senderNick + ": " + message);
                        }
                    }
                }
            } finally {
                lock.readLock().unlock();
            }
        }
    }
}


