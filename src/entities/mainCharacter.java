package entities;
import java.awt.*;
import engine.Animation;


import engine.Character;

public class mainCharacter extends Character {

    private String name = "mc";
    private String folderName = "mainCharacter/";
    public Animation attackLT;
    public Animation attackRT;
    
    

    public mainCharacter(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.moveLT = new Animation(name + "_wl" , 7, folderName);
        this.moveRT = new Animation(name + "_wr" , 7, folderName);
        this.idleLT = new Animation(name + "_il" , 7, folderName);
        this.idleRT = new Animation(name + "_ir" , 7, folderName);
        this.attackLT = new Animation(name + "_al", 4, folderName);
        this.attackRT = new Animation(name + "_ar", 4, folderName);
        this.isFacingRight = true;
        this.isAttacking = false;
        this.currentAnimation = whichIdle(isFacingRight);

    }
     public void goLT(int dx){
        if(!isAttacking){
         isFacingRight = false;
         super.goLT(dx);
        currentAnimation = moveLT;
        }
    }
    public void goRT(int dx){
        if(!isAttacking){
        isFacingRight = true;
        super.goRT(dx);
        currentAnimation = moveRT;
        }
    }
    public void goUP(int dy){
        if(!isAttacking){
        super.goUP(dy);
        currentAnimation = whichIdle(isFacingRight);
        }
    }
    public void goDN(int dy){
        if(!isAttacking){
        super.goDN(dy);
        currentAnimation = whichIdle(isFacingRight);
        }
    }

    public void jump(int h){
        if(!isAttacking){
        super.jump(h);
        currentAnimation = whichIdle(isFacingRight);
        }
    }

    public void attack(){
        isAttacking = true;
        //currentAnimation = attackLT;
        if(isFacingRight){
            currentAnimation = attackRT;
        }
        else{
            currentAnimation = attackLT;
        }
    }
    public void stopAttack(){
        isAttacking = false;
        currentAnimation = whichIdle(isFacingRight);
    }
    public Animation whichIdle(Boolean directionIsRight){
        if(directionIsRight){
            return idleRT;
        }
        else {
            return idleLT;
        }
    }

    public void checkIdle(boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {
        if (!upPressed && !downPressed && !leftPressed && !rightPressed && !isAttacking) {
            currentAnimation = whichIdle(isFacingRight);
        }
    }
    public void draw(Graphics pen){
        super.draw(pen);
    }

    public void update(boolean upPressed, boolean downPressed, boolean leftPressed, boolean rightPressed) {
        super.update();
        checkIdle(upPressed, downPressed, leftPressed, rightPressed);
    }

    

    
    
}
