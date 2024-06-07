package P3;

import javax.swing.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class JListSW{
    private JList<String> list;
    private DefaultListModel<String> modelList;

    public JList<String> getJList(){
        return list;
    }

    public JListSW(){
        modelList = new DefaultListModel<>();
        list = new JList<>(modelList);
    }

    public void addUser(String user){
        if (!modelList.contains(user)){
            modelList.addElement(user);
            sortModel();
        }
    }

    private void sortModel(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < modelList.size(); i++){
            list.add((String) modelList.get(i));
        }

        Collections.sort(list);
        modelList.removeAllElements();
        for (String i : list){
            modelList.addElement(i);
        }
    }

    public void removeUser(String user){
        for (int i = 0; i < modelList.size(); i++){
            if (modelList.get(i).contains(user)){
                modelList.remove(i);
                break;
            }
        }
    }
}