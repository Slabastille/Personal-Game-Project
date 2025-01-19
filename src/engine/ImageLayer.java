package engine;
import java.awt.*;
import java.util.ArrayList;

public class ImageLayer {
    private ArrayList<Image> images;
    int x;
    int y;
    int z;

    public ImageLayer(ArrayList<String> filenames, int x, int y, int z) {
        images = new ArrayList<>();

        for (String filename : filenames) {
            Image img = Toolkit.getDefaultToolkit().getImage(filename);
            if (img == null) {
                System.err.println("Error loading image: " + filename);
            } else {
                images.add(img);
            }
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void draw(Graphics pen) {
        for (int i = 0; i < images.size(); i++) {
            pen.drawImage(images.get(i), x + i * 800 / z, y / z, 800, 525, null);
            // System.out.println("layer dimensions  X : " + x + " Y : " + y);
        }
    }
}