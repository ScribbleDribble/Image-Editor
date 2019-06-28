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
        //Filter filter2 = new Brightness(image2, -30);
        ColourOver filter3 = new ColourOver(image, "red");

        Filter gamma = new Gamma(image, 7);

        gamma.adjustPixels();
        gamma.saveChanges();





    }
}
