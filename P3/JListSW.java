import javax.swing.*;

public class JListSW{
    private JList<String> list;
    private DefaultListModel<String> listModel;

    public JListSW(){
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
    }


    public JList<String> getJList(){
        return list;
    }


    public void addUser(String user){
        if (!listModel.contains(user)){
            listModel.addElement(user);
        }
    }


    public void removeUser(String user){
        for (int i = 0; i < listModel.size(); i++){
            if (listModel.get(i).contains(user)){
                listModel.remove(i);
                break;
            }
        }
    }
}
