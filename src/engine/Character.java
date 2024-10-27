package engine;
import java.awt.*;

public class Character extends Rect{

    public Animation animation;
    public Animation moveLT;
    public Animation moveRT;
    public Animation jump;
    public Animation idle;
    public Animation currentAnimation;

    public Character(int x, int y, int w, int h) {
        super(x, y, w, h);
        

    }

    public void moveLT(int dx){
        super.moveLT(dx);
    }
    public void moveRT(int dx){
        super.moveRT(dx);
    }
    public void moveUP(int dy){
        super.moveUP(dy);
    }
    public void moveDN(int dy){
        super.moveDN(dy);
    }

    // public void draw(Graphics pen){
    //    super.draw(pen);
    // }

    public void draw(Graphics pen){
        pen.drawImage(currentAnimation.nextImage(), x, y, null);
    }

    
}
