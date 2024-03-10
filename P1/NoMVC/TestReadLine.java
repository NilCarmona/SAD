package P1.NoMVC;

import java.io.*;

class TestReadLine {
  public static void main(String[] args) {
    BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in)); //creem un objecte de la classe EditableBufferedReader que llegeix de la entrada estandar
    String str = null;
    try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\nline is: " + str);
 }
}
