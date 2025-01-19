package entities;
import java.awt.Graphics;
import java.awt.Color;

public class Bullet {
    private int x;
    private int y;
    private int width;
    private int height;
    private int vx;
    private boolean isActive;

    public Bullet(int x, int y, int vx) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.width = 3;
        this.height = 3;
        this.isActive = true;
    }

    public void update() {
        x += vx;

        if(x < -5 || x > 10000){
            isActive = false;
        }
    }

    public void draw(Graphics g ) { 
        if(isActive) {
            System.out.println("Drawing bullet at: " + x + ", " + y);
            g.setColor(Color.YELLOW);
            g.fillRect(x, y, width, height);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    
}
