package entities;
import java.awt.*;
import java.util.ArrayList;
import engine.Rect;
import engine.Camera;
import javax.sound.sampled.*;

import engine.Level;

public class levelOne extends Level {
    private ArrayList<String> layer1Images;
    private ArrayList<String> layer2Images;
    private ArrayList<String> layer3Images;
    public Rect floor;
    

    public levelOne() {
        super();
        layer1Images = new ArrayList<>();
        layer2Images = new ArrayList<>();
        layer3Images = new ArrayList<>();
        
        
        loadLevelData();
    }



    public void loadLevelData() {

        WIDTH = 6392;
        HEIGHT = 600;

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
        
        
        
        // Add walls for platforms
        

        // // Add enemies
        // addEntity(new hooliganShotgun(350, 100, 100, 100, false));
        
        
        addWall(460, 436, 256 ); 
        addWall(655, 304, 96);
        // addWall(new Rect(550 , 550 , 500 , 20));
        // addWall(new Rect(276, 512, 5, 30));

        //Left barrier
        leftBarrier = new Rect(-10, 0, 10, 620);
        
        //Floor
        addWall(0 , 568 , 6392);
        
        //Right barrier
        rightBarrier = new Rect(6392, 0, 10, 620);
        
        // playBackgroundMusic("src/assets/sounds/Rob Dougan - Clubbed to Death (Kurayamino Variation) [ ezmp3.cc ].wav");
    }
    @Override
    public void draw(Graphics g) {
        // super.draw(g);
        // drawPlatform(g, "src/assets/images/ScrollingCity/Platforms/Floor.png", 60, 565, 200, 20);
        backgrounds.draw(g);
        leftBarrier.draw(g);
        rightBarrier.draw(g);

        g.setColor(Color.PINK);
        // floor.draw(g);
        for (Rect wall : getWalls()) {
            // drawWall(g, wall);
            // wall.draw(g);
            drawPlatform(g, "src/assets/images/ScrollingCity/Platforms/Floor.png", wall.x, wall.y, wall.w, 32);

        }
        
    }
    public void update(){
        super.update();
        // for (Rect wall : getWalls()){
        //     wall.x = wall.x - Camera.x;
        //     wall.y = wall.y - Camera.y;
        // }
    }


    public void addWall(int x, int y, int w) {
        getWalls().add(new Rect (x, y, w, 32));
    }



    private void drawPlatform(Graphics g, String imagePath, int startX, int startY, int totalWidth, int tileHeight) {
        
        Image tile = Toolkit.getDefaultToolkit().getImage(imagePath);

        if (tile == null) {
            System.err.println("Error loading image: " + imagePath);
            return;
        }

        int tileWidth = tile.getWidth(null);
        if (tileWidth <= 0) {
            System.err.println("Could not determine tile width for: " + imagePath);
            return;
        }

        for (int xOffset = 0; xOffset < totalWidth; xOffset += tileWidth) {
            g.drawImage(tile, startX + xOffset, startY, tileWidth, tileHeight, null);
        }
}

    
}
