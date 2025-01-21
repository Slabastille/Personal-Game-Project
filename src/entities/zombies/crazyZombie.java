package entities.zombies;

import engine.Character;
import entities.Enemies;
import entities.mainCharacter;
import engine.Animation;
import engine.*;
import java.awt.*;

public class crazyZombie extends Enemies {
    private String name = "zw";
    private String folderName = "crazyZombie/";
    public crazyZombie(int x, int y, int w, int h, boolean isFacingRight) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 10, folderName, 7);
        this.moveRT = new Animation(name + "_wr", 10, folderName, 7);
        this.idleLT = new Animation(name + "_il", 5, folderName, 7);
        this.idleRT = new Animation(name + "_ir", 5, folderName, 7);
        this.attackLT = new Animation(name + "_al", 4, folderName, 7);
        this.attackRT = new Animation(name + "_ar", 4, folderName, 7);
        this.dying = new Animation(name + "_dr", 5, folderName, 7);
        this.isFacingRight = isFacingRight;
        this.currentAnimation = idleAnimation(isFacingRight);
        this.detectionRange = 150;
        this.attackRange = 50;
        this.speed = 2;
        this.dieSound = loadSound("src/assets/sounds/crazyZombie/crazyZombieDead.wav");
        this.hurtSound = loadSound("src/assets/sounds/crazyZombie/crazyZombieHurt.wav");
        this.attackSound = loadSound("src/assets/sounds/crazyZombie/crazyZombieAttack.wav");
        this.health = 100;
        this.isDead = false;
        this.damage = 25;
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

    public void draw(Graphics pen){
        super.draw(pen);
    }

    
  

}
