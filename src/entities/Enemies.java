package entities;
import java.awt.*;
import engine.Character;
import engine.Animation;

public class Enemies extends Character {

    public int detectionRange;

    public Enemies(int x, int y, int w, int h) {
        super(x, y, w, h);
    }
    public void goLT(int dx){
        if(!isAttacking){            
            super.goLT(dx);
        }

    }
    public void goRT(int dx){
        if(!isAttacking){            
            super.goRT(dx);
        }
    }
    public void chase(Character main, int dx){
        if(!isAttacking){
            super.chase(main, dx);
        }
    }

    public void attack(){}
    
    public void stopAttack(){}

    public void detectAndAttack(Character mainCharacter){
        double distance = Math.sqrt(Math.pow(mainCharacter.x - x, 2) + Math.pow(mainCharacter.y - y, 2));

        if (distance <= detectionRange)
        {
            System.out.println("Attack distance reached");
            isAttacking = true;
            attack();
        }
        else
        {
            System.out.println("Attack distance not reached yet: " + distance);
            isAttacking = false;
            stopAttack();
        }
    }
    public void facingDirection(Character mainCharacter){
        this.isFacingRight = mainCharacter.x > this.x;
    }


    public void setDirection(Character mainCharacter){
        //Add checker to detect the maincharacter before getting the enemy to change directions here
        this.isFacingRight = mainCharacter.x > this.x;
        //this.currentAnimation = isFacingRight ? idleRT : idleLT;
        if (isFacingRight){
            // System.out.println("Facing Right " + isFacingRight + " right");
        }
        else{
            // System.out.println("Facing Left " + isFacingRight + " right");
        }
    }

    public void setIdle(){
        if(!isAttacking){
            this.currentAnimation = isFacingRight ? idleRT : idleLT;
        }
    }

    public void update(mainCharacter character){
            super.update();
            setDirection(character);
            setIdle();
            detectAndAttack(character);
    }

    public void draw(Graphics pen){
        super.draw(pen);
    }

    public Animation idleAnimation(boolean isFacingRight){
        if(isFacingRight){
            return idleRT;
        }
        else {
            return idleLT;
        }
    }

    

}
