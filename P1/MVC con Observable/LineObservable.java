import java.io.*;


public class LineObservable{ //LOGICA Y ESTADO DE LA FRASE, INTENTAR NO HACER PRINTS

    private StringBuilder phrase;
    private int cursorPosition;
    private boolean insert = true;

    public LineObservable(){
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
        if(cursorPosition >0) 
		this.cursorPosition -= 1;
    }

    public void moveToStart(){
		cursorPosition = 0;
	}

    public void moveToEnd(){
		cursorPosition = this.getNumLetters();
	}

    public void insert(){
        insert =! insert;
	}

    public void backspace(){
        if(this.getCursorPosition()>0){
        phrase.deleteCharAt(cursorPosition-1);
        cursorPosition --;
        }
	}
    
    public void write(char letter) {
        if(this.getInsert()||(this.getCursorPosition() >= this.getNumLetters())){
            phrase.insert(cursorPosition,letter);
                        
        } else if(!this.getInsert()){
            phrase.setCharAt(cursorPosition, letter);
                      
         }
         cursorPosition ++; // he vist que el cursor si que augmenta en overwrite tambe (en els editors que he provat)
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
    public String getTmpString(){
        return phrase.substring(cursorPosition);
    }
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

