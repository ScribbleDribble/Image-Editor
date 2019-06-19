package sample.Filters;

import sun.awt.image.ImageWatched;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) throws IOException {

        System.out.println("testing");
        File f = new File("family.jpg");

        LinkedList<Filter> filterList = new LinkedList<>();

        BufferedImage image = null;

        image = ImageIO.read(f);

        Filter filter = new Greyscale(image);
        Filter filter2 = new Brightness(image, 0);

        filter.adjustPixels();
        filter2.adjustPixels();

        filter.writeOver();

        System.out.println(((Greyscale) filter).getImg());
        System.out.println(((Brightness) filter2).getImg());

        //filter2.writeOver();


    }
}
