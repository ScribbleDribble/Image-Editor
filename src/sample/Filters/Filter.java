package sample.Filters;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;


public abstract class Filter {

    protected int width;
    protected int height;
    protected BufferedImage img;

    public Filter() {}

    public Filter(BufferedImage img) throws IOException {

        this.img = img;
        this.height = img.getHeight();
        this.width = img.getWidth();
    }

    public BufferedImage getImg() {
        return img;
    }

    public void adjustPixels() {}

    public void writeOver() {
        try {

            File out = new File("Out.jpg");
            ImageIO.write(img, "jpg", out);
        }

        catch(IOException e) {
            System.out.println(e);
        }
    }



}