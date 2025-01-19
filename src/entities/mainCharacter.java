package entities;
import java.awt.*;
import java.util.ArrayList;
import entities.Bullet;

import engine.*;


import engine.Character;

public class mainCharacter extends Character {

    private String name = "mc";
    private String folderName = "mainCharacter/";
    private int jumpCount = 0;
    private int maxJumps = 2;
    private ArrayList<Bullet> bullets;
    public int magSize = 20;
    public int ammo = 100;
    public long lastShotTime;
    public long shotCooldown;
    public Animation reloadLT;
    public Animation reloadRT;
    public boolean isShooting;
    public boolean isReloading;
    public boolean isMoving;

    public mainCharacter(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl" , 7, folderName, 7);
        this.moveRT = new Animation(name + "_wr" , 7, folderName, 7);
        this.idleLT = new Animation(name + "_il" , 7, folderName, 7);
        this.idleRT = new Animation(name + "_ir" , 7, folderName, 7);
        this.attackLT = new Animation(name + "_al", 4, folderName, 7);
        this.attackRT = new Animation(name + "_ar", 4, folderName, 7);
        this.reloadLT = new Animation(name + "_rdl", 13 , folderName, 7);
        this.reloadRT = new Animation(name + "_rdr", 13 , folderName, 7);
        this.isFacingRight = true;
        this.isAttacking = false;
        this.currentAnimation = whichIdle(isFacingRight);
        this.bullets = new ArrayList<>();
        this.isShooting = false;
        this.isReloading = false;
        this.isMoving = false;
        
        this.shotCooldown = 250;
        this.lastShotTime = 0;
        this.physicsEnabled = true;
    }
     public void goLT(int dx){
        if(!isAttacking){
            isFacingRight = false;
            isMoving = true;
            super.goLT(dx);
            currentAnimation = moveLT;
        }
    }
    public void goRT(int dx){
        if(!isAttacking){
            isFacingRight = true;
            isMoving = true;
            super.goRT(dx);
            currentAnimation = moveRT;
        }
    }

    public void goDN(int dy){
        if(!isAttacking){
            super.goDN(dy);
            currentAnimation = whichIdle(isFacingRight);
        }
    }

    public void rld(){
        if(!isAttacking && magSize < 20 && ammo > 0){
            isReloading = true;
            if(isFacingRight){
                currentAnimation = reloadRT;
                currentAnimation.reset();
            }
            else{
                currentAnimation = reloadLT;
                currentAnimation.reset();
            }
            
        }
    }

    public void jump(int h){
        if(!isAttacking && jumpCount < maxJumps){ 
            super.jump(h);
            // System.out.println("Jump count = " + jumpCount);
            currentAnimation = whichIdle(isFacingRight);
            jumpCount++;
        }
    }

    public void checkLanding(ArrayList <Rect> Walls) {
        for (Rect wall : Walls) {
            if (this.overlaps(wall) && this.cameFromAbove(wall)) {
                this.jumpCount = 0;
                break;  
            }
        }
    }

    
    public Animation whichIdle(Boolean directionIsRight){
        return directionIsRight ? idleRT : idleLT;
    }

    public void checkIdle(boolean leftPressed, boolean rightPressed) {
        if (!leftPressed && !rightPressed && !isAttacking) {
            currentAnimation = whichIdle(isFacingRight);
        }
    }
    @Override
    public void draw(Graphics pen){
        int padding = -35; // Adjust this value to control the padding
        int imageX = x + padding ;
        int imageY = y + padding - 35;
        int imageW = w - 2 * padding;
        int imageH = h - 2 * padding;

        pen.drawRect(x , y , w, h);
       
        if(currentAnimation.isComplete() && !isMoving && !isReloading && !isAttacking){
            currentAnimation = whichIdle(isFacingRight);
        }
        // else{
        //     pen.drawImage(currentAnimation.nextImage(), imageX , imageY, imageW , imageH, null);
        // }
            pen.drawImage(currentAnimation.nextImage(), imageX , imageY, imageW , imageH, null);

        
        for (Bullet bullet : bullets) {
                bullet.draw(pen);
        }
    }



    //public void update(boolean leftPressed, boolean rightPressed, Rect[] Walls){
    public void update(boolean leftPressed, boolean rightPressed){ 
        super.update();
        if(!isReloading){
            checkIdle(leftPressed, rightPressed);
        }
        if(currentAnimation.isComplete() && isReloading){
            reload();
            isReloading = false;
            currentAnimation = whichIdle(isFacingRight);
        }
        // if(currentAnimation.name.contains("al") || currentAnimation.name.contains("ar") && isAttacking && currentAnimation.getNext() == 2){
        //     shoot();
        // }
        // stats();
        // checkLanding(Walls);
        bulletsUpdate();
        // System.out.println("Player Coordinates  X: " + this.x + " Y: " + this.y);
    }

    
   

    public void attack(){
        currentAnimation = isFacingRight ? attackRT :  attackLT;
        if(magSize > 0){
            isAttacking = true;
            // currentAnimation.reset();
            shoot();    
            // stopAttack();
        }
    }

    public void stopAttack(){
        isAttacking = false;
        stopShooting();
        currentAnimation = whichIdle(isFacingRight);
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if(magSize > 0 && !isShooting && (currentTime - lastShotTime >= shotCooldown)){
        // if(magSize > 0 && isAttacking){
            int butlletVelocity = isFacingRight ? 10 : -10;
            int bulletX = isFacingRight ? x + w : x;
            bullets.add(new Bullet(bulletX, y + h / 3, butlletVelocity));
            magSize -= 1;
            lastShotTime = currentTime;
        }
    }

    public void stopShooting(){
        isAttacking = false;
        // isShooting = false;
    }

    private void bulletsUpdate(){
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (!bullet.isActive()) {
                bullets.remove(i);
                i--;
            }
        }
    }
    public void reload(){
            magSize = 20;
            ammo -= 20;
    }
    public void stats(){
        System.out.println("Player Health: " + health);
    }
    //Next work on bullets hitting and damaging enemies and reload deature to swap btwn mag and full amount
    
    
}
