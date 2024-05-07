package MVC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class Menu {    
    //variables
    boolean jugar;
    boolean opcionJaque;
    boolean normas;
    Controller controller;
    //constructor
        public Menu() {
        jugar = false;
        opcionJaque = false;
        normas = false;
        
        createAndShowGUI();       

    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Menú de Juego");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10)); // 3 filas, 1 columna

        JButton botonGrande = createStyledButton("¡Jugar!", 24, Color.decode("#2ecc71"), Color.WHITE);
        JButton botonMediano = createStyledButton("Jaques Desactivados", 18, Color.decode("#3498db"), Color.WHITE);
        JButton botonPequeno = createStyledButton("Normas del Ajedrez", 14, Color.decode("#e74c3c"), Color.WHITE);

        botonGrande.setPreferredSize(new Dimension(200, 100)); // Tamaño personalizado para el botón grande
        botonMediano.setPreferredSize(new Dimension(200, 50)); // Tamaño personalizado para el botón mediano
        botonPequeno.setPreferredSize(new Dimension(200, 30)); // Tamaño personalizado para el botón pequeño

        panel.add(botonGrande);
        panel.add(botonMediano);
        panel.add(botonPequeno);

        frame.add(panel);
        frame.setVisible(true);

        // Agregar listeners a los botones
        botonGrande.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se presione el botón grande
                System.out.println("¡Jugar!");
                jugar = true;
                frame.setVisible(false);
                controller = new Controller(opcionJaque);              

            }
        });

        botonMediano.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se presione el botón mediano
                               
                if(opcionJaque){
                    opcionJaque = false;
                }else{
                    opcionJaque = true;
                }
                if(opcionJaque){
                    botonMediano.setText("Jaques Activados");
                }else{
                    botonMediano.setText("Jaques Desactivados");
                }   
                

            }
        });

        botonPequeno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Acción a realizar cuando se presione el botón pequeño
                System.out.println("Se abrirán las normas del ajedrez en un navegador web.");
                try {
                    Desktop.getDesktop().browse(new URL("https://es.wikipedia.org/wiki/Ajedrez").toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

       
    }

    private static JButton createStyledButton(String text, int fontSize, Color backgroundColor, Color textColor) {
        JButton button = new JButton(text);
        button.setBackground(backgroundColor); // Color de fondo
        button.setForeground(textColor); // Color del texto
        button.setFont(new Font("Arial", Font.PLAIN, fontSize)); // Fuente y tamaño del texto
        return button;
    }
}


