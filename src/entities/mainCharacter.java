package entities;
import java.awt.*;
import engine.Animation;


import engine.Character;

public class mainCharacter extends Character {

    private String name = "mc";
    private String folderName = "mainCharacter/";
    public mainCharacter(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 7, folderName);
        this.moveRT = new Animation(name + "_wr", 7, folderName);
        this.idle = new Animation(name + "_il", 7, folderName);
        currentAnimation = moveLT;
    }
     public void moveLT(int dx){
        super.moveLT(dx);
        currentAnimation = moveLT;
    }
    public void moveRT(int dx){
        super.moveRT(dx);
        currentAnimation = moveRT;
    }
    public void moveUP(int dy){
        super.moveUP(dy);
    }
    public void moveDN(int dy){
        super.moveDN(dy);
        currentAnimation = idle;
    }

    
    
}
