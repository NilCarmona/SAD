package P1;
//import java.util.Observable;
import java.io.*;


public class Line /*extends Observable*/{
    //ATRIBUTS
    private StringBuilder frase;
    private int cursorPosition;
    private int numLetters;
    //Console console;

    
    //CONSTRUCTOR
    public Line(){
        frase= new StringBuilder();// frase inicial buida
        cursorPosition = 0;
        //console = new Console();
        //this.addObserver(console);

    }

    public void moveRight() throws IOException{ 
        if(cursorPosition < numLetters){
            System.out.print("\u001b[1C");//Movem cursor a la dreta
            this.cursorPosition = cursorPosition+1;
        }
	}

	public void moveLeft(){
        if(cursorPosition!=0)
        System.out.print("\u001b[1D"); //Movem cursor a la esquerra
		this.cursorPosition = cursorPosition-1;
    }

    public void moveToStart(){
        while(cursorPosition!=0){
            moveLeft();
        }
        //System.out.print("\u001b["+cursorPosition+"D"); //Movem el cursor a la esquerra 'cursorPosition' cops
		//cursorPosition = 0;
	}

    public void moveToEnd(){
        while(cursorPosition!=numLetters){
            try {
                moveRight();
            } catch (Exception e) {
                // TODO: handle exception
            }           
        }
        //System.out.print("\u001b["+numLetters+"C"); //Movem el cursor a la dreta 'numLetters' cops
		//cursorPosition = numLetters;
	}

    public void insert(char letter){
        frase.insert(cursorPosition,letter);
	}

    public void backspace(){
        frase.deleteCharAt(cursorPosition-1);
        cursorPosition --;
	}

    public void write(char letter) {
		frase.insert(cursorPosition,letter);
        cursorPosition++;
	}

    public void supr(){
        frase.deleteCharAt(cursorPosition+1);
    }
    //Stringbuilder a String
    public String toString(){
		return frase.toString();
	}

    //GETTERS
    public int getCursorPosition(){
		return cursorPosition;
	}

	public int getNumLetters(){
		return frase.length();
	}
}
