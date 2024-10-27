package entities.hooligans;

import engine.Character;
import engine.Animation;
import engine.*;
import java.awt.*;

public class hooliganBat extends Character {
    private String name = "hb";
    private String folderName = "hooliganBat/";
    public hooliganBat(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 7, folderName);
        this.moveRT = new Animation(name + "_wr", 7, folderName);
        //this.idle = new Animation(name + "_il", 10);
        currentAnimation = moveRT;
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
        //currentAnimation = idle;
    }

    
  

}
