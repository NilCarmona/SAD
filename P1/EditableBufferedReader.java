package P1;
import java.io.*;


public class EditableBufferedReader extends BufferedReader{

    //rivate View vista = new View();
    
public EditableBufferedReader(Reader reader) {
    super(reader);
   
   
    try {
        //vista.style(3);
        setRaw();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
      
    //el metodo read tiene que modificar el read de BufferedReader para que las teclas de escape sean reconocidas
    @Override
    public int read() throws IOException {        
        int caracter=0;
        setRaw();
        caracter= super.read();
        if(caracter==TeclasEscape.ESC){ // ^[ => ESC
            caracter=super.read(); // llegim el següent caràcter despres del [
            //el control v,z surt com a ^C perque es salta el [.]
            if(caracter==TeclasEscape.ESTILO){ //e minuscula
                caracter= TeclasEscape.xESTILO;
                return caracter; //el caracter es el de estilo
            }
            //si no es una e minuscula es un [
            caracter= super.read(); //Llegim el següent caràcter, saltem el [
            if(caracter>64){//Si després llegim una lletra
                switch (caracter) {
                    case TeclasEscape.RIGHT: caracter = TeclasEscape.xRIGHT;
                        break;
                    case TeclasEscape.LEFT: caracter = TeclasEscape.xLEFT;
                        break;
                    case TeclasEscape.INICIO: caracter= TeclasEscape.xINICIO;
                        break;
                    case TeclasEscape.FIN: caracter= TeclasEscape.xFIN;
                        break;
                    case TeclasEscape.UP: caracter= TeclasEscape.xUP;
                        break;
                    case TeclasEscape.DOWN: caracter= TeclasEscape.xDOWN;
                        break;
                } 
            }else{ //Si llegim un número
                switch (caracter){
                    case TeclasEscape.INSERT: caracter=TeclasEscape.xINSERT;
                        break;
                    case TeclasEscape.SUPR: caracter= TeclasEscape.xSUPR;
                        break;
                    case TeclasEscape.AVPAG: caracter= TeclasEscape.xAVPAG;
                        break;
                    case TeclasEscape.REPAG: caracter= TeclasEscape.xREPAG;
                        break;
                }
                super.read(); //Obviem el "~"
            }
        
        }
        return caracter;
    }

    public String readLine() throws IOException{
    setRaw();
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
                System.err.print("he esborrat");
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
                line.moveToStart();
                break;

            case TeclasEscape.xFIN:
                int numLetters= line.getNumLetters();
                System.out.print("\u001b[0"+numLetters+"C"); //Movem el cursor al final de la frase
                line.moveToEnd();
                break;

            /*case TeclasEscape.xESTILO:
                System.out.println("\n\rQue estilo desea?\n\r0 (restablecer)\n\r1 (negrita)\n\r2 (dim)\n\r3 (cursiva)\n\r4 (subrayado)\n\r5 (colores de texto)\n\r6 (colores de fondo)\n\r");
                caracter=read();
                vista.style(caracter);
                caracter=0;
                break;*/

            default:
                line.write((char)caracter);
                System.out.print((char) caracter);
                break;
         }
    }

                unsetRaw();
                
                return line.toString();  
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

    
}



