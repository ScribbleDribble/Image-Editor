package sample.Filters;



import javax.imageio.ImageIO;
import javax.swing.text.html.ImageView;
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

    // for testing purposes
    public BufferedImage getImg() {
        return img;
    }

    // algorithm for a specific filter, to be implemented
    public void adjustPixels() {}

    // writes image adjustment on output file. To be done each time the user wants to preview an image
    public void writeOver() {
        try {

            File out = new File("Out.jpg");
            ImageIO.write(img, "jpg", out);
        }

        catch(IOException e) {
            System.out.println(e);
        }
    }

    public void saveChanges() throws IOException {

        File outFinal = new File("OutFinal.jpg");
        ImageIO.write(img, "jpg", outFinal);

    }


}