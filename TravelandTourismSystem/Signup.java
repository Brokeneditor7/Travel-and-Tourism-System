package TravelandTourismSystem;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField usernameField, nameField, addressField, mobileField;
    private JPasswordField passwordField;
    private JComboBox genderCombo, securityQuestionCombo;
    private JTextField answerField;
    private JButton createButton, backButton;

    public static void main(String[] args) {
        Signup frame = new Signup();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public Signup() {
        setTitle("Signup - Travel and Tourism System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(390, 200, 850, 600);
        setMinimumSize(new Dimension(850, 600));
        setLocationRelativeTo(null);

        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon("D:/netbeans-25-bin/Himanshu1/src/TravelandTourismSystem/SignUpBg.jpg");
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Tahoma", Font.BOLD, 18);

        JLabel titleLabel = new JLabel("Create a New Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 1;
        contentPane.add(lblUsername, gbc);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(usernameField, gbc);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 2;
        contentPane.add(lblName, gbc);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(nameField, gbc);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 3;
        contentPane.add(lblPassword, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(passwordField, gbc);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 4;
        contentPane.add(lblAddress, gbc);

        addressField = new JTextField();
        addressField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(addressField, gbc);

        JLabel lblMobile = new JLabel("Mobile:");
        lblMobile.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 5;
        contentPane.add(lblMobile, gbc);

        mobileField = new JTextField();
        mobileField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(mobileField, gbc);

        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 6;
        contentPane.add(lblGender, gbc);

        genderCombo = new JComboBox(new String[]{"Male", "Female", "Other"});
        genderCombo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(genderCombo, gbc);

        JLabel lblSecurityQuestion = new JLabel("Security Question:");
        lblSecurityQuestion.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 7;
        contentPane.add(lblSecurityQuestion, gbc);

        securityQuestionCombo = new JComboBox(new String[]{
                "Your NickName?", "Your Lucky Number?", "Your childhood SuperHero?", "Your childhood Name?"
        });
        securityQuestionCombo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(securityQuestionCombo, gbc);

        JLabel lblAnswer = new JLabel("Answer:");
        lblAnswer.setFont(labelFont);
        gbc.gridx = 0; gbc.gridy = 8;
        contentPane.add(lblAnswer, gbc);

        answerField = new JTextField();
        answerField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        gbc.gridx = 1;
        contentPane.add(answerField, gbc);

        createButton = new JButton("Create");
        createButton.addActionListener(this);
        createButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        createButton.setBackground(Color.BLACK);
        createButton.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 9;
        contentPane.add(createButton, gbc);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        contentPane.add(backButton, gbc);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn();
            if (ae.getSource() == createButton) {
                String sql = "INSERT INTO users (username, name, password, address, mobile, gender, securityQuestion, Answer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement st = conn.c.prepareStatement(sql);

                st.setString(1, usernameField.getText());
                st.setString(2, nameField.getText());
                st.setString(3, new String(passwordField.getPassword()));
                st.setString(4, addressField.getText());
                st.setString(5, mobileField.getText());
                st.setString(6, (String) genderCombo.getSelectedItem());
                st.setString(7, (String) securityQuestionCombo.getSelectedItem());
                st.setString(8, answerField.getText());

                int i = st.executeUpdate();
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Account Created Successfully");
                }
            } else if (ae.getSource() == backButton) {
                this.setVisible(false);
                new LoginPage().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
