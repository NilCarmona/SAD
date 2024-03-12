import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;

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
        String[] comanda = {"C:/Archivos de Programa/Java/jdk-21/bin/jshell", "-c", "stty raw -echo </dev/tty"}; // /bin/sh, per a executar el codi com a una cadena, i amb l'stty configurem el terminal en raw mode
        Runtime.getRuntime().exec(comanda).waitFor(); //Executem la comanda i esperem a que aquesta acabi

    }catch(InterruptedException ex){
        ex.printStackTrace();
    }
}

public void unsetRaw() throws IOException{  //Fem el mateix procès que abans, pero ara per tornar al mode 'cooked'
 try{
 String[] comanda = {"C:/Archivos de Programa/Java/jdk-21/bin/jshell", "-c", "stty cooked -echo </dev/tty"};
 Runtime.getRuntime().exec(comanda).waitFor();
 
 }catch(InterruptedException ex){
    ex.printStackTrace();
 }
}

public int read() throws IOException{
    int caracter = 0;
    setRaw();
    caracter = super.read();
    if(caracter == Keys.ESC){ //si entra aqui es que es una tecla especial
        caracter = super.read(); //Obviem el "["
        caracter = super.read();
        switch (caracter) {
            case Keys.INSERT: caracter = Keys.xINSERT;
                break;
            case Keys.RIGHT: caracter = Keys.xRIGHT;
                break;
            case Keys.LEFT: caracter = Keys.xLEFT;
                break;
            case Keys.INICIO: caracter = Keys.xINICIO;
                break;
            case Keys.FIN: caracter = Keys.xFIN;
                super.read(); //Obviem el "~"
                break;
            case Keys.SUPR: caracter = Keys.xSUPR;
                super.read(); //Obviem el "~"
                break;
        } 
    }

    return caracter;
}

public String readLine() throws IOException{  
    setRaw();
    Line2 line = new Line2();
    int caracter = 0;
    while(caracter != '\r'){ //mismo que do-while??
        caracter= read();

        switch(caracter){ //hacer estructura if-else?

            case '\r': 
                //va a salir del bucle
            break;

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

            case Keys.xINSERT:
                line.insert((char)caracter);
                break;
            
            case Keys.xSUPR:
                System.out.print("\u001b[1C");
                System.out.print("\u001b[P");
                System.out.print("\u001b[1D");
                line.supr();
                break;

            case Keys.xINICIO: //hacerlo con left??
                int cursorPosition= line.getCursorPosition();
                System.out.print("\u001b["+cursorPosition+"D"); //Movem el cursor a la esquerra 'cursorPosition' cops.
                line.moveToStart();
                break;

            case Keys.xFIN:
                int numLetters= line.getNumLetters();
                System.out.print("\u001b[0"+numLetters+"C"); //Movem el cursor al final de la frase
                line.moveToEnd();
                break;

            default: //Si no es cap de les anteriors, es una lletra
                line.write((char)caracter);
                System.out.print((char) caracter);
                break;
         }
    }

    unsetRaw();
    
return line.toString();  
}

}

