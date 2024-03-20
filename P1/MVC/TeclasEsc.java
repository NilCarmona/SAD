
interface TeclasEsc { // Interfície que conté les constants de les tecles especials
    //SON CONSTANTS -> final static
    // Tecles especials
    public final static int BACKSPACE = 127;

    // Sequencies d'escape de les tecles especials (en decimal) 
 
    public final static int ESC = 27; // ESC => ^[
    public final static int CSI = 91; // CSI => [

    //(ordenats per codi ASCII)
    // TECLES QUE SEGUEIXEN DE NUMERO I UNA TILDA 
    public final static int INSERT = 50; //^[[2~
    public final static int SUPR = 51;//^[[3~
    public final static int REPAG = 53; //^[[5~
    public final static int AVPAG = 54; //^[[6~
    
    // Tecles especials que comencen per ESC seguides de [ i un altre caracter    
    public final static int UP = 65; //^[[A
    public final static int DOWN = 66; //^[[B
    public final static int RIGHT = 67; //^[[C
    public final static int LEFT = 68; //^[[D
    //69 no es fa servir
    public final static int END = 70; //^[[F
    public final static int START = 72; //^[[H

    // Tecles especials que comencen per ESC seguides de dos caracters (fer servit per edicio)
    public final static int EDIT = 101; //^[e

    //UTILITZAR NUMEROS NEGATIUS PER A LES NOVES TECLAS ESPECIALS

    // Conversions de les sequencies d'escape
    public final static int newINSERT = -1;
    public final static int newSUPR = -2;
    public final static int newRIGHT = -3;
    public final static int newLEFT = -4;
    //public final static int newBACKSPACE = -5; NO NECESITEM ASSIGNAR UN NOU
    public final static int newEND = -6;
    public final static int newSTART = -7;   
    public final static int newEDIT = -8; //Nova tecla especial per edicio

    //FER SERVIR EN EXTRA
    public final static int newAVPAG = -9;
    public final static int newREPAG = -10;
    public final static int newUP = -11;
    public final static int newDOWN = -12;
    
}
