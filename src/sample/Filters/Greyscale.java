package sample.Filters;

import java.io.File;

public class Greyscale {


    public Greyscale() {

    }

    public Greyscale(File f, int width, int height) {
        super(f, width, height);

    }

    public void adjustPixels() {


        for (int i = 0; i < super.height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int p = image.getRGB(j, i);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                int avg = (r + b + g)/ 3;

                // or new rgb expression together
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                img.setRGB(j, i, p);

            }
        }
    }

}
