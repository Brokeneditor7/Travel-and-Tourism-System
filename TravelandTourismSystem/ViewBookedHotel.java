package TravelandTourismSystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class ViewBookedHotel extends JFrame {
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewBookedHotel frame = new ViewBookedHotel("testuser"); // Replace with actual username
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ViewBookedHotel(String username) {
        setBounds(180, 40, 850, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/hotel3.jpg"));
        Image i3 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel la1 = new JLabel(i2);
        la1.setBounds(400, 0, 900, 700);
        add(la1);

        JLabel lblName = new JLabel("VIEW BOOKED HOTEL DETAILS");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(88, 11, 350, 53);
        contentPane.add(lblName);

        JLabel lb3 = new JLabel("Username :");
        lb3.setBounds(35, 70, 200, 14);
        contentPane.add(lb3);

        JLabel l1 = new JLabel();
        l1.setBounds(271, 70, 200, 14);
        contentPane.add(l1);

        JLabel lblId = new JLabel("Hotel Name :");
        lblId.setBounds(35, 110, 200, 14);
        contentPane.add(lblId);

        JLabel l2 = new JLabel();
        l2.setBounds(271, 110, 200, 14);
        contentPane.add(l2);

        JLabel lb2 = new JLabel("Number of Persons :");
        lb2.setBounds(35, 150, 200, 14);
        contentPane.add(lb2);

        JLabel l3 = new JLabel();
        l3.setBounds(271, 150, 200, 14);
        contentPane.add(l3);

        JLabel lblName_1 = new JLabel("Number of Days :");
        lblName_1.setBounds(35, 190, 200, 14);
        contentPane.add(lblName_1);

        JLabel l4 = new JLabel();
        l4.setBounds(271, 190, 200, 14);
        contentPane.add(l4);

        JLabel lblGender = new JLabel("AC / Non-AC :");
        lblGender.setBounds(35, 230, 200, 14);
        contentPane.add(lblGender);

        JLabel l5 = new JLabel();
        l5.setBounds(271, 230, 200, 14);
        contentPane.add(l5);

        JLabel lblCountry = new JLabel("Food Included (Yes/No) :");
        lblCountry.setBounds(35, 270, 200, 14);
        contentPane.add(lblCountry);

        JLabel l6 = new JLabel();
        l6.setBounds(271, 270, 200, 14);
        contentPane.add(l6);

        JLabel lblReserveRoomNumber = new JLabel("ID :");
        lblReserveRoomNumber.setBounds(35, 310, 200, 14);
        contentPane.add(lblReserveRoomNumber);

        JLabel l7 = new JLabel();
        l7.setBounds(271, 310, 200, 14);
        contentPane.add(l7);

        JLabel lblCheckInStatus = new JLabel("Booking Date :");
        lblCheckInStatus.setBounds(35, 350, 200, 14);
        contentPane.add(lblCheckInStatus);

        JLabel l8 = new JLabel();
        l8.setBounds(271, 350, 200, 14);
        contentPane.add(l8);

        JLabel lblDeposite = new JLabel("Total Price :");
        lblDeposite.setBounds(35, 390, 200, 14);
        contentPane.add(lblDeposite);

        JLabel l9 = new JLabel();
        l9.setBounds(271, 390, 200, 14);
        contentPane.add(l9);

        Conn c = new Conn();
        try {
            ResultSet rs = c.s.executeQuery("SELECT * FROM bookhotels WHERE username = '" + username + "'");
            while (rs.next()) {
                l1.setText(rs.getString("username"));
                l2.setText(rs.getString("hotel_name"));
                l3.setText(rs.getString("persons"));
                l4.setText(rs.getString("days"));
                l5.setText(rs.getString("ac"));
                l6.setText(rs.getString("food"));
                l7.setText(rs.getString("id"));
                l8.setText(rs.getString("booking_date"));
                l9.setText(rs.getString("total_price"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });
        btnExit.setBounds(160, 470, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}
