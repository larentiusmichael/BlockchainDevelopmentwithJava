package DataControl;

import Dataset.DatasetIndex;
import UI.GUIInterchange;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserData {
    private String name, loginID, password;
    private Stack<String[]> userData = new Stack<String[]>();
    private String[] adminData;
    private GUIInterchange guiInterchange;

    public UserData(GUIInterchange guiInterchange) {
        this.guiInterchange = guiInterchange;
        readAll();
    }

    public void readAll() {
        String usrData[], line;
        try(BufferedReader in = new BufferedReader(new FileReader(DatasetIndex.userFile));) {
            while((line = in.readLine()) !=null) {
                usrData = line.split("%");
                userData.push(usrData);
            }
        }
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "File not exist!",
                    "User Data", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException ex) {
            Logger.getLogger(UserData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Stack<String[]> getUserData() {
        return userData;
    }

    public void checkLogin(String userID, String password) {
        String[] loginAdmin = null;
        String msg = "";

        for(int i=0; i<userData.size(); i++) {
            if(userID.equals(userData.get(i)[0]) & password.equals(userData.get(i)[2])) {
                JOptionPane.showMessageDialog(null,
                        "Welcome "+userData.get(i)[1],
                        "Login",
                        JOptionPane.INFORMATION_MESSAGE);
                loginAdmin = userData.get(i);
                adminData = loginAdmin;
            }
            else  {
                msg = "Incorrect username/password.";
                adminData = null;

                JOptionPane.showMessageDialog(null,
                        msg,
                        "Login",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public void logout() {
        System.gc();
    }
}
