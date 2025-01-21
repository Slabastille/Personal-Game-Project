package entities.hooligans;

import engine.Character;
import entities.Enemies;
import entities.mainCharacter;
import engine.Animation;
import engine.*;
import java.awt.*;

public class hooliganBat extends Enemies {

    private String name = "hb";
    private String folderName = "hooliganBat/";

    public hooliganBat(int x, int y, int w, int h, boolean isFacingRight) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 7, folderName, 7);
        this.moveRT = new Animation(name + "_wr", 7, folderName, 7);
        this.idleLT = new Animation(name + "_il", 6, folderName, 7);
        this.idleRT = new Animation(name + "_ir", 6, folderName, 7);
        this.attackLT = new Animation(name + "_al", 5, folderName, 7);
        this.attackRT = new Animation(name + "_ar", 5, folderName, 7);
        this.isFacingRight = isFacingRight;
        this.dying = isFacingRight ? new Animation(name + "_dr", 4, folderName, 7) : new Animation(name + "_dl", 4, folderName, 7);
        this.currentAnimation = idleAnimation(isFacingRight);
        this.detectionRange = 150;
        this.attackRange = 50;
        this.speed = 2;
        this.dieSound = loadSound("src/assets/sounds/hooliganBat/hooliganBatDead.wav");
        this.hurtSound = loadSound("src/assets/sounds/hooliganBat/hooliganBatHurt.wav");
        this.attackSound = loadSound("src/assets/sounds/hooliganBat/hooliganBatAttack.wav");
        this.health = 100;
        this.isDead = false;
        this.damage = 30;
    }
    public void goLT(int dx){
        super.goLT(dx);
        currentAnimation = moveLT;
    }
    public void goRT(int dx){
        super.goRT(dx);
        currentAnimation = moveRT;
    }
    
   
    public void attack(mainCharacter character) {
        super.attack(character);
        // isAttacking = true;
        // playSound(attackSound);
        // currentAnimation = isFacingRight ? attackRT : attackLT;
    }
    public void stopAttack(mainCharacter character) {
        super.stopAttack(character);
        // currentAnimation = isFacingRight ? idleRT : idleLT;
    }
     public void update() {
        super.update();
    }
    public void draw(Graphics pen) {
        super.draw(pen);
    }

    
  

}
