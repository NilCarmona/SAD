import java.io.*;


public class Line2{
    //ATRIBUTOS
    private StringBuilder phrase;//Instanciar un StringBuilder para crear una cadena de caracteres mutable
    private int cursorPosition;
    private int numLetters;

    //CONSTUCTOR
    public Line2(){
        phrase = new StringBuilder();
        cursorPosition = 0;
    }
    
    //GETTERS. Al tindre els atributs privats, es fan metodes per accedir a ells (encapsulament). 
    public String toString(){
		return phrase.toString();
	}

    public int getCursorPosition(){
		return cursorPosition;
	}

	public int getNumLetters(){
		return phrase.length();
	}
    //METODOS 
    public void moveRight() throws IOException, InterruptedException{ 
        //Este código ejecuta el comando tput cols y recupera el número de columnas disponibles en la terminal. Este valor se almacena en la variable width
        Process process = Runtime.getRuntime().exec(new String[] { "tput", "cols" });
        process.waitFor();
        int width = Integer.parseInt(new String(process.getInputStream().readAllBytes()).trim());
        //int width = Integer.parseInt(Runtime.getRuntime().exec(new String[]{"stty", "size"}).getInputStream().read().split(" ")[1]);
        //Este código solo funciona en sistemas Linux. En Windows, se debe usar el comando "mode con" para obtener el ancho de la terminal.

        if(cursorPosition != width)
		    this.cursorPosition = cursorPosition + 1; // probar var +=1
	}

	public void moveLeft(){
        if(cursorPosition != 0) //probar var > 0
		this.cursorPosition = cursorPosition-1;// -=1
    }

    public void moveToStart(){
		cursorPosition = 0;
	}

    public void moveToEnd(){
		cursorPosition = numLetters;
	}

    public void insert(char letter){
        phrase.insert(cursorPosition,letter);
	}

    public void backspace(){
        phrase.deleteCharAt(cursorPosition-1);
        cursorPosition --;
	}

    public void write(char letter) {
		phrase.insert(cursorPosition,letter);
        cursorPosition++;
	}

    public void supr(){
        phrase.deleteCharAt(cursorPosition+1);
    }
    





}


