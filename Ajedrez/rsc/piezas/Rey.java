package rsc.piezas;

import rsc.tablero.Tablero;

public class Rey {
    String color;
    // Constructor
    public Rey(String color) {
        this.color = color;
    }
    public String getcolor(){
        return color;
    }
    public String[][] posiblesMovimientosRey(int filaOrigen, int columnaOrigen, Tablero tablero){
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
    
}
