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
    private Level currentLevel;
    public mainCharacter Zack;
    // public Rect Zack;


    public Game() {
        loadCustomFont();
        setLevel("levelOne");
        Zack = new mainCharacter(200, 100, 100, 100);
        // Zack = new Rect (200, 100, 100, 100);
        Zack.health = 100;

    }

    public void setLevel(String levelName) {
        if (levelName.equals("levelOne")) {
            currentLevel = new levelOne();
        } 
         
    }
    
    
    

    @Override 
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.WHITE);
        currentLevel.draw(g);
        if (Zack != null) {
            Zack.draw(g);
        }
        drawHUD(g);
        Camera.draw(g);
    }

    public void run(){
        super.run();

    }

    @Override
    public void updateGame() {
        // Zack.physicsOff();


        if (LT_PRESSED) {
            System.out.println("LT_PRESSED");
            Camera.moveLT(5);
            Zack.goLT(2);
        }
        if (RT_PRESSED) {
            System.out.println("RT_PRESSED");
            Camera.moveRT(5);
            Zack.goRT(2);
        }
        if (UP_PRESSED) {
            System.out.println("UP_PRESSED");
        }
        if (DN_PRESSED) {
            System.out.println("DN_PRESSED");
            Zack.goDN(5);
        }
        if(SB_PRESSED){
            System.out.println("SB PRESSED");
            Zack.jump(10);
            SB_PRESSED = false;
        }
        if(RR_PRESSED){
            System.out.println("RR PRESSED");
            Zack.rld();
            // Zack.reload();
            // RR_PRESSED = false;
        }
        
        
        


        // for (Rect wall : currentLevel.getWalls()) {
        //     for(Character character : AllCharacters){
        //         if(character.overlaps(wall)){
        //             character.pushedOutOf(wall);
        //         }
        //     }
        // }


        for (Rect wall : currentLevel.getWalls()) {
            if (Zack.overlaps(wall)) {
                Zack.pushedOutOf(wall);
            }
        }

        

        currentLevel.wallCollision(Zack);
        Zack.update(LT_PRESSED, RT_PRESSED);
        Zack.checkLanding(currentLevel.getWalls());
        

        currentLevel.update();
        // System.out.println("Zack Location  " + Zack.x + "  , " + Zack.y);

    }

    @Override
    public void onAttackStart(){
        Zack.attack();

    }

    @Override
    public void onAttackStop() {
        Zack.stopAttack();
    }
    
    private void loadCustomFont() {
        try {
            // Load the custom font from a file
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/fonts/bloodlustacadital.ttf")).deriveFont(40f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            customFont = new Font("Arial", Font.PLAIN, 40); // Fallback font
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