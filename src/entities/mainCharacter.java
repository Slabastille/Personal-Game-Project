package entities;
import java.awt.*;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

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
    public int ammo = 20;
    public long lastShotTime;
    public long shotCooldown;
    public Animation reloadLT;
    public Animation reloadRT;
    public boolean isShooting;
    public boolean isReloading;
    public boolean isMoving;
    private SoundManager soundManager;
    private boolean canAttack;
    private Level currentLevel; 

    

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
        this.dying = new Animation(name + "_dd", 4 , folderName, 7);
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
        this.soundManager = new SoundManager();
        this.soundManager.loadSound("bullet", "src/assets/sounds/mainCharacter/newBullet.wav");
        this.soundManager.loadSound("reload", "src/assets/sounds/mainCharacter/mainCharacterReload.wav");
        this.dieSound = loadSound("src/assets/sounds/mainCharacter/mainCharacterDead.wav");
        this.hurtSound = loadSound("src/assets/sounds/mainCharacter/mainCharacterHurt.wav");
        this.isDead = false;
        this.canAttack = true;
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

    public void setCurrentLevel(Level currentLevel) {
        this.currentLevel = currentLevel;
    }

    public void rld(){

        if(!isAttacking && magSize < 20 && ammo > 0){
            soundManager.playSound("reload");
            isReloading = true;
            if(isFacingRight){
                currentAnimation = reloadRT;
                currentAnimation.reset();
            }
            else{
                currentAnimation = reloadLT;
                currentAnimation.reset();
            }
            // soundManager.stopSound("reload");

            
        }
    }

    public void jump(int h){
        if(!isAttacking && jumpCount < maxJumps){ 
            super.jump(h);
            currentAnimation = whichIdle(isFacingRight);
            jumpCount++;
        }
    }

    public void checkLanding(ArrayList <Rect> Walls) {
        boolean landed = false;
        for (Rect wall : Walls) {
            if (this.overlaps(wall) && this.cameFromAbove(wall)) {
                vy = 0;
                landed = true;
                break;  
            }
        }
        if(landed){
            this.jumpCount = 0;
            // System.out.println("Landed and reset jump count");
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
        int padding = -35; 
        int imageX = x + padding ;
        int imageY = y + padding - 35;
        int imageW = w - 2 * padding;
        int imageH = h - 2 * padding;

        pen.drawRect(x , y , w, h);
       
        if (isDead && currentAnimation.isComplete()) {
            pen.drawImage(currentAnimation.getLastImage(), imageX, imageY, imageW, imageH, null);
        }
        else if(currentAnimation.isComplete() && !isMoving && !isReloading && !isAttacking){
            currentAnimation = whichIdle(isFacingRight);
        }
        else{
            pen.drawImage(currentAnimation.nextImage(), imageX , imageY, imageW , imageH, null);
        }
        

        for (Bullet bullet : bullets) {
                bullet.draw(pen);
        }
    }



    public void update(boolean leftPressed, boolean rightPressed){ 
        super.update();
        // System.out.println("Health " +  this.health);

        if(isDead){
            if (currentAnimation.isComplete()) {
                currentAnimation.getLastImage();
            }
            return;
        }

        if(!isReloading && !isDead){
            checkIdle(leftPressed, rightPressed);
        }
        if(currentAnimation.isComplete() && isReloading && !isDead){
            reload();
            isReloading = false;
            soundManager.stopSound("reload");
            currentAnimation = whichIdle(isFacingRight);
        }

        if(currentAnimation.isComplete() && !isDead){
            currentAnimation.reset();
        }
        if(health <= 0 && !isDead){
            this.die();
        }
        bulletsUpdate();
    }

    
   

    public void attack(){
        if(canAttack && magSize > 0){
            soundManager.playSound("bullet");
            currentAnimation = isFacingRight ? attackRT :  attackLT;
            isAttacking = true;
            shoot();
            canAttack = false;
            soundManager.stopSound("bullet");

        }
        else{
            currentAnimation = whichIdle(isFacingRight);
        }
    }

    public void stopAttack(){
        isAttacking = false;
        stopShooting();
        currentAnimation = whichIdle(isFacingRight);
        canAttack = true;
    }

    public void shoot() {
        long currentTime = System.currentTimeMillis();
        if(magSize > 0 && !isShooting && (currentTime - lastShotTime >= shotCooldown)){
            int butlletVelocity = isFacingRight ? 10 : -10;
            int bulletX = isFacingRight ? x + w : x;
            bullets.add(new Bullet(bulletX, y + h / 3, butlletVelocity));
            magSize -= 1;
            lastShotTime = currentTime;
        }
    }

    public void stopShooting(){
        isAttacking = false;
        isShooting = false;
    }

    private void bulletsUpdate(){
        // for (int i = 0; i < bullets.size(); i++) {
        //     Bullet bullet = bullets.get(i);
        //     bullet.update();
        //     if (!bullet.isActive()) {
        //         bullets.remove(i);
        //         i--;
        //     }
        // }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.update();
            if (!bullet.isActive()) {
                bullets.remove(i);
                i--;
            } else {
                // Check for collision with enemies
                for (Enemies enemy : currentLevel.getEntities()) {
                    if (bullet.overlaps(enemy) && Math.abs(enemy.x - this.x) <= 700) {
                        enemy.takeDamage(20); // Assume each bullet does 10 damage
                        bullet.deactivate();
                        break;
                    }
                }
                // Check for collision with regular Rects
                for (Rect platform : currentLevel.getPlatforms()) {
                    if (bullet.overlaps(platform)) {
                        bullet.deactivate();
                        break;
                    }
                }
            }
        }
    }
    public void reload(){
            int addedAmmo = 20 - magSize;
            magSize += addedAmmo;
            ammo -= addedAmmo;

    }
    public void stats(){
        System.out.println("Player Health: " + health);
    }
    public void takeDamage(int damage){
        health -= damage;
        playSound(this.hurtSound);

    }

    public void die() {
        if(!isDead){
            currentAnimation = dying;
            if(currentAnimation.isComplete()){
                isDead = true;
            }
            playSound(dieSound);
        }
    }

    //Next work on bullets hitting and damaging enemies and reload deature to swap btwn mag and full amount
    
    
    
}
