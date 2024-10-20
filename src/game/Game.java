package game;
import java.awt.Graphics;
import java.awt.*;
import java.awt.Toolkit;

import engine.Rect;

public class Game extends GamePanel
{
    Image Scene1 = Toolkit.getDefaultToolkit().getImage("src/assets/images/sceneOne/sceneOne.png");
	Rect r1 = new Rect(200, 200, 50, 30);
	Rect r2 = new Rect(300, 20, 75, 30);
	Rect[] Walls = {
            new Rect (10, 10, 700, 100),  //top wall
            new Rect (10, 20, 30, 550),   //left wall
            new Rect (700, 200, 1500, 30),//right wall
            new Rect (10, 550, 700, 30),  //bottom wall
        };
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        g.drawImage(Scene1, 0, 0, null);
        g.setColor(Color.WHITE);
        r1.draw(g);
        r2.draw(g);
        for(Rect wall : Walls){
            wall.draw(g);
        }
        
    }

    public void run(){
        super.run();
    }


	// public void paint(Graphics pen)
	// {
    //     r1.draw(pen);
    //     r2.draw(pen);
	
	// }
	
	



    @Override
    public void updateGame() {
        
        
        if (LT_PRESSED) {
            System.out.println("LT_PRESSED");
            r1.moveLT(5);
        }
        if (RT_PRESSED) {
            System.out.println("RT_PRESSED");
            r1.moveRT(5);
        }
        if (UP_PRESSED) {
            System.out.println("UP_PRESSED");
            r1.moveUP(5);
        }
        if (DN_PRESSED) {
            System.out.println("DN_PRESSED");
            r1.moveDN(5);
        }
        
        for (Rect wall : Walls) {
            if(r1.overlaps(wall)){
                r1.pushOutOf(wall);
            }
            if(r2.overlaps(wall)){
                r2.pushOutOf(wall);
            }
        }

        r2.chase(r1);
    }


	
}