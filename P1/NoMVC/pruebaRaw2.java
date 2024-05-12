/*package P1.NoMVC;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.Termios;

public class pruebaRaw2 {


public class ModoRaw {

    public static void main(String[] args) throws IOException {
        InputStream in = System.in;
        FileDescriptor fd = in.getFD();
        Termios termios = new Termios();

        // Obtener la configuración actual
        tcgetattr(fd, termios);

        // Habilitar modo Raw
        termios.c_lflag &= ~ICANON;
        termios.c_lflag &= ~ECHO;

        // Establecer la nueva configuración
        tcsetattr(fd, TCSANOW, termios);

        // Leer caracteres hasta que se presione Enter
        int c;
        while ((c = in.read()) != '\n') {
            System.out.print((char) c);
        }

        // Restaurar modo Cooked
        tcsetattr(fd, TCSANOW, termios);
    }

    private static native int tcgetattr(int fd, Termios termios);
    private static native int tcsetattr(int fd, int optional_actions, Termios termios);
}

}*/
