package sample.Model;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import sample.Frame.Drawable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class PictureModel {

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

    public void printList() {
        System.out.println(collection);
    }


    public void savePicture(BufferedImage bufferedImage) throws IOException {

        File outFinal = new File("OutFinal.jpg");
        ImageIO.write(bufferedImage, "jpg", outFinal);
    }


}
