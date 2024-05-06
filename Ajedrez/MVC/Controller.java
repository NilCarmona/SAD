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
    private boolean damaBlancaMuerta = false;
    private boolean damaNegraMuerta = false;


    //constructor
    public Controller() {
        
        model = new Model();
        view = new View();
        labels = view.getLabels(); //labels temporales
        view.setVisible(true);
        tablero = model.getTablero();
        view.actualizarTablero(tablero);
        view.addMouseListener(new MiMouseListener()); //añadir listener a la vista
                
    }
    //LOGICA DE LOS LISTENERS DE MOUSE (MOUSELISTENER) 
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
                    if (view.getLabels()[i][j] == e.getSource()) {
                        //ESTO DEBERIA ESTAR EN EL CONTROLADOR PERO LOS PRINTS COMO VIEW Y LOS MOVIMIENTOS COMO MODEL
                        //1 CLICK: ESTO ES PARA LOS POSIBLES MOVIMIENTOS (VERDES Y ROJOS) TENER EN CUENTA LOS BORDES
                        if (filaOrigen == -1 && columnaOrigen == -1 || ((model.getTablero().getElemento(i, j).matches("[ptcadrPTCADR]")) && view.getLabels()[i][j].getBackground() != Color.RED)) {
                            System.out.println("PRIMER CLICK: " + i + ", " + j);
                            //para resetear cuando cancelo mov
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    if ((k + l) % 2 == 0) {
                                        labels[k][l].setBackground(Color.WHITE);
                                        //view.setLabels(labels);
                                        //view.getLabels()[k][l].setBackground(Color.GRAY);
                                    }else{
                                        labels[k][l].setBackground(Color.GRAY);
                                        //view.setLabels(labels);
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
                                    //view.setLabels(labels);
                                    //view.getLabels()[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    
                                    //POSIBLES MOVIMIENTOS
                                   
                                    switch(model.getTablero().getElemento(i, j)){
                                        case "P":
                                             pintarMovimientos = model.peonB.posiblesMovimientosPeon(filaOrigen, columnaOrigen, model.getTablero());   
                                            
                                             break;
                                        case "T":
                                            pintarMovimientos = model.torreB.posiblesMovimientosTorre(filaOrigen, columnaOrigen,model.getTablero());
                                            break;
                                        case "C":
                                            pintarMovimientos = model.caballoB.posiblesMovimientosCaballo(filaOrigen, columnaOrigen,model.getTablero());
                                            break;
                                        case "A":
                                            pintarMovimientos = model.alfilB.posiblesMovimientosAlfil(filaOrigen, columnaOrigen, model.getTablero());
                                            break;                                               
                                        case "D":
                                            pintarMovimientos = model.damaB.posiblesMovimientosDama(filaOrigen, columnaOrigen,model.getTablero());
                                            break;
                                        case "R":
                                            pintarMovimientos = model.reyB.posiblesMovimientosRey(filaOrigen, columnaOrigen,model.getTablero());
                                            break;                                        
                                    }
    
                                }
                                else if(model.getTablero().getElemento(i, j).matches("[ptcadr]")){
                                    System.out.println("Es turno de blancas");
                                    
                                }
                                else {
                                    System.out.println("No hay piezas en esa posicion");
                                }
                                
                            }
                            else if(player2Turn){
                                System.out.println("Turno de negras");
                                if (model.getTablero().getElemento(i,j).matches("[ptcadr]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    //view.setLabels(labels);
                                    //view.getLabels()[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    //POSIBLES MOVIMIENTOS
                                    //model.posiblesMovimientos(tablero.getElemento(filaOrigen, columnaOrigen));
                                    switch(model.getTablero().getElemento(i, j)){
                                        case "p":                                            
                                            pintarMovimientos = model.peonN.posiblesMovimientosPeon(filaOrigen, columnaOrigen, model.getTablero());
                                            
                                            break;                                        
                                        case "t":
                                            pintarMovimientos = model.torreN.posiblesMovimientosTorre(filaOrigen, columnaOrigen, model.getTablero());
                                            break;
                                        case "c":
                                            pintarMovimientos = model.caballoN.posiblesMovimientosCaballo(filaOrigen, columnaOrigen, model.getTablero());
                                            break;
                                        case "a":                                            
                                            pintarMovimientos=model.alfilN.posiblesMovimientosAlfil(filaOrigen, columnaOrigen, model.getTablero());
                                            break;
                                        case "d":
                                            pintarMovimientos = model.damaN.posiblesMovimientosDama(filaOrigen, columnaOrigen, model.getTablero());
                                            break;
                                        case "r":                                            
                                            pintarMovimientos = model.reyN.posiblesMovimientosRey(filaOrigen, columnaOrigen, model.getTablero());
                                            break;
                                    }
    
                                }
                                else if(model.getTablero().getElemento(i,j).matches("[PTCADR]")){
                                    System.out.println("Es turno de negras");
                                    
                                }
                                else {
                                    System.out.println("No hay piezas en esa posicion");
                                }

                                
                            }
                        //pintar los movimientos almacenados en pintarMovimientos
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {                                

                                if (pintarMovimientos[k][l] == "verde") {
                                    //view.getLabels()[k][l].setBackground(Color.GREEN);
                                    //pintarMovimientos[k][l]=null;
                                    labels[k][l].setBackground(Color.GREEN);
                                    //view.setLabels(labels);

                                    pintarMovimientos[k][l]=null;
                                    
                                } else if (pintarMovimientos[k][l] == "rojo") {
                                    //view.getLabels()[k][l].setBackground(Color.RED);
                                    //pintarMovimientos[k][l]=null;
                                    labels[k][l].setBackground(Color.RED);
                                    //view.setLabels(labels);
                                    pintarMovimientos[k][l]=null;

                                }else{
                                                                       
                                    if ((k + l) % 2 == 0) {//pintar los cuadros
                                    //view.getLabels()[k][l].setBackground(Color.WHITE);
                                    labels[k][l].setBackground(Color.WHITE);
                                    //view.setLabels(labels);
                                    
                                } else {
                                    labels[k][l].setBackground(Color.GRAY);
                                    //view.setLabels(labels);
                                }

                                }
                            }
                        }
                        //model.movimientos= null;

                        if(model.getTablero().getElemento(i, j).matches("[ptcadrPTCADR]")){
                            System.out.println("Pieza seleccionada: " + model.getTablero().getElemento(i, j));
                        }
                        else{
                            System.out.println("No hay pieza seleccionada");
                        }
                        labels[i][j].setBackground(Color.YELLOW);

                        view.setLabels(labels);

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
                                        //view.getLabels()[k][l].setBackground(Color.WHITE);
                                        labels[k][l].setBackground(Color.WHITE);
                                        //view.setLabels(labels);
                                        
                                    } else {
                                        labels[k][l].setBackground(Color.GRAY);
                                        //view.setLabels(labels);
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
                                    if (view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.GREEN || view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        if(view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED){
                                            
                                            //comer pieza
                                            System.out.println("COMER PIEZA");
                                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[r]")){
                                                System.out.println("GAME OVER, GANA BLANCAS");
                                                //IMPLEMENTAR MENU ETC...
                                            }else if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[d]")){
                                                System.out.println("DAMA NEGRA MUERTA");
                                                damaNegraMuerta = true;
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
                                    if (view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.GREEN || view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        if(view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED){
                                            
                                            //comer pieza
                                            System.out.println("COMER PIEZA");
                                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[R]")){
                                                System.out.println("GAME OVER, GANA NEGRAS");
                                                 //IMPLEMENTAR MENU ETC...
                                            }else if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[D]")){
                                                System.out.println("DAMA BLANCA MUERTA");
                                                damaBlancaMuerta = true;

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
                            //PROMOCION DE PEON
                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[P]")&& filaDestino == 0){
                                    //escojer
                                    //view.....(menu)
                                    
                                    if(damaBlancaMuerta){
                                    tablero.setElemento(filaDestino, columnaDestino, "D");
                                    model.setTablero(tablero);
                                    view.actualizarTablero(model.getTablero());
                                    damaBlancaMuerta = false;
                                    }
                                
                            }else if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[p]")&& filaDestino == 7){
                                    //escojer
                                    if(damaNegraMuerta){
                                    tablero.setElemento(filaDestino, columnaDestino, "d");
                                    model.setTablero(tablero);
                                    view.actualizarTablero(model.getTablero());
                                    damaNegraMuerta = false;
                                    }
                            }
                            model.check();
                            
                            if(model.getJaqueBlanco()){
                                System.out.println("JAQUE AL BLANCO");
                            }
                            if(model.getJaqueNegro()){
                                System.out.println("JAQUE Al NEGRO");
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
    

