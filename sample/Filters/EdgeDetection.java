package sample.Filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EdgeDetection extends Filter{

    private Mat dest;
    private String mode;
    private File f;

    public EdgeDetection(String mode, File f, BufferedImage img) throws IOException {
        super(img);
        this.mode = mode;
        this.f = f;
    }

    @Override
    public void adjustPixels() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        Imgcodecs imgcodecs = new Imgcodecs();

        String filename = f.getAbsolutePath();
        Mat matrix = imgcodecs.imread(filename);

        dest = new Mat();

        if (this.mode == "sobel")
            Imgproc.Sobel(matrix, dest, -1, 1, 0);

        else if (this.mode == "canny")
            Imgproc.Canny(matrix, dest, 60, 60 * 3);

    }


    // write new image to buffered image. beforehand, this was already done using the adjustPixels method
    @Override
    public void writeOver() {
        Imgcodecs.imwrite("C:/Users/m1cah/Java Projects/Image-Editor-/Out.jpg", dest);

        try {
            File out = new File("Out.jpg");
            ImageIO.read(out);
        }

        catch (Exception e)
        {
            System.out.println("File not found");
        }

    }

    @Override
    public void saveChanges(){
        Imgcodecs.imwrite("C:/Users/m1cah/Java Projects/Image-Editor-/OutFinal.jpg", dest);
    }

}
