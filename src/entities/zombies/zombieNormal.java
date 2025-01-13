// package entities.zombies;

// import engine.Character;
// import entities.Enemies;
// import engine.Animation;
// import engine.*;
// import java.awt.*;

// public class zombieNormal extends Enemies {
//     private String name = "zn";
//     private String folderName = "zombieNormal/";
//     public zombieNormal(int x, int y, int w, int h, boolean isFacingRight) {
//         super(x, y, w, h);
//         this.moveLT = new Animation(name + "_wl", 8, folderName);
//         this.moveRT = new Animation(name + "_wr", 8, folderName);
//         this.idleLT = new Animation(name + "_il", 8, folderName);
//         this.idleRT = new Animation(name + "_ir", 8, folderName);
//         this.attackLT = new Animation(name + "_al", 4, folderName);
//         this.attackRT = new Animation(name + "_ar", 4, folderName);
//         this.isFacingRight = isFacingRight;
//         this.currentAnimation = idleAnimation(isFacingRight);
//         this.detectionRange = 70;
//     }
//     public void goLT(int dx){
//         super.goLT(dx);
//         currentAnimation = moveLT;
//     }
//     public void goRT(int dx){
//         super.goRT(dx);
//         currentAnimation = moveRT;
//     }
//     public void attack() {
//         super.attack();
//         currentAnimation = isFacingRight ? attackRT : attackLT;
//     }
//     public void stopAttack() {
//         super.stopAttack();
//         currentAnimation = isFacingRight ? idleRT : idleLT;
//     }
//     public void update() {
//         super.update();
//     }

//     public void draw(Graphics pen){
//         // super.draw(pen);
//         int padding = -20; // Adjust this value to control the padding
//         int imageX = x + padding ;
//         int imageY = y + padding - 20;
//         int imageW = w - 2 * padding;
//         int imageH = h - 2 * padding;

//         pen.drawRect(x, y, w, h);
//         pen.drawImage(currentAnimation.nextImage(), imageX, imageY, imageW , imageH, null);
//     }

    
  

// }
