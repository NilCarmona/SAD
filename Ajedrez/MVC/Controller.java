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
    boolean opcionJaque;
    //constructor
    public Controller(boolean opcionJaques) {
        opcionJaque = opcionJaques;
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
            
        }
        public MiMouseListener(int filaOrigen, int columnaOrigen) {
            this.filaOrigen = filaOrigen;
            this.columnaOrigen = columnaOrigen;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
                      
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (view.getLabels()[i][j] == e.getSource()) {
                        //1 CLICK: ESTO ES PARA LOS POSIBLES MOVIMIENTOS (VERDES Y ROJOS) TENER EN CUENTA LOS BORDES
                        if (filaOrigen == -1 && columnaOrigen == -1 || ((model.getTablero().getElemento(i, j).matches("[ptcadrPTCADR]")) && view.getLabels()[i][j].getBackground() != Color.RED)) {
                            //para resetear cuando cancelo mov
                            for (int k = 0; k < 8; k++) {
                                for (int l = 0; l < 8; l++) {
                                    if ((k + l) % 2 == 0) {
                                        labels[k][l].setBackground(Color.WHITE);
                                        view.setLabels(labels);
                                    }else{
                                        labels[k][l].setBackground(Color.GRAY);
                                        view.setLabels(labels);
                                    }
                                }
                            }
                            if(player1Turn){
                                //System.out.println("Turno de blancas");
                                if (model.getTablero().getElemento(i,j).matches("[PTCADR]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    view.setLabels(labels);                              
                                    
                                    //POSIBLES PIEZAS
                                   
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
                                    view.mostrarMensaje("Es turno de blancas");                                    
                                }
                                else {                                    
                                    view.mostrarMensajeTemporal("No hay piezas en esa posicion", 500);
                                }
                                
                            }
                            else if(player2Turn){                                
                                if (model.getTablero().getElemento(i,j).matches("[ptcadr]")) {
                                    filaOrigen = i;
                                    columnaOrigen = j;
                                    labels[filaOrigen][columnaOrigen].setBackground(Color.YELLOW);
                                    view.setLabels(labels);
                                    
                                    //POSIBLES PIEZAS
                                    
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
                                    view.mostrarMensaje("Es turno de negras");                                    
                                }
                                else {                                    
                                    view.mostrarMensajeTemporal("No hay piezas en esa posicion", 500);
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
                                }else{ //pintar los otros cuadros como siempre                                                  
                                    if ((k + l) % 2 == 0) {                                     
                                    labels[k][l].setBackground(Color.WHITE);                                    
                                } else {
                                    labels[k][l].setBackground(Color.GRAY);                                    
                                }

                                }
                            }
                        }                    

                        if(model.getTablero().getElemento(i, j).matches("[ptcadrPTCADR]")){
                            //System.out.println("Pieza seleccionada: " + model.getTablero().getElemento(i, j));
                        }
                        else{                            
                            view.mostrarMensajeTemporal("No hay pieza seleccionada",500);
                        }
                        labels[i][j].setBackground(Color.YELLOW);
                        //DECIRLE A LA VIEW QUE ACTUALICE LOS LABELS
                        view.setLabels(labels);

                        //2 clikc: AQUI ES PARA EL MOVIMIENTO
                        }else if ( filaOrigen != -1 && columnaOrigen != -1){                            
                            //cancelar movimiento
                            if (filaOrigen == i && columnaOrigen == j) {                      
                            
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
                            
                            }else{ //mover pieza                            
                            //guardar destino
                            filaDestino = i;
                            columnaDestino = j;

                            if(player1Turn){
                                if (model.getTablero().getElemento(filaOrigen, columnaOrigen).matches("[PTCADR]")) {                                    
                                    //unicamente moverme a lo verde o rojo
                                    if (view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.GREEN || view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        if(view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED){                                            
                                            //MATAR pieza
                                            view.mostrarMensajeTemporal("eliminando pieza",300);
                                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[r]")){
                                                view.mostrarMensaje("GAME OVER, GANA BLANCAS");
                                                gameOver = true;                                                
                                            }else if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[d]")){
                                                damaNegraMuerta = true;                                                
                                            }
                                        }
                                        //MOVER PIEZA
                                        moverPieza(filaOrigen, columnaOrigen, filaDestino, columnaDestino, tablero);
                                    }
                                    else{                                        
                                        view.mostrarMensajeTemporal("Movimiento invalido",750);
                                    }                                   
                                }
                                else{
                                    view.mostrarMensaje("Es turno de blancas");
                                }
                            }
                            else if(player2Turn){
                                if (model.getTablero().getElemento(filaOrigen, columnaOrigen).matches("[ptcadr]")) {                                    
                                    if (view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.GREEN || view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED) {
                                        if(view.getLabels()[filaDestino][columnaDestino].getBackground() == Color.RED){                                           
                                                                                        
                                            view.mostrarMensajeTemporal("eliminando pieza",300);
                                            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[R]")){
                                                view.mostrarMensaje("GAME OVER, GANA NEGRAS");
                                                gameOver = true;                                                 
                                            }else if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[D]")){                                                
                                                damaBlancaMuerta = true;                                                 
                                            }
                                        }
                                        moverPieza(filaOrigen, columnaOrigen, filaDestino, columnaDestino, tablero);
                                    }
                                    else{                                        
                                        view.mostrarMensajeTemporal("Movimiento invalido", 750);                                       
                                    }                                
                                }else{
                                    view.mostrarMensaje("Es turno de negras");
                                }                           
                            }
                            //FUNCION PROMOCION DE PEON
                            promocionPeon();
                            
                            if(opcionJaque){                                
                                model.check();
                            
                            if(model.getJaqueBlanco() && player1Turn){
                                view.mostrarMensajeTemporal("JAQUE AL REI BLANCO",1000);                                
                            }
                            if(model.getJaqueNegro() && player2Turn){
                                view.mostrarMensajeTemporal("JAQUE AL REI NEGRO",1000);
                            }   
                        }
                            if(gameOver){
                                //Repetir partida
                                int option = view.mostrarFinPartida();

                                if (option == 0) {
                                    // Volver a jugar
                                    // Aquí debes reiniciar el juego o realizar las acciones necesarias para comenzar una nueva partida
                                    view.setVisible(false);
                                    Controller controller = new Controller(opcionJaque);

                                } else if (option == 0) {
                                    // Ver repetición
                                    // Aquí debes implementar la lógica para mostrar la repetición de la partida
                                }                        

                            }
                                            
                        }
                    }
                }
            }
        }            

        }
        public void promocionPeon(){
            if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[P]")&& filaDestino == 0){                
                int choice = 0;
                if(damaBlancaMuerta){                    
                    String[] options = {"DAMA", "CABALLO", "ALFIL", "TORRE"};
                    choice = view.mostrarPromocion(options);                                 
                }else{
                    String[] options = {"CABALLO", "ALFIL", "TORRE"};
                    choice = view.mostrarPromocion(options);                    
                }

                if (choice == 0) {                    
                    if(damaBlancaMuerta){
                        setActualizar("D");
                        damaBlancaMuerta = false; 
                    }else if(!damaBlancaMuerta){
                        setActualizar("C");
                    }
                } else if (choice == 1) {
                    if(damaBlancaMuerta){
                        setActualizar("C");
                    }else if(!damaBlancaMuerta){
                        setActualizar("A");
                    }                        
                } else if (choice == 2) {                    
                    if(damaBlancaMuerta){
                        setActualizar("A");
                    }else if(!damaBlancaMuerta){ 
                        setActualizar("T");
                    }                               
                } else{                    
                    setActualizar("T");
                }                
            
        }else if(model.getTablero().getElemento(filaDestino, columnaDestino).matches("[p]")&& filaDestino == 7){
                //escojer                
                int choice = 0;                
                if(damaNegraMuerta){                    
                    String[] options = {"DAMA", "CABALLO", "ALFIL", "TORRE"};
                    choice = view.mostrarPromocion(options);                    
                }else{
                    String[] options = {"CABALLO", "ALFIL", "TORRE"};
                    choice = view.mostrarPromocion(options);                    
                }                
                if (choice == 0) {                    
                    if(damaNegraMuerta){
                        setActualizar("d");
                        damaNegraMuerta = false; 

                    }else if(!damaNegraMuerta){
                        setActualizar("c");
                    }
                } else if (choice == 1) {
                    if(damaNegraMuerta){
                        setActualizar("t");
              
                    }else if(!damaNegraMuerta){
                        setActualizar("a");
                    }                        
                } else if (choice == 2) {                    
                    if(damaNegraMuerta){
                        setActualizar("a");
                    }else if(!damaNegraMuerta){ 
                        setActualizar("t");
                    }                               
                } else{                    
                    setActualizar("t");
                }                     

        }
        }
        public void cambioTurno(){
            if(player1Turn){
                player1Turn = false;
                player2Turn = true;
            }else if(player2Turn){
                player2Turn = false;
                player1Turn = true;
            }
        }
        public void setActualizar(String pieza){
            tablero.setElemento(filaDestino, columnaDestino, pieza);
            model.setTablero(tablero);
            view.actualizarTablero(model.getTablero());
        }
        public void moverPieza(int filaOrigen, int columnaOrigen, int filaDestino, int columnaDestino, Tablero tablero){
            tablero.setElemento(filaDestino, columnaDestino, tablero.getElemento(filaOrigen, columnaOrigen));
            tablero.setElemento(filaOrigen, columnaOrigen, "");
            model.setTablero(tablero);
            view.actualizarTablero(model.getTablero());
            filaOrigen = -1;
            columnaOrigen = -1;                                       
            cambioTurno();
        }

        @Override
        public void mousePressed(MouseEvent e) {
           
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    }
}
    

