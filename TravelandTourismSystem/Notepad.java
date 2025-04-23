package TravelandTourismSystem;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea textArea;
    JMenuItem newFile, openFile, saveFile, exit, undo, redo, findReplace, darkMode;
    JLabel statusBar;
    UndoManager undoManager;
    boolean isDarkMode = false;

    public Notepad() {
        setTitle("Notepad");
        setSize(900, 600);
        setBounds(120,0,900,600);
        
        textArea = new JTextArea();
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textArea.setMargin(new Insets(5, 5, 5, 5));
        undoManager = new UndoManager();
        textArea.getDocument().addUndoableEditListener(e -> undoManager.addEdit(e.getEdit()));
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu viewMenu = new JMenu("View");
        
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        findReplace = new JMenuItem("Find & Replace");
        darkMode = new JMenuItem("Toggle Dark Mode");
        
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        exit.addActionListener(this);
        undo.addActionListener(this);
        redo.addActionListener(this);
        findReplace.addActionListener(this);
        darkMode.addActionListener(this);
        
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        
        editMenu.add(undo);
        editMenu.add(redo);
        editMenu.add(findReplace);
        
        viewMenu.add(darkMode);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);
        
        statusBar = new JLabel("Line: 1, Column: 1");
        add(statusBar, BorderLayout.SOUTH);
        
        textArea.addCaretListener(e -> {
            int pos = textArea.getCaretPosition();
            int line = 1, column = 1;
            try {
                line = textArea.getLineOfOffset(pos) + 1;
                column = pos - textArea.getLineStartOffset(line - 1) + 1;
            } catch (Exception ex) {}
            statusBar.setText("Line: " + line + ", Column: " + column);
        });
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newFile) {
            textArea.setText("");
        } else if (e.getSource() == openFile) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    textArea.read(br, null);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error opening file");
                }
            }
        } else if (e.getSource() == saveFile) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    textArea.write(bw);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error saving file");
                }
            }
        } else if (e.getSource() == exit) {
            System.exit(0);
        } else if (e.getSource() == undo) {
            if (undoManager.canUndo()) undoManager.undo();
        } else if (e.getSource() == redo) {
            if (undoManager.canRedo()) undoManager.redo();
        } else if (e.getSource() == findReplace) {
            findAndReplace();
        } else if (e.getSource() == darkMode) {
            toggleDarkMode();
        }
    }
    
    private void findAndReplace() {
        String find = JOptionPane.showInputDialog(this, "Find:");
        if (find != null && !find.isEmpty()) {
            String replace = JOptionPane.showInputDialog(this, "Replace with:");
            if (replace != null) {
                textArea.setText(textArea.getText().replace(find, replace));
            }
        }
    }
    
    private void toggleDarkMode() {
        if (!isDarkMode) {
            textArea.setBackground(Color.DARK_GRAY);
            textArea.setForeground(Color.WHITE);
            isDarkMode = true;
        } else {
            textArea.setBackground(Color.WHITE);
            textArea.setForeground(Color.BLACK);
            isDarkMode = false;
        }
    }
    
    public static void main(String[] args) {
        new Notepad();
    }
}