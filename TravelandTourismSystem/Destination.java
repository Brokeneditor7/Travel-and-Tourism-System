package TravelandTourismSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Destination extends JFrame implements Runnable {
    JLabel[] imageLabels;
    Thread th;
    String[] imagePaths = {
            "icons/dest1.jpeg", "icons/dest2.png", "icons/dest3.jpg", "icons/dest4.jpg", "icons/dest5.jpg",
            "icons/dest6.jpg", "icons/dest7.jpeg", "icons/dest8.jpg", "icons/dest9.jpg", "icons/dest10.jpg"
    };
    int currentIndex = 0;

    public void run() {
        try {
            while (true) {
                imageLabels[currentIndex].setVisible(true);
                Thread.sleep(1500);
                imageLabels[currentIndex].setVisible(false);
                currentIndex = (currentIndex + 1) % imageLabels.length;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Destination() {
        
        setBounds(200, 20, 900, 680);
        getContentPane().setBackground(new Color(220, 250, 250));
        setLayout(null);
        setResizable(true);

        th = new Thread(this);
        imageLabels = new JLabel[imagePaths.length];

        for (int i = 0; i < imagePaths.length; i++) {
            imageLabels[i] = new JLabel();
            add(imageLabels[i]);
            imageLabels[i].setVisible(false);
        }

        loadImages();
        imageLabels[0].setVisible(true);
        th.start();

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                loadImages();
            }
        });
    }

    private void loadImages() {
        int width = getWidth();
        int height = getHeight();
        for (int i = 0; i < imagePaths.length; i++) {
            ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(imagePaths[i]));
            Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageLabels[i].setIcon(new ImageIcon(img));
            imageLabels[i].setBounds(0, 0, width, height);
        }
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> new Destination().setVisible(true));
    }
}
