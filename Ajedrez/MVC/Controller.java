package MVC;

import rsc.tablero.Tablero;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent; 

public class Controller {
    //variables
    private Model model;
    private View view;
    private JLabel [][] labels;
    private Tablero tablero;
    private boolean player1Turn = true;
    private boolean player2Turn = false;
    private boolean gameOver = false; 


    //constructor
    public Controller() {
        
        model = new Model();
        view = new View();
        labels = view.getLabels();
        view.setVisible(true);
        tablero = model.getTablero();
        view.actualizarTablero(tablero);
        view.addMouseListener(new MiMouseListener());
                
    }

    private class MiMouseListener implements MouseListener {
        private int filaOrigen = -1;
        private int columnaOrigen = -1;
        private int filaDestino = -1;
        private int columnaDestino = -1;

        String [][] pintarMovimientos;
        
    
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
                    if (view.labels[i][j] == e.getSource()) {
                        //ESTO DEBERIA ESTAR EN EL CONTROLADOR PERO LOS PRINTS COMO VIEW Y LOS MOVIMIENTOS COMO MODEL
                        //1 CLICK: ESTO ES PARA LOS POSIBLES MOVIMIENTOS (VERDES Y ROJOS) TENER EN CUENTA LOS BORDES
                        if (filaOrigen == -1 && columnaOrigen == -1 || ((model.getTablero().getElemento(i, j).matches("[ptcadrPTCADR]")) && labels[i][j].getBackground() != Color.RED)) {
                            System.out.println("PRIMER CLICK: " + i + ", " + j);
                            //para resetear cuando cancelo mov
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    if ((k + l) % 2 == 0) {
                                        labels[k][l].setBackground(Color.WHITE);
                                    } else {
                                        labels[k][l].setBackground(Color.GRAY);
                                    }
                                }
                            }
                            if(player1Turn){
                                System.out.println("Turno de blancas");
                                if (model.getTablero().getElemento(i,j).matches("[PTCADR]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    //VIEW
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    
                                    //POSIBLES MOVIMIENTOS
                                   
                                    switch(model.getTablero().getElemento(i, j)){
                                        case "P":
                                             pintarMovimientos = model.posiblesMovimientosPeon(filaOrigen, columnaOrigen);   
                                            break;
                                        case "T":
                                            pintarMovimientos = model.posiblesMovimientosTorre(filaOrigen, columnaOrigen);
                                            break;
                                        case "C":
                                            pintarMovimientos = model.posiblesMovimientosCaballo(filaOrigen, columnaOrigen);
                                            break;
                                        case "A":
                                            pintarMovimientos = model.posiblesMovimientosAlfil(filaOrigen, columnaOrigen);
                                            break;                                               
                                        case "D":
                                            pintarMovimientos = model.posiblesMovimientosDama(filaOrigen, columnaOrigen);
                                            break;
                                        case "R":
                                            pintarMovimientos = model.posiblesMovimientosRey(filaOrigen, columnaOrigen);
                                            break;                                        
                                    }
    
                                }
                                else if(model.getTablero().getElemento(i, j).matches("[ptcadr]")){
                                    System.out.println("Es turno de blancas");
                                    
                                }
                                else {
                                    System.out.println("No hay piezas tuyas en esa posicion");
                                }
                                
                            }
                            else if(player2Turn){
                                System.out.println("Turno de negras");
                                if (model.getTablero().getElemento(i,j).matches("[ptcadr]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    //POSIBLES MOVIMIENTOS
                                    //model.posiblesMovimientos(tablero.getElemento(filaOrigen, columnaOrigen));
                                    switch(model.getTablero().getElemento(i, j)){
                                        case "p":                                            
                                            pintarMovimientos = model.posiblesMovimientosPeon(filaOrigen, columnaOrigen);
                                            break;                                        
                                        case "t":
                                            pintarMovimientos = model.posiblesMovimientosTorre(filaOrigen, columnaOrigen);
                                            break;
                                        case "c":
                                            pintarMovimientos = model.posiblesMovimientosCaballo(filaOrigen, columnaOrigen);
                                            break;
                                        case "a":                                            
                                            pintarMovimientos=model.posiblesMovimientosAlfil(filaOrigen, columnaOrigen);
                                            break;
                                        case "d":
                                            pintarMovimientos = model.posiblesMovimientosDama(filaOrigen, columnaOrigen);
                                            break;
                                        case "r":                                            
                                            pintarMovimientos = model.posiblesMovimientosRey(filaOrigen, columnaOrigen);
                                            break;
                                    }
    
                                }
                                else if(model.getTablero().getElemento(i,j).matches("[PTCADR]")){
                                    System.out.println("Es turno de negras");
                                    
                                }
                                else {
                                    System.out.println("No hay piezas tuyas en esa posicion");
                                }

                                
                            }
                        //pintar los movimientos almacenados en pintarMovimientos
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (pintarMovimientos[k][l] == "verde") {
                                    labels[k][l].setBackground(Color.GREEN);
                                    pintarMovimientos[k][l]=null;
                                } else if (pintarMovimientos[k][l] == "rojo") {
                                    labels[k][l].setBackground(Color.RED);
                                    pintarMovimientos[k][l]=null;
                                }
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
                                if (model.getTablero().getElemento(filaOrigen, columnaOrigen).matches("[PTCADR]")) {
                                    //model.moverPieza(filaOrigen, columnaOrigen,filaDestino , columnaDestino);
                                    //unicamente moverme a lo verde o rojo
                                    if (labels[filaDestino][columnaDestino].getBackground() == Color.GREEN || labels[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        if(labels[filaDestino][columnaDestino].getBackground() == Color.RED){
                                            
                                            //comer pieza
                                            System.out.println("COMER PIEZA");
                                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[r]")){
                                                System.out.println("GAME OVER, GANA BLANCAS");
                                                //IMPLEMENTAR MENU ETC...
                                            }
                                            

                                        }
                                        tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
                                        tablero.setElemento(filaOrigen, columnaOrigen, "");
                                        model.setTablero(tablero);
                                        view.actualizarTablero(model.getTablero());
                                        filaOrigen = -1;
                                        columnaOrigen = -1;
                                        //funcion cambio de turno
                                        player1Turn = false;
                                        player2Turn = true;
                                    }
                                    else{
                                        System.out.println("Movimiento invalido");
                                        

                                    }
                                                                    
                                    
                                }
                                else{
                                    System.out.println("Es turno de blancas");
                                }
                            }
                            else if(player2Turn){
                                if (model.getTablero().getElemento(filaOrigen, columnaOrigen).matches("[ptcadr]")) {
                                    //model.moverPieza(filaOrigen, columnaOrigen,filaDestino , columnaDestino);
                                    if (labels[filaDestino][columnaDestino].getBackground() == Color.GREEN || labels[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        if(labels[filaDestino][columnaDestino].getBackground() == Color.RED){
                                            
                                            //comer pieza
                                            System.out.println("COMER PIEZA");
                                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[R]")){
                                                System.out.println("GAME OVER, GANA NEGRAS");
                                                 //IMPLEMENTAR MENU ETC...
                                            }
                                            

                                        }
                                        tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
                                        tablero.setElemento(filaOrigen, columnaOrigen, "");
                                        model.setTablero(tablero);
                                        view.actualizarTablero(model.getTablero());
                                        filaOrigen = -1;
                                        columnaOrigen = -1;
                                        //funcion cambio de turno
                                        player2Turn = false;
                                        player1Turn = true;
                                    }
                                    else{
                                        System.out.println("Movimiento invalido");
                                        
                                    }
                                
                                }
                                
                                else{
                                    System.out.println("Es turno de negras");
                                }
                            
                                
                            
                            }
                            //SUSTITUIR TODO ESTE CODIGO POR FUNCIONES CHECK Y CHECKMATE en MODELO
                            //CHECK     
                            /*if(model.check()){
                                System.out.println("CHECK");
                            }*/
                            model.check();
                            
                            

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
    


