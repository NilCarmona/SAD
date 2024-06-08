
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
public class SwingClient implements ActionListener {
    private Document doc;
    private SimpleAttributeSet attributeSet;
    private JListSW list;
    private JTextField message;
    private JButton button;
    private MySocket socket;
    private String name;
    private boolean connected = true;    

    public SwingClient(String name, MySocket socket) {
        this.socket = socket;
        this.name = name;
    }
    public void actionPerformed(ActionEvent event) {         
        connected = true;               
        String text = message.getText();
        message.setText("");
        if (text.length() > 0) {
            socket.writeString(name + ": " + text);
            try {
                doc.insertString(doc.getLength(), text + "\n", attributeSet);
            } catch (BadLocationException e) {
                System.out.println("Error. Message not valid.");
            }
        }
    }

    public void addMessage(String text) {        
        String rcv[] = text.split(": ", 2);
           if(rcv[1].equals("connection")){
               list.addUser(rcv[0]+" (online)");
            }else if(rcv[1].equals("disconnection")){
                list.removeUser(rcv[0]+" (online)");
                list.addUser(rcv[0]); 

            }else{        
            try {
                list.removeUser(rcv[0]);
                list.addUser(rcv[0]+ " (online)");                                
                doc.insertString(doc.getLength(), rcv[0] + ": " + rcv[1] + "\n", attributeSet);
            } catch (BadLocationException e) {
                System.out.println("Error. Message not valid.");
            }
        }      
    }    
    public void createAndShowGUI(String name) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("chat de "+ name);
    
        JPanel out = new JPanel();
        out.setLayout(new BoxLayout(out, BoxLayout.LINE_AXIS));
        JTextPane pane = new JTextPane();
        pane.setEditable(false);
        attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        pane.setCharacterAttributes(attributeSet, true);
        attributeSet = new SimpleAttributeSet();
        doc = pane.getStyledDocument();
        JScrollPane scrollPane = new JScrollPane(pane);
        scrollPane.setPreferredSize(new Dimension(450, 150));
        out.add(scrollPane, BorderLayout.CENTER);
        list = new JListSW();
        JScrollPane jscroll = new JScrollPane(list.getJList());
        out.add(jscroll);
        JPanel inp = new JPanel();
        inp.setLayout(new BoxLayout(inp, BoxLayout.LINE_AXIS));
        message = new JTextField(30);        
        button = new JButton("Enviar");
        message.addActionListener(this);
        button.addActionListener(this);
        inp.add(message);
        inp.add(button);
        frame.add(out, BorderLayout.CENTER);
        frame.add(inp, BorderLayout.PAGE_END);    
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        try {
            doc.insertString(doc.getLength(), "Benvingut " + name + ", escriu exit per sortir del chat\n", attributeSet);
            
        } catch (BadLocationException e) {
            System.out.println("Error. Message not valid.");
        }
        list.addUser("Tots els usuaris connectats:");
        list.addUser(name);     
    }

    public boolean isConnected() {
        return connected;
    }
    public void disconnect() {
        connected = false;
    }

}

