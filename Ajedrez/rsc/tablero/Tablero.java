package rsc.tablero;

public class Tablero {
    private Casilla[][] casillas;
    private static final String[][] POSICIONES_INICIALES = {
        {"t", "c", "a", "d", "r", "a", "c", "t"},
        {"p", "p", "p", "p", "p", "p", "p", "p"},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", ""},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"T", "C", "A", "D", "R", "A", "C", "T"}    
    };
    public Tablero() {
        casillas = new Casilla[8][8];
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                casillas[fila][columna] = new Casilla(POSICIONES_INICIALES[fila][columna]);
            }
        }
    }
    public Tablero(String[][] posiciones) {
        casillas = new Casilla[8][8];
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                casillas[fila][columna] = new Casilla(posiciones[fila][columna]);
            }
        }
    }
    public String getElemento(int fila, int columna) {
        if(fila < 0 || fila > 7 || columna < 0 || columna > 7) {
            return "";
        }else{
            return casillas[fila][columna].getValor();
        }
        
    }
    public void setElemento(int fila, int columna, String valor) {
        casillas[fila][columna].setValor(valor);
    }
}
