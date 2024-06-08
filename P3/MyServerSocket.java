

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {
    private ServerSocket serverSocket;

    // Constructor que crea un MyServerSocket escoltant en un port específic
    public MyServerSocket(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el ServerSocket", e);
        }
    }

    // Mètode per acceptar connexions entrant i retornar un MySocket
    public MySocket accept() {
        try {
            Socket socket = serverSocket.accept();
            return new MySocket(socket);
        } catch (IOException e) {
            throw new RuntimeException("Error al acceptar la connexió", e);
        }
    }

    // Mètode per tancar el ServerSocket
    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error al tancar el ServerSocket", e);
        }
    }
}

