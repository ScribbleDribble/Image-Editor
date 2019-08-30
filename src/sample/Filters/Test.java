package sample.Filters;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Test {

    public static void main(String[] args) throws IOException {


        File f = new File("red.jpg");
        System.out.println(f.exists());
        BufferedImage image = ImageIO.read(f);
        BufferedImage imageCopy = ImageIO.read(f);

        Filter flip = new FlipHorizontal(image, imageCopy);

        flip.adjustPixels();
        flip.saveChanges();
        //Filter filter = new EdgeDetection("canny", f, image);

        //filter.adjustPixels();
        //filter.saveChanges();

    }
}
