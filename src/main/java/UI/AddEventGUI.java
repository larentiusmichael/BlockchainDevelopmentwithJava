package UI;

import DataControl.EventData;
import DataControl.UserData;
import UI.GUIInterchange;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEventGUI extends JFrame {
    private JTextField eventIDTF;
    private JTextField eventNameTF;
    private JTextField locationTF;
    private JButton backButton;
    private JButton addButton;
    private JPanel mainPanel;
    private JTextField maxSeats1TF;
    private JTextField price1TF;
    private DateTimePicker startDateTimeTF;
    private DateTimePicker endDateTimeTF;
    private JTextField maxSeats2TF;
    private JTextField price2TF;
    private JTextField maxSeats3TF;
    private JTextField price3TF;
    UserData userData;

    public AddEventGUI(String title, GUIInterchange guiInterchange, UserData userData) {
        setContentPane(mainPanel);
        setTitle(title);
        setSize(470,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.userData = userData;
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userData.logout();
                guiInterchange.mainPage(guiInterchange);
                dispose();
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = "",
                        name = "",
                        start = "",
                        end = "",
                        location = "";

                int maxSeats1 = 0,
                        maxSeats2 = 0,
                        maxSeats3 = 0;

                double price1 = 0,
                        price2 = 0,
                        price3 = 0;

                String checkID = "",
                        checkName = "",
                        checkStart = "",
                        checkEnd = "",
                        checkLocation = "",
                        checkMaxSeats1 = "",
                        checkPrice1 = "",
                        checkMaxSeats2 = "",
                        checkPrice2 = "",
                        checkMaxSeats3 = "",
                        checkPrice3 = "";

                if(!eventIDTF.getText().equals("")) {
                    ID = eventIDTF.getText();
                }
                else {
                    checkID = "Event ID\n";
                }

                if(!eventNameTF.getText().equals("")) {
                    name = eventNameTF.getText();
                }
                else {
                    checkName = "Event Name\n";
                }

                if(!startDateTimeTF.toString().equals("")) {
                    start = startDateTimeTF.toString();
//                    System.out.println(start);
                }
                else {
                    checkStart = "Event Starting Datetime\n";
                }

                if(!endDateTimeTF.toString().equals("")) {
                    end = endDateTimeTF.toString();
                }
                else {
                    checkEnd = "Event Ending Datetime\n";
                }

                if(!locationTF.getText().equals("")) {
                    location = locationTF.getText();
                }
                else {
                    checkLocation = "Event Location\n";
                }

                if(!maxSeats1TF.getText().equals("")) {
                    try {
                        maxSeats1 = Integer.parseInt(maxSeats1TF.getText());
                    } catch (NumberFormatException ex) {
                        checkMaxSeats1 = "Category 1 Max Seats Must in Integer\n";
                    }
                }
                else {
                    checkMaxSeats1 = "Category 1 Max Seats\n";
                }

                if(!price1TF.getText().equals("")) {
                    try{
                        price1 = Double.parseDouble(price1TF.getText());
                    } catch (NumberFormatException ex) {
                        checkPrice1 = "Category 1 Price Must in Double\n";
                    }
                }
                else {
                    checkPrice1 = "Category 1 Price\n";
                }

                if(!maxSeats2TF.getText().equals("")) {
                    try {
                        maxSeats2 = Integer.parseInt(maxSeats2TF.getText());
                    } catch (NumberFormatException ex) {
                        checkMaxSeats2 = "Category 2 Max Seats Must in Integer\n";
                    }
                }
                else {
                    checkMaxSeats2 = "Category 2 Max Seats\n";
                }

                if(!price2TF.getText().equals("")) {
                    try {
                        price2 = Double.parseDouble(price2TF.getText());
                    } catch (NumberFormatException ex) {
                        checkPrice2 = "Category 2 Price Must in Double\n";
                    }
                }
                else {
                    checkPrice2 = "Category 2 Price\n";
                }

                if(!maxSeats3TF.getText().equals("")) {
                    try {
                        maxSeats3 = Integer.parseInt(maxSeats3TF.getText());
                    } catch (NumberFormatException ex) {
                        checkMaxSeats3 = "Category 3 Max Seats Must in Integer\n";
                    }
                }
                else {
                    checkMaxSeats3 = "Category 3 Max Seats\n";
                }

                if(!price3TF.getText().equals("")) {
                    try {
                        price3 = Double.parseDouble(price3TF.getText());
                    } catch (NumberFormatException ex) {
                        checkPrice3 = "Category 3 Price Must in Double\n";
                    }
                }
                else {
                    checkPrice3 = "Category 3 Price\n";
                }

                if(checkID.equals("") && checkName.equals("") && checkStart.equals("") && checkEnd.equals("") &&
                        checkLocation.equals("") && checkMaxSeats1.equals("") && checkMaxSeats2.equals("")  &&
                        checkMaxSeats3.equals("") && checkPrice1.equals("") && checkPrice2.equals("") &&
                        checkPrice3.equals("")) {

                    EventData eventData = new EventData(ID, name, start, end,
                            location, maxSeats1, maxSeats2,
                            maxSeats3, maxSeats1, maxSeats2,
                            maxSeats3, price1, price2, price3);
                    eventData.writeAllEvent();

                    JOptionPane.showMessageDialog(new JFrame(), "The event had been added.",
                            "Add Event", JOptionPane.INFORMATION_MESSAGE);

                    userData.logout();
                    guiInterchange.mainPage(guiInterchange);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Please follow the requirement(s): \n" +
                                    checkID + checkName + checkStart + checkEnd +
                                    checkLocation + checkMaxSeats1 + checkPrice1 + checkMaxSeats2 +
                                    checkPrice2 + checkMaxSeats3 + checkPrice3,
                            "Add Event", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
