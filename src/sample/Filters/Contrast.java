package sample.Filters;


import java.awt.image.BufferedImage;
import java.io.IOException;

public class Contrast extends Filter {

    private int contrast_value;

    public Contrast(BufferedImage img, int contrast_value) throws IOException {
        super(img);
        this.contrast_value = contrast_value;
    }

    public void adjustPixels() {

        int width = img.getWidth();
        int height = img.getHeight();
        int r, g, b;
        double adjusted_r = 0, adjusted_g = 0, adjusted_b = 0;

        //contrast correction factor
        double factor;

        factor =(double) (259 * (contrast_value + 255))/ (double)(255 * (259 - contrast_value));

        System.out.println(factor);

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                int p = img.getRGB(j, i);

                r = p >> 16 & 0xff;
                g = p >> 8 & 0xff;
                b = p & 0xff;

                adjusted_r = Colour.colourBoundaryCheckDouble(  factor * (r - 128) + 128);
                adjusted_g = Colour.colourBoundaryCheckDouble(  factor * (g - 128) + 128);
                adjusted_b = Colour.colourBoundaryCheckDouble( factor * (b - 128) + 128);

                img.setRGB(j, i, (int) adjusted_r << 16 | (int) adjusted_g << 8 | (int) adjusted_b);

            }
        }
    }

}
