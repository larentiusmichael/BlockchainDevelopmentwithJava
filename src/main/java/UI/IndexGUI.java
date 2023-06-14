package UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexGUI extends JFrame {
    private JButton btnBook;
    private JButton btnManage;
    private JPanel mainPanel;
    private JButton viewEventsButton;

    public IndexGUI(GUIInterchange guiInterchange) {
        setContentPane(mainPanel);
        setTitle("Booking Ticket System");
        setSize(450,300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        btnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiInterchange.bookTicketPage(guiInterchange);
                dispose();
            }
        });
        btnManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiInterchange.loginPage(guiInterchange);
                dispose();
            }
        });
        viewEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiInterchange.viewEventPage(guiInterchange);
                dispose();
            }
        });
    }
}
