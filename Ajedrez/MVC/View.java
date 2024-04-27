package MVC;


import rsc.tablero.Tablero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class View extends JFrame {
    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;
    JPanel panel = new JPanel(new GridLayout(FILAS, COLUMNAS));
    JLabel [][] labels ;
    Tablero tablero;

    //var de controller
    private boolean player1Turn = true;
    private boolean player2Turn = false;
    private boolean movimientoInvalido = false;
    private boolean movcancelado = false;
    
    
    
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
        tablero = new Tablero();
        actualizarTablero(tablero);

        addMouseListener(new MiMouseListener());
        
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
    
    public void addMouseListener(MouseListener listener) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				labels[i][j].addMouseListener(listener);
	}

//TODO ESTO DEBERIA ESTAR EN EL CONTROLADOR
    private class MiMouseListener implements MouseListener {
        private int filaOrigen = -1;
        private int columnaOrigen = -1;
        private int filaDestino = -1;
        private int columnaDestino = -1;
    
        public MiMouseListener() {
            // Empty implementation
        }
        public MiMouseListener(int filaOrigen, int columnaOrigen) {
            this.filaOrigen = filaOrigen;
            this.columnaOrigen = columnaOrigen;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Lógica para manejar el clic en la JLabel
            
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (labels[i][j] == e.getSource()) {
                        //ESTO DEBERIA ESTAR EN EL CONTROLADOR PERO LOS PRINTS COMO VIEW Y LOS MOVIMIENTOS COMO MODEL
                        //1 CLICK: ESTO ES PARA LOS POSIBLES MOVIMIENTOS (VERDES Y ROJOS) TENER EN CUENTA LOS BORDES
                        if (filaOrigen == -1 && columnaOrigen == -1 || ((tablero.getElemento(i, j).matches("[ptcadrPTCADR]")) && labels[i][j].getBackground() != Color.RED)) {
                            System.out.println("PRIMER CLICK: " + i + ", " + j);
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    if ((k + l) % 2 == 0) {
                                        labels[k][l].setBackground(Color.WHITE);
                                    } else {
                                        labels[k][l].setBackground(Color.GRAY);
                                    }
                                }
                            }

                            //
                            if(player1Turn){
                                System.out.println("Turno de blancas");
                                if (tablero.getElemento(i,j).matches("[PTCADR]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    
                                    //POSIBLES MOVIMIENTOS
                                    //model.posiblesMovimientos(tablero.getElemento(filaOrigen, columnaOrigen));
                                    switch(tablero.getElemento(i, j)){
                                        case "P":
                                            //model.posiblesMovimientosPeon(filaOrigen, columnaOrigen);
                                            if (filaOrigen == 6) {
                                                // Lógica para mover el peón 2 casillas hacia adelante
                                                for (int k = 1; k <= 2; k++) {
                                                    labels[filaOrigen - k][columnaOrigen].setBackground(Color.GREEN);
                                                }
                                                
                                            } else if(!tablero.getElemento(filaOrigen - 1, columnaOrigen).matches("[ptcadrPTCADR]")) {
                                                
                                                labels[filaOrigen - 1][columnaOrigen].setBackground(Color.GREEN);
                                            }if(tablero.getElemento(filaOrigen - 1, columnaOrigen - 1).matches("[ptcadr]")){
                                                labels[filaOrigen - 1][columnaOrigen - 1].setBackground(Color.RED);
    
                                            }if (tablero.getElemento(filaOrigen - 1, columnaOrigen + 1).matches("[ptcadr]")){
                                                labels[filaOrigen - 1][columnaOrigen + 1].setBackground(Color.RED);
                                            }
                                            break;
                                        
                                        case "T":
                                            //model.posiblesMovimientosTorre(filaOrigen, columnaOrigen);
                                            break;
                                        case "C":
                                            //model.posiblesMovimientosCaballo(filaOrigen, columnaOrigen);
                                            break;
                                        case "A":
                                            //model.posiblesMovimientosAlfil(filaOrigen, columnaOrigen);
                                            break;
                                        case "D":
                                            //model.posiblesMovimientosDama(filaOrigen, columnaOrigen);
                                            break;
                                        case "R":
                                            //model.posiblesMovimientosRey(filaOrigen, columnaOrigen);
                                            break;
                                    }
    
                                }
                                else if(tablero.getElemento(i, j).matches("[ptcadr]")){
                                    System.out.println("Es turno de blancas");
                                    
                                }
                                else {
                                    System.out.println("No hay piezas tuyas en esa posicion");
                                }
                                
                            }
                            else if(player2Turn){
                                System.out.println("Turno de negras");
                                if (tablero.getElemento(i,j).matches("[ptcadr]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    //POSIBLES MOVIMIENTOS
                                    //model.posiblesMovimientos(tablero.getElemento(filaOrigen, columnaOrigen));
                                    switch(tablero.getElemento(i, j)){
                                        case "p":
                                            //model.posiblesMovimientosPeon(filaOrigen, columnaOrigen);
                                            if (filaOrigen == 1) {
                                                // Lógica para mover el peón 2 casillas hacia adelante
                                                for (int k = 1; k <= 2; k++) {
                                                    labels[filaOrigen + k][columnaOrigen].setBackground(Color.GREEN);
                                                }
                                                
                                            } else if(!tablero.getElemento(filaOrigen + 1, columnaOrigen).matches("[ptcadrPTCADR]")) {
                                                
                                                labels[filaOrigen + 1][columnaOrigen].setBackground(Color.GREEN);
                                            }if(tablero.getElemento(filaOrigen + 1, columnaOrigen + 1).matches("[PTCADR]")){
                                                labels[filaOrigen + 1][columnaOrigen + 1].setBackground(Color.RED);
    
                                            }if (tablero.getElemento(filaOrigen + 1, columnaOrigen - 1).matches("[PTCADR]")){
                                                labels[filaOrigen + 1][columnaOrigen - 1].setBackground(Color.RED);
                                            }
                                            break;
                                        
                                        case "t":
                                            //model.posiblesMovimientosTorre(filaOrigen, columnaOrigen);
                                            break;
                                        case "c":
                                            //model.posiblesMovimientosCaballo(filaOrigen, columnaOrigen);
                                            break;
                                        case "a":
                                            //model.posiblesMovimientosAlfil(filaOrigen, columnaOrigen);
                                            break;
                                        case "s":
                                            //model.posiblesMovimientosDama(filaOrigen, columnaOrigen);
                                            break;
                                        case "r":
                                            //model.posiblesMovimientosRey(filaOrigen, columnaOrigen);
                                            break;
                                    }
    
                                }
                                else if(tablero.getElemento(i,j).matches("[PTCADR]")){
                                    System.out.println("Es turno de negras");
                                    
                                }
                                else {
                                    System.out.println("No hay piezas tuyas en esa posicion");
                                }

                                
                            }

                        //2 clikc: AQUI ES PARA EL MOVIMIENTO

                        }else if ( filaOrigen != -1 && columnaOrigen != -1){
                            System.out.println("SEGUNDO CLICK: " + i + ", " + j);
                            //cancelar mov (posible otra implementacion)
                            if (filaOrigen == i && columnaOrigen == j) {
                            System.out.println("Cancelar movimiento");
                            filaOrigen = -1;
                            columnaOrigen = -1;
                            movcancelado = true;
                            //tengo que resetear los colores
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    if ((k + l) % 2 == 0) {
                                        labels[k][l].setBackground(Color.WHITE);
                                    } else {
                                        labels[k][l].setBackground(Color.GRAY);
                                    }
                                }
                            }
                            }else{
                            System.out.println("Mover a: " + i + ", " + j);
                            filaDestino = i;
                            columnaDestino = j;

                            if(player1Turn){
                                if (tablero.getElemento(filaOrigen, columnaOrigen).matches("[PTCADR]")) {
                                    //model.moverPieza(filaOrigen, columnaOrigen,filaDestino , columnaDestino);
                                    //unicamente moverme a lo verde o rojo
                                    if (labels[filaDestino][columnaDestino].getBackground() == Color.GREEN || labels[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
                                        tablero.setElemento(filaOrigen, columnaOrigen, "");
                                        actualizarTablero(tablero);
                                        filaOrigen = -1;
                                        columnaOrigen = -1;
                                        //funcion cambio de turno
                                        player1Turn = false;
                                        player2Turn = true;
                                    }
                                    else{
                                        System.out.println("Movimiento invalido");
                                        movimientoInvalido = true;

                                    }
                                                                    
                                    
                                }
                                else{
                                    System.out.println("Es turno de blancas");
                                }
                            }
                            else if(player2Turn){
                                if (tablero.getElemento(filaOrigen, columnaOrigen).matches("[ptcadr]")) {
                                    //model.moverPieza(filaOrigen, columnaOrigen,filaDestino , columnaDestino);
                                    if (labels[filaDestino][columnaDestino].getBackground() == Color.GREEN || labels[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
                                        tablero.setElemento(filaOrigen, columnaOrigen, "");
                                        actualizarTablero(tablero);
                                        filaOrigen = -1;
                                        columnaOrigen = -1;
                                        //funcion cambio de turno
                                        player2Turn = false;
                                        player1Turn = true;
                                    }
                                    else{
                                        System.out.println("Movimiento invalido");
                                        movimientoInvalido = true;
                                    }
                                
                                }
                                
                                else{
                                    System.out.println("Es turno de negras");
                                }
                            
                                
                            
                            }
                     
                        }
                    }
              
                    }  
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {
            // Empty implementation
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Empty implementation
            

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // Empty implementation
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Empty implementation
        }
    }
}

    

    


