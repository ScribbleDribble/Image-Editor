package sample.Filters;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) throws IOException {


        File f = new File("family.jpg");

        LinkedList<Filter> filterList = new LinkedList<>();

        BufferedImage image = null;

        image = ImageIO.read(f);
        BufferedImage image2 = ImageIO.read(f);

        Filter filter = new Greyscale(image);
        Filter filter2 = new Brightness(image2, -30);
        Filter filter3 = new ColourOver(image, "cyan");

        filter2.adjustPixels();
        filter2.writeOver();

       // Filter filter60 = new Gamma(image2, 2.2);

        //filter3.adjustPixels();
       //ilter3.writeOver();

/*
        filter.adjustPixels();
        filter.writeOver();
*/



    }
}
