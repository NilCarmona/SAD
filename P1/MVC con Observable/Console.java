import java.util.Observer;// RECORDAR UTILIZAR VERSION JAVA ANTIGUA
import java.util.Observable;

public class Console implements Observer{
    @Override
    public void update(Observable o, Object obj){
        switch (obj.toString()) {
            case "\u001b[P":
                System.out.print("\\u001b[P");
                break;
            case "\u001b[1C":
                System.out.print("\u001b[C");
                break;
        
            case "\u001b[1D":
                System.out.print("\u001b[D");
                break;
        
            case "\u001b[\"+cursorPosition+\"D":
                System.out.print(obj);
                break;
        
            case "EDIT":
                System.out.print("\u001b[2J");
                System.out.print("\u001b[0;0H");
                System.out.print("\u001b[0m");
                System.out.print("\u001b[?25h");
                break;
        
            case "strtmp":
                System.out.print(obj);
                break;

            case "\033[\"+ pos + \"D":
                System.out.print(obj);
                break;

            case "\033[K":
            System.out.print("\033[K");
                break;
                
        
            
            default: //el caracter
                System.out.print(obj);
                break;
        }
    }
}
