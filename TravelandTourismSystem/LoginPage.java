package TravelandTourismSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class ImagePanel extends JPanel {
    private Image bgImage;

    public ImagePanel(String imagePath) {
        bgImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    }
}

class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(243, 232, 226);  // Soft Beige
        Color color2 = new Color(245, 158, 11);   // Light Orange

        GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}

public class LoginPage extends JFrame {
    private JTextField userField;
    private JPasswordField passField;

    public LoginPage() {
        setTitle("Tour & Travel Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 2));

        // Left Image Panel
        ImagePanel imagePanel = new ImagePanel("/icons/package2.jpg");

        // Right Login Panel with Gradient
        GradientPanel loginPanel = new GradientPanel();
        loginPanel.setLayout(null);
        loginPanel.setBorder(BorderFactory.createLineBorder(new Color(217, 119, 6), 10));  // Burnt Orange Border

        JLabel titleLabel = new JLabel("Login to Tour and Travel ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(124, 45, 18));  // Warm Brown
        titleLabel.setBounds(70, 50, 250, 30);
        loginPanel.add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 120, 100, 30);
        userLabel.setFont(new Font("Arial", Font.BOLD, 18));
        userLabel.setForeground(new Color(124, 45, 18));
        userField = new JTextField();
        userField.setBounds(170, 120, 190, 30);
        loginPanel.add(userLabel);
        loginPanel.add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 170, 100, 30);
        passLabel.setForeground(new Color(124, 45, 18));
        passLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passField = new JPasswordField();
        passField.setBounds(170, 170, 190, 30);
        loginPanel.add(passLabel);
        loginPanel.add(passField);

        // Buttons
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        // Button Styling
        loginButton.setBackground(new Color(154, 52, 18));  // Deep Brown
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));

        signupButton.setBackground(new Color(245, 158, 11));  // Golden Orange
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBounds(90, 230, 300, 40);
        buttonPanel.setOpaque(false);
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        loginPanel.add(buttonPanel);

        // Add panels
        add(imagePanel);
        add(loginPanel);

        setVisible(true);

        // Button Actions
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // **SIGN UP BUTTON OPENS SIGNUP PAGE**
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Signup().setVisible(true);
                dispose();  // Closes login page
            }
        });
    }

    private void login() {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter username and password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travel", "root", "2025");
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new DashBoard(username);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            con.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Connection Failed!", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
