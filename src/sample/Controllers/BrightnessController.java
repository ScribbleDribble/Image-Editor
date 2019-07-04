package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import sample.Filters.Brightness;
import sample.Filters.Filter;


import javax.imageio.ImageIO;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class BrightnessController implements Initializable {

    @FXML
    Slider slider;

    @FXML
    ImageView imgView;

    @FXML
    Button btnAdjustBrightness;

    @FXML
    private Controller controller;

    @FXML
    Button btnFinalise;

    private Image img;
    private static BufferedImage bufferedImage = null;
    private File f = null;

    private static final double INIT_VALUE = 0;

    private Filter filter;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setValue(INIT_VALUE);
        slider.setMin(-255);
        slider.setMax(255);


    }

    // supplies image data to the local filter controller
    public void setImageContext(Image image, BufferedImage bufferedImage, File f, Controller controller) throws IOException{
        this.img = image;
        this.bufferedImage = bufferedImage;
        imgView.setImage(this.img);
        this.f = f;
        this.controller = controller;

        System.out.println(bufferedImage.toString());


        if (bufferedImage == null)
        {
            throw new IllegalArgumentException("buffered image is null");
        }

        File out = new File("Out.jpg");
        ImageIO.write(bufferedImage, "jpg", out);
    }




    public void btnAdjustBrightnessAction(ActionEvent event) throws IOException {

        if (bufferedImage != null) {

            // brightness values on scale of -1 to 1 so we need to normalise value to get an appropriate brightness adjustment

            // to reset image so that values dont add to previous image.

            bufferedImage = ImageIO.read(f);


            Filter filter = new Brightness(bufferedImage, (int) (slider.getValue()));
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString(), 300, 200, false, true);

            imgView.setImage(img);

            this.filter = filter;


        } else {
            System.out.println("no image selected");
        }

    }


    public void btnFinaliseAction() throws IOException{

        filter.saveChanges();

        // draw image onto the canvas inside the main scene
        controller.getGraphicsContext().drawImage(img, 0, 0);
        controller.setImage(img);

        // image view method
        //controller.setImage(img);
        controller.setBufferedImage(bufferedImage);

        File outFile = new File("OutFinal.jpg");
        controller.setFile(outFile);

    }



}