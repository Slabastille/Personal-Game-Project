package entities.zombies;

import engine.Character;
import engine.Animation;
import engine.*;
import java.awt.*;

public class zombieFemale extends Character {
    private String name = "zf";
    private String folderName = "zombieFemale/";
    public zombieFemale(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl", 7, folderName);
        this.moveRT = new Animation(name + "_wr", 7, folderName);
        //this.idle = new Animation(name + "_il", 10);
        currentAnimation = moveRT;
    }
    public void goLT(int dx){
        super.goLT(dx);
        currentAnimation = moveLT;
    }
    public void goRT(int dx){
        super.goRT(dx);
        currentAnimation = moveRT;
    }
    public void goUP(int dy){
        super.goUP(dy);
    }
    public void goDN(int dy){
        super.goDN(dy);
        //currentAnimation = idle;
    }
    public void update() {
        
    }

    // public void draw(Graphics pen){
    //     pen.drawImage(currentAnimation.nextImage(), x, y, null);
    // }

  

}
