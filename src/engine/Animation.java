package engine;

import java.awt.*;


public class Animation
{
	private Image[] image;
	private int     next;
	
	private int duration;
	private int delay;

	private boolean isComplete;

    public String name;
	
	public Animation(String name, int count, String folderName, int duration)
	{
		this.image = new Image[count];
        this.name = name;
		
		for(int i = 0; i < count; i++){
            try{
                image[i] = Toolkit.getDefaultToolkit().getImage("src/assets/images/" + folderName + name + "_" + i + ".png");
                if(image[i] == null){
                    throw new Exception("Image is null");
                }
            }catch(Exception e){
                System.out.println("Error loading image: " + "src/assets/images/" + folderName + name + "_" + i + ".png");
            }
            }
		
		this.duration = duration;
		
		this.delay = duration;
		this.next = 0;
		this.isComplete = false;
	}
	
	
	public Image nextImage()
	{
		// if(isComplete)
		// {
		// 	return image[image.length - 1];
		// }
		if(delay == 0)
		{
			next++;
			
			if(next == image.length) {
				isComplete = true;
				next =  1;
			}
			
			delay = duration;
		}
		
		delay--;
		return image[next];
	}

	public boolean isComplete()
	{
		return isComplete;
	}

	public void reset()
	{
		next = 0;
		delay = duration;
		isComplete = false;
	}

	public int getNext(){
		return next;
	}

	public Image getLastImage() {
        return image[image.length - 1];
    }

}