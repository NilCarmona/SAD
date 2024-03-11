package P1;
import java.io.IOException;
import java.io.InputStream;
import java.io.Termios;

public class RawMode {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        Termios termios = new Termios();

        // Obtener la configuración actual de la terminal
        tcgetattr(in.getFD(), termios);

        // Habilitar el modo raw
        termios.c_lflag &= ~ICANON;
        termios.c_lflag &= ~ECHO;

        // Establecer la nueva configuración
        tcsetattr(in.getFD(), TCSANOW, termios);

        // Leer las teclas y ejecutar la acción correspondiente
        while (true) {
            int c = in.read();

            switch (c) {
                case 'a':
                    System.out.println("Se presionó la tecla 'a'");
                    break;
                case 'b':
                    System.out.println("Se presionó la tecla 'b'");
                    break;
                case '\n':
                    System.out.println("Se presionó la tecla Enter");
                    break;
                case '\t':
                    System.out.println("Se presionó la tecla Tab");
                    break;
                case 27: // Escape
                    // Leer el siguiente carácter para determinar la secuencia de escape
                    int next = in.read();
                    switch (next) {
                        case 91: // '['
                            // Leer el siguiente carácter para determinar la flecha de dirección
                            int arrow = in.read();
                            switch (arrow) {
                                case 65: // 'A'
                                    System.out.println("Se presionó la flecha arriba");
                                    break;
                                case 66: // 'B'
                                    System.out.println("Se presionó la flecha abajo");
                                    break;
                                case 67: // 'C'
                                    System.out.println("Se presionó la flecha derecha");
                                    break;
                                case 68: // 'D'
                                    System.out.println("Se presionó la flecha izquierda");
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
    }

    private static native int tcgetattr(int fd, Termios termios);

    private static native int tcsetattr(int fd, int when, Termios termios);
}
