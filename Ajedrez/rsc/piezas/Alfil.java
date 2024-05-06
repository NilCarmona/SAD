package rsc.piezas;

import rsc.tablero.Tablero;

public class Alfil {  
     String color;

    public Alfil(String color) {
        this.color = color;
    }
    public String[][] posiblesMovimientosAlfil(int filaOrigen, int columnaOrigen, Tablero tablero){
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

    
    public String getColor() {
        return color;
    }
}
