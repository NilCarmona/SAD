package rsc.piezas;

import rsc.tablero.Tablero;

public class Peon {
    String color;

    public Peon(String color) {
        this.color = color;
    }
    public String[][] posiblesMovimientosPeon(int filaOrigen, int columnaOrigen, Tablero tablero){
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
            if(filaOrigen > 0){
            movimientos[filaOrigen - 1][columnaOrigen] = "verde";
            }
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
            if(filaOrigen < 7){
            movimientos[filaOrigen + 1][columnaOrigen] = "verde";
            }
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

    
}
