package UI;

import DataControl.UserData;

import javax.swing.*;

public class GUIInterchange {
    private final String bookTitle = "Ticket Booking";
    private String[] consumerData;
    private String manageTitle = "Ticket Management";
    private UserData adminData;

    public GUIInterchange() {}

    public void setConsumerData(String[] consumerData) {
        this.consumerData = consumerData;
    }

    public void setAdminData(UserData adminData) {
        this.adminData = adminData;
    }

    public void mainPage(GUIInterchange guiInterchange) {
        JFrame page = new IndexGUI(guiInterchange);
        page.setLocationRelativeTo(null);
        page.setVisible(true);
    }

    public void addEventPage(GUIInterchange guiInterchange) {
        JFrame managePage = new AddEventGUI(manageTitle, guiInterchange, adminData);
        managePage.setLocationRelativeTo(null);
        managePage.setVisible(true);
    }

    public void loginPage(GUIInterchange guiInterchange) {
        JFrame page = new LoginGUI(manageTitle, guiInterchange);
        page.setLocationRelativeTo(null);
        page.setVisible(true);
    }

    public void bookTicketPage(GUIInterchange guiInterchange) {
        JFrame page = new BookingPageGUI(bookTitle, guiInterchange);
        page.setLocationRelativeTo(null);
        page.setVisible(true);
    }

    public void viewEventPage(GUIInterchange guiInterchange) {
        JFrame page = new ViewEventGUI(bookTitle, guiInterchange);
        page.setLocationRelativeTo(null);
        page.setVisible(true);
    }
}
