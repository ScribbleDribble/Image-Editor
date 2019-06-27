package sample;



import java.awt.image.BufferedImage;
import javafx.scene.image.Image;

public class Model {

    // we could possibly have an observable list here of filters that displays all the finalised changes the user has
    // ...made and allow them the choice to undo them visually

    private Image image;
    private BufferedImage bufferedImage;


    public void setImage(Image image) {
        this.image = image;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public Image getImage() {
        return image;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}