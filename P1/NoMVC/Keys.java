interface Keys { // que tippo

    // Codificacions de les sequencies d'escape 

    public final static int RETURN=13;
    public final static int BACKSPACE = 127;

    // Totes les sequencies d'escape comencen per ESC => ^[ o per => ^[[
 
    public final static int ESC = 27; // ESC => ^[
    public final static int CSI = 91; // CSI => [
    public final static int ALTRES_SEQ = 94; // => ^


    // Funcions amb ESC + CSI

    public final static int RIGHT = 67; //^[[C
    public final static int LEFT = 68; //^[[D
    public final static int UP = 65; //^[[A
    public final static int DOWN = 66; //^[[D
    public final static int FIN = 70; //^[[F
    public final static int INICIO = 72; //^[[H
    public final static int INSERT = 50; //^[[2~
    public final static int AVPAG = 54; //^[[6~
    public final static int REPAG = 53; //^[[5~
    public final static int SUPR = 51;//^[[3~
    
    public final static int EXIT = 101; //^[e


    // Conversions de les sequencies d'escape

    public final static int xBACKSPACE = -1;
    public final static int xRIGHT = -2;
    public final static int xLEFT = -3;
    public final static int xINICIO = -4;
    public final static int xFIN = -5;
    public final static int xINSERT = -6;
    public final static int xAVPAG = -7;
    public final static int xREPAG = -8;
    public final static int xSUPR = -9;
    
    public final static int xUP = -11;
    public final static int xDOWN = -12;
    public final static int xEXIT = -13;
}
