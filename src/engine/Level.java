package engine;

import java.awt.*;
import java.awt.image.ImageObserver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import entities.*;
// import entities.hooligans.hooliganShotgun;

public abstract class Level {
    public ArrayList<Rect> platforms;
    public ArrayList<Enemies> entities;
    public ArrayList<Powerups> powerups;
    public Background backgrounds;
    private Clip backgroundClip;
    public Clip powerUpsGrabbed;
    public int WIDTH;
    public int HEIGHT;
    public Rect leftBarrier;
    public Rect rightBarrier;
    public Rect floor;
    public boolean levelCompleted;
    public boolean gameOver;




    public Level() {
        platforms = new ArrayList<>();
        entities = new ArrayList<>();
        powerups = new ArrayList<>();
        backgrounds = new Background();

        
        //Initialize Background
        //Initialize Enemies
        //Initialize Walls
        //loadLevelData();
    }

    public void addEntity(Enemies entity) {
        entities.add(entity);
    }

    public void removeEntity(Enemies entity) {
        entities.remove(entity);
    }

    public void addPowerup(Powerups power) {
        powerups.add(power);
    }

    public void removepower(Powerups power) {
        powerups.remove(power);
    }

    public void draw(Graphics g){
        backgrounds.draw(g);
        leftBarrier.draw(g);
        rightBarrier.draw(g);
        for (Rect platform : getPlatforms()) {
            drawPlatform(g, "src/assets/images/ScrollingCity/Platforms/Floor.png", platform.x, platform.y, platform.w, 32);

        }
        for (Enemies entity :  getEntities()){
            entity.draw(g);
        }

    }

    public void drawPlatform(Graphics g, String imagePath, int startX, int startY, int totalWidth, int tileHeight) {
        Image tile = Toolkit.getDefaultToolkit().getImage(imagePath);
        if (tile == null) {
            System.err.println("Error loading image: " + imagePath);
            return;
        }
        ImageObserver observer = new ImageObserver() {
            @Override
            public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                return (infoflags & ALLBITS) == 0;
            }
        };
        
        int tileWidth = tile.getWidth(observer);
        if (tileWidth == -1) {
            // System.err.println("Could not determine tile width for: " + imagePath);
            return;
        }

        for (int xOffset = 0; xOffset < totalWidth; xOffset += tileWidth) {
            g.drawImage(tile, startX + xOffset, startY, tileWidth, tileHeight, null);
        }
    }

    


    

    public void platformCollision(Rect r) {
        for (Rect platform : getPlatforms()) {
            if (r.overlaps(platform)) {
                r.pushedOutOf(platform);
            }
        }
        if (r.overlaps(leftBarrier)) {
                r.pushedOutOf(leftBarrier);
            }
        if (r.overlaps(rightBarrier)) {
                r.pushedOutOf(rightBarrier);
            }
        for (Enemies entity : getEntities()){
            for(Rect platform : getPlatforms())
                if((entity).overlaps(platform)){
                    entity.pushedOutOf(platform);
                }
        }
    }

 

    public void update(mainCharacter character) {
        if(entities == null) {
            return;
        }
        for (Enemies entity : entities) {
            entity.update(character, this);
        }
        applyPowerups(character);
    }

    public ArrayList<Rect> getPlatforms() {
        return platforms;
    }

    public ArrayList<Enemies> getEntities() {
        return entities;
    }

    public ArrayList<Powerups> getPowerups() {
        return powerups;
    }
    public void playClipOnce(Clip clip) {
        if (clip != null) {
            clip.stop(); // Stop the clip if it is already running
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start(); // Start playing the clip
        }
    }
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

    // public void playSound(Clip clip) {
    //     if (clip != null) {
    //         if (clip.isRunning()) {
    //             clip.stop();
    //         }
    //         clip.setFramePosition(0);
    //         clip.start();
    //     }
    // }
    public void playBackgroundMusic(String filePath) {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void applyPowerups(mainCharacter player){
        for (int i = 0; i < powerups.size(); i++) {
            Powerups powerup = powerups.get(i);
            if (player.overlaps(powerup)) {
                playClipOnce(powerUpsGrabbed);
                powerup.applyEffect(player, this);
                powerups.remove(i);
                i--; // Adjust the index after removal
            }
        }
    }

    public abstract void loadLevelData();
    public abstract void resetPlayer(mainCharacter player, int x, int y);
}