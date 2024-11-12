package game;
import java.awt.*;

import engine.*;
import engine.Character;
import engine.Rect;
import entities.*;
import entities.zombies.*;
import entities.hooligans.*;
import engine.*;

public class Game extends GamePanel
{
    Image Scene1 = Toolkit.getDefaultToolkit().getImage("src/assets/images/sceneOne/sceneOne.png");
    // Image Scene1 = Toolkit.getDefaultToolkit().getImage("src/assets/images/mainCharacter/mc_ar_0.png");
	Rect[] Walls = {
            //new Rect (10, 10, 700, 100),  //top wall
            new Rect (0, 0, 10, 600),   //left wall
            //new Rect (700, 200, 1500, 30),//right wall
            new Rect (10, 550, 800, 30),  //bottom wall
        };


    mainCharacter Zack = new mainCharacter(300, 100, 100, 100);
    

    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);


        //g.drawImage(Scene1, 0, 0, null);
        //g.setColor(Color.WHITE);
        
        Zack.draw(g);
         

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
            Zack.goLT(5);
        }
        if (RT_PRESSED) {
            System.out.println("RT_PRESSED");
            Zack.goRT(5);
        }
        if (UP_PRESSED) {
            System.out.println("UP_PRESSED");
            Zack.goUP(5);
        }
        if (DN_PRESSED) {
            System.out.println("DN_PRESSED");
            Zack.goDN(5);
        }
        if(ZZ_PRESSED){
            System.out.println("ZZ PRESSED");
            Zack.attack();
        }
        if(SB_PRESSED){
            System.out.println("SB PRESSED");
            Zack.jump(5);
        }
        
        // Zack.sayHello();
        for (Rect wall : Walls) {
            if(Zack.overlaps(wall)){
                Zack.pushedOutOf(wall);
                
            }
        }
        
        Zack.update(UP_PRESSED, DN_PRESSED, LT_PRESSED, RT_PRESSED);
        // Zack.physicsOff();
        


    }

    public void onAttackStart(){
        Zack.attack();
    }

    @Override
    public void onAttackStop() {
        Zack.stopAttack();
    }
    

	
}