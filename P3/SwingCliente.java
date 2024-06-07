package P3;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class SwingCliente implements ActionListener {

    private Document document;
    private SimpleAttributeSet attributeSet;
    private JListSW list;
    private JTextField textField;
    private JButton button;
    private MySocket mysocket;

    public SwingCliente( MySocket socket) {
        this.mysocket = socket;
    }

    public void actionPerformed(ActionEvent event) {//escribimos el mensaje del cliente y lo enviamos a la caja
        String text = textField.getText();
        textField.setText("");
        if (text.length() > 0) {
            mysocket.writeString(text);
            try {
                document.insertString(document.getLength(), text + "\n", attributeSet);
            } catch (BadLocationException e) {
                System.out.println("Error, mensaje no valido.");
            }
        }
    }

    public void addMessage(String text) {//Cojemos el mensaje del servidor y añadimos el usuario que lo envio a la JList si es nuevo. Después añadimos el mensaje a la caja
        String split[] = text.split(": ", 2);
        if (split[1].equals("EXIT")) {
            try {
                document.insertString(document.getLength(), split[0] + " salio del chat." + "\n", attributeSet);
            } catch (BadLocationException e) {
                System.out.println("Error, mensaje no valido.");
            }
            list.removeUser(split[0]);

        } else {
                list.addUser(split[0]);

            try {
                document.insertString(document.getLength(), split[0] + ":  " + split[1] + "\n", attributeSet);
            } catch (BadLocationException e) {
                System.out.println("Error, mensaje no valido.");
            }
        }
    }

    public void GUI() {//Inicialización de display del cliente
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Cliente");
        JPanel out = new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.LINE_AXIS));
        
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        pane.setCharacterAttributes(attributeSet, true);
        
        attributeSet = new SimpleAttributeSet();
        document = pane.getStyledDocument();
        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setPreferredSize(new Dimension(500, 300));
        out.add(scrollPane, BorderLayout.CENTER);
        
        list = new JListSW();
        JScrollPane jscroll = new JScrollPane(list.getJList());
        jscroll.setPreferredSize(new Dimension(100, 300));
        out.add(jscroll);  //Muestra al cliente los usuarios conectados
        
        JPanel inp = new JPanel();
        inp.setLayout(new BoxLayout(inp, BoxLayout.LINE_AXIS));
        textField = new JTextField(40);
        button = new JButton("Enviar");
        textField.addActionListener(this);
        button.addActionListener(this);
        
        inp.add(textField);
        inp.add(button);
        
        frame.add(out, BorderLayout.CENTER);
        frame.add(inp, BorderLayout.PAGE_END);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setVisible(true);
    }

}

