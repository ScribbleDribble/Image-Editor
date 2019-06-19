package sample.Filters;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Greyscale extends Filter {

    public Greyscale(BufferedImage img) throws IOException {
        super(img);
    }

    public void adjustPixels() {

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int p = img.getRGB(j, i);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int avg = (r + b + g) / 3;

                // or new rgb expression together
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(j, i, p);

            }
        }
    }

}
