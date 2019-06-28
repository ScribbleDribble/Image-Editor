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
            // brightness values on scale of -1 to 1 so we need to normalise value to get an appropriate brightness adjustment

            // to reset image so that values dont add to previous image.

            // UNDER CONSTRUCTION
            bufferedImage = ImageIO.read(f);

            filter = new Greyscale(bufferedImage);
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString());
            imgView.setImage(img);


        } else {
            System.out.println("no image selected");
        }

    }


    public void btnFinaliseAction() throws IOException {
        controller.setImage(img);
        controller.setBufferedImage(bufferedImage);

        // save resultant change to the final file and send file to main controller so it can now be loaded everywhere else
        filter.saveChanges();
        File outFile = new File("OutFinal.jpg");
        controller.setFile(outFile);

    }


}
