package sample.Frame;

import sun.awt.image.ImageWatched;

import javafx.scene.canvas.Canvas;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.LinkedList;

public class Picture {

    private LinkedList<Drawable> collection = new LinkedList<>();

    public void add(Drawable drawable) {
        collection.add(drawable);
    }

    public void remove(Drawable drawable) {
        collection.removeFirstOccurrence(drawable);
    }

    public void drawPicture(Canvas canvas) {

        for (Drawable drawable : collection) {
            drawable.draw(canvas);
        }

    }


}
