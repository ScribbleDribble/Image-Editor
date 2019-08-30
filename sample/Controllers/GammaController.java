package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import sample.Filters.Filter;
import sample.Filters.Gamma;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GammaController implements Initializable {


    @FXML
    private Slider slider;
    @FXML
    private Button btnAdjust;
    @FXML
    private ImageView imgView;
    @FXML
    private Button btnFinalise;

    private Filter filter;

    private static final double INIT_VALUE = 0;
    private static final double MAX_VALUE = 8;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setMax(MAX_VALUE);
        slider.setMin(INIT_VALUE);
    }

    private BufferedImage bufferedImage;
    private Image img = null;
    private File f;
    private Controller controller;


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

            filter = new Gamma(bufferedImage, (int) slider.getValue());
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

        controller.getGraphicsContext().drawImage(img, 0,0);
        controller.setImage(img);
        controller.setBufferedImage(bufferedImage);

        filter.saveChanges();
        File outFile = new File("OutFinal.jpg");
        controller.setFile(outFile);

    }


}
