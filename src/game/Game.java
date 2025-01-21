package game;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import engine.*;
import engine.Character;
import entities.*;
import entities.zombies.*;
import entities.hooligans.*;
import engine.*;

public class Game extends GamePanel
{
    private Font customFont;
    public Level currentLevel;
    public int spawnX;
    public int spawnY;
    // public Rect Zack;


    public Game() {
        loadCustomFont();
        setLevel("levelOne");
        Zack = new mainCharacter(15  , 100, 100, 100);
        // Zack = new mainCharacter(4650  , 100, 100, 100);
        // Zack = new mainCharacter(620  , 100, 100, 100);
        // Zack = new mainCharacter(3270  , 100, 100, 100);

        Zack.health = 100;
        Zack.setCurrentLevel(currentLevel);
    }

    public void setLevel(String levelName) {
        if (levelName.equals("levelOne")) {
            currentLevel = new levelOne();
        } 
        
    }

    public void resetGame() {
        setLevel("levelOne");
        Zack = new mainCharacter(15, 100, 100, 100);
        // Zack = new mainCharacter(4890  , 100, 100, 100);

        Zack.health = 100;
        Zack.setCurrentLevel(currentLevel);
        if (currentLevel instanceof levelOne) {
        }
    }
    
    @Override 
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-Camera.x, -Camera.y);

        currentLevel.draw(g2d);
        if (Zack != null) {
            Zack.draw(g2d);
        }
        
        g2d.translate(Camera.x, Camera.y);
        
        drawHUD(g2d);
        Camera.draw(g2d);
    }

    public void run(){
        super.run();

    }

    @Override
    public void updateGame() {
        if (currentLevel.gameOver) {
                resetGame();
                return; 
        }

        // Zack.physicsOff();

        if (LT_PRESSED) {
            // System.out.println("LT_PRESSED");
            Zack.goLT(5);
        }
        if (RT_PRESSED) {
            // System.out.println("RT_PRESSED");
            Zack.goRT(5);
        }
        // if (UP_PRESSED) {
        //     System.out.println("UP_PRESSED");
        //     Zack.goUP(5);
        // }
        // if (DN_PRESSED) {
        //     System.out.println("DN_PRESSED");
        //     Zack.goDN(5);
        // }
        if(SB_PRESSED){
            System.out.println("SB PRESSED");
            Zack.jump(10);
            SB_PRESSED = false;
        }
        if(RR_PRESSED){
            System.out.println("RR PRESSED");
            Zack.rld();
        }

        if(QQ_PRESSED){
            currentLevel.resetPlayer(Zack, spawnX, spawnY);
        }


        //Development purposes only
        if(N1_PRESSED){
            spawnX = 2650;
            spawnY = 100;
        }
        if(N2_PRESSED){
            spawnX = 6000;
            spawnY = 100;
        }
        if(N3_PRESSED){
            spawnX = 3270;
            spawnY = 100;
        }


        
        // Update camera position to follow the player
    
        
        


        
        
        
        
        
        currentLevel.platformCollision(Zack);
        Camera.shift(Zack, 800, 600, currentLevel);
        Zack.update(LT_PRESSED, RT_PRESSED);
        Zack.checkLanding(currentLevel.getPlatforms());
        currentLevel.update(Zack);
        
        // System.out.println("Zack Location  " + Zack.x + "  , " + Zack.y);
        // System.out.println("Camera dimensions : " + Camera.x + " , " + Camera.y);

    }

    @Override
    public void onAttackStart(){
        Zack.attack();
    }

    @Override
    public void onAttackStop(){
        Zack.stopAttack();
    }
    
    public void loadCustomFont() {
        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/bloodlustacadital.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); 
        }
    }

    public void drawHUD( Graphics g){
        int healthBarWidth = (int) (200 * (Zack.health / 100.0));
        g.setFont(customFont);
        g.setColor(Color.RED);
        g.drawString("SOLDIER X", 10, 30);
        g.drawString("PROLOGUE", 620, 30);
        g.drawString("SCAR :  " + Zack.magSize + " / " + Zack.ammo, 620, 65);
        g.setColor(Color.GRAY);
        g.fillRect(10, 40, 200, 25);
        g.setColor(Color.RED);
        g.fillRect(10, 40, healthBarWidth, 25);   
    }
	

}