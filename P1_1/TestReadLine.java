package P1_1;
import java.io.*;

class TestReadLine {
  public static void main(String[] args) {
    System.out.println("Presione Alt+E [EXIT] para resetar el terminal\r");
    BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
    String str = null;
    try {
      str = in.readLine();
    } catch (IOException e) { e.printStackTrace(); }
    System.out.println("\nline is: " + str);
  }
}