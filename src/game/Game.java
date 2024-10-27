package game;
import java.awt.Graphics;
import java.awt.*;
import java.awt.Toolkit;
import engine.*;
import engine.Character;
import entities.*;
import entities.zombies.*;
import entities.hooligans.*;
import engine.*;

public class Game extends GamePanel
{
    //Image Scene1 = Toolkit.getDefaultToolkit().getImage("src/assets/images/sceneOne/sceneOne.png");
    Image Scene1 = Toolkit.getDefaultToolkit().getImage("src/assets/images/mainCharacter/mc_ar_0.png");
	Rect r1 = new Rect(200, 200, 50, 30);
	Rect r2 = new Rect(300, 20, 75, 30);
	Rect[] Walls = {
            new Rect (10, 10, 700, 100),  //top wall
            new Rect (10, 20, 30, 550),   //left wall
            new Rect (700, 200, 1500, 30),//right wall
            new Rect (10, 550, 700, 30),  //bottom wall
        };

    //Animation mainCharacter = new Animation("mc_ar", 4);

    mainCharacter Zack = new mainCharacter(300, 100, 50, 50);
    crazyZombie joe = new crazyZombie(600, 100, 50, 50);
    zombieFemale jane = new zombieFemale(400, 100, 50, 50);
    zombieNormal chris = new zombieNormal(400, 150, 50, 50);
    hooliganBat Tony = new hooliganBat(400, 150, 50, 50);
    hooliganRifle Tommy = new hooliganRifle(480, 150, 50, 50);
    hooliganShotgun Remy = new hooliganShotgun(200, 150, 50, 50);
    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);

        //g.drawImage(mainCharacter.nextImage(),100,100,null);
        Zack.draw(g);
        joe.draw(g);
        jane.draw(g);
        chris.draw(g);
        Tony.draw(g);
        Tommy.draw(g);
        Remy.draw(g);
        g.drawImage(Scene1, 0, 0, null);
        //g.setColor(Color.WHITE);
        r1.draw(g);
        r2.draw(g);
        for(Rect wall : Walls){
            wall.draw(g);
        }

        
    }

    public void run(){
        super.run();
    }

    @Override
    public void updateGame() {
        
        
        if (LT_PRESSED) {
            System.out.println("LT_PRESSED");
            Zack.moveLT(5);
        }
        if (RT_PRESSED) {
            System.out.println("RT_PRESSED");
            Zack.moveRT(5);
        }
        if (UP_PRESSED) {
            System.out.println("UP_PRESSED");
            Zack.moveUP(5);
        }
        if (DN_PRESSED) {
            System.out.println("DN_PRESSED");
            Zack.moveDN(5);
        }
        
        for (Rect wall : Walls) {
            if(Zack.overlaps(wall)){
                Zack.pushOutOf(wall);
            }
            if(r2.overlaps(wall)){
                r2.pushOutOf(wall);
            }
        }

        r2.chase(Zack);
        joe.chase(Zack);
        jane.chase(Zack);
        chris.chase(Zack);
        Tony.chase(Zack);
        Tommy.chase(Zack);
        Remy.chase(Zack);
    }


	
}