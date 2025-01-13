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
        floor = new Rect(50 , 565 , 4000, 20);
        
        
        loadLevelData();
    }



    public void loadLevelData() {
        for (int i = 1; i < 9; i++) {
            layer1Images.add("src/assets/images/ScrollingCity/BuildingsLyr1/ScrollCityBuilding1" + i + ".png");
            layer2Images.add("src/assets/images/ScrollingCity/BuildingsLyr2/ScrollCityBuilding2" + i + ".png");
            layer3Images.add("src/assets/images/ScrollingCity/BuildingsLyr3/ScrollCityBuilding3" + i + ".png");
        
        }
  

        //Initialize background
        if(layer1Images != null || layer2Images != null || layer3Images != null) {
            backgrounds.addLayer(layer3Images, 0, 75, 3);
            backgrounds.addLayer(layer2Images, 0, 75, 2);
            backgrounds.addLayer(layer1Images, 0, 75, 1);
        }
        

        
        // Add walls for platforms
         addWall(new Rect(350  , 450 , 200, 20)); // Example platform
        //  addWall(floor); // Example platform
        // addWall(new Rect(50 - Camera.x, 565 - Camera.y, 200 ,20));
        //  addWall(new Rect(800 , 400 , 200, 20)); // Example platform
        // addWall(new Rect(700, 200, 200, 20)); // Example platform

        // // Add enemies
        // addEntity(new hooliganShotgun(350, 100, 100, 100, false));
        // playBackgroundMusic("src/assets/sounds/Rob Dougan - Clubbed to Death (Kurayamino Variation) [ ezmp3.cc ].wav");
        addWall(floor);
    }

    public void draw(Graphics g) {
        super.draw(g);
        // drawPlatform(g, "src/assets/images/ScrollingCity/Platforms/Floor.png", 60, 565, 200, 20);
        g.setColor(Color.PINK);
        floor.draw(g);
        
    }

    private void addBuilding(int x, int y) {
        // Add building walls (example)
        addWall(new Rect(x, y, 800, 600)); // Main building structure
        addWall(new Rect(x + 100, y + 500, 600, 100)); // Roof
    }

    private void addWall(Rect wall) {
        getWalls().add(wall);
    }

    private void drawPlatform(Graphics g, String Image, int x, int y, int width, int height) {
        Image img = Toolkit.getDefaultToolkit().getImage(Image);
        //g.drawImage(img, x, y, width, height, null);
        if(img != null){
          
            for(int i = 0; i < width; i += img.getWidth(null)){
                g.drawImage(img, x + i - Camera.x, y - Camera.y, width, height, null);
            }
        }
    }
    
}
