package MVC;

import rsc.tablero.Tablero; // Replace "some.package" with the actual package name of the Tablero class


public class Model {
    //variables
    private Tablero tablero;
    //String[][] movimientos = new String[8][8];
    String[][] posibleCheck= new String[8][8];

    //constructor
    public Model() {
        //inicializar tablero
        tablero = new Tablero();

             
    }
    public Tablero getTablero(){
        return tablero;
    }
    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }
    
    
    //movimientos

    public String[][] posiblesMovimientosPeon(int filaOrigen, int columnaOrigen){
        String[][] movimientos = new String[8][8];
       //model.posiblesMovimientosPeon(filaOrigen, columnaOrigen);
        
        // Lógica para mover el peón 1 casilla hacia adelante
        if(tablero.getElemento(filaOrigen, columnaOrigen)== "P"){
        if (filaOrigen == 6) {
            if (!tablero.getElemento(filaOrigen - 1, columnaOrigen).matches("[ptcadrPTCADR]")) {
            movimientos[filaOrigen - 1][columnaOrigen] = "verde";
            if (!tablero.getElemento(filaOrigen - 2, columnaOrigen).matches("[ptcadrPTCADR]")) {
                movimientos[filaOrigen - 2][columnaOrigen] = "verde";
            }
            }
        } else if (!tablero.getElemento(filaOrigen - 1, columnaOrigen).matches("[ptcadrPTCADR]")) {
            movimientos[filaOrigen - 1][columnaOrigen] = "verde";
        }

        if (columnaOrigen > 0 && tablero.getElemento(filaOrigen - 1, columnaOrigen - 1).matches("[ptcadr]")) {
            movimientos[filaOrigen - 1][columnaOrigen - 1] = "rojo";
        }

        if (columnaOrigen < 7 && tablero.getElemento(filaOrigen - 1, columnaOrigen + 1).matches("[ptcadr]")) {
            movimientos[filaOrigen - 1][columnaOrigen + 1] = "rojo";
        }
    }else if(tablero.getElemento(filaOrigen, columnaOrigen)== "p"){
        if (filaOrigen == 1) {
            if (!tablero.getElemento(filaOrigen + 1, columnaOrigen).matches("[ptcadrPTCADR]")) {
                movimientos[filaOrigen + 1][columnaOrigen] = "verde";
                if (!tablero.getElemento(filaOrigen + 2, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + 2][columnaOrigen] = "verde";
                }
            }
        } else if (!tablero.getElemento(filaOrigen + 1, columnaOrigen).matches("[ptcadrPTCADR]")) {
            movimientos[filaOrigen + 1][columnaOrigen] = "verde";
        }

        if (columnaOrigen > 0 && tablero.getElemento(filaOrigen + 1, columnaOrigen - 1).matches("[PTCADR]")) {
            movimientos[filaOrigen + 1][columnaOrigen - 1] = "rojo";
        }

        if (columnaOrigen < 7 && tablero.getElemento(filaOrigen + 1, columnaOrigen + 1).matches("[PTCADR]")) {
            movimientos[filaOrigen + 1][columnaOrigen + 1] = "rojo";
        }
    }

        return movimientos;


    }
    
    
    
    public String[][] posiblesMovimientosTorre(int filaOrigen, int columnaOrigen){
        String[][] movimientos = new String[8][8];
        // Lógica para mover la torre
        //ver si es blanca o negra
        if(tablero.getElemento(filaOrigen,columnaOrigen) == "T"){
            for (int k = filaOrigen - 1; k >= 0; k--) {
                if (tablero.getElemento(k, columnaOrigen).matches("[ptcadr]")) {
                    
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }
            
            // Lógica para los movimientos de la torre hacia abajo
            for (int k = filaOrigen + 1; k < 8; k++) {
                if (tablero.getElemento(k, columnaOrigen).matches("[ptcadr]")) {
                    
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }
            
            // Lógica para los movimientos de la torre hacia la izquierda
            for (int l = columnaOrigen - 1; l >= 0; l--) {
                if (tablero.getElemento(filaOrigen, l).matches("[ptcadr]")) {
                   
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                  
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }
            
            // Lógica para los movimientos de la torre hacia la derecha
            for (int l = columnaOrigen + 1; l < 8; l++) {
                if (tablero.getElemento(filaOrigen, l).matches("[ptcadr]")) {
                    
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                    
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }

        
        }else if(tablero.getElemento(filaOrigen,columnaOrigen) == "t"){
            for (int k = filaOrigen - 1; k >= 0; k--) {
                if (tablero.getElemento(k, columnaOrigen).matches("[PTCADR]")) {
                    
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }
            
            // Lógica para los movimientos de la torre hacia abajo
            for (int k = filaOrigen + 1; k < 8; k++) {
                if (tablero.getElemento(k, columnaOrigen).matches("[PTCADR]")) {
                    
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                   
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }
            
            // Lógica para los movimientos de la torre hacia la izquierda
            for (int l = columnaOrigen - 1; l >= 0; l--) {
                if (tablero.getElemento(filaOrigen, l).matches("[PTCADR]")) {
                   
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                  
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }
            
            // Lógica para los movimientos de la torre hacia la derecha
            for (int l = columnaOrigen + 1; l < 8; l++) {
                if (tablero.getElemento(filaOrigen, l).matches("[PTCADR]")) {
                    
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")){
                        
                        movimientos[filaOrigen][l] = "verde";
                    } else {
                        break;
                }
        
        }
    }
            
        return movimientos;
    }

    public String[][] posiblesMovimientosCaballo(int filaOrigen, int columnaOrigen){
        String[][] movimientos = new String[8][8];
        // Lógica para mover el caballo
        //
        int[] dxcaballo = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] dycaballo = {1, 2, 2, 1, -1, -2, -2, -1};
        if(tablero.getElemento(filaOrigen,columnaOrigen) == "C"){
            
            for (int k = 0; k < 8; k++) {
                int x = filaOrigen + dxcaballo[k];
                int y = columnaOrigen + dycaballo[k];
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (tablero.getElemento(x, y).matches("[ptcadr]")) {
                        movimientos[x][y] = "rojo";
                    } else if (!tablero.getElemento(x, y).matches("[ptcadrPTCADR]")) {
                        movimientos[x][y] = "verde";
                    }
                }
            }
        }else if(tablero.getElemento(filaOrigen,columnaOrigen) == "c"){
           
            for (int k = 0; k < 8; k++) {
                int x = filaOrigen + dxcaballo[k];
                int y = columnaOrigen + dycaballo[k];
                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                    if (tablero.getElemento(x, y).matches("[PTCADR]")) {
                        movimientos[x][y] = "rojo";
                    } else if (!tablero.getElemento(x, y).matches("[ptcadrPTCADR]")) {
                        movimientos[x][y] = "verde";
                    }
                }
            }
        }
        

        
        
        return movimientos;
    }

    public String[][] posiblesMovimientosAlfil(int filaOrigen, int columnaOrigen){
        String[][] movimientos = new String[8][8];
        // Lógica para mover el alfil
        //ver si es blanco o negro
        if(tablero.getElemento(filaOrigen,columnaOrigen) == "A"){
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[ptcadr]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[ptcadr]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
            
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[ptcadr]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[ptcadr]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
        }else if (tablero.getElemento(filaOrigen, columnaOrigen)=="a"){
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[PTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[PTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
            
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[PTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[PTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
        }           
               
        return movimientos;
    
    }

    public String[][] posiblesMovimientosDama(int filaOrigen, int columnaOrigen){
        String[][] movimientos = new String[8][8];
        // Lógica para mover la dama
        //ver si es blanca o negra
        if(tablero.getElemento(filaOrigen, columnaOrigen)=="D"){
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[ptcadr]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[ptcadr]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
            
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[ptcadr]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[ptcadr]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = filaOrigen - 1; k >= 0; k--) {
                if (tablero.getElemento(k, columnaOrigen).matches("[ptcadr]")) {
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }
            // Lógica para los movimientos de la torre hacia arriba
            for (int k = filaOrigen + 1; k < 8; k++) {
                if (tablero.getElemento(k, columnaOrigen).matches("[ptcadr]")) {
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }

            // Lógica para los movimientos de la torre hacia abajo
            for (int k = filaOrigen + 1; k < 8; k++) {
                if (tablero.getElemento(k, columnaOrigen).matches("[ptcadr]")) {
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }

            // Lógica para los movimientos de la torre hacia la izquierda
            for (int l = columnaOrigen - 1; l >= 0; l--) {
                if (tablero.getElemento(filaOrigen, l).matches("[ptcadr]")) {
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }

            // Lógica para los movimientos de la torre hacia la derecha
            for (int l = columnaOrigen + 1; l < 8; l++) {
                if (tablero.getElemento(filaOrigen, l).matches("[ptcadr]")) {
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }

        }else if(tablero.getElemento(filaOrigen, columnaOrigen)=="d"){
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[PTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen - k >= 0 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[PTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen - k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen - k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }
            
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen - k >= 0; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[PTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen - k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen - k] = "verde";
                } else {
                    break;
                }
            }
            for (int k = 1; filaOrigen + k < 8 && columnaOrigen + k < 8; k++) {
                if (tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[PTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen + k, columnaOrigen + k).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen + k][columnaOrigen + k] = "verde";
                } else {
                    break;
                }
            }

            for (int k = filaOrigen - 1; k >= 0; k--) {
                if (tablero.getElemento(k, columnaOrigen).matches("[PTCADR]")) {
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }
            // Lógica para los movimientos de la torre hacia arriba
            for (int k = filaOrigen + 1; k < 8; k++) {
                if (tablero.getElemento(k, columnaOrigen).matches("[PTCADR]")) {
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }

            // Lógica para los movimientos de la torre hacia abajo
            for (int k = filaOrigen + 1; k < 8; k++) {
                if (tablero.getElemento(k, columnaOrigen).matches("[PTCADR]")) {
                    movimientos[k][columnaOrigen] = "rojo";
                    break;
                } else if (!tablero.getElemento(k, columnaOrigen).matches("[ptcadrPTCADR]")) {
                    movimientos[k][columnaOrigen] = "verde";
                } else {
                    break;
                }
            }

            // Lógica para los movimientos de la torre hacia la izquierda
            for (int l = columnaOrigen - 1; l >= 0; l--) {
                if (tablero.getElemento(filaOrigen, l).matches("[PTCADR]")) {
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }

            // Lógica para los movimientos de la torre hacia la derecha
            for (int l = columnaOrigen + 1; l < 8; l++) {
                if (tablero.getElemento(filaOrigen, l).matches("[PTCADR]")) {
                    movimientos[filaOrigen][l] = "rojo";
                    break;
                } else if (!tablero.getElemento(filaOrigen, l).matches("[ptcadrPTCADR]")) {
                    movimientos[filaOrigen][l] = "verde";
                } else {
                    break;
                }
            }
            
        
        

        }
        return movimientos;
    }

    public String[][] posiblesMovimientosRey(int filaOrigen, int columnaOrigen){
        String[][] movimientos = new String[8][8];
        // Lógica para mover el rey
        int[] dxrey = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] dyrey = {1, 0, -1, 1, -1, 1, 0, -1};
    if(tablero.getElemento(filaOrigen, columnaOrigen) == "R"){
        
        
        for (int k = 0; k < 8; k++) {
            int x = filaOrigen + dxrey[k];
            int y = columnaOrigen + dyrey[k];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (tablero.getElemento(x, y).matches("[ptcadr]")) {
                    
                    movimientos[x][y] = "rojo";
                } else if (!tablero.getElemento(x, y).matches("[ptcadrPTCADR]")) {
                    
                    movimientos[x][y] = "verde";
                }
            }
        }
    } else if(tablero.getElemento(filaOrigen, columnaOrigen) == "r"){
      
        
        for (int k = 0; k < 8; k++) {
            int x = filaOrigen + dxrey[k];
            int y = columnaOrigen + dyrey[k];
            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                if (tablero.getElemento(x, y).matches("[PTCADR]")) {
                    
                    movimientos[x][y] = "rojo";
                } else if (!tablero.getElemento(x, y).matches("[ptcadrPTCADR]")) {
                    
                    movimientos[x][y] = "verde";
                }
            }
        }
    
    }
    return movimientos;
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
                                        switch (this.tablero.getElemento(l, k)) {
                                            
                                            case "p":
                                                posibleCheck = posiblesMovimientosPeon(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "P":
                                                posibleCheck = posiblesMovimientosPeon(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "t":
                                                posibleCheck = posiblesMovimientosTorre(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "T":
                                                posibleCheck = posiblesMovimientosTorre(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "c":
                                                posibleCheck = posiblesMovimientosCaballo(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "C":
                                                posibleCheck = posiblesMovimientosCaballo(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "a":
                                                posibleCheck = posiblesMovimientosAlfil(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "A":
                                                posibleCheck = posiblesMovimientosAlfil(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "d":
                                                posibleCheck = posiblesMovimientosDama(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "D":
                                                posibleCheck = posiblesMovimientosDama(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "r":
                                                posibleCheck = posiblesMovimientosRey(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            case "R":
                                                posibleCheck = posiblesMovimientosRey(l, k);
                                                if (posibleCheck[reiBY][reiBX] == "rojo") {
                                                    System.out.println("Jaque al rey blanco");
                                                    //return true;
                                                }else if (posibleCheck[reiNY][reiNX] == "rojo") {
                                                    System.out.println("Jaque al rey negro");
                                                    //return true;
                                                }else{
                                                    //return false;
                                                }
                                                break;
                                            default:
                                                break;
                                            }
                                        }
                                        


                                        
                                        
                        
                                           

    

                                    
                                    }
                                
                                
                                
    }
}



                
              
                     
                
            




