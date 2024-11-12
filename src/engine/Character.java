package engine;
import java.awt.*;

public class Character extends Rect{

    public Animation animation;
    public Animation moveLT;
    public Animation moveRT;
    public Animation jump;
    public Animation idleLT;
    public Animation idleRT;
    public Animation currentAnimation;
    public boolean isFacingRight;
    public boolean isAttacking;
    

    public Character(int x, int y, int w, int h) {
        super(x, y, w, h);
        

    }

    public void goLT(int dx){
        super.goLT(dx);
    }
    public void goRT(int dx){
        super.goRT(dx);
    }
    public void goUP(int dy){
        super.goUP(dy);
    }
    public void goDN(int dy){
        super.goDN(dy);
    }

    // public void draw(Graphics pen){
    //    super.draw(pen);
    // }

    public void draw(Graphics pen){
        int padding = -35; // Adjust this value to control the padding
        int imageX = x + padding ;
        int imageY = y + padding - 35;
        int imageW = w - 2 * padding;
        int imageH = h - 2 * padding;

        pen.drawRect(x, y, w, h);
        pen.drawImage(currentAnimation.nextImage(), imageX, imageY, imageW , imageH, null);
        // pen.drawImage(currentAnimation.nextImage(), x, y, w, h, null);
    }

    public void update(){
        this.move();
    }
    

    
}
