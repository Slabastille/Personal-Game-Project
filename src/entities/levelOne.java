package entities;
import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import engine.Rect;
import entities.zombies.crazyZombie;
import entities.zombies.zombieFemale;
import entities.zombies.zombieNormal;
import game.AnnouncementOverlay;
import game.Game;
import entities.hooligans.hooliganBat;
import entities.hooligans.hooliganRifle;
import entities.hooligans.hooliganShotgun;
import engine.Camera;
import javax.sound.sampled.*;
import engine.Character;

import engine.Level;
import engine.Powerups;

public class levelOne extends Level {
    private ArrayList<String> layer1Images;
    private ArrayList<String> layer2Images;
    private ArrayList<String> layer3Images;
    private boolean surpriseHooliganBat;
    private boolean surpriseHordeFirstFlag;
    private boolean surpriseHordeSecondFlag;
    private boolean weMeanBusiness;
    private AnnouncementOverlay announcementOverlay;
    


    

    public levelOne() {
        super();
        layer1Images = new ArrayList<>();
        layer2Images = new ArrayList<>();
        layer3Images = new ArrayList<>();
        surpriseHooliganBat = false;
        surpriseHordeFirstFlag = false;
        surpriseHordeSecondFlag = false;
        weMeanBusiness = false;
        announcementOverlay = new AnnouncementOverlay();
        gameOver = false;
        powerUpsGrabbed = loadSound("src/assets/sounds/powerups/powerupPickup.wav");
        loadLevelData();
    }



    public void loadLevelData() {
        WIDTH = 6392;
        HEIGHT = 600;

        //Initialize Layers
        for (int i = 1; i < 9; i++) {
            layer1Images.add("src/assets/images/ScrollingCity/BuildingsLyr1/ScrollCityBuilding1" + i + ".png");
            layer2Images.add("src/assets/images/ScrollingCity/BuildingsLyr2/ScrollCityBuilding2" + i + ".png");
            layer3Images.add("src/assets/images/ScrollingCity/BuildingsLyr3/ScrollCityBuilding3" + i + ".png");
        }
        //Initialize background
        if(layer1Images != null || layer2Images != null || layer3Images != null) {
            backgrounds.addLayer(layer3Images, 0, 100, 1);
            backgrounds.addLayer(layer2Images, 0, 100, 1);
            backgrounds.addLayer(layer1Images, 0, 100, 1);
        }

        // Add Powerups
        addPowerup(new Powerups(885, 240, 25 , 25, "ammo"));
        addPowerup(new Powerups(6325, 375, 50, 50, "checkpoint"));
        addPowerup(new Powerups(4650, 130, 50, 50, "ammo"));
        addPowerup(new Powerups(4725, 130, 50, 50, "health"));


        

        // // Add enemies
        // addEntity(new hooliganShotgun(350, 100, 100, 100, false));å
        addEntity(new zombieNormal   (620, 430, 100, 100, false));
        addEntity(new zombieNormal   (620, 473, 100, 100, false));
        addEntity(new crazyZombie    (1351, 343, 100, 100, false));
        addEntity(new zombieFemale   (1620, 430, 100, 100, true));
        addEntity(new zombieFemale   (1745, 430, 100, 100, true));
        addEntity(new zombieFemale   (1870, 430, 100, 100, false));
        addEntity(new zombieFemale   (1995, 430, 100, 100, false));
        addEntity(new hooliganBat(3550, 345, 100, 100, false));
        addEntity(new hooliganBat  (3850, 345, 100 , 100, false));
        addEntity(new crazyZombie    (5985, 328, 100, 100, false));
        addEntity(new hooliganBat(5702, 471, 100, 100, false));


        
        
        
        addPlatform(460,  436, 256 ); 
        addPlatform(850,  304, 96);
        addPlatform(1300, 440, 100);
        addPlatform(3500, 440, 100);
        addPlatform(3800, 460, 100);
        
        addPlatform(4651, 206, 100);
        addPlatform(4851, 356, 100);

        addPlatform(5250, 356, 100);
        addPlatform(5378, 388, 50);
        addPlatform(5442, 420, 50);
        

        

        addPlatform(6000, 425, 50);
        addPlatform(6300, 440, 80);

        //Left barrier
        leftBarrier = new Rect(-10, 0, 10, 620);
        
        //Floor (0 - 6392)
        //floor 1
        addPlatform(0 , 568 , 705);
        //floor 2
        addPlatform(870, 568, 255);
        //floor 3
        addPlatform(1620, 568, 500);
        //floor 4 
        addPlatform(2300, 568, 1000);
        //floor 5
        addPlatform(4300, 568, 1500);



        //Right barrier
        rightBarrier = new Rect(6392, 0, 10, 620);
        
    }
    public void draw(Graphics g) {
        super.draw(g);
        for (Powerups power : getPowerups()){
            power.draw(g);
        }
        

        g.setColor(Color.PINK);
        // floor.draw(g);
        announcementOverlay.draw(g);
                
    }
    public void update(mainCharacter character){
        super.update(character);
        gameOver(character);
        // for (Rect wall : getWalls()){
        //     wall.x = wall.x - Camera.x;
        //     wall.y = wall.y - Camera.y;
        // }
        if(character.x >= 2700 && !surpriseHooliganBat){
            addEntity(new hooliganBat(2300 , 430, 100, 100, true));
            addEntity(new hooliganBat(2450, 430 , 100, 100, true));
            addEntity(new hooliganBat(2950, 430 , 100, 100, false));
            addEntity(new hooliganBat(3100, 430 , 100, 100, false));
            surpriseHooliganBat = true;
            announcementOverlay.announce("ZOMBIES ARENT YOUR ONLY PROBLEM !!!!", 5000, 2350, 250, 80);
        }
        unleashHorde(character);

        Iterator<Enemies> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Enemies entity = iterator.next();
            if (entity.isDead && entity.currentAnimation == entity.dying && entity.currentAnimation.isComplete()) {
                iterator.remove();
            }
            

        }
        if(character.isDead && character.currentAnimation == character.dying && character.currentAnimation.isComplete()){
            character.currentAnimation.getLastImage();
        }

        if(character.x >= 1625 && !weMeanBusiness){
            weMeanBusiness = true;
            announcementOverlay.announce("WE MEAN BUSINESS !!!!", 3000, 1650, 400, 80);
        }
        
    }


    public void addPlatform(int x, int y, int w) {
        getPlatforms().add(new Rect (x, y, w, 32));
    }

    

    

    public void resetPlayer(mainCharacter player, int x, int y){
        player.x = x;
        player.y = y;
    }

    public void unleashHorde(mainCharacter character){
        if(character.x >= 5375){
            surpriseHordeFirstFlag = true;
        }
        if(surpriseHordeFirstFlag && character.x <= 5250 && !surpriseHordeSecondFlag){
            addEntity(new zombieNormal(5100, 468, 100, 100  , true));
            addEntity(new zombieNormal(4900, 468, 100, 100  , true));
            addEntity(new zombieNormal(4700, 468, 100, 100  , true));
            addEntity(new zombieNormal(4500, 468, 100, 100  , true));
            addEntity(new zombieNormal(4300, 468, 100, 100  , true));
            surpriseHordeSecondFlag = true;
        }
        
    }

    public void gameOver(mainCharacter character) {
        if (character.health <= 0 && !gameOver) {
            announcementOverlay.announce("Game Over", 20000, 1625, 350, 130); 
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    gameOver = true; 
                }
            }, 20000);
        }
    }



    
}
