package sample.Filters;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Brightness extends Filter{

    private int brightness;

    public Brightness(BufferedImage img, int brightness) throws IOException {
        super(img);
        this.brightness = brightness;
    }

    public BufferedImage getImage() {
        return getImg();
    }
    /*
    public Brightness(File f) {
        this.f = f;
    }
    */
    public void adjustPixels() {

        int r, g, b, adjusted_r, adjusted_g, adjusted_b;

        for (int i = 0; i < super.height; i++)
        {
            for (int j = 0; j < super.width; j++)
            {
                int p = img.getRGB(j, i);


                r = p >> 16 & 0xff;
                g = p >> 8 & 0xff;
                b = p & 0xff;


                adjusted_r = Colour.colourBoundaryCheck( r + brightness);
                adjusted_g = Colour.colourBoundaryCheck( g + brightness);
                adjusted_b = Colour.colourBoundaryCheck( b + brightness);

                img.setRGB(j, i, adjusted_r << 16 | adjusted_g << 8 | adjusted_b);

                //end

            }
        }

    }

}
