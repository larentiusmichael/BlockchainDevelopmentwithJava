package UI;

import DataControl.DateTimeConverter;
import DataControl.EventData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;
import java.util.Stack;

public class BookingPageGUI extends JFrame {
    private JComboBox<String> eventCB;
    private JComboBox<String> catCB;
    private JTextField nameTF;
    private JTextField emailTF;
    private JTextField phoneTF;
    private JTextField icTF;
    private JTextField seatTF;
    private JTextField priceTF;
    private JButton purchaseButton;
    private JButton backButton;
    private JPanel mainPanel;
    private JTextField DateTF;
    private Stack<String[]> eventList = new Stack<>();
    private String eventID = "";
    private int catID = -1;
    private double price = 0;
    private int seatNo = 0;
    private String eventDate = "";

    public BookingPageGUI(String title, GUIInterchange guiInterchange) {
        setContentPane(mainPanel);
        setTitle(title);
        setSize(500,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        EventData eventData = new EventData();
        this.eventList = eventData.getEventData();
        priceTF.setEnabled(false);
        seatTF.setEnabled(false);
        DateTF.setEnabled(false);

        DateTimeConverter dateTimeConverter = new DateTimeConverter();

        eventCB.addItem("(Please Select)");
        if(eventList != null) {
            for (String[] strings : eventList) {
                eventCB.addItem(strings[0] + " - " + strings[1]);
            }
        }

        catCB.addItem("(Please Select)");
        catCB.addItem("Category 1");
        catCB.addItem("Category 2");
        catCB.addItem("Category 3");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiInterchange.mainPage(guiInterchange);
                dispose();
            }
        });
        purchaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = "";
                String email = "";
                String phone = "";
                String identity = "";
                int eventSelect = eventCB.getSelectedIndex()-1;
                int catSelect = catCB.getSelectedIndex()+4;
                int seatNo = Integer.parseInt(eventList.get(eventSelect)[catSelect]) -
                        Integer.parseInt(eventList.get(eventSelect)[3 + catSelect]) + 1;

                String checkName = "",
                        checkEmail = "",
                        checkPhone = "",
                        checkIdentity = "",
                        checkEvent = "",
                        checkCat = "";

                if(!nameTF.getText().equals("")) {
                    name = nameTF.getText();
                }
                else {
                    checkName = "Purchaser Name\n";
                }

                if(!emailTF.getText().equals("")) {
                    email = emailTF.getText();
                }
                else {
                    checkEmail = "Purchaser Email\n";
                }

                if(!phoneTF.getText().equals("")) {
                    phone = phoneTF.getText();
                }
                else {
                    checkPhone = "Purchaser Phone No\n";
                }

                if(!icTF.getText().equals("")) {
                    identity = icTF.getText();
                }
                else {
                    checkIdentity = "Purchaser Identity No\n";
                }

                if(eventSelect < 0) {
                    checkEvent = "Event\n";
                }

                if(catSelect < 5) {
                    checkCat = "Seat Category\n";
                }

                if(checkName.equals("") && checkEmail.equals("") && checkPhone.equals("") &&
                        checkIdentity.equals("") && checkEvent.equals("") && checkCat.equals("")) {

                    // TODO: Blockchain Implementation

                    guiInterchange.mainPage(guiInterchange);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Please follow the requirement(s): \n" +
                                    checkName + checkEmail + checkPhone +
                                    checkIdentity + checkEvent + checkCat,
                            "Purchase Ticket", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        eventCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eventSelect = eventCB.getSelectedIndex()-1;
                if(eventSelect >= 0) {
                    eventID = eventList.get(eventSelect)[0];
                    eventDate = dateTimeConverter.getFormattedDate(eventList.get(eventSelect)[2]);

                    int catSelect = catCB.getSelectedIndex()-1;
                    if(catSelect >= 0) {
                        catID = 5+catSelect;
                        price = Double.parseDouble(eventList.get(eventSelect)[11+catSelect]);
                        seatNo = Integer.parseInt(eventList.get(eventSelect)[catID]) -
                                Integer.parseInt(eventList.get(eventSelect)[3 + catID]) + 1;
                    }
                }
                else {
                    catID = 0;
                    price = 0;
                    seatNo = 0;
                }

                priceTF.setText(String.valueOf((price != 0? price : "")));
                DateTF.setText((!Objects.equals(eventDate, "") ? eventDate : ""));
                seatTF.setText(String.valueOf((seatNo != 0? seatNo : "")));
            }
        });
        catCB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int catSelect = catCB.getSelectedIndex()-1;
                if(catSelect >= 0) {
                    catID = 5+catSelect;

                    int eventSelect = eventCB.getSelectedIndex()-1;
                    if(eventSelect >= 0) {
                        eventID = eventList.get(eventSelect)[0];
                        eventDate = dateTimeConverter.getFormattedDate(eventList.get(eventSelect)[2]);
                        price = Double.parseDouble(eventList.get(eventSelect)[11 + catSelect]);
                        seatNo = Integer.parseInt(eventList.get(eventSelect)[catID]) -
                                Integer.parseInt(eventList.get(eventSelect)[3 + catID]) + 1;
                    }
                }
                else {
                    catID = 0;
                    price = 0;
                    seatNo = 0;
                }

                priceTF.setText(String.valueOf((price != 0? price : "")));
                DateTF.setText((!Objects.equals(eventDate, "") ? eventDate : ""));
                seatTF.setText(String.valueOf((seatNo != 0? seatNo : "")));
            }
        });
    }
}
