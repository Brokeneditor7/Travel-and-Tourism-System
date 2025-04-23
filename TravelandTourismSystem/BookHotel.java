package TravelandTourismSystem;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class BookHotel extends JFrame {
    private JPanel contentPane;
    JTextField t1, t2;
    Choice c1, c2, c3;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BookHotel frame = new BookHotel("");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BookHotel(String username) {
        setBounds(120, 30, 1100, 600);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/book.jpg"));
        JLabel la1 = new JLabel(i1);
        la1.setBounds(480, 0, 800, 700);
        add(la1);

        JLabel lblName = new JLabel("BOOK HOTEL");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 300, 53);
        contentPane.add(lblName);

        JLabel la2 = new JLabel("Username :");
        la2.setBounds(35, 70, 200, 14);
        contentPane.add(la2);

        JLabel l1 = new JLabel(username);
        l1.setBounds(271, 70, 200, 14);
        contentPane.add(l1);

        JLabel lblId = new JLabel("Select Hotel :");
        lblId.setBounds(35, 110, 200, 14);
        contentPane.add(lblId);

        c1 = new Choice();
        Conn c = new Conn();
        try {
            ResultSet rs = c.s.executeQuery("select * from hotels");
            while (rs.next()) {
                c1.add(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {}
        c1.setBounds(271, 110, 150, 30);
        add(c1);

        JLabel la3 = new JLabel("Total Persons");
        la3.setBounds(35, 150, 200, 14);
        contentPane.add(la3);

        t1 = new JTextField("0");
        t1.setBounds(271, 150, 150, 20);
        contentPane.add(t1);
        t1.setColumns(10);

        JLabel la4 = new JLabel("Number of Days");
        la4.setBounds(35, 190, 200, 14);
        contentPane.add(la4);

        t2 = new JTextField("0");
        t2.setBounds(271, 190, 150, 20);
        contentPane.add(t2);
        t2.setColumns(10);

        JLabel la5 = new JLabel("AC / Non-AC");
        la5.setBounds(35, 230, 200, 14);
        contentPane.add(la5);

        c2 = new Choice();
        c2.add("AC");
        c2.add("Non-AC");
        c2.setBounds(271, 230, 150, 30);
        add(c2);

        JLabel la6 = new JLabel("Food Included :");
        la6.setBounds(35, 270, 200, 14);
        contentPane.add(la6);

        c3 = new Choice();
        c3.add("Yes");
        c3.add("No");
        c3.setBounds(271, 270, 150, 30);
        add(c3);

        JLabel lblDeposite = new JLabel("Total Price :");
        lblDeposite.setBounds(35, 430, 200, 14);
        contentPane.add(lblDeposite);

        JLabel l5 = new JLabel();
        l5.setBounds(271, 430, 200, 14);
        l5.setForeground(Color.RED);
        contentPane.add(l5);

        JButton b1 = new JButton("Check Price");
        b1.addActionListener(e -> {
            try {
                String q1 = "select * from hotels where name = '" + c1.getSelectedItem() + "'";
                ResultSet rs = c.s.executeQuery(q1);
                if (rs.next()) {
                    double cost = rs.getDouble("cost_per_day");
                    double food = rs.getDouble("food_charges");
                    double ac = rs.getDouble("ac_charges");
                    int persons = Integer.parseInt(t1.getText());
                    int days = Integer.parseInt(t2.getText());
                    if (persons > 0 && days > 0) {
                        double total = cost * persons * days + (c2.getSelectedItem().equals("AC") ? ac : 0) + (c3.getSelectedItem().equals("Yes") ? food : 0);
                        l5.setText("Rs " + total);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter valid numbers for persons and days.");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        b1.setBounds(50, 470, 120, 30);
        contentPane.add(b1);

        // Add the "Book" button
        JButton b2 = new JButton("Book");
        b2.setBounds(200, 470, 120, 30);
        b2.addActionListener(e -> {
            // Logic for saving the booking information in the database
            try {
                String hotelName = c1.getSelectedItem();
                int persons = Integer.parseInt(t1.getText());
                int days = Integer.parseInt(t2.getText());
                String ac = c2.getSelectedItem();
                String food = c3.getSelectedItem();
                double totalPrice = Double.parseDouble(l5.getText().substring(3));  // Getting the total price
                
                // Insert the booking details into the database (bookhotels table)
                String query = "INSERT INTO bookhotels (username, hotel_name, persons, days, ac, food, total_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, username);
                pst.setString(2, hotelName);
                pst.setInt(3, persons);
                pst.setInt(4, days);
                pst.setString(5, ac);
                pst.setString(6, food);
                pst.setDouble(7, totalPrice);
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Booking Successful!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error during booking.");
            }
        });
        contentPane.add(b2);

        // Add the "Back" button
        JButton b3 = new JButton("Back");
        b3.setBounds(350, 470, 120, 30);
        b3.addActionListener(e -> {
            // Close the frame when "Back" button is clicked
            dispose();
        });
        contentPane.add(b3);

        getContentPane().setBackground(Color.WHITE);
    }
}
