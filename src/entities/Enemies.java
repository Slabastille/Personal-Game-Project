package entities;
import java.awt.*;
import engine.Character;
import engine.Animation;

public class Enemies extends Character {

    public int detectionRange;

    public Enemies(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void attack(){}
    
    public void stopAttack(){}

    public void detectAndAttack(Character mainCharacter){
        double distance = Math.sqrt(Math.pow(mainCharacter.x - x, 2) + Math.pow(mainCharacter.y - y, 2));

        if (distance <= detectionRange)
        {
            isAttacking = true;
            attack();
        }
        else
        {
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
            System.out.println("Facing Right " + isFacingRight + " right");
        }
        else{
            System.out.println("Facing Left " + isFacingRight + " right");
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
    }

    public void draw(Graphics pen){
        super.draw(pen);
    }

    

}
