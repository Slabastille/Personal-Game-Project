package entities;
import java.awt.Graphics;

import engine.Rect;

import java.awt.Color;

public class Bullet extends Rect {
    private int vx;
    private boolean isActive;

    public Bullet(int x, int y, int vx) {
        super(x, y, 3, 3); // Assuming bullet size is 3x3
        this.vx = vx;
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
            g.fillRect(x, y, w, h);
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        isActive = false;
    }

    @Override
    public boolean overlaps(Rect r) {
        return (x + w >= r.x) && (x <= r.x + r.w) && (y + h >= r.y) && (y <= r.y + r.h);
    }
}
