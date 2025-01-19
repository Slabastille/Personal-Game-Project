package engine;
import java.awt.*;
import java.util.ArrayList;

public class Background {
    private ArrayList<ImageLayer> layers;

    public Background() {
        layers = new ArrayList<ImageLayer>();
    }

    public void addLayer(ArrayList<String> filenames, int x, int y, int z) {
        layers.add(new ImageLayer(filenames, x, y, z));
    }

    public void draw(Graphics pen) {
        for (ImageLayer layer : layers) {
            layer.draw(pen);
            // System.out.println("layer dimensions  X : " + layer.x + " Y : " + layer.y);
        }
    }
}