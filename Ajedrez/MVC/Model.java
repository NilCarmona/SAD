package MVC;

import rsc.piezas.*;
import java.util.Arrays;



import rsc.tablero.Tablero; // Replace "some.package" with the actual package name of the Tablero class


public class Model {
    //variables
    private Tablero tablero;
    //String[][] movimientos;
    String[][] posibleCheck= new String[8][8];
    //todas las piezas
    Peon peonB;
    Peon peonN;
    Torre torreB;
    Torre torreN;
    Caballo caballoB;
    Caballo caballoN;
    Alfil alfilB;
    Alfil alfilN;
    Dama damaB;
    Dama damaN;
    Rey reyB;
    Rey reyN;

    //constructor
    public Model() {
        //inicializar tablero
        tablero = new Tablero();

        //inicializar piezas
        peonB = new Peon("B");
        peonN = new Peon("N");
        torreB = new Torre("B");
        torreN = new Torre("N");
        caballoB = new Caballo("B");
        caballoN = new Caballo("N");
        alfilB = new Alfil("B");
        alfilN = new Alfil("N");
        damaB = new Dama("B");
        damaN = new Dama("N");
        reyB = new Rey("B");
        reyN = new Rey("N");

       // movimientos = new String[8][8];

    }
    public Tablero getTablero(){
        return tablero;
    }
    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
    
//FUNCION JAQUE
    public void check(){        
        //encontrar rei
        int reiBX = 0;
        int reiBY = 0;
        int reiNX = 0;
        int reiNY = 0;
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (tablero.getElemento(y, x).matches("[R]")) {
                    reiBX = x;
                    reiBY = y;
                } else if (tablero.getElemento(y, x).matches("[r]")) {
                    reiNX = x;
                    reiNY = y;
                }
            }
        }
        System.out.println("Rey blanco: " + reiBY + ", " + reiBX);
        System.out.println("Rey negro: " +reiNY + ", " + reiNX);

        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {                                   
            //hacemos todos ya que unicamente entrara dentro de la funcion si es la que le toca, sino hara break (reutilizamos)
            posibleCheck = peonB.posiblesMovimientosPeon(l, k, tablero);            
            //System.out.println(posibleCheck);
            if (posibleCheck[reiBY][reiBX] == "rojo") {
            System.out.println("Jaque al rey blanco");                                                    
            }else if (posibleCheck[reiNY][reiNX] == "rojo"){ 
            System.out.println("Jaque al rey negro");
            }                                                   
            posibleCheck = torreB.posiblesMovimientosTorre(l, k, tablero);
            if (posibleCheck[reiBY][reiBX] == "rojo") {
            System.out.println("Jaque al rey blanco");                                                    
            }else if (posibleCheck[reiNY][reiNX] == "rojo"){
            System.out.println("Jaque al rey negro");
            }
            posibleCheck = caballoB.posiblesMovimientosCaballo(l, k, tablero);
            if (posibleCheck[reiBY][reiBX] == "rojo") {
            System.out.println("Jaque al rey blanco");                                                   
            }else if (posibleCheck[reiNY][reiNX] == "rojo"){
            System.out.println("Jaque al rey negro");
            }                      
            posibleCheck = alfilB.posiblesMovimientosAlfil(l, k, tablero);
            if (posibleCheck[reiBY][reiBX] == "rojo") {
            System.out.println("Jaque al rey blanco");                                                  
            }else if (posibleCheck[reiNY][reiNX] == "rojo"){
            System.out.println("Jaque al rey negro");
            }                            
            posibleCheck = damaB.posiblesMovimientosDama(l, k, tablero);
            if (posibleCheck[reiBY][reiBX] == "rojo") {
            System.out.println("Jaque al rey blanco");                                                    
            }else if (posibleCheck[reiNY][reiNX] == "rojo"){
            System.out.println("Jaque al rey negro");
            }
            posibleCheck = reyB.posiblesMovimientosRey(l, k, tablero);
            if (posibleCheck[reiBY][reiBX] == "rojo") {
            System.out.println("Jaque al rey blanco");                                                    
            }else if (posibleCheck[reiNY][reiNX] == "rojo"){
            System.out.println("Jaque al rey negro");
            }
            }
        }
        
    }
}



                
              
                     
                
            



