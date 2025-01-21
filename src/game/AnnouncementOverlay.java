package game;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AnnouncementOverlay {
    private String message;
    private int x;
    private int y;
    private boolean isVisible;
    private Font customFont;
    private Font currentFont;


    public AnnouncementOverlay() {
        loadCustomFont();
        this.message = "";
        this.x = 0;
        this.y = 0;
        this.isVisible = false;
    }

    public void announce(String message, int displayLength, int x, int y, float fontSize) {
        this.message = message;
        this.x = x;
        this.y = y;
        this.isVisible = true;
        this.currentFont = customFont.deriveFont(fontSize);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isVisible = false;
                clearMessage();
            }
        }, displayLength);
    }

    public void draw(Graphics g) {
        if (isVisible) {
            g.setColor(Color.BLACK);
            g.setFont(currentFont);
            g.drawString(message, x, y);
        }
    }

    private void loadCustomFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/bloodlustacadital.ttf")).deriveFont(80f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            // System.out.println("Custom font loaded successfully.");
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); 
            System.out.println("Failed to load custom font. Using default font.");
        }
    }

    private void clearMessage() {
        this.message = "";
    }

    
}