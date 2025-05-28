package main;

import javax.swing.*;     // For JFrame and JPanel
import java.awt.*;        // For Graphics, Color, etc.
import java.awt.event.*;  // For KeyEvent, KeyListener

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hour Zero "); // Create game window
        GamePanel gamePanel = new GamePanel(); // Create game panel

        frame.add(gamePanel); // Add game logic
        frame.setSize(800, 600); // Set window size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close window exits game
        frame.setVisible(true); // Show window
        gamePanel.activate(); // activates the key listener after setup
    }
}

//test pull 
