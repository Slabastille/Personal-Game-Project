package engine;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Character extends Rect{

    public Animation animation;
    public Animation moveLT;
    public Animation moveRT;
    public Animation jump;
    public Animation idleLT;
    public Animation idleRT;
    public Animation attackLT;
    public Animation attackRT;
    public Animation currentAnimation;
    public Animation dying;
    public boolean isFacingRight;
    public boolean isAttacking;
    public boolean isDead;
    public int health;
    public Clip attackSound;
    public Clip dieSound;
    public Clip hurtSound;
    

    public Character(int x, int y, int w, int h) {
        super(x, y, w, h);
        health = 100;

    }

    public void goLT(int dx){
        super.goLT(dx);
    }
    public void goRT(int dx){
        super.goRT(dx);
    }
    public void goUP(int dy){
        super.goUP(dy);
    }
    public void goDN(int dy){
        super.goDN(dy);
    }

    // public void draw(Graphics pen){
    //    super.draw(pen);
    // }

    public Clip loadSound(String filepath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filepath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playSound(Clip clip) {
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    @Override
    public void draw(Graphics pen){
        int padding = -35; // Adjust this value to control the padding
        int imageX = x + padding ;
        int imageY = y + padding - 35;
        int imageW = w - 2 * padding;
        int imageH = h - 2 * padding;

        pen.drawRect(x, y, w, h);
        pen.drawImage(currentAnimation.nextImage(), imageX, imageY, imageW , imageH, null);
        // pen.drawImage(currentAnimation.nextImage(), x, y, w, h, null);
    }

    public void update(){
        this.move();
        // playIdleSound(idleSound);
    }

    public void playIdleSound(Clip idleSound) {
        if (idleSound != null) {
            if (idleSound.isRunning()) {
                idleSound.stop();
            }
            idleSound.setFramePosition(0);
            idleSound.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            System.err.println("Idle sound clip is null");
        }
    }

    public void takeDamage(int damage) {
        playSound(this.hurtSound);
        health -= damage;
        if (health <= 0) {
            playSound(dieSound);
            die();
        }
    }


    public void die() {
        if (attackSound != null && attackSound.isRunning()) {
            attackSound.stop();
        }
        // if (idleSound != null && idleSound.isRunning()) {
        //     idleSound.stop();
        // }
        if(!isDead){
            System.out.println("Current sound is on" + (dieSound == null) );
            currentAnimation = dying;
            if(currentAnimation.isComplete()){
                isDead = true;
            }
        }
        // if (isDead) {
        //     currentAnimation.nextImage(); // Advance dying animation
        // }
        // Play die sound
        // Implement death logic here (e.g., remove from game)
    }
    

    
}
