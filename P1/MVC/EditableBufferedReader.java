import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
//podriamos importar todos los io pero no es necesario


public class EditableBufferedReader extends BufferedReader implements TeclasEsc{
    //CONSTRUCTOR
    public EditableBufferedReader(Reader text){
    super(text);
    //para una linea no necesitamos instanciar Line desde aqui, sera variable local en readLine
    }

    //METODOS
    //Este método se utiliza para configurar la terminal en modo raw. En el modo raw, los caracteres de entrada están disponibles para ser leídos uno por uno tan pronto como se escriben, en lugar de esperar a que se presione la tecla Enter. 
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


    //Este método se utiliza para leer un carácter de entrada y devolver su valor entero (que he puesto en la interfaz TeclasEsc).
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
        Line line = new Line();
        int caracter = 0;
        while(caracter!='\r'){
            caracter = read();

            switch(caracter){

                case TeclasEsc.newRIGHT:
                    if(line.getCursorPos() < line.getNumLetters()){
                        try{ //NO QUIERO PODER HACER DERECHA SI NO HAY CARACTERES (COMO EN ESTE EDITOR DE CODIGO)
                            line.moveRight();
                            System.out.print("\u001b[1C");//Movem cursor a la dreta
                    }catch(InterruptedException ex){}
                    }
                    break;

                case TeclasEsc.newLEFT:
                    if(line.getCursorPos() >= 0){ // SE QUE AUN INTENTANDO IR A LA IZQUIERDA SI NO HAY CARACTERES NO LO HACE, PERO TAMPOCO QUIERO QUE SE HAGA EL PRINT 
                    line.moveLeft();
                    System.out.print("\u001b[1D"); //Movem cursor a la esquerra
                    }
                    break;

                case TeclasEsc.BACKSPACE: //no hi ha conversio (127)
                    if(line.getCursorPos() > 0){
                    System.out.print("\u001b[1D\u001b[P"); //Cursor a la esquerra
                    //System.out.print("\u001b[P"); //Esborrem el contingut del cursor
                    line.backspace();
                    }
                    break;

                case TeclasEsc.newINSERT: // hacer tipo instert
                    line.insert();
                    //if (line.getInsert()) System.out.print("\033[4h"); //Insert mode
                    //else System.out.print("\033[4l"); //Overwrite mode
                    break;
            
                case TeclasEsc.newSUPR:
                    if(line.getCursorPos() >= 0 && (line.getCursorPos() < line.getNumLetters()-1)){
                        System.out.print("\u001b[1C");// a la derecha
                        System.out.print("\u001b[P");// elimino
                        System.out.print("\u001b[1D");// a la izquierda
                        line.supr();
                    }
                    break;

                case TeclasEsc.newSTART:
                    //System.out.print("\033[H"); //Movem el cursor a l'inici de la pantalla
                    int cursorPos= line.getCursorPos();
                    System.out.print("\u001b["+cursorPos+"D"); //Movem el cursor a la esquerra 'cursorPosition' cops
                    line.toStart();
                    break;

                case TeclasEsc.newEND:
                    //System.out.print("\033[F"); //Movem el cursor al final de la pantalla
                    while (line.getCursorPos() < line.getNumLetters()) {
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

                case TeclasEsc.newEDIT:
                    System.out.print("\u001b[2J");
                    System.out.print("\u001b[0;0H");
                    System.out.print("\u001b[0m");
                    System.out.print("\u001b[?25h");
                    break; 
            
                case TeclasEsc.newDOWN:
                    //NO FEM RES DE MOMENT
                    break;
    
                case TeclasEsc.newUP:
                    //NO FEM RES DE MOMENT
                    break;
            
                case '\r': 
                    //NO FEM RES 
                    break;
            
                default:
                    if(line.getInsert() && (line.getCursorPos() < line.getNumLetters())){
                        int pos = line.getNumLetters()-line.getCursorPos();
                        String strtmp = line.getTmpString();
                        System.out.print("\033[K");
                        line.write((char)caracter);
                        System.out.print((char) caracter);
                        System.out.print(strtmp);
                        System.out.print("\033["+ pos + "D");
                    }else { //OVERWRITE      
                        line.write((char)caracter);
                        System.out.print((char) caracter);// para que se vea en el momento
                        if(line.getCursorPos() < line.getNumLetters()){
                            //System.out.print("\u001b[1D"); NOMES SI VOLEM QUE NO ES MOGUI EL CURSOR, TAMBE CAMBIAR LINE I TREURE EL CURSOR ++
                        }
                    }
                    break;
            }
        }

        unsetRaw();
    return line.toString();  
    }
}
