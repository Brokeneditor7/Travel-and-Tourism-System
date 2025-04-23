package TravelandTourismSystem;

import java.awt.*;
import javax.swing.*;

public class Splash {
    public static void main(String[] args) {
        SplashFrame f1 = new SplashFrame();
        f1.setVisible(true);
        int i;
        int x = 1;
        for (i = 2; i <= 600; i += 10, x += 7) {
            f1.setLocation(650 - ((i + x) / 2), 350 - (i / 2));
            f1.setSize(i + x, i);
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class SplashFrame extends JFrame implements Runnable {
    Thread t1;

    SplashFrame() {
        setLayout(new BorderLayout());

        // Load and scale the image
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icons/splash.jpg"));
        Image i1 = c1.getImage().getScaledInstance(1030, 750, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);

        // Create JLabel with Image
        JLabel imageLabel = new JLabel(i2);
        imageLabel.setLayout(new BorderLayout());

        // Panel for Text (Upper Left Center)
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 220)); // Left align with padding
        textPanel.setOpaque(false); // Make it transparent

        // Text Label
        JLabel textLabel = new JLabel("Welcome to Travel and Travel System");
        textLabel.setFont(new Font("Arial", Font.BOLD, 30));
        textLabel.setForeground(Color.WHITE);

        textPanel.add(textLabel);
        imageLabel.add(textPanel, BorderLayout.NORTH); // Place text at the top

        add(imageLabel);

        setUndecorated(true); // Hide window borders
        setSize(1030, 750);
        setLocationRelativeTo(null); // Center on screen

        t1 = new Thread(this);
        t1.start();
    }

    public void run() {
        try {
            Thread.sleep(5000);
            this.setVisible(false);

            // Open Login Page
            LoginPage l = new LoginPage();
            l.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
