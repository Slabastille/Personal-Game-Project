package engine;

import java.awt.*;

public class Animation {

    private Image[] image;
    private int next;

    //Image naming scheme
    //character.action.index
    public Animation(String name ,  int count, String folderName){

        image = new Image[count];

        for(int i = 0; i < count; i++){
            //src/assets/images/mainCharacter/mc_ar_1.png
                image[i] = Toolkit.getDefaultToolkit().getImage("src/assets/images/" + folderName + name + "_" + i + ".png");
            }
        next = -1;
    }
    

    public Image nextImage(){
        next++;
        if(next >= image.length){
            next = 0;
        }
        System.out.println("Next Image");
        System.out.println(next);
        return image[next];
    }
}
