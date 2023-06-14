package UI;

import DataControl.UserData;
import UI.GUIInterchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JTextField IDTextField;
    private JTextField passwordTextField;
    private JButton backButton;
    private JButton loginButton;
    private JPanel mainPanel;
    private GUIInterchange guiInterchange;

    public LoginGUI(String title, GUIInterchange guiInterchange) {
        this.guiInterchange = guiInterchange;
        setContentPane(mainPanel);
        setTitle(title);
        setSize(470,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiInterchange.mainPage(guiInterchange);
                dispose();
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
        IDTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
        passwordTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
    }

    private void Login() {
        String checkUser="", checkPass="";

        if(!IDTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
            UserData data = new UserData(guiInterchange);
            data.checkLogin(IDTextField.getText(), passwordTextField.getText());

            guiInterchange.setAdminData(data);
            guiInterchange.addEventPage(guiInterchange);
            dispose();
        }
        else {
            if(IDTextField.getText().isEmpty())
                checkUser = "User ID.\n";

            if(passwordTextField.getText().isEmpty())
                checkPass = "Password.\n";

            JOptionPane.showMessageDialog(new JFrame(), "Please enter: \n" + checkUser + checkPass,
                    "Login", JOptionPane.ERROR_MESSAGE);
        }
    }
}
