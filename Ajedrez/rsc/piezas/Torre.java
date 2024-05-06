package rsc.piezas;

import rsc.tablero.Tablero;

public class Torre {
    String color;
    // Constructor
    public Torre(String color) {
        this.color = color;
    }
    public String getcolor(){
        return color;
    }
    public String[][] posiblesMovimientosTorre(int filaOrigen, int columnaOrigen, Tablero tablero){
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
    
}
