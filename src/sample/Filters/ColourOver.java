package sample.Filters;


import java.awt.image.BufferedImage;

import java.io.IOException;



public class ColourOver extends Filter {

    private String colour;

    public ColourOver(BufferedImage img, String colour) throws IOException {
        super(img);
        this.colour = colour;
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

                switch (colour) {

                    case ("red"):
                        p = (r << 16) | (0 << 8) | 0;

                    case ("blue"):
                        p = (0 << 16) | (0 << 0) | b;

                    case ("green"):
                        p = (0 << 16) | (g << 8) | 0;

                    case ("yellow"):
                        p = (r << 16) | (g << 8) | 0;

                    case ("magenta"):
                        p = (r << 16) | (0 << 8) | b;

                    case ("cyan"):
                        p = (0 << 16) | (g << 8 ) | b;

                }

                img.setRGB(j, i, p);

            }
        }
    }

}
