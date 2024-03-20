import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Observable;
import java.util.Observer;


public class EditableBufferedReader extends BufferedReader implements Keys{

    Line line;
    Console console;
    Observable miObservable;

public EditableBufferedReader(Reader text){
    super(text);
    line = new Line();
    console = new Console();
    miObservable = new Observable();    
    miObservable.addObserver(console);
}

public void setRaw() throws IOException{ 
    try{
        String[] cmd = {"/bin/sh", "-c", "stty raw -echo </dev/tty"}; // /bin/sh, per a executar el codi com a una cadena, i amb l'stty configurem el terminal en raw mode
        Runtime.getRuntime().exec(cmd).waitFor(); //Executem la comanda i esperem a que aquesta acabi

    }catch(InterruptedException ex){
        ex.printStackTrace();
    }
}

public void unsetRaw() throws IOException{  //Fem el mateix procès que abans, pero ara per tornar al mode 'cooked'
 try{
    String[] cmd = {"/bin/sh", "-c", "stty cooked echo </dev/tty"};
    Runtime.getRuntime().exec(cmd).waitFor();
 
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
            case Keys.UP: 
            caracter = Keys.xUP;
            break;
            case Keys.DOWN: 
            caracter = Keys.xDOWN;
            break;
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
    
    int caracter = 0;
    while(caracter!='\r'){
        caracter = read();

        switch(caracter){

            case Keys.DOWN:
            //NO FEM RES DE MOMENT
            
            break;

            case Keys.UP:
            //NO FEM RES DE MOMENT
            
            break;

            case '\r': break;

            case Keys.BACKSPACE: 
                if(line.getCursorPosition()>0){
                //miObservable.setChanged(); SERIA NECESARIO PONER UN SET CHANGED CADA VEZ QUE EL ESTADO CAMBIA, PERO EL ESTADO LO LLEVO EN EL LINE Y ESTE SE ACTUALIZA EN BASE AL LINE
                miObservable.notifyObservers("\u001b[1D");
                miObservable.notifyObservers("\u001b[P");
                line.backspace();
                }
                break;

            case Keys.xRIGHT:
                if(line.getCursorPosition() < line.getNumLetters()){
                    try{ //NO QUIERO PODER HACER DERECHA SI NO HAY CARACTERES (COMO EN ESTE EDITOR DE CODIGO)
                        line.moveRight();
                        miObservable.notifyObservers("\u001b[1C");//Movem cursor a la dreta
                }catch(InterruptedException ex){}
                }
                break;

            case Keys.xLEFT:
                if(line.getCursorPosition()>=0){ // SE QUE AUN INTENTANDO IR A LA IZQUIERDA SI NO HAY CARACTERES NO LO HACE, PERO TAMPOCO QUIERO QUE SE HAGA EL PRINT 
                line.moveLeft();
                miObservable.notifyObservers("\u001b[1D"); //Movem cursor a la esquerra
                }
                break;

            case Keys.xINSERT: // hacer tipo instert
                line.insert();
                break;
            
            case Keys.xSUPR:
                if(line.getCursorPosition()>=0 && (line.getCursorPosition()<line.getNumLetters()-1)){
                    
                    miObservable.notifyObservers("\u001b[1C");// a la derecha
                    miObservable.notifyObservers("\u001b[P");
                    miObservable.notifyObservers("\u001b[1D");
                    line.supr();
                }
                
                break;

            case Keys.xINICIO:
                //System.out.print("\033[H"); //Movem el cursor a l'inici de la pantalla
                int cursorPosition= line.getCursorPosition();
                miObservable.notifyObservers("\u001b[\"+cursorPosition+\"D");
                line.moveToStart();
                break;

            case Keys.xFIN:
                //System.out.print("\033[F"); //Movem el cursor al final de la pantalla
                while (line.getCursorPosition()<line.getNumLetters()) {
                    try {
                        line.moveRight();
                        miObservable.notifyObservers("\u001b[1C");
                        
                    } catch (Exception e) {
                         
                    }
                }
                                
                break;

            case Keys.xEXIT:
                miObservable.notifyObservers("EDIT");               
               
                break;          

            default:
                if(line.getInsert() && (line.getCursorPosition()< line.getNumLetters())){
                    int pos = line.getNumLetters()-line.getCursorPosition();
                    String strtmp = line.getTmpString();
                    miObservable.notifyObservers("\033[K");
                    line.write((char)caracter);
                    miObservable.notifyObservers((char) caracter);
                    miObservable.notifyObservers(strtmp);
                    miObservable.notifyObservers("\033["+ pos + "D");
                                    
                }else {      
                    line.write((char)caracter);
                    miObservable.notifyObservers((char) caracter);
                    if(line.getCursorPosition()<line.getNumLetters()){
                        miObservable.notifyObservers("\u001b[1D");
                    }
                    
                }
                break;
         }
    }

    unsetRaw();
    
return line.toString();  
}

}









