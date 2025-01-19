package engine;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import engine.*;
import entities.*;
// import entities.hooligans.hooliganShotgun;

public abstract class Level {
    public ArrayList<Rect> walls;
    public ArrayList<Character> entities;
    public Background backgrounds;
    private Clip backgroundClip;
    public int WIDTH;
    public int HEIGHT;
    public Rect leftBarrier;
    public Rect rightBarrier;

    public Level() {
        walls = new ArrayList<>();
        entities = new ArrayList<>();
        backgrounds = new Background();
        //Initialize Background
        //Initialize Enemies
        //Initialize Walls
        //loadLevelData();
    }

    public void addEntity(Character entity) {
        entities.add(entity);
    }

    public void removeEntity(Character entity) {
        entities.remove(entity);
    }


    public void draw(Graphics g) {
        // for (Rect wall : walls) {
        //     g.setColor(Color.RED);
        //     wall.draw(g);
        // }
        // for (Character entity : entities) {
        //     entity.draw(g);
        // }

       
    }

    

    public void wallCollision(Rect r) {
        for (Rect wall : getWalls()) {
            if (r.overlaps(wall)) {
                r.pushedOutOf(wall);
            }
        }
        if (r.overlaps(leftBarrier)) {
                r.pushedOutOf(leftBarrier);
            }
        if (r.overlaps(rightBarrier)) {
                r.pushedOutOf(rightBarrier);
            }
    }

 

    public void update() {
        if(entities == null) {
            return;
        }
        for (Character entity : entities) {
            entity.update();
        }
    }

    public ArrayList<Rect> getWalls() {
        return walls;
    }

    public ArrayList<Character> getEntities() {
        return entities;
    }

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

    public abstract void loadLevelData();
}