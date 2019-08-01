package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Filters.EdgeDetection;
import sample.Filters.Filter;
import sample.Filters.Gamma;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EdgeDetectionController {

    @FXML
    ImageView imgView;
    @FXML
    RadioButton canny;
    @FXML
    RadioButton sobel;

    @FXML
    Button btnAdjust;
    @FXML
    Button btnFinalise;

    private Image img;
    private BufferedImage bufferedImage;
    private File f;
    private Controller controller;
    private Filter filter;


    public void setImageContext(Image image, BufferedImage bufferedImage, File f, Controller controller) {

        this.img = image;
        this.bufferedImage = bufferedImage;
        imgView.setImage(this.img);
        this.f = f;
        this.controller = controller;

    }

    public void btnAdjustAction() throws IOException {

        bufferedImage = ImageIO.read(f);

        if (bufferedImage != null) {

            if (canny.isSelected())
            {
                Filter filter = new EdgeDetection("canny", this.f, bufferedImage);
                filter.adjustPixels();
                filter.writeOver();

            }

            else if (sobel.isSelected())
            {
                Filter filter = new EdgeDetection("sobel", this.f, bufferedImage);
                filter.adjustPixels();
                filter.writeOver();
            }
        }

        File f = new File("Out.jpg");
        img = new Image(f.toURI().toString(), 300, 200, false, true);
        imgView.setImage(img);

    }

    public void btnFinaliseAction() throws IOException {

        controller.getGraphicsContext().drawImage(img, 0, 0);

        controller.setImage(img);
        controller.setBufferedImage(bufferedImage);

        filter.saveChanges();
        File outFile = new File("OutFinal.jpg");
        controller.setFile(outFile);

    }



}
