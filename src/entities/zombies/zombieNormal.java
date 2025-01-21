package entities.zombies;

import engine.Character;
import entities.Enemies;
import entities.mainCharacter;
import engine.*;
import java.awt.*;
import javax.sound.sampled.*;


public class zombieNormal extends Enemies {
    private String name = "zn";
    private String folderName = "zombieNormal/";
    public zombieNormal(int x, int y, int w, int h, boolean isFacingRight) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 8, folderName, 7);
        this.moveRT = new Animation(name + "_wr", 8, folderName, 7);
        this.idleLT = new Animation(name + "_il", 8, folderName, 7);
        this.idleRT = new Animation(name + "_ir", 8, folderName, 7);
        this.attackLT = new Animation(name + "_al", 4, folderName, 12);
        this.attackRT = new Animation(name + "_ar", 4, folderName, 7);
        this.dying = new Animation(name + "_dd", 5 , folderName, 7);
        this.isFacingRight = isFacingRight;
        this.currentAnimation = idleAnimation(isFacingRight);
        this.detectionRange = 500;
        this.attackRange = 70;
        this.speed = 2;
        this.dieSound = loadSound("src/assets/sounds/normalZombie/normalZombieDead.wav");
        this.hurtSound = loadSound("src/assets/sounds/normalZombie/normalZombieHurt.wav");
        this.attackSound = loadSound("src/assets/sounds/normalZombie/normalZombieAttack.wav");
        this.health = 100;
        this.isDead = false;
        this.damage = 10;
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
        
        // if(currentAnimation.isComplete()){
            // character.takeDamage(damage);
        // }
    }
    public void stopAttack(mainCharacter character) {
        super.stopAttack(character);
        // currentAnimation = isFacingRight ? idleRT : idleLT;
        // damageApplied = false;
        // playIdleSound(idleSound);

    }

    @Override
    public void update(mainCharacter character, Level level) {
        super.update(character, level);
    //     if (character.isDead) {
    //         if (isAttacking) {
    //             stopAttack(character);
    //         }
    //         stopChasing(); 
    //         currentAnimation = idleAnimation(isFacingRight); 
    //     return; 
    // }
    //     if(!character.isDead){

    //         detectAndAttack(character, level);
            
    //         if (currentAnimation == attackLT || currentAnimation == attackRT) {
    //             if (!damageApplied && currentAnimation.getNext() == 1) {
    //                 character.takeDamage(damage);
    //                 damageApplied = true; 
    //                 System.out.println("Main character took " + damage + " damage. Health: " + character.health);
    //             }
    //             if (currentAnimation.isComplete()) {
    //                 damageApplied = false;
    //                 currentAnimation.reset();
    //             }
    //         }
    //     }
    //     // System.out.println("Current Animation " + currentAnimation.name);

    //     if(health <= 0 && !isDead){
    //         this.die();
    //     }
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

    public void detectAndAttack(mainCharacter character, Level level){
        super.detectAndAttack(character, level);
        // if (character.isDead) {
        //     // Stop any ongoing actions if the character is dead
        //     if (isAttacking) {
        //         stopAttack(character);
        //     }
        //     stopChasing();
        //     currentAnimation = idleAnimation(isFacingRight);
        //     return; // Exit early to prevent further detection or attacking
        // }
        // double distance = Math.sqrt(Math.pow(character.x - x, 2) + Math.pow(character.y - y, 2));

        
        // if(distance <= attackRange){
        //     if(!isAttacking){
        //         // System.out.println("Attack distance reached");
        //         isAttacking = true;
        //         stopChasing();
        //         attack(character);
        //     }
        // }
        // else if (distance <= detectionRange){
        //     setDirection(character);

        //     if(isAttacking){
        //         // System.out.println("Detection range reached");
        //         isAttacking = false;
        //         stopAttack(character);
        //     }
        //     chase(character, speed, level);
        // }
        // else
        // {
        //     if(isAttacking){
        //         isAttacking = false;
        //         stopAttack(character);
        //     }
        //     stopChasing();
        // }
    }
}
