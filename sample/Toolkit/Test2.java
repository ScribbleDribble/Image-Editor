package sample.Toolkit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Test2 {

    public static void main(String[] args) throws IOException {

        File f = new File("OutFinal.jpg");

        BufferedImage bufferedImage = ImageIO.read(f);

        double red, green, blue;

        red = 205;
        green = 51;
        blue =  51;

        int colour = (int) red << 16 | (int) green << 8 | (int) blue ;
        int newCol =  0 << 16 | 250 << 8 | 0;

        PaintBucket pb = new PaintBucket(bufferedImage);
        pb.floodFill(338, 97, colour, newCol);

        try {

            File out = new File("OutFinal.jpg");
            ImageIO.write(bufferedImage, "jpg", out);
        }

        catch(IOException e) {
            System.out.println(e);
        }

    }

}
