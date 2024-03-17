package P1_1;
import java.io.*;


public class Line{

    private StringBuilder phrase;
    private int cursorPosition;
    private int numLetters;
    private boolean insert = false;

    public Line(){
        phrase = new StringBuilder();
        cursorPosition = 0;
    }

    public void moveRight() throws IOException, InterruptedException{ 
        Process process = Runtime.getRuntime().exec(new String[] { "tput", "cols" });
        process.waitFor();
        int width = Integer.parseInt(new String(process.getInputStream().readAllBytes()).trim());

        if(cursorPosition!=width)
		    this.cursorPosition = cursorPosition+1;
	}

	public void moveLeft(){
        if(cursorPosition!=0) // >0
		this.cursorPosition = cursorPosition-1; // provar this.cursorPosition -=1;
    }

    public void moveToStart(){
		cursorPosition = 0;
	}

    public void moveToEnd(){
		cursorPosition = numLetters;
	}

    public void insert(char letter){
        //insert =! insert;
        phrase.insert(cursorPosition,letter);
	}

    public void backspace(){
        phrase.deleteCharAt(cursorPosition-1);
        cursorPosition --;
	}

    public void write(char letter) {
        /*if(insert){// Reemplaza el carácter en la posición indicada por el índice con el carácter especificado.No modifica la longitud del StringBuilder.
            phrase.setCharAt(cursorPosition,letter);    
        }*/
		phrase.insert(cursorPosition,letter);
        cursorPosition++;
	}

    public void supr(){
        phrase.deleteCharAt(cursorPosition+1);
    }

    public boolean hasNext(){
        return phrase.length()> cursorPosition;
    }

    public String toString(){
		return phrase.toString();//+"\n"+cursorPosition;
	}

    public int getCursorPosition(){
		return cursorPosition;
	}

	public int getNumLetters(){
		return phrase.length();
	}






}