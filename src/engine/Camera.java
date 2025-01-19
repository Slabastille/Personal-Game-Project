package engine;

import java.awt.Color;
import java.awt.Graphics;
import entities.mainCharacter;

public class Camera {
    public static int x;
    public static int y;

    public static void moveLT(int dx){
        x -= dx;
    }
    public static void moveRT(int dx){
        x += dx;
    }
    public static void moveUP(int dy){
        y -= dy;
    }
    public static void moveDN(int dy){
        y += dy;
    }

    public static void shift(mainCharacter main, int w, int h, Level currLevel){
        
        

        int margin = 100; // Margin from the screen edge before the camera moves

        if (main.x < x + margin) {
            x = main.x - margin;
        } else if (main.x + main.w > x + w - margin) {
            x = main.x + main.w - w + margin;
        }

        if (main.y > h / 3 && main.y < 500 - h / 3) {
            y = main.y - h / 2 + main.h / 2;
        }

        x = Math.max(0, Math.min(x, currLevel.WIDTH - w));
        y = Math.max(0, Math.min(y, currLevel.HEIGHT - h));
    }

    public static void draw(Graphics g){
        int screenWidth = 800;
        int screenHeight = 600;
        g.setColor(Color.RED);
        g.drawRect(0, 0, screenWidth, screenHeight);
        g.drawRect(1, 1, screenWidth - 2, screenHeight - 2);
    
    }
}
