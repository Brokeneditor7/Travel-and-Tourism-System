package TravelandTourismSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteCustomer extends JFrame {
    private JPanel contentPane;
    Choice c1;
    JLabel l2, l3, l4, l5, l6, l7, l8, l9;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DeleteCustomer frame = new DeleteCustomer();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DeleteCustomer() {
        setBounds(280, 120, 850, 550);
        
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image i3 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l1 = new JLabel(i2);
        l1.setBounds(500, 100, 300, 300);
        add(l1);

        // Title
        JLabel lblName = new JLabel("DELETE CUSTOMER DETAILS");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(118, 11, 300, 53);
        contentPane.add(lblName);

        // Username Label & Choice
        JLabel lb3 = new JLabel("Username :");
        lb3.setBounds(35, 70, 200, 14);
        contentPane.add(lb3);

        c1 = new Choice();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT username  FROM customer");
            while (rs.next()) {
                c1.add(rs.getString("username"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        c1.setBounds(271, 70, 150, 30);
        add(c1);

        // Labels for Customer Details
        JLabel lblId = new JLabel("ID :"); lblId.setBounds(35, 110, 200, 14); contentPane.add(lblId);
        l2 = new JLabel(); l2.setBounds(271, 110, 200, 14); contentPane.add(l2);

        JLabel lb2 = new JLabel("Number :"); lb2.setBounds(35, 150, 200, 14); contentPane.add(lb2);
        l3 = new JLabel(); l3.setBounds(271, 150, 200, 14); contentPane.add(l3);

        JLabel lblName_1 = new JLabel("Name :"); lblName_1.setBounds(35, 190, 200, 14); contentPane.add(lblName_1);
        l4 = new JLabel(); l4.setBounds(271, 190, 200, 14); contentPane.add(l4);

        JLabel lblGender = new JLabel("Gender :"); lblGender.setBounds(35, 230, 200, 14); contentPane.add(lblGender);
        l5 = new JLabel(); l5.setBounds(271, 230, 200, 14); contentPane.add(l5);

        JLabel lblCountry = new JLabel("Country :"); lblCountry.setBounds(35, 270, 200, 14); contentPane.add(lblCountry);
        l6 = new JLabel(); l6.setBounds(271, 270, 200, 14); contentPane.add(l6);

        JLabel lblReserveRoomNumber = new JLabel("Permanent Address :");
        lblReserveRoomNumber.setBounds(35, 310, 200, 14);
        contentPane.add(lblReserveRoomNumber);
        l7 = new JLabel(); l7.setBounds(271, 310, 200, 14); contentPane.add(l7);

        JLabel lblCheckInStatus = new JLabel("Phone :"); lblCheckInStatus.setBounds(35, 350, 200, 14); contentPane.add(lblCheckInStatus);
        l8 = new JLabel(); l8.setBounds(271, 350, 200, 14); contentPane.add(l8);

        JLabel lblDeposite = new JLabel("Email :"); lblDeposite.setBounds(35, 390, 200, 14); contentPane.add(lblDeposite);
        l9 = new JLabel(); l9.setBounds(271, 390, 200, 14); contentPane.add(l9);

        // Check Button
        JButton b1 = new JButton("Check");
        b1.addActionListener(e -> {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("SELECT * FROM customer WHERE username = '" + c1.getSelectedItem() + "'");
                if (rs.next()) {
                    l2.setText(rs.getString("id"));
                    l3.setText(rs.getString("number")); // Ensure 'number' exists in DB
                    l4.setText(rs.getString("name"));
                    l5.setText(rs.getString("gender"));
                    l6.setText(rs.getString("country"));
                    l7.setText(rs.getString("address"));
                    l8.setText(rs.getString("phone"));
                    l9.setText(rs.getString("email"));
                } else {
                    JOptionPane.showMessageDialog(null, "User not found!");
                }
                rs.close();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        });
        b1.setBounds(425, 70, 80, 22);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        contentPane.add(b1);

        // Delete Button
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(e -> {
            try {
                Conn c = new Conn();
                String username = c1.getSelectedItem();
                String q1 = "DELETE FROM customer WHERE username = '" + username + "'";
                c.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
                setVisible(false);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        btnDelete.setBounds(100, 430, 120, 30);
        btnDelete.setBackground(Color.BLACK);
        btnDelete.setForeground(Color.WHITE);
        contentPane.add(btnDelete);

        // Back Button
        JButton btnExit = new JButton("Back");
        btnExit.addActionListener(e -> setVisible(false));
        btnExit.setBounds(260, 430, 120, 30);
        btnExit.setBackground(Color.BLACK);
        btnExit.setForeground(Color.WHITE);
        contentPane.add(btnExit);

        getContentPane().setBackground(Color.WHITE);
    }
}
