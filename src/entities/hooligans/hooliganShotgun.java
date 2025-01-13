// package entities.hooligans;

// import engine.Character;
// import entities.mainCharacter;
// import engine.Animation;
// import entities.Enemies;
// import engine.*;
// import java.awt.*;
// import java.awt.geom.AffineTransform;


// public class hooliganShotgun extends Enemies 
// {
//     private String name = "hs";
//     private String folderName = "hooliganShotgun/";
    

//     public hooliganShotgun(int x, int y, int w, int h, boolean isFacingRight) {
//         super(x, y, w, h);
//         this.moveLT = new Animation(name + "_wl", 8, folderName);
//         this.moveRT = new Animation(name + "_wr", 8, folderName);
//         this.idleLT = new Animation(name + "_il", 6, folderName);
//         this.idleRT = new Animation(name + "_ir", 6, folderName);
//         this.attackLT = new Animation(name + "_al", 12, folderName);
//         this.attackRT = new Animation(name + "_ar", 12, folderName);
//         this.isFacingRight = isFacingRight;
//         this.currentAnimation = idleLT;
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


//     public void attack(){
//         currentAnimation = isFacingRight ? attackRT : attackLT;
//     }
    
//     public void stopAttack(){
//         currentAnimation = isFacingRight ? idleRT : idleLT;
//     }

//     public void update(){
//         super.update();
//     }

//     public void draw(Graphics pen) {
//             // super.draw(pen);
        
//         if(isAttacking){
//             int padding = -35; // Adjust this value to control the padding
//             int shift = 35;

//             int imageX = isFacingRight ? x + padding + shift : x + padding - shift;
//             int imageY = y + padding - 35;
//             int imageW = w - 2 * padding;
//             int imageH = h - 2 * padding;

//             pen.drawRect(x, y, w, h);
//             pen.drawImage(currentAnimation.nextImage(), imageX, imageY, imageW , imageH, null);




//         }
//         else{
//             super.draw(pen);
//         }

//     }
    


    
  

// }
