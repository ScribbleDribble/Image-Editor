package sample.Controllers;


import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Filters.Filter;

import sample.Filters.Greyscale;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class GreyscaleController {

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

            filter = new Greyscale(bufferedImage);
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString(), 550, 550, true, true);
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
