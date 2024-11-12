package entities.hooligans;

import engine.Character;
import entities.mainCharacter;
import engine.Animation;
import entities.Enemies;
import engine.*;
import java.awt.*;
import java.awt.geom.AffineTransform;


public class hooliganShotgun extends Character 
{
    private String name = "hs";
    private String folderName = "hooliganShotgun/";
    private Animation attackLT;
    private Animation attackRT;

    public hooliganShotgun(int x, int y, int w, int h, boolean isFacingRight) 
    {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 8, folderName);
        this.moveRT = new Animation(name + "_wr", 8, folderName);
        this.attackLT = new Animation(name + "_al", 12, folderName);
        this.attackRT = new Animation(name + "_ar", 12, folderName);
        this.idleLT = new Animation(name + "_il", 6, folderName);
        this.idleRT = new Animation(name + "_ir", 6, folderName);
        this.isFacingRight = isFacingRight;
        //this.detectionRange = 200;
        this.currentAnimation = idleLT;

    }

    public void goLT(int dx)
    {
        isFacingRight = false;
        super.goLT(dx);
        currentAnimation = moveLT;
    }

    public void goRT(int dx)
    {
        isFacingRight = true;
        super.goRT(dx);
        currentAnimation = moveRT;
    }

    public void goUP(int dy)
    {
        super.goUP(dy);
    }

    public void goDN(int dy)
    {
        super.goDN(dy);
        //currentAnimation = idle;
    }

    public void attack()
    {
        currentAnimation = isFacingRight ? attackRT : attackLT;
    }
    
    public void stopAttack()
    {
        currentAnimation = isFacingRight ? idleRT : idleLT;
    }

    public void draw(Graphics pen)
    {
        super.draw(pen);
    }
    public void update(mainCharacter character) 
    {
        //super.update(character);
        //detectAndAttack(character);
        //currentAnimation.nextImage();
    }


    
  

}
