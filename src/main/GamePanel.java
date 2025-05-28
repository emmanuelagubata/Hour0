package main;

import javax.swing.*;     // For JFrame and JPanel
import java.awt.*;        // For Graphics, Color, etc.
import java.awt.event.*;  // For KeyEvent, KeyListener

public class GamePanel extends JPanel implements KeyListener {
    private Character player; // Declares variable 'player'

    
    public GamePanel() {
        this.player = new Character(400, 300); // Creates player, adds specific position to where it starts
        setFocusable(true); // Makes character visible
    }

    public void activate() {
        addKeyListener(this); // Character 'listens' to whatever user is inputting
    }

    // Draws everything on screen (character)
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clears the screen before we draw anything new
        g.setColor(Color.DARK_GRAY); // Sets background color
        g.fillRect(0, 0, getWidth(), getHeight()); // Fils entire panel with color
        player.draw(g); // Draws character
    }

    // Adds key inputs for character
    @Override
    public void keyPressed(KeyEvent e) {
        int dx = 0; // Tracks movement along x position
        int dy = 0; // Tracks movement along y position
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> dy = -1; // Tracks what key is being pressed (in this case 'UP' key)
            case KeyEvent.VK_DOWN -> dy = 1; // Tracks what key is being pressed (in this case 'DOWN' key)
            case KeyEvent.VK_LEFT -> dx = -1; // Tracks what key is being pressed (in this case 'LEFT' key)
            case KeyEvent.VK_RIGHT -> dx = 1; // Tracks what key is being pressed (in this case 'RIGHT' key)
        }
        player.move(dx, dy);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    } // Triggers when key is released

    @Override
    public void keyTyped(KeyEvent e) {
    } // Triggers when key is typed
}