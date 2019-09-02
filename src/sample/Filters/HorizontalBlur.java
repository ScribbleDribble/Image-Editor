package sample.Filters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class HorizontalBlur extends Filter {

    int blurAmount;

    public HorizontalBlur(BufferedImage bufferedImage, int blurAmount) throws IOException {
        super(bufferedImage);
        this.blurAmount = blurAmount;
    }


    @Override
    public void adjustPixels() {

        BufferedImage bufferedImage = super.img;
        int r, g, b, redC, greenC, blueC;
        float r2, g2, b2;


        for (int y = 0; y < bufferedImage.getHeight(); y++)
            for (int x = 0; x < bufferedImage.getWidth(); x++)
            {


                int col = bufferedImage.getRGB(x, y);

                r = col >> 16 & 0xff;
                g = col >> 8 & 0xff;
                b = col & 0xff;

                r = r / 2;
                g = g / 2;
                b = b / 2;


                if (x  < bufferedImage.getWidth() - 1)
                {
                    int colRight = bufferedImage.getRGB(x+1, y);

                    r2 = (float) r;
                    g2 = (float) g;
                    b2 = (float) b;

                    redC = colRight >> 16 & 0xff;
                    greenC = colRight >> 8 & 0xff;
                    blueC = colRight & 0xff;

                    for (int i = 0; i < blurAmount; i++) {
                        r2 += redC * (0.5 / blurAmount);
                        g2 += greenC * (0.5 / blurAmount);
                        b2 += blueC * (0.5 / blurAmount);
                    }

                    bufferedImage.setRGB(x, y, (int) r2 << 16 | (int) g2 << 8 | (int) b2);
                }

                bufferedImage.setRGB(x, y, r << 16 | g << 8 | b);
            }

    }

}
