package main;

import java.awt.*;                      // Basic drawing tools (e.g., Graphics, Color)
import java.awt.image.BufferedImage;   // Holds image data that can be drawn and modified
import java.io.File;          // Used to load image files from disk
import java.io.IOException;                   // Handles file paths to access images
import javax.imageio.ImageIO;            // Handles file loading errors

// ------------------------------------
// Character class handles player movement, animation, and rendering
// ------------------------------------
public class Character {

    // ------------------------------
    // 1. Position and Speed Settings
    // ------------------------------
    private int x, y;                   // Character position on screen
    private int speed = 5;             // How fast the character moves per key press

    // ------------------------------
    // 2. Animation Sprites
    // ------------------------------
    private BufferedImage walkDown, walkUp, walkRight, walkLeft;  // Sprite sheets for different directions
    private BufferedImage currentFrame;                           // The frame being shown

    // ------------------------------
    // 3. Sprite Sheet Frame Constants
    // ------------------------------
    private static final int FRAME_WIDTH = 48;     // Width of each animation frame
    private static final int FRAME_HEIGHT = 64;    // Height of each animation frame
    private static final int MAX_FRAMES = 8;       // Total number of frames in the animation

    // ------------------------------
    // 4. Animation Tracking
    // ------------------------------
    private int frameIndex = 0;       // Tracks current animation frame
    private int frameDelay = 0;       // Controls delay between frames
    private String direction = "Down";// Default facing direction

    // ------------------------------
    // 5. Constructor: Load Sprites
    // ------------------------------
    public Character(int startX, int startY) {
        this.x = startX;
        this.y = startY;

        try {
            // Load sprite sheets for each direction
            walkDown = ImageIO.read(new File("Characters/Mercury/Walk/walk_Down.png"));
            walkUp = ImageIO.read(new File("Characters/Mercury/Walk/walk_Up.png"));
            walkRight = ImageIO.read(new File("Characters/Mercury/Walk/walk_right_down.png"));
            walkLeft = ImageIO.read(new File("Characters/Mercury/Walk/walk_left_down.png"));

            // Start with the first frame of walking down
            currentFrame = walkDown.getSubimage(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        } catch (IOException e) {
            System.out.println("Failed to load character sprites: " + e.getMessage());
        }
    }

    // ------------------------------
    // 6. Movement and Animation Logic
    // ------------------------------
    public void move(int dx, int dy) {
        if (dx == 0 && dy == 0) return; // No movement? Do nothing.

        x += dx * speed;
        y += dy * speed;

        // Set direction based on movement
        if (dx == 1) direction = "Right";
        else if (dx == -1) direction = "Left";
        else if (dy == 1) direction = "Down";
        else if (dy == -1) direction = "Up";

        // Animate with delay
        if (++frameDelay >= 5) {
            frameIndex = (frameIndex + 1) % MAX_FRAMES; // Loops back to 0 after MAX_FRAMES
            frameDelay = 0;
        }

        // Switch frame based on direction
        try {
            switch (direction) {
                case "Down" ->
                    currentFrame = walkDown.getSubimage(frameIndex * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
                case "Up" ->
                    currentFrame = walkUp.getSubimage(frameIndex * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
                case "Right" ->
                    currentFrame = walkRight.getSubimage(frameIndex * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
                case "Left" ->
                    currentFrame = walkLeft.getSubimage(frameIndex * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
            }
        } catch (Exception e) {
            System.out.println("Animation error: " + e.getMessage());
        }
    }

    // ------------------------------
    // 7. Draw Character on the Screen
    // ------------------------------
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g; // Cast to 2D graphics for better drawing features
        g2d.drawImage(currentFrame, x, y, FRAME_WIDTH, FRAME_HEIGHT, null);
    }
}
