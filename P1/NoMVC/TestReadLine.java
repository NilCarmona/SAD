import java.io.*;

class TestReadLine {
  public static void main(String[] args) {
    System.out.println("Presione Alt+E para entrar en modo EDIT\r");
    System.out.println("Estas en modo insert (los caracteres se añaden), Pulsa la tecla insert para salir de este modo (los caracteres se sobreponen)\r");
    
    BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
    String str = null;
    try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\nline is: " + str);
  }
}
