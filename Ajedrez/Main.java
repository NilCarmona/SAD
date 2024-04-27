
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import MVC.*;

public class Main {
    public static void main(String[] args) {
        
        View view = new View();
        SwingUtilities.invokeLater(() -> view.setVisible(true));
        //Controller controler = new Controller(view, new Model());
        //controler.reiniciarPartida();
    }
}

