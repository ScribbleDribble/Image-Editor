package sample.Filters;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public abstract class Filter {

    private int width;
    private int height;
    private File f;
    private BufferedImage img;

    public Filter(File f, int width, int height) {
        this.f = f;
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void adjustPixels() {}

    public void writeOver() {
        try {

            File out = new File("Out.jpg");

            ImageIO.write(img, "jpg", f);
        }

        catch(IOException e) {
            System.out.println(e);
        }
    }



}