package engine;

import java.awt.Color;
import java.awt.Graphics;

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

    public static void draw(Graphics pen) {
        // Draw the camera view here
        // pen.setColor(Color.BLACK);
        // pen.drawRect(x, y, 800 , 600); // Example dimensions for the camera view
        // System.out.println("Camera position: x: " + x + ", y: " + y);
    }
}
