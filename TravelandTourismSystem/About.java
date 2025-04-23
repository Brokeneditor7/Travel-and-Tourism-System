package TravelandTourismSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class About extends JFrame implements ActionListener {

    JButton b1;
    JLabel l1;
    JTextArea t1;
    String s;

    public About() {
        setTitle("About Project");
        
        setLayout(new BorderLayout());
        
        l1 = new JLabel("About Project", SwingConstants.CENTER);
        l1.setForeground(Color.RED);
        l1.setFont(new Font("RALEWAY", Font.BOLD, 20));
        add(l1, BorderLayout.NORTH);
        
        s = "The objective of the Travel and Tourism Management System "
                + "project is to develop a system that automates the processes "
                + "and activities of a travel agency.\n\n"
                + "This application will help in accessing the information related "
                + "to travel destinations with great ease. Users can track their tours "
                + "and access travel agency information efficiently.\n\n"
                + "Advantages of Project:\n"
                + "• Gives accurate information\n"
                + "• Simplifies manual work\n"
                + "• Minimizes documentation\n"
                + "• Provides up-to-date information\n"
                + "• Friendly Environment with warning messages\n"
                + "• Travelers' details can be provided\n"
                + "• Booking confirmation notification";
        
        t1 = new JTextArea(s);
        t1.setEditable(false);
        t1.setFont(new Font("RALEWAY", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(t1);
        add(scrollPane, BorderLayout.CENTER);
        
        b1 = new JButton("Exit");
        b1.addActionListener(this);
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(b1);
        add(bottomPanel, BorderLayout.SOUTH);
        
        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new About());
    }
}
