package TravelandTourismSystem;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import java.util.Random;

public class Paytm extends JFrame {
    JButton payButton, backButton;
    
    Paytm() {
        setSize(800, 600);
        setLocation(280, 90);
        setTitle("Payment For Tour and Travel System");
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);
        

        // Background Image
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("Icons/pytm.jpg"));
        Image i2 = c1.getImage().getScaledInstance(500, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(300, 0, 500, 600);
        add(img);
        
        // Pay Button
        payButton = new JButton("Pay");
        payButton.setBounds(60, 300, 100, 40);
        add(payButton);
        
        JLabel heading = new JLabel("Pay Through Paytm");
        heading.setBounds(60,70,400,40);
        heading.setFont(new Font("Arial",Font.BOLD,20));
        heading.setForeground(Color.white);
        add(heading);
        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(150, 300, 100, 40);
        add(backButton);
       
        // Pay Button Action
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processPayment();
            }
        });
        
        // Back Button Action
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });
        
        setVisible(true);
    }
    
    public void processPayment() {
        Random rand = new Random();
        boolean isSuccess = rand.nextBoolean(); // Random payment success/fail
        
        if (isSuccess) {
            int option = JOptionPane.showConfirmDialog(this, "Transaction Successful! Do you want to print the receipt?", "Payment Success", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                double amount = fetchAmountFromDatabase();
                generatePDF(amount);
                JOptionPane.showMessageDialog(this, "Receipt saved as 'PaymentReceipt.pdf'");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Transaction Failed! Please try again.", "Payment Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public double fetchAmountFromDatabase() {
        double amount = 0.0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Travel", "root", "2025");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cost FROM payments WHERE id=1"); // Change query as needed
            if (rs.next()) {
                amount = rs.getDouble("cost");
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return amount;
    }
    
    public void generatePDF(double amount) {
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream("PaymentReceipt.pdf"));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);
            
            document.add(new Paragraph("Payment Receipt"));
            document.add(new Paragraph("------------------------------"));
            document.add(new Paragraph("Transaction ID: " + System.currentTimeMillis()));
            document.add(new Paragraph("Amount: Rs. " + amount));
            document.add(new Paragraph("Status: Successful"));
            document.add(new Paragraph("Thank you for your payment!"));
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Paytm();
    }
}
