package MVC;

import rsc.tablero.Tablero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class View extends JFrame {

    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;
    private JPanel panel = new JPanel(new GridLayout(FILAS, COLUMNAS));
    private JLabel [][] labels ;
    //Tablero tablero;

   
    
    
    //CONSTRUCTOR
    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Tablero de Ajedrez");
        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);
        labels = new JLabel[FILAS][COLUMNAS];

        // Inicializa la matriz de JLabel
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                labels[fila][columna] = new JLabel();
                labels[fila][columna].setOpaque(true);
                panel.add(labels[fila][columna]);

                if ((fila + columna) % 2 == 0) {
                    labels [fila][columna].setBackground(Color.WHITE);
                } else {
                    labels [fila][columna].setBackground(Color.GRAY);
                }
                
            }
        }
               
        add(panel);
    }

    public void actualizarTablero(Tablero tablero) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                String pieza = tablero.getElemento(fila, columna);
                
                switch (pieza) {
                    case "P":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/white-pawn-50.png")));
                        break;
                    case "T":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/white-rook-50.png")));
                        break;
                    case "C":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/white-knight-50.png")));
                        break;
                    case "A":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/white-bishop-50.png")));
                        break;
                    case "D":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/white-queen-50.png")));
                        break;
                    case "R":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/white-king-50.png")));
                        break;
                    case "p":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/black-pawn-50.png")));
                        break;
                    case "t":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/black-rook-50.png")));
                        break;
                    case "c":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/black-knight-50.png")));
                        break;
                    case "a":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/black-bishop-50.png")));
                        break;
                    case "d":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/black-queen-50.png")));
                        break;
                    case "r":
                        labels[fila][columna].setIcon(new ImageIcon(getClass().getResource("/rsc/images/black-king-50.png")));
                        break;
                    default:
                        labels[fila][columna].setIcon(null);
                        break;
                }
                if ((fila + columna) % 2 == 0) {
                    labels[fila][columna].setBackground(Color.WHITE);
                } else {
                    labels[fila][columna].setBackground(Color.GRAY);
                }
                labels[fila][columna].setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
    }
    
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        }
    public void mostrarMensajeTemporal(String mensaje, int tiempo) {
        JOptionPane pane = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = pane.createDialog(null, "Mensaje Temporal");
        dialog.setModal(false);
        dialog.setVisible(true);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
            dialog.dispose();
            }
        }, tiempo);
        }
        
    public int mostrarFinPartida(String mensaje) {
        int option = JOptionPane.showOptionDialog(null, "¿Qué deseas hacer?", "Fin de la partida", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[] {"Volver a jugar", "Ver repetición"}, null);
        return option; 
    }    
    public void addMouseListener(MouseListener listener) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				labels[i][j].addMouseListener(listener);
	}
    // Getters y Setters
    public JLabel[][] getLabels() {
        return labels;
    }
    public void setLabels(JLabel[][] labels) {
        this.labels = labels;
    }


}

    

    





    

    

