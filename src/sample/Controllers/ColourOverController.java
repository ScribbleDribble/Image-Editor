package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Filters.ColourOver;
import sample.Filters.Contrast;
import sample.Filters.Filter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColourOverController {

    @FXML
    RadioButton red;
    @FXML
    RadioButton green;
    @FXML
    RadioButton blue;
    @FXML
    RadioButton yellow;
    @FXML
    RadioButton magenta;
    @FXML
    RadioButton cyan;
    @FXML
    Button btnAdjust;
    @FXML
    Button btnFinalise;
    @FXML
    ImageView imgView;

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

        bufferedImage = ImageIO.read(f);

        if (img != null)
        {
            if (red.isSelected())
            {
                System.out.println();
                Filter colourOver = new ColourOver(bufferedImage, "red");
                colourOver.adjustPixels();
                colourOver.writeOver();
            }

            else if (green.isSelected())
            {
                Filter colourOver = new ColourOver(bufferedImage, "green");
                colourOver.adjustPixels();
                colourOver.writeOver();
            }

            else if (blue.isSelected())
            {
                Filter colourOver = new ColourOver(bufferedImage, "blue");
                colourOver.adjustPixels();
                colourOver.writeOver();
            }
            else if (yellow.isSelected())
            {
                Filter colourOver = new ColourOver(bufferedImage, "yellow");
                colourOver.adjustPixels();
                colourOver.writeOver();
            }

            else if (magenta.isSelected())
            {
                Filter colourOver = new ColourOver(bufferedImage, "magenta");
                colourOver.adjustPixels();
                colourOver.writeOver();
            }

            else if (cyan.isSelected())
            {
                Filter colourOver = new ColourOver(bufferedImage, "cyan");
                colourOver.adjustPixels();
                colourOver.writeOver();
            }

            File f = new File("Out.jpg");
            img = new Image(f.toURI().toString());

            imgView.setImage(new Image(f.toURI().toString()));

        }

        else {
            System.out.println("no file selected");
        }


    }

    public void btnFinaliseAction() {
        controller.setImage(img);
        controller.setBufferedImage(bufferedImage);
    }





}
