package P1_1;

import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;



//package P0;
//HOLA

public class EditableBufferedReader extends BufferedReader{

//private Line2 line;
//private View vista;

public EditableBufferedReader(Reader text){
    super(text);
    //this.line = new Line2();
    //this.vista= new View();
}

public void setRaw() throws IOException{ 
    try{
        String[] comanda = {"/bin/sh", "-c", "stty raw -echo </dev/tty"}; // /bin/sh, per a executar el codi com a una cadena, i amb l'stty configurem el terminal en raw mode
        Runtime.getRuntime().exec(comanda).waitFor(); //Executem la comanda i esperem a que aquesta acabi

    }catch(InterruptedException ex){
        ex.printStackTrace();
    }
}

public void unsetRaw() throws IOException{  //Fem el mateix procès que abans, pero ara per tornar al mode 'cooked'
 try{
 String[] comanda = {"/bin/sh", "-c", "stty cooked echo </dev/tty"};
 Runtime.getRuntime().exec(comanda).waitFor();
 
 }catch(InterruptedException ex){
    ex.printStackTrace();
 }
}

/*public int read() throws IOException{
    int caracter = 0;
    setRaw();
    caracter = super.read();
    if(caracter == Keys.ESC){
        caracter = super.read(); //Obviem el "["
        caracter = super.read(); //
        if(caracter < 64){//Si després llegim un numero
            switch (caracter) { 
                case Keys.INSERT: caracter=Keys.xINSERT;
                break;
                case Keys.SUPR: caracter= Keys.xSUPR;
                break;
            }
            super.read(); //Obviem el "~"
        }else{ //Si llegim una lletra
            switch (caracter){
                case Keys.INSERT: caracter = Keys.xINSERT;// proba de borrar instert de aqui
                    break;
                case Keys.RIGHT: caracter = Keys.xRIGHT;
                    break;
                case Keys.LEFT: caracter = Keys.xLEFT;
                    break;
                case Keys.INICIO: caracter= Keys.xINICIO;
                    break;
                case Keys.FIN: caracter= Keys.xFIN;
                    break;
            } 
        }            
               
        }
        return caracter;
}*/

public int read() throws IOException{

    int caracter = 0;
    setRaw();
    caracter = super.read();
    if(caracter == Keys.ESC){
        caracter = super.read(); //Obviem el "["
        /*if(caracter==Keys.EXIT){
            caracter= Keys.xEXIT;
            return caracter;
        }*/
        caracter = super.read(); //lleguim el seguent a "["	
        switch (caracter) {
            case Keys.RIGHT: 
            caracter = Keys.xRIGHT;
            break;
            case Keys.LEFT: 
            caracter = Keys.xLEFT;
            break;
            case Keys.INICIO: 
            caracter= Keys.xINICIO;
            break;
            case Keys.FIN: 
            caracter= Keys.xFIN;
            break;
            case Keys.INSERT: 
            caracter=Keys.xINSERT;
            break;
            case Keys.AVPAG: 
            caracter=Keys.xAVPAG;
            break;
            case Keys.REPAG: 
            caracter=Keys.xREPAG;
            break;
            case Keys.SUPR: 
            caracter= Keys.xSUPR;
            break;
        }

        if(caracter == Keys.xINSERT || caracter == Keys.xSUPR || caracter == Keys.xAVPAG || caracter == Keys.xREPAG){
            super.read(); //Obviem el "~"
        }
    }
    return caracter;
}

    


public String readLine() throws IOException{  
    setRaw();
    Line line = new Line();
    int caracter = 0;
    while(caracter!='\r'){
        caracter = read();

        switch(caracter){

            case '\r': break;

            case Keys.BACKSPACE: 
                System.out.print("\u001b[1D"); //Cursor a la esquerra
                System.out.print("\u001b[P"); //Esborrem el contingut del cursor
                line.backspace();
                break;

            case Keys.xRIGHT:
                try{
                line.moveRight();
                System.out.print("\u001b[1C");//Movem cursor a la dreta
                }catch(InterruptedException ex){}
                break;

            case Keys.xLEFT:
                line.moveLeft();
                System.out.print("\u001b[1D"); //Movem cursor a la esquerra
                break;

            case Keys.xINSERT: // no es hacer tipo instert?
                //line.insert();
                //if (line.getInsert()) System.out.print("\033[4h"); //Insert mode
                //else System.out.print("\033[4l"); //Overwrite mode
                line.insert((char)caracter); 
                break;
            
            case Keys.xSUPR:
                System.out.print("\u001b[1C");// a la derecha
                System.out.print("\u001b[P");// elimino
                System.out.print("\u001b[1D");// a la izquierda
                //System.out.print("\u001b[1C\u001b[P"); // Or "\u001b[1C\u001b[1D]" if [P] is unclear
                //System.out.print("\b \b");
                line.supr();
                break;

            case Keys.xINICIO:
                // System.out.print("\033[H"); //Movem el cursor a l'inici de la pantalla
                int cursorPosition= line.getCursorPosition();
                System.out.print("\u001b["+cursorPosition+"D"); //Movem el cursor a la esquerra 'cursorPosition' cops
                line.moveToStart();
                break;

            case Keys.xFIN:
                // System.out.print("\033[F"); //Movem el cursor al final de la pantalla
                int numLetters= line.getNumLetters();
                System.out.print("\u001b[0"+numLetters+"C"); //Movem el cursor al final de la frase
                line.moveToEnd();
                break;

            /*case Keys.xEXIT:
                System.out.print("\u001b[2J");
                System.out.print("\u001b[0;0H");
                System.out.print("\u001b[0m");
                System.out.print("\u001b[?25h");
                break; */         

            default:
                line.write((char)caracter);
                System.out.print((char) caracter);// para que se vea en el momento
                break;
         }
    }

    unsetRaw();
    
return line.toString();  
}

}

/* APUNTES

    Mover el cursor:
        \u001b[<n>A: Mueve el cursor hacia arriba n líneas.
        \u001b[<n>B: Mueve el cursor hacia abajo n líneas.
        \u001b[<n>C: Mueve el cursor hacia la derecha n columnas.
        \u001b[<n>D: Mueve el cursor hacia la izquierda n columnas.

    Posicionar el cursor:
        \u001b[<fila>;<columna>H: Posiciona el cursor en la fila y columna especificadas (1-based).

    Borrar la pantalla:
        \u001b[2J: Borra toda la pantalla.
        \u001b[1J: Borra desde el cursor hasta el principio de la pantalla.
        \u001b[0J: Borra desde el cursor hasta el final de la pantalla.

    Borrar la línea actual:
        \u001b[2K: Borra toda la línea actual.
        \u001b[1K: Borra desde el cursor hasta el principio de la línea.
        \u001b[0K: Borra desde el cursor hasta el final de la línea.

    Cambiar el color del texto y fondo:
        \u001b[<estilo>m: Cambia el estilo de texto y fondo. Algunos valores comunes son 0 (restablecer), 1 (negrita), 2 (dim), 3 (cursiva), 4 (subrayado), 30-37 (colores de texto) y 40-47 (colores de fondo).

    Restablecer los atributos:
        \u001b[0m: Restablece todos los atributos de estilo y color a sus valores predeterminados.



*/
