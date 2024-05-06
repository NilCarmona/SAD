package rsc.piezas;

import rsc.tablero.Tablero;

public class Caballo {

    String color;
    // Constructor
    public Caballo(String color) {
        this.color = color;
    }
    public String getcolor(){
        return color;
    }

    public String[][] posiblesMovimientosCaballo(int filaOrigen, int columnaOrigen, Tablero tablero){
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


    
}
