import java.io.*;

class TestReadLine {
  public static void main(String[] args) {
    System.out.println("Presione Alt+E para menú de estilos de letra\r");
    BufferedReader in = new EditableBufferedReader(
      new InputStreamReader(System.in));
    String str = null;
    try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\nline is: " + str);
  }
}