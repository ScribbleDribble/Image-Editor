package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import sample.Filters.Contrast;
import sample.Filters.Filter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContrastController implements Initializable {

    @FXML
    Button btnFinalise;
    @FXML
    Button btnAdjust;
    @FXML
    Slider slider;
    @FXML
    ImageView imgView;

    private Image img;
    private BufferedImage bufferedImage;
    private File f;
    private Controller controller;

    private static final double INIT_VALUE = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setValue(INIT_VALUE);
        slider.setMax(255);
        slider.setMin(-255);
    }

    public void setImageContext(Image image, BufferedImage bufferedImage, File f, Controller controller) {
        this.img = image;
        this.bufferedImage = bufferedImage;
        imgView.setImage(this.img);
        this.f = f;
        this.controller = controller;
    }

    public void btnAdjustAction() throws IOException {


        if (bufferedImage != null) {
            // brightness values on scale of -1 to 1 so we need to normalise value to get an appropriate brightness adjustment

            // to reset image so that values dont add to previous image.

            // UNDER CONSTRUCTION
            bufferedImage = ImageIO.read(f);

            Filter filter = new Contrast(bufferedImage, (int) slider.getValue());
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString());
            imgView.setImage(img);


        } else {
            System.out.println("no image selected");
        }
    }

    public void btnFinaliseAction() {
        controller.setImage(img);
        controller.setBufferedImage(bufferedImage);
    }

}
