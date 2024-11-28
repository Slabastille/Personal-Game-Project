package entities.zombies;

import engine.Character;
import entities.Enemies;
import engine.Animation;
import engine.*;
import java.awt.*;

public class crazyZombie extends Enemies {
    private String name = "zw";
    private String folderName = "crazyZombie/";
    public crazyZombie(int x, int y, int w, int h, boolean isFacingRight) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 10, folderName);
        this.moveRT = new Animation(name + "_wr", 10, folderName);
        this.idleLT = new Animation(name + "_il", 5, folderName);
        this.idleRT = new Animation(name + "_ir", 5, folderName);
        this.attackLT = new Animation(name + "_al", 4, folderName);
        this.attackRT = new Animation(name + "_ar", 4, folderName);
        this.isFacingRight = isFacingRight;
        this.currentAnimation = idleAnimation(isFacingRight);
        this.detectionRange = 70;
    }
    public void goLT(int dx){
        super.goLT(dx);
        currentAnimation = moveLT;
    }
    public void goRT(int dx){
        super.goRT(dx);
        currentAnimation = moveRT;
    }
     public void attack() {
        super.attack();
        currentAnimation = isFacingRight ? attackRT : attackLT;
    }
    public void stopAttack() {
        super.stopAttack();
        currentAnimation = isFacingRight ? idleRT : idleLT;
    }
    public void update() {
        super.update();
    }

    public void draw(Graphics pen){
        super.draw(pen);
    }

    
  

}
