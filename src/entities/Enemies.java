package entities;
import java.awt.*;

import javax.sound.sampled.Clip;

import engine.Character;
import engine.Level;
import engine.Animation;
import engine.Rect;

public class Enemies extends Character {

    public int detectionRange;
    public int attackRange;
    public int speed;
    private int originalX;
    private int originalY;
    public int damage;
    public boolean damageApplied;
    

    public Enemies(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.physicsEnabled = true;
        this.originalX = x;
        this.originalY = y;
        this.damageApplied = false;

    }
    public void goLT(int dx){
        if(!isAttacking){            
            super.goLT(dx);
            isFacingRight = false;
            currentAnimation = moveLT;
        }

    }
    public void goRT(int dx){
        if(!isAttacking){            
            super.goRT(dx);
            isFacingRight = true;
            currentAnimation = moveRT;
        }
    }

    public boolean isPlatformAhead(int direction, Level level) {
        int checkX = (direction > 0) ? this.x + this.w : this.x - 1;
        Rect platformBelow = new Rect(checkX, this.y + this.h + 1, 1, 1);

        for (Rect platform : level.getPlatforms()) {
            if (platform.overlaps(platformBelow)) {
                return true;
            }
        }
        return false;
    }

    public void chase(Character main, int speed, Level level){
        if(!isAttacking && Math.abs(main.y - this.y) <= 100){
            if (main.x < this.x && isPlatformAhead(-1 , level)) {
                goLT(speed);
            } else if (main.x > this.x && isPlatformAhead(1 , level)) {
                goRT(speed);
            } else {
                stopChasing();
                returnToOriginalPosition(level);

            }
        }
        else {
            stopChasing();
            returnToOriginalPosition(level);

        }
    }

    public void stopChasing(){
        this.vx = 0;
        this.vy = 0;
        this.currentAnimation = idleAnimation(isFacingRight);
    }

    public void attack(mainCharacter character){
        isAttacking = true;
        playSound(attackSound);
        currentAnimation = isFacingRight ? attackRT : attackLT;
    }
    
    public void stopAttack(mainCharacter character){
        currentAnimation = isFacingRight ? idleRT : idleLT;
        damageApplied = false;
    }

    public void detectAndAttack(mainCharacter character, Level level){
        // double distance = Math.sqrt(Math.pow(character.x - x, 2) + Math.pow(character.y - y, 2));

        
        // if(distance <= attackRange)
        // {
        //     // System.out.println("Attack distance reached");
        //     isAttacking = true;
        //     attack(character);
        // }
        // else
        // {
        //     // System.out.println("Attack distance not reached yet: " + distance);
        //     isAttacking = false;
        //     stopAttack(character);
        // }

        if (character.isDead) {
            // Stop any ongoing actions if the character is dead
            if (isAttacking) {
                stopAttack(character);
            }
            stopChasing();
            currentAnimation = idleAnimation(isFacingRight);
            return; // Exit early to prevent further detection or attacking
        }
        double distance = Math.sqrt(Math.pow(character.x - x, 2) + Math.pow(character.y - y, 2));

        
        if(distance <= attackRange){
            if(!isAttacking){
                // System.out.println("Attack distance reached");
                isAttacking = true;
                stopChasing();
                attack(character);
            }
        }
        else if (distance <= detectionRange){
            setDirection(character);

            if(isAttacking){
                // System.out.println("Detection range reached");
                isAttacking = false;
                stopAttack(character);
            }
            chase(character, speed, level);
        }
        else
        {
            if(isAttacking){
                isAttacking = false;
                stopAttack(character);
            }
            stopChasing();
        }
    }
    public void facingDirection(Character mainCharacter){
        this.isFacingRight = mainCharacter.x > this.x;
    }


    public void setDirection(Character mainCharacter){
        //Add checker to detect the maincharacter before getting the enemy to change directions here
        this.isFacingRight = mainCharacter.x > this.x;
        //this.currentAnimation = isFacingRight ? idleRT : idleLT;
        if (isFacingRight){
            // System.out.println("Facing Right " + isFacingRight + " right");
        }
        else{
            // System.out.println("Facing Left " + isFacingRight + " right");
        }
    }

    public void setIdle(){
        if(!isAttacking){
            this.currentAnimation = isFacingRight ? idleRT : idleLT;
        }
    }

    public void update(mainCharacter character, Level level){
            super.update();
            setIdle();
            if (character.isDead) {
            if (isAttacking) {
                stopAttack(character);
            }
            stopChasing(); 
            currentAnimation = idleAnimation(isFacingRight); 
        return; 
    }
        if(!character.isDead){

            detectAndAttack(character, level);
            
            if (currentAnimation == attackLT || currentAnimation == attackRT) {
                if (!damageApplied && currentAnimation.getNext() == 1) {
                    character.takeDamage(damage);
                    damageApplied = true; 
                    System.out.println("Main character took " + damage + " damage. Health: " + character.health);
                }
                if (currentAnimation.isComplete()) {
                    damageApplied = false;
                    currentAnimation.reset();
                }
            }
        }
        // System.out.println("Current Animation " + currentAnimation.name);

        if(health <= 0 && !isDead){
            this.die();
        }
    }

    public void draw(Graphics pen){
        super.draw(pen);
    }

    public Animation idleAnimation(boolean isFacingRight){
        if(isFacingRight){
            return idleRT;
        }
        else {
            return idleLT;
        }
    }

        public void returnToOriginalPosition(Level level) {
        if (Math.abs(this.x - originalX) > speed) {
            if (this.x > originalX && isPlatformAhead(-1, level)) {
                goLT(speed);
            } else if (this.x < originalX && isPlatformAhead(1, level)) {
                goRT(speed);
            } else {
                stopChasing();
            }
        } else {
            stopChasing();
        }
    }

    public boolean shouldBeRemoved() {
        return this.health <= 0; 
    }

    

    

}
