package MVC;

import javax.swing.JOptionPane;

import org.w3c.dom.events.MouseEvent;

import rsc.tablero.Tablero;
import java.awt.event.MouseListener;

public class Controller {
    
    private final View view;
    private final Model model;
    private Tablero tablero;
    
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;

        //view.addMouseListener(new MiMouseListener());

        //this.view.actualizarTablero(this.model.getTablero());
        
    }
    
    public void moverPieza(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino) {
        //model.moverPieza(filaOrigen, columnaOrigen, filaDestino, columnaDestino);
        tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
        tablero.setElemento(filaOrigen, columnaOrigen, "");
        view.actualizarTablero(tablero);
        
    }
    
    /*private class MiMouseListener implements MouseListener {
        private int filaOrigen = -1;
        private int columnaOrigen = -1;
        private int filaDestino = -1;
        private int columnaDestino = -1;

        public MiMouseListener() {
            // Empty implementation
        }

        @Override
       public void mouseClicked(MouseEvent e) {
            // Lógica para manejar el clic en la JLabel

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (view.labels[i][j] == e.getSource()) {

                        if (filaOrigen == -1 && columnaOrigen == -1) {
                            System.out.println("Seleccionar pieza: " + i + ", " + j);
                            filaOrigen = i;
                            columnaOrigen = j;
                        } else if (filaOrigen == i && columnaOrigen == j) {
                            System.out.println("Cancelar movimiento");
                            filaOrigen = -1;
                            columnaOrigen = -1;
                        } else {
                            System.out.println("Mover a: " + i + ", " + j);
                            filaDestino = i;
                            columnaDestino = j;
                            //controller.moverPieza(filaOrigen, columnaOrigen,filaDestino , columnaDestino);
                            tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
                            tablero.setElemento(filaOrigen, columnaOrigen, "");
                            view.actualizarTablero(tablero);
                            filaOrigen = -1;
                            columnaOrigen = -1;
                        }
                    }
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // TODO: Implement mousePressed method
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO: Implement mouseReleased method
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO: Implement mouseEntered method
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO: Implement mouseExited method
        }
    }*/

}
