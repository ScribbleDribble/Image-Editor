package sample.Filters;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) throws IOException {


        File f = new File("family.jpg");

        BufferedImage image = ImageIO.read(f);


        //Filter filter = new Brightness(image, 0);

        Filter filter = new EdgeDetection("canny", f, image);
        filter.adjustPixels();
        filter.saveChanges();

    }
}
