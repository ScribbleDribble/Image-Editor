package sample;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Model {

    // we could possibly have an observable list here of filters that displays all the finalised changes the user has
    // ...made and allow them the choice to undo them visually

    private Image image;
    private BufferedImage bufferedImage;

    public Image getImage() {
        return image;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}