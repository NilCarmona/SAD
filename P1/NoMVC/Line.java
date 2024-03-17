import java.io.*;


public class Line{

    private StringBuilder phrase;
    private int cursorPosition;
    private int numLetters;
    private boolean insert = true;

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
        if(cursorPosition > 0) 
		this.cursorPosition -= 1;
    }

    public void moveToStart(){
		cursorPosition = 0;
	}

    public void moveToEnd(){
		cursorPosition = numLetters;
	}

    public void insert(){
        insert =! insert;
	}

    public void backspace(){
        if(cursorPosition>numLetters){
        phrase.deleteCharAt(cursorPosition-1);
        cursorPosition --;
        }
	}
    //mejorar el modo insert
    public void write(char letter) {
        if(insert){
            phrase.insert(cursorPosition,letter);
            cursorPosition++;
        }else{
            phrase.setCharAt(cursorPosition,letter); 
        }
	}

    public void supr(){
        phrase.deleteCharAt(cursorPosition+1);
    }
    //Metodos tipicos de cadenas
    public boolean hasNext(){
        return phrase.length()> cursorPosition;
    }

    public String toString(){
		return phrase.toString();
	}
    //GETTERS
    public boolean getInsert(){
        return insert;
    }
    public int getCursorPosition(){
		return cursorPosition;
	}

	public int getNumLetters(){
		return phrase.length();
	}






}
