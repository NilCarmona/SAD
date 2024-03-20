import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;



public class EditableBufferedReader extends BufferedReader implements Keys{



public EditableBufferedReader(Reader text){
    super(text);
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
    Line line = new Line();
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

            case Keys.BACKSPACE: //no hi ha conversio (127)
                if(line.getCursorPosition()>0){
                System.out.print("\u001b[1D\u001b[P"); //Cursor a la esquerra
                //System.out.print("\u001b[P"); //Esborrem el contingut del cursor
                line.backspace();
                }
                break;

            case Keys.xRIGHT:
                if(line.getCursorPosition() < line.getNumLetters()){
                    try{ //NO QUIERO PODER HACER DERECHA SI NO HAY CARACTERES (COMO EN ESTE EDITOR DE CODIGO)
                        line.moveRight();
                        System.out.print("\u001b[1C");//Movem cursor a la dreta
                }catch(InterruptedException ex){}
                }
                break;

            case Keys.xLEFT:
                if(line.getCursorPosition()>=0){ // SE QUE AUN INTENTANDO IR A LA IZQUIERDA SI NO HAY CARACTERES NO LO HACE, PERO TAMPOCO QUIERO QUE SE HAGA EL PRINT 
                line.moveLeft();
                System.out.print("\u001b[1D"); //Movem cursor a la esquerra
                }
                break;

            case Keys.xINSERT: // hacer tipo instert
                line.insert();
                //if (line.getInsert()) System.out.print("\033[4h"); //Insert mode
                //else System.out.print("\033[4l"); //Overwrite mode
                break;
            
            case Keys.xSUPR:
                if(line.getCursorPosition()>=0 && (line.getCursorPosition()<line.getNumLetters()-1)){
                    
                    System.out.print("\u001b[1C");// a la derecha
                    System.out.print("\u001b[P");// elimino
                    System.out.print("\u001b[1D");// a la izquierda
                    line.supr();
                }
                
                break;

            case Keys.xINICIO:
                //System.out.print("\033[H"); //Movem el cursor a l'inici de la pantalla
                int cursorPosition= line.getCursorPosition();
                System.out.print("\u001b["+cursorPosition+"D"); //Movem el cursor a la esquerra 'cursorPosition' cops
                line.moveToStart();
                break;

            case Keys.xFIN:
                //System.out.print("\033[F"); //Movem el cursor al final de la pantalla
                while (line.getCursorPosition()<line.getNumLetters()) {
                    try {
                        line.moveRight();
                        System.out.print("\u001b[1C");
                    } catch (Exception e) {
                      System.out.println("Error en tecla Fin");   
                    }
                }
                //AIXO NO ES POT FER AIXI JA QUE SI PULSO DOS COPS LA TECLA FIN ES MOU UN ALTRE COP EL NUMERO DE LLETRES (MAL)
                //int numLetters= line.getNumLetters();
                //System.out.print("\u001b[0"+numLetters+"C"); //Movem el cursor al final de la frase
                
                break;

            case Keys.xEXIT:
                System.out.print("\u001b[2J");
                System.out.print("\u001b[0;0H");
                System.out.print("\u001b[0m");
                System.out.print("\u001b[?25h");
                break;          

            default:
                if(line.getInsert() && (line.getCursorPosition()< line.getNumLetters())){
                    int pos = line.getNumLetters()-line.getCursorPosition();
                    String strtmp = line.getTmpString();
                    System.out.print("\033[K");
                    line.write((char)caracter);
                    System.out.print((char) caracter);
                    System.out.print(strtmp);
                    System.out.print("\033["+ pos + "D");

                
                }else { //else estaba bien       
                    line.write((char)caracter);
                    System.out.print((char) caracter);// para que se vea en el momento
                    if(line.getCursorPosition()<line.getNumLetters()){
                    System.out.print("\u001b[1D");
                    }
                    
                }
                break;
         }
    }

    unsetRaw();
    
return line.toString();  
}

}









