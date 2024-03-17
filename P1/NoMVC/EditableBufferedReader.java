import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;



//package P0;
//HOLA

public class EditableBufferedReader extends BufferedReader implements Keys{

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



public int read() throws IOException{

    int caracter = 0;
    setRaw();
    caracter = super.read();
    if(caracter == Keys.ESC){
        caracter = super.read(); //Obviem el "["
        if(caracter==Keys.EXIT){
            caracter= Keys.xEXIT;
            return caracter;
        }
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
                
                System.out.print("\u001b[1D\u001b[P"); //Cursor a la esquerra
                //System.out.print("\u001b[P"); //Esborrem el contingut del cursor
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
                line.insert();
                if (line.getInsert()) System.out.print("\033[4h"); //Insert mode
                else System.out.print("\033[4l"); //Overwrite mode
                break;
            
            case Keys.xSUPR:
                if(line.getCursorPosition() < line.getNumLetters()-1){
                    System.out.print("\u001b[1C");// a la derecha
                    System.out.print("\u001b[P");// elimino
                    System.out.print("\u001b[1D");// a la izquierda
                    line.supr();
                }
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

            case Keys.xEXIT:
                System.out.print("\u001b[2J");
                System.out.print("\u001b[0;0H");
                System.out.print("\u001b[0m");
                System.out.print("\u001b[?25h");
                break;          

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



