package rsc.piezas;

import rsc.tablero.Tablero;

public class Dama {
    // Atributos
    String color;
    int fila;
    int columna;
    
    // Constructor
    public Dama(String color) {
        this.color = color;
    }
    public String getcolor(){
        return color;
    }
    public String[][] posiblesMovimientosDama(int filaOrigen, int columnaOrigen, Tablero tablero){
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
    
}
