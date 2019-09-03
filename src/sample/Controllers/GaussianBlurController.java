package sample.Controllers;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Filters.Blur;
import sample.Filters.Filter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GaussianBlurController {

    @FXML
    ImageView imgView;
    @FXML
    Button btnAdjust;
    @FXML
    Button btnFinalise;


    private File f;
    private Image img;
    private BufferedImage bufferedImage;
    private Controller controller;

    private Filter filter;
    private static final int IMAGE_WIDTH = 550;
    private static final int IMAGE_HEIGHT = 550;


    public void setImageContext(Image image, BufferedImage bufferedImage, File f, Controller controller) throws IOException {
        this.img = image;
        this.bufferedImage = bufferedImage;
        imgView.setImage(this.img);
        this.f = f;
        this.controller = controller;

        File out = new File("Out.jpg");
        //ImageIO.write(bufferedImage, "jpg", out);
    }


    public void btnAdjustAction() throws IOException {


        if (bufferedImage != null) {

            bufferedImage = ImageIO.read(f);
            BufferedImage bufferedImageCopy = ImageIO.read(f);

            filter = new Blur(bufferedImage, bufferedImageCopy, "gaussian");
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
            imgView.setImage(img);


        } else {
            System.out.println("no image selected");
        }

    }

    public void btnAdjustAction2() throws IOException {


        if (bufferedImage != null) {

            bufferedImage = ImageIO.read(f);
            BufferedImage bufferedImageCopy = ImageIO.read(f);

            filter = new Blur(bufferedImage, bufferedImageCopy, "gaussian");
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
            imgView.setImage(img);


        } else {
            System.out.println("no image selected");
        }

    }


    public void btnFinaliseAction() throws IOException {

        // clear canvas
        controller.getGraphicsContext().clearRect(
                0,
                0,
                controller.getCanvas().getWidth(),
                controller.canvas.getHeight()
        );

        controller.getGraphicsContext().drawImage(img, 0, 0);
        controller.setImage(img);
        controller.setBufferedImage(bufferedImage);

        // save resultant change to the final file and send file to main controller so it can now be loaded everywhere else

        try {
            filter.saveChanges();
            File outFile = new File("OutFinal.jpg");
            controller.setFile(outFile);
        }

        catch (NullPointerException enull)
        {
            System.out.println("Alert user no changes have been made?");
        }

    }


}