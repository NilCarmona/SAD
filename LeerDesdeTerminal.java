import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class LeerDesdeTerminal {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Crear un objeto BufferedReader para leer desde la entrada estándar (el terminal)
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Leer una línea de texto del terminal
        //String linea = br.readLine();
        // Convertir el entero a carácter
        //char caracter = (char) br.read();
        
        Scanner scanner = new Scanner(System.in);
        String nombre = scanner.nextLine();
        scanner.close();
        System.out.println(nombre);
       
         
        // Imprimir el carácter convertido
        //System.out.println("Carácter convertido: " + caracter);
        // Imprimir la línea leída
        //System.out.println("Leíste: " + linea);
    }
    
}
