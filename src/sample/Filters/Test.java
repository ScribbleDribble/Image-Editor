package sample.Filters;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Test {

    public static void main(String[] args) throws IOException {


        File f = new File("test.png");
        System.out.println(f.exists());
        BufferedImage image = ImageIO.read(f);
        BufferedImage imageCopy = ImageIO.read(f);

        Filter bl = new Blur(image, imageCopy, "gaussian");
        bl.adjustPixels();
        bl.saveChanges();

        //Filter filter = new EdgeDetection("canny", f, image);

        //filter.adjustPixels();
        //filter.saveChanges();

    }
}
