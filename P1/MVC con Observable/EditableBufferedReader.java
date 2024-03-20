import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Observable;
import java.util.Observer;


public class EditableBufferedReader extends BufferedReader implements TeclasEsc{

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
        String[] cmd = {"/bin/sh", "-c", "stty raw -echo </dev/tty"}; 
        Runtime.getRuntime().exec(cmd).waitFor();// hace que el hilo actual espere hasta que Process haya terminado.
        //El comando es stty raw -echo </dev/tty, que se utiliza para configurar la terminal en modo raw y desactivar el eco de los caracteres de entrada. 
        //Este comando se ejecuta en un shell (/bin/sh), que se especifica como el primer elemento del array cmd.
    }catch(InterruptedException e){
        e.printStackTrace();
    }
}
//En el modo 'cooked', los caracteres de entrada se almacenan en un búfer hasta que se presiona la tecla Enter, en lugar de ser leídos uno por uno tan pronto como se escriben.
public void unsetRaw() throws IOException{  
try{
    String[] cmd = {"/bin/sh", "-c", "stty cooked echo </dev/tty"};
    Runtime.getRuntime().exec(cmd).waitFor();

}catch(InterruptedException e){
    e.printStackTrace();
}
}



public int read() throws IOException{
    int caracter = 0;
    setRaw();
    caracter = super.read();
    if(caracter == TeclasEsc.ESC){ //si el caracter empieza por ESC (27) es una tecla especial
        caracter = super.read(); //Obviem el "["
        if(caracter==TeclasEsc.EDIT){ //si el seguent caracter es "e" (101) es una tecla especial
            caracter= TeclasEsc.newEDIT;
            return caracter;
        }
        caracter = super.read(); //lleguim el seguent a "["	
        switch (caracter) {
            case TeclasEsc.INSERT: 
            caracter=TeclasEsc.newINSERT;
            break;
            case TeclasEsc.newSUPR: 
            caracter= TeclasEsc.newSUPR;
            break;
            case TeclasEsc.newREPAG: 
            caracter=TeclasEsc.newREPAG;
            break;
            case TeclasEsc.AVPAG: 
            caracter=TeclasEsc.newAVPAG;
            break;
            case TeclasEsc.UP: 
            caracter = TeclasEsc.newUP;
            break;
            case TeclasEsc.DOWN: 
            caracter = TeclasEsc.newDOWN;
            break;
            case TeclasEsc.RIGHT: 
            caracter = TeclasEsc.newRIGHT;
            break;
            case TeclasEsc.LEFT: 
            caracter = TeclasEsc.newLEFT;
            break;
            case TeclasEsc.END: 
            caracter= TeclasEsc.newEND;
            break;
            case TeclasEsc.START: 
            caracter= TeclasEsc.newSTART;
            break;           
        }

        if(caracter == TeclasEsc.newINSERT || caracter == TeclasEsc.newSUPR || caracter == TeclasEsc.newAVPAG || caracter == TeclasEsc.newREPAG){
            super.read(); //Obviem el "~" que es el seguent caracter de les tecles especials que he posat adalt
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

            case TeclasEsc.newRIGHT:
                if(line.getCursorPos() < line.getNumLetters()){
                    try{ //NO QUIERO PODER HACER DERECHA SI NO HAY CARACTERES (COMO EN ESTE EDITOR DE CODIGO)
                        line.moveRight();
                        miObservable.notifyObservers("\u001b[1C");//Movem cursor a la dreta
                }catch(InterruptedException ex){}
                }
                break;
            
            case TeclasEsc.newLEFT:
                if(line.getCursorPos()>=0){ // SE QUE AUN INTENTANDO IR A LA IZQUIERDA SI NO HAY CARACTERES NO LO HACE, PERO TAMPOCO QUIERO QUE SE HAGA EL PRINT 
                line.moveLeft();
                miObservable.notifyObservers("\u001b[1D"); //Movem cursor a la esquerra
                }
                break;            

            case TeclasEsc.BACKSPACE: 
                if(line.getCursorPos()>0){
                //miObservable.setChanged(); SERIA NECESARIO PONER UN SET CHANGED CADA VEZ QUE EL ESTADO CAMBIA, PERO EL ESTADO LO LLEVO EN EL LINE Y ESTE SE ACTUALIZA EN BASE AL LINE
                miObservable.notifyObservers("\u001b[1D");
                miObservable.notifyObservers("\u001b[P");
                line.backspace();
                }
                break;            

            case TeclasEsc.newINSERT: // hacer tipo instert
                line.insert();
                break;
            
            case TeclasEsc.newSUPR:
                if(line.getCursorPos()>=0 && (line.getCursorPos()<line.getNumLetters()-1)){                    
                    miObservable.notifyObservers("\u001b[1C");// a la derecha
                    miObservable.notifyObservers("\u001b[P");
                    miObservable.notifyObservers("\u001b[1D");
                    line.supr();
                }                
                break;

            case TeclasEsc.newSTART:
                //System.out.print("\033[H"); //Movem el cursor a l'inici de la pantalla
                int cursorPosition= line.getCursorPos();
                miObservable.notifyObservers("\u001b[\"+cursorPosition+\"D");
                line.toStart();
                break;

            case TeclasEsc.newEND:
                //System.out.print("\033[F"); //Movem el cursor al final de la pantalla
                while (line.getCursorPos()<line.getNumLetters()) {
                    try {
                        line.moveRight();
                        miObservable.notifyObservers("\u001b[1C");
                        
                    } catch (Exception e) {
                         
                    }
                }
                                
                break;

            case TeclasEsc.newEDIT:
                miObservable.notifyObservers("EDIT");               
               
                break;  
            
            case TeclasEsc.newDOWN:
                //NO FEM RES DE MOMENT
                
                break;
    
            case TeclasEsc.newUP:
                //NO FEM RES DE MOMENT
                
            break;
    
                case '\r': break;

            default:
                if(line.getInsert() && (line.getCursorPos()< line.getNumLetters())){
                    int pos = line.getNumLetters()-line.getCursorPos();
                    String strtmp = line.getTmpString();
                    miObservable.notifyObservers("\033[K");
                    line.write((char)caracter);
                    miObservable.notifyObservers((char) caracter);
                    miObservable.notifyObservers(strtmp);
                    miObservable.notifyObservers("\033["+ pos + "D");
                                    
                }else {      
                    line.write((char)caracter);
                    miObservable.notifyObservers((char) caracter);
                    if(line.getCursorPos()<line.getNumLetters()){
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









