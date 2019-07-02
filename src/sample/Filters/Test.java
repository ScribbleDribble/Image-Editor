package sample.Filters;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Test {

    public static void main(String[] args) throws IOException {


        File f = new File("OutFinal.png");

        BufferedImage image = null;

        image = ImageIO.read(f);

        Filter filter = new Brightness(image, 0);

        filter.adjustPixels();
        filter.writeOver();


    }
}
