package sample.Filters;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Gamma extends Filter {

    private double gamma;

   public Gamma(BufferedImage img, double gamma) throws IOException {
       super(img);
       this.gamma = gamma;
   }


   public void adjustPixels() {

       int width = img.getWidth();
       int height = img.getHeight();
       int r, g, b;
       double adjusted_r = 0, adjusted_g = 0, adjusted_b = 0;

       double gamma_correction, gamma = 3;

       for (int i = 0; i < height; i++)
       {
           for (int j = 0; j < width; j++)
           {

               int p = img.getRGB(j, i);

               r = p >> 16 & 0xff;
               g = p >> 8 & 0xff;
               b = p & 0xff;

               gamma_correction = 1.0 / gamma;

               adjusted_r = 255 * (Math.pow(((double) r/ 255.0), gamma_correction));
               adjusted_g = 255 * (Math.pow(((double) g / 255.0), gamma_correction));
               adjusted_b = 255 * (Math.pow(((double) b / 255.0), gamma_correction));

               img.setRGB(j, i, (int) adjusted_r << 16 | (int) adjusted_g << 8 | (int) adjusted_b);

           }
       }

   }



}