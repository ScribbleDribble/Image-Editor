package sample.Filters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Blur extends Filter{

    final double[][] boxBlurKernel = {{1/25.0, 1/25.0, 1/25.0, 1/25.0 ,1/25.0},
                                {1/25.0, 1/25.0, 1/25.0, 1/25.0, 1/25.0},
                                {1/25.0, 1/25.0, 1/25.0, 1/25.0, 1/25.0},
                                {1/25.0, 1/25.0, 1/25.0, 1/25.0 ,1/25.0},
                                {1/25.0, 1/25.0, 1/25.0, 1/25.0, 1/25.0},
                                };

    final double[][] gaussianBlur = { {1/256.0, 4/256.0, 6/256.0, 4/256.0, 1/256.0},
                                {4/256.0, 16/256.0, 24/256.0, 16/256.0, 4/256.0},
                                {6/256.0, 24/256.0, 36/256.0, 24/256.0, 6/256.0},
                                {4/256.0, 16/256.0, 24/256.0, 16/256.0, 4/256.0},
                                {1/256.0, 4/256.0, 6/256.0, 4/256.0, 1/256.0}
                                };

    String mode;
    //convolution result
    BufferedImage newImage;

    public Blur(BufferedImage bufferedImage, BufferedImage copyImage, String kernel) throws IOException {
        super(bufferedImage);
        this.newImage = copyImage;
        this.mode = kernel;
    }

    @Override
    public void adjustPixels() {

        int width = super.img.getWidth();
        int height = super.img.getHeight();
        BufferedImage bufferedImage = super.img;
        int rgb, r, g, b;
        double r2, g2, b2;

        double redSum = 0, greenSum = 0, blueSum = 0;

        int tempX, tempY;

        final double[][] kernel;

        if (mode == "gaussian")
        {
            kernel = gaussianBlur;
        }
        else {
            kernel = boxBlurKernel;
        }

        for(int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
            {

                if (x >= 1 && x < width - 3 && y >= 1 && y < height - 3)
                {
                    tempX = x-1;
                    tempY = y-1;

                    for (int i = 0; i < kernel.length; i++)
                    {
                        for (int j = 0; j < kernel.length; j++) {


                            rgb = bufferedImage.getRGB(tempX, tempY);
                            r = rgb >> 16 & 0xff;
                            g = rgb >> 8 & 0xff;
                            b = rgb & 0xff;

                            r2 = r * kernel[i][j];
                            g2 = g * kernel[i][j];
                            b2 = b * kernel[i][j];

                            redSum += r2;
                            greenSum += g2;
                            blueSum += b2;

                            tempY++;
                        }
                        tempX++;
                        tempY -= kernel.length;
                    }

                    newImage.setRGB(x, y, (int)redSum << 16 | (int)greenSum << 8 |(int) blueSum);

                    redSum = 0;
                    greenSum = 0;
                    blueSum = 0;
                }

                else {
                    newImage.setRGB(x, y, bufferedImage.getRGB(x, y));
                }

            }
        super.img = newImage;
    }
}


