package engine;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Image;
import engine.Rect;
import entities.*;

public class Powerups extends Rect {
    private String type;
    private Image health =      Toolkit.getDefaultToolkit().getImage("src/assets/images/ScrollingCity/Powerups/health.png");
    private Image ammo =        Toolkit.getDefaultToolkit().getImage("src/assets/images/ScrollingCity/Powerups/ammo.png");
    private Image checkpoint =  Toolkit.getDefaultToolkit().getImage("src/assets/images/ScrollingCity/Powerups/checkpoint.png");
    
    private float glowPhase = 0f;       
    private float glowSpeed = 0.1f;     
    private float baseGlow  = 8f;       
    private float glowRange = 4f;     


    public Powerups(int x, int y, int w, int h, String type) {
        super(x, y, w, h);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        float dynamicGlow = (float) (baseGlow + glowRange * Math.sin(glowPhase));
        glowPhase += glowSpeed; // increment the phase for the next frame

        g2d.setColor(new Color(255, 255, 0, 128)); // semi-transparent yellow
        g2d.setStroke(new BasicStroke(dynamicGlow));

        int offset = Math.round(dynamicGlow / 2);
        g2d.drawOval(x - offset, y - offset, w + offset * 2, h + offset * 2);

        switch (type) {
            case "health":
                g.drawImage(health, this.x, this.y, this.w, this.h, null);
                break;
            case "ammo":
                g.drawImage(ammo, this.x, this.y, this.w, this.h, null);
                break;
            case "checkpoint":
                g.drawImage(checkpoint, this.x, this.y, this.w, this.h, null);
                break;
        }

        g2d.dispose();
    }

    public void applyEffect(mainCharacter player, Level currLevel) {
        switch (type) {
            case "health":
                if(player.health < 100){
                    int needed = 100 - player.health;
                    int amountToAdd = Math.min(50, needed);
                    player.health += amountToAdd; 
                }
                break;
            case "ammo":
                player.ammo += 30;
                break;
            case "checkpoint":
                currLevel.levelCompleted = true;
                break;
        }
    }
}