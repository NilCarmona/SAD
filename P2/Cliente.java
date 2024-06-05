package P2;

import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        MySocket socket = new MySocket("localhost", 12345);
        System.out.println("Connected to server on port 12345.");
        System.out.println("Type 'exit' to quit.");
        System.out.println("Write your name: ");
        Thread inputThread = new Thread(new Runnable() {
            @Override
            public void run() {
                
                try (BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in))) {
                    String line;
                    while ((line = keyboardReader.readLine()) != null) {
                        socket.writeString(line);                        
                        if (line.equals("exit")) {
                            System.exit(0);
                            break;
                        }                       
                                 
                    }
                } catch (IOException e) {
                    System.err.println("Error reading from keyboard: " + e.getMessage());
                } finally {
                    socket.close();
                }
            }
        });

        Thread outputThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String line;
                while ((line = socket.readString()) != null ) {
                    System.out.println(line); //Broadcast message
                }
            }
        });

        inputThread.start();
        outputThread.start();

        try {
            inputThread.join();
            outputThread.join();
        } catch (InterruptedException e) {
            System.err.println("Threads interrupted: " + e.getMessage());
        }
    }
}

