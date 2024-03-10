package P1.NoMVC;
import java.io.*;


public class EditableBufferedReader extends BufferedReader{
     //Constants
     private static final int RIGHT = 'C';
     private static final int LEFT = 'D';
     private static final int HOME = 'H';
     private static final int END = 'F';
     //private static final int SUPR = '3';
     private static final int INS = '2';
     private static final int BKSP = 127;
    //ATRIBUTS
    
    //CONSTRUCTOR   
    public EditableBufferedReader(Reader reader) {
        super(reader); //Mismo constructor que BufferedReader
        /*try {
            setRaw();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    //METODES 
    //El metodo read tiene que modificar el read de BufferedReader para que las teclas de escape sean reconocidas
    @Override //Utilizar parsing, ver clase Scanner y sus problemas
/*    public int read() throws IOException {  
        //La clase escaner tiene implementada la interfaz iterable, por lo que se puede recorrer con un for each.      
        /*Scanner scanner = new Scanner(System.in);
        //ESTO IRIA EN EL MAIN:
        String nombre = scanner.nextLine();
        scanner.close(); // Cerramos el scanner
        System.out.println(nombre);

        int caracter = 0;
        setRaw(); //Queremos que el terminal este en modo raw.
        caracter = super.read();
        if(caracter == TeclasEscape.ESC){ // ^[ => ESC
            caracter = super.read(); // llegim el següent caràcter despres del [
            //el control v,z surt com a ^C perque es salta el [.]
            caracter = super.read(); //Llegim el següent caràcter, saltem el [
            if(caracter > 64){ //Si després llegim una lletra
                switch (caracter) {
                    case TeclasEscape.RIGHT: 
                        caracter = TeclasEscape.xRIGHT;
                        break;
                    case TeclasEscape.LEFT: 
                        caracter = TeclasEscape.xLEFT;
                        break;
                    case TeclasEscape.INICIO: 
                        caracter= TeclasEscape.xINICIO;
                        break;
                    case TeclasEscape.FIN: 
                        caracter= TeclasEscape.xFIN;
                        break;
                    case TeclasEscape.UP: 
                        caracter= TeclasEscape.xUP;
                        break;
                    case TeclasEscape.DOWN: 
                        caracter= TeclasEscape.xDOWN;
                        break;
                } 
            }else{ //Si llegim un número
                switch (caracter){
                    case TeclasEscape.INSERT: 
                        caracter=TeclasEscape.xINSERT;
                        break;
                    case TeclasEscape.SUPR: 
                        caracter= TeclasEscape.xSUPR;
                        break;
                    case TeclasEscape.AVPAG: 
                        caracter= TeclasEscape.xAVPAG;
                        break;
                    case TeclasEscape.REPAG: 
                        caracter= TeclasEscape.xREPAG;
                        break;
                }
                super.read(); //Obviem el "~"
            }
        
        }
        unsetRaw(); //Quan ja no volem que el terminal estigui en mode raw
        return caracter;
        
    }

    public String readLine() throws IOException{
    //setRaw();
    Line line = new Line();
    int caracter=0;
    
    while(caracter!='\r'){ //mentre no es premi enter
        caracter= read();//aqui fa el read per cada caracter 
        switch(caracter){

            case '\r': 
            //Si es prem enter no fem res
            break; 

            case TeclasEscape.BACKSPACE: 
                System.out.print("\u001b[1D"); //Cursor a la esquerra
                System.out.print("\u001b[P"); //Esborrem el contingut del cursor
                line.backspace();
                break;

            case TeclasEscape.xRIGHT:
                try{
                line.moveRight();
                System.out.println("\n\rNegrita:\n\r\u001b[1m");
                System.out.print("dreta");

                //System.out.print("\u001b[1C");//Movem cursor a la dreta
                }catch(IOException e){
                    System.out.print("error");//Movem cursor a la dreta
                }
                break;

            case TeclasEscape.xLEFT:
                line.moveLeft();
                //System.out.print("\u001b[1D"); //Movem cursor a la esquerra
                break;

            case TeclasEscape.xINSERT:
                line.insert((char)caracter);
                break;
            
            case TeclasEscape.xSUPR:
                System.out.print("\u001b[1C");
                System.out.print("\u001b[P");
                System.out.print("\u001b[1D");
                line.supr();
                break;

            case TeclasEscape.xINICIO:
                int cursorPosition= line.getCursorPosition();
                System.out.print("\u001b["+cursorPosition+"D"); //Movem el cursor a la esquerra 'cursorPosition' cops
                line.home();
                break;

            case TeclasEscape.xFIN:
                int numLetters= line.getNumLetters();
                System.out.print("\u001b[0"+numLetters+"C"); //Movem el cursor al final de la frase
                line.moveToEnd();
                break;

            default:
                line.write((char)caracter);
                System.out.print((char) caracter);
                break;
         }
    }

    //unsetRaw();
    return line.toString();  
} 
*/
   
    public int read() throws IOException{
            setRaw();
              StringBuilder stri = new StringBuilder();
        try{
            
        stri.append((char)super.read());
        
            switch(stri.toString()) {
                case "\033[2~":
                case "\033[3~": 
                case "\033[C": 
                case "\033[D":  
                case "\033[F":   
                case "\033[H":  return -stri.charAt(2);
                default:        return  stri.charAt(stri.length() - 1);
            }
        }catch (IOException e){
            throw e;
        }
    }

    @Override
    public String readLine() throws IOException{
        try{
            setRaw();
            Line line = new Line();
            int key = 0;
            while (key != '\r'){
                key = this.read();
                    switch(key) {
                    case -RIGHT: 
                    line.moveRight();
                    case -LEFT: 
                    line.moveLeft();
                    break;
                    case -HOME: 
                    line.home();
                    break;
                    case -END: 
                    line.moveToEnd();
                    break;
                    case -INS: //UNICAMENTE MODO? NO LETRA?
                    line.insert((char) key);;
                    break;
                    //case SUPR:
                    //line.supr();
                    //break;
                    case BKSP: 
                    line.backSpace();
                    break;
                    default:
                    line.write((char)key);
                    System.out.print((char)key);// para ver si se va escribiendo caracter a caracter, en el process (letra a letra)
                    break;
                }
            }
            return line.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            unsetRaw();
        }
    }

    public void setRaw() throws IOException{ //Las teclas de escape se leen como bytes individuales.No se produce ninguna traducción de caracteres.
        try{
            Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "stty -echo raw </dev/tty" });//EN LINUX
            //EN WINDOWS NO ES POSIBLE ACTUALMENT POR SEGURIDAD EN LA API
            //String[] com = {"C:/Archivos de Programa/Java/jdk-21/bin/jshell", "-c", "stty -echo raw </dev/tty"}; 
            //Process process = Runtime.getRuntime().exec(com); 
            //process.waitFor();// PARA QUE ESPERE A QUE TERMINE EL PROCESO, VER LETRA A LETRA
        }catch(IOException Io){
            Io.printStackTrace();
        /*{catch (InterruptedException e) {
            e.printStackTrace();*/
        }
    }

    public void unsetRaw() throws IOException{  // Las teclas de escape se interpretan como comandos especiales. Se produce la traducción de caracteres.
        try{
            Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "stty echo cooked </dev/tty" }); //EN LINUX
            //EN WINDOWS NO ES POSIBLE ACTUALMENT POR SEGURIDAD EN LA API
            //String[] com = {"C:/Archivos de Programa/Java/jdk-21/bin/jshell", "-c", "stty -cooked raw </dev/tty"}; 
            //Process process = Runtime.getRuntime().exec(com);
           // process.waitFor();// PARA QUE ESPERE A QUE TERMINE EL PROCESO, VER LETRA A LETRA
        }catch(IOException Io){
            Io.printStackTrace();
        /*}catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
    }
}



