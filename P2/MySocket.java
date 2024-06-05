package P2;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;


public class MySocket {
    //atributs
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    // Constructor amb host i port específic
    public MySocket(String host, int port) {
        try {
            this.socket = new Socket(host, port);
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el socket", e);
        }
    }
    // Constructor que accepta un Socket existent 
    public MySocket(Socket socket) {
        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException("Error al crear el socket", e);
        }
    }
    public void close() {
        try {
            socket.close();
            reader.close();
            writer.close();        
        } catch (IOException e) {
            throw new RuntimeException("Error al tancar el socket", e);
        }
    }
    //Escriure 
    public void writeString(String data) {
        writer.println(data);
    }
    //Llegir    
    public String readString() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Error al llegir del socket", e);
        }
    }    
}
