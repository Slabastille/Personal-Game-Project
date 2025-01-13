// package entities.hooligans;

// import engine.Character;
// import entities.Enemies;
// import engine.Animation;
// import engine.*;
// import java.awt.*;

// public class hooliganRifle extends Enemies {
//     private String name = "hr";
//     private String folderName = "hooliganRifle/";
//     public hooliganRifle(int x, int y, int w, int h, boolean isFacingRight) {
//         super(x, y, w, h);
//         this.moveLT = new Animation(name + "_wl", 7, folderName);
//         this.moveRT = new Animation(name + "_wr", 7, folderName);
//         this.idleLT = new Animation(name + "_il", 8, folderName);
//         this.idleRT = new Animation(name + "_ir", 8, folderName);
//         this.attackLT = new Animation(name + "_al", 4, folderName);
//         this.attackRT = new Animation(name + "_ar", 4, folderName);
//         this.isFacingRight = isFacingRight;
//         this.currentAnimation = idleAnimation(isFacingRight);
//         this.detectionRange = 250;
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

    
  

// }
