package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Filters.Brightness;
import sample.Filters.Filter;

import javafx.scene.control.Button;

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
    MenuBar myMenuBar;

    @FXML
    Button btnAdjustBrightness;

    private Image img;
    private BufferedImage bufferedImage = null;
    private BufferedImage tempBufferedImage = null;
    private File f = null;


    private static final double INIT_VALUE = 0;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        slider.setValue(INIT_VALUE);
        slider.setMin(-255);
        slider.setMax(255);

    }



    public void btnAdjustBrightnessAction(ActionEvent event) throws IOException {

        if (bufferedImage != null)
        {
            // brightness values on scale of -1 to 1 so we need to normalise value to get an appropriate brightness adjustment

            // to reset image so that values dont add to previous image.
            bufferedImage = ImageIO.read(f);

            Filter filter = new Brightness(bufferedImage, (int) (slider.getValue()));
            filter.adjustPixels();
            filter.writeOver();

            File f = new File("Out.jpg");

            img = new Image(f.toURI().toString());
            imgView.setImage(img);



        }

        else{
            System.out.println("no image selected");
        }

    }


    public void menuOpenFileAction(ActionEvent e) {

        File initialDir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures");
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(initialDir);
        // set supported images to load
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png, *.JPEG"));

        File selectedFile = fc.showOpenDialog(null);
        f = selectedFile;


        if (selectedFile != null)
        {
            Image image = new Image(selectedFile.toURI().toString());
            imgView.setImage(image);
            // store image inside field so it can be retrieved later
            this.img = image;

            try {
                bufferedImage = ImageIO.read(selectedFile);
                //store copy of loaded image so the original image can be reloaded.
                tempBufferedImage = ImageIO.read(selectedFile);
            }

            catch (IOException err)
            {
                System.out.println(err);
            }
        }


        else
        {
            System.out.println("file not found");
        }

    }





    public void menuItemAction(ActionEvent event) throws IOException {

/*
        if (event.getSource() == menuBrightness)
        {
            stageLoader("../brightnessScene.fxml");
        }
/*
        Parent brightnessView = FXMLLoader.load(getClass().getResource("../brightnessScene.fxml"));
        Scene brightScene = new Scene(brightnessView);

        // get stage information
        Stage window = (Stage) myMenuBar.getScene().getWindow();


        window.setScene(brightScene);
        //window.setFullScreen(true);
        window.show();
*/
    }

    public void stageLoader(String fxmlFile) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

        Scene scene = new Scene(root);

        Stage window = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(scene);
        window.show();

    }


}
