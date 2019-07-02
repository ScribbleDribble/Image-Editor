package sample.Shapes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sample.Frame.Drawable;

import javax.swing.text.html.ImageView;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 *
 * Canvas image
 *
 */

public class CImage implements Drawable {

    private File f;
    private Image image;

    public CImage(Image image) {
        this.image = image;
    }

    @Override
    public void draw(Canvas canvas) {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 100, 100, image.getWidth() * 2, image.getHeight() * 2);

    }
}
