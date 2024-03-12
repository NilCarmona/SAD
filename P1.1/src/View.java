import java.io.*;
import java.util.Scanner;

/*
Puede cambiar la vista de la consola
*/ 
public class View {

    public void restablish(){
        System.out.println("\u001b[0m\n\r");
    }

    public void bold(){
        restablish();
        System.out.println("\n\rNegrita:\n\r\u001b[1m");
    }

    public void dim(){
        restablish();
        System.out.println("\n\rdim:\n\r\u001b[2m");
    }

    public void italics(){
        restablish();
        System.out.println("\n\rCursiva:\n\r\u001b[3m");
    }

    public void underline(){
        restablish();
        System.out.println("\n\rSubrayado:\n\r\u001b[4m");
    }

    public void changeColor() throws IOException{
        System.out.println("\n\rSelecciona un color de texto:\n\r0: Negro\n\r1: Rojo \n\r2: Verde \n\r3: Amarillo \n\r4: Azul \n\r5: Magenta (púrpura) \n\r6: Cian (verde azulado) \n\r7: Blanco\n\r");
        EditableBufferedReader reader = new EditableBufferedReader(new InputStreamReader(System.in));
        String color = reader.readLine();
        switch(color){
            case "0": //0 Black
            case "1": //1 Red
            case "2": //2 Green
            case "3": //3 Yellow
            case "4": //4 Blue
            case "5": //5 Magenta
            case "6": //6 Cyan
            case "7": //7 White
                System.out.print("\n\r\u001b[3"+color+"m");
                break;
            default:
                System.out.print("Caracter incorrecto!\n\r");
                break; 
        }
    }

    public void changeBackgroundColor() throws IOException{
        System.out.println("\n\rSelecciona un color de texto:\n\r0: Negro\n\r1: Rojo \n\r2: Verde \n\r3: Amarillo \n\r4: Azul \n\r5: Magenta (púrpura) \n\r6: Cian (verde azulado) \n\r7: Blanco\n\r");
        EditableBufferedReader reader = new EditableBufferedReader(new InputStreamReader(System.in));
        String color = reader.readLine();
        switch(color){
            case "0": //0 Black
            case "1": //1 Red
            case "2": //2 Green
            case "3": //3 Yellow
            case "4": //4 Blue
            case "5": //5 Magenta
            case "6": //6 Cyan
            case "7": //7 White
                System.out.print("\n\r\u001b[4"+color+"m");
                break;
            default:
                System.out.print("Caracter incorrecto!\n\r");
                break; 
        }
    }

    public void style(int caracter) throws IOException{
                switch(caracter){
                    case '0': //0
                        System.out.println("Restablecido\n\r");
                        restablish();
                        break;
                    case '1': //1
                        bold();
                        break;
                    case '2': //2
                        dim();
                        break;
                    case '3': //3
                        italics();
                        break;
                    case '4': //4
                        underline();
                        break;
                    case '5': //5                
                        changeColor();
                        break;
                    case '6': //6
                        System.out.println("\n\rSelecciona un color de fondo:\n\r0: Negro\n\r1: Rojo \n\r2: Verde \n\r3: Amarillo \n\r4: Azul \n\r5: Magenta (púrpura) \n\r6: Cian (verde azulado) \n\r7: Blanco\n\r");
                        changeBackgroundColor();
                        break;
                    default:
                        System.out.println("Caracter incorrecto!\n\r");
                        break;
                }
    }
    
}