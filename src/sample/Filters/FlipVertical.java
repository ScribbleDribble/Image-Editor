package sample.Filters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class FlipVertical extends Filter {

    private BufferedImage newImage;

    public FlipVertical(BufferedImage bufferedImage, BufferedImage copyImage) throws IOException {
        super(bufferedImage);
        newImage = copyImage;
    }

    @Override
    public void adjustPixels() {

        for (int y = 0; y < super.img.getHeight(); y++)
            for (int x = 0; x < super.img.getWidth(); x++)
            {
                if (y != (( super.img.getHeight() - 1 ) / 2))
                {
                    int newY = super.img.getHeight()-1 - y;
                    newImage.setRGB(x, newY, super.img.getRGB(x,y));
                }
                else {
                    newImage.setRGB(x, y, super.img.getRGB(x , y));
                }
            }
        super.img = newImage;
    }

    // returns buffered image with the resultant edit
    public BufferedImage getCopyBufferedImage() {
        return newImage;
    }


}
