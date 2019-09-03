package sample.Toolkit;

import java.awt.*;
import java.awt.image.BufferedImage;

import java.util.ArrayDeque;

public class PaintBucket {

    private BufferedImage bufferedImage;

    public PaintBucket(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public boolean isOld(int x, int y, int oldRGB) {

        Color pixelColor = new Color(bufferedImage.getRGB(x, y));
        Color oldColor = new Color(oldRGB);

        if ((x >= bufferedImage.getWidth() || x < 0)
                || (y >= bufferedImage.getHeight() || y < 0))
        {
            return false;
        }

        else if (pixelColor.getRGB() == oldColor.getRGB())
        {
            return true;
        }

        return false;
    }

    public void floodFill(int x, int y, int oldRGB, int newRGB) {

        ArrayDeque<int[]> stack =  new ArrayDeque<>();

        int [] cur = new int[2];
        int [] curUp;
        int [] curDown;
        int [] curLeft;
        int [] curRight;

        cur[0] = x;
        cur[1] = y;
        stack.push(cur);

        while ( !stack.isEmpty() )
        {

            cur = stack.pop();
            //cur.clone so take contents of cur and not the address
            curUp = cur.clone();
            curDown = cur.clone();
            curLeft = cur.clone();
            curRight = cur.clone();

            if (isOld(cur[0], cur[1], oldRGB))
            {
                bufferedImage.setRGB(cur[0], cur[1], newRGB);

                curRight[0] = cur[0] + 1;
                curLeft[0] = cur[0] - 1;
                curDown[1] = cur[1] + 1;
                curUp[1] = cur[1] - 1;

                stack.push(curDown);
                stack.push(curUp);
                stack.push(curLeft);
                stack.push(curRight);
            }

        }

    }

}
