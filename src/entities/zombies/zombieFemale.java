package entities.zombies;

import engine.Character;
import entities.Enemies;
import entities.mainCharacter;
import engine.Animation;
import engine.*;
import java.awt.*;

public class zombieFemale extends Enemies {
    private String name = "zf";
    private String folderName = "zombieFemale/";
    public zombieFemale(int x, int y, int w, int h, boolean isFacingRight) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 7, folderName, 7);
        this.moveRT = new Animation(name + "_wr", 7, folderName, 7);
        this.idleLT = new Animation(name + "_il", 5, folderName, 7);
        this.idleRT = new Animation(name + "_ir", 5, folderName, 7);
        this.attackLT = new Animation(name + "_al", 4, folderName, 7);
        this.attackRT = new Animation(name + "_ar", 4, folderName, 7);
        this.dying = new Animation(name + "_dd", 5, folderName, 7);
        this.isFacingRight = isFacingRight;
        this.currentAnimation = idleAnimation(isFacingRight);
        this.detectionRange = 200;
        this.attackRange = 70;
        this.speed = 3;
        this.dieSound = loadSound("src/assets/sounds/zombieFemale/zombieFemaleDead.wav");
        this.hurtSound = loadSound("src/assets/sounds/zombieFemale/zombieFemaleHurt.wav");
        this.attackSound = loadSound("src/assets/sounds/zombieFemale/zombieFemaleAttack.wav");
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
    }
    public void stopAttack(mainCharacter character) {
        super.stopAttack(character);
    }
    public void update() {
        super.update();
    }

    public void draw(Graphics pen){
        // super.draw(pen);
        int padding = -20; // Adjust this value to control the padding
        int imageX = x + padding ;
        int imageY = y + padding - 20;
        int imageW = w - 2 * padding;
        int imageH = h - 2 * padding;

        pen.drawRect(x, y, w, h);
        pen.drawImage(currentAnimation.nextImage(), imageX, imageY, imageW , imageH, null);
    }

  

}
