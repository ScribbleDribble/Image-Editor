package sample.Filters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlipHorizontal extends Filter {

    private BufferedImage newImage;

    public FlipHorizontal(BufferedImage bufferedImage, BufferedImage copyImage) throws IOException {
        super(bufferedImage);
        newImage = copyImage;
    }

    @Override
    public void adjustPixels() {

        for (int y = 0; y < super.img.getHeight(); y++)
            for (int x = 0; x < super.img.getWidth(); x++)
            {
                if (x != (( super.img.getWidth() - 1 ) / 2))
                {
                    int newX = super.img.getWidth() -1 - x;
                    newImage.setRGB(newX, y, super.img.getRGB(x,y));
                }
                else {
                    newImage.setRGB(x, y, super.img.getRGB(x , y));
                }
            }
        super.img = newImage;
    }




}
