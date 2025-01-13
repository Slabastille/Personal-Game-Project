// package entities.hooligans;

// import engine.Character;
// import entities.Enemies;
// import engine.Animation;
// import engine.*;
// import java.awt.*;

// public class hooliganBat extends Enemies {

//     private String name = "hb";
//     private String folderName = "hooliganBat/";

//     public hooliganBat(int x, int y, int w, int h, boolean isFacingRight) {
//         super(x, y, w, h);
//         this.moveLT = new Animation(name + "_wl", 7, folderName);
//         this.moveRT = new Animation(name + "_wr", 7, folderName);
//         this.idleLT = new Animation(name + "_il", 6, folderName);
//         this.idleRT = new Animation(name + "_ir", 6, folderName);
//         this.attackLT = new Animation(name + "_al", 12, folderName);
//         this.attackRT = new Animation(name + "_ar", 12, folderName);
//         this.isFacingRight = isFacingRight;
//         this.currentAnimation = idleAnimation(isFacingRight);
//         this.detectionRange = 100;
//     }
//     public void goLT(int dx){
//         super.goLT(dx);
//         currentAnimation = moveLT;
//     }
//     public void goRT(int dx){
//         super.goRT(dx);
//         currentAnimation = moveRT;
//     }
    
//     public void update() {
//         super.update();
//     }
//     public void attack() {
//         super.attack();
//         currentAnimation = isFacingRight ? attackRT : attackLT;
//     }
//     public void stopAttack() {
//         super.stopAttack();
//         currentAnimation = isFacingRight ? idleRT : idleLT;
//     }
    
//     public void draw(Graphics pen) {
//         super.draw(pen);
//     }

    
  

// }
