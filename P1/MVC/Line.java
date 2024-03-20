import java.io.*;

//LOGICA Y ESTADO DE LA FRASE, INTENTAR NO HACER PRINTS asi sigue siendo MVC

public class Line{ 
    private StringBuilder frase;
    private int cursorPos;
    private boolean insert = true;

    public Line(){
        frase = new StringBuilder();
        cursorPos = 0;
    }

    public void moveRight() throws IOException, InterruptedException{ 
        Process process = Runtime.getRuntime().exec(new String[] { "tput", "cols" }); //Este comando devuelve el número de columnas en la terminal.
        process.waitFor(); //Espera a que el proceso termine
        int width = Integer.parseInt(new String(process.getInputStream().readAllBytes()).trim()); //Este entero representa el ancho de la terminal en columnas.
        if(cursorPos!=width) //UNICAMENTE SI EL CURSOR NO ESTA AL FINAL DE LA LINEA
		    this.cursorPos += 1;
	}

	public void moveLeft(){ 
        if(cursorPos>0){//UNICAMENTE SI EL CURSOR NO ESTA AL PRINCIPIO DE LA LINEA
		this.cursorPos -= 1;
        }
    }

    public void toStart(){ //unicamente la logica de mover el cursor, no se hace print
		cursorPos = 0;
	}

    public void toEnd(){ 
		cursorPos = this.getNumLetters();
	}

    public void insert(){//cambia el estado de insert
        insert =! insert;
	}

    public void backspace(){
        if(this.getCursorPos()>0){
        frase.deleteCharAt(cursorPos-1);
        cursorPos --;
        }
	}
    
    public void write(char letter) {
        if(this.getInsert()||(this.getCursorPos() >= this.getNumLetters())){
            frase.insert(cursorPos,letter);
                        
        } else if(!this.getInsert()){//OVERWRITE
            frase.setCharAt(cursorPos, letter);
                      
         }
         cursorPos ++; // he vist que el cursor si que augmenta en overwrite tambe (en els editors que he provat)
        }
	

    public void supr(){                    
           frase.deleteCharAt(cursorPos+1);        
    }
    //Metodos tipicos de cadenas
    public boolean hasNext(){
        return frase.length()> cursorPos;
    }

    public String toString(){
		return frase.toString();
	}
    //GETTERS
    public String getTmpString(){
        return frase.substring(cursorPos);
    }
    public boolean getInsert(){
        return insert;
    }
    public int getCursorPos(){
		return cursorPos;
	}

	public int getNumLetters(){
		return frase.length();
	}






}

