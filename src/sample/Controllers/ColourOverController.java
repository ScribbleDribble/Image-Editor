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
import sample.Filters.Filter;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ColourOverController implements Initializable {

    @FXML
    MenuItem menuOpenFile;

    @FXML
    MenuBar myMenuBar;


    @FXML
    MenuItem menuBrightness;

    @FXML
    MenuItem menuContrast;
    @FXML
    MenuItem menuColourOver;
    @FXML
    MenuItem menuGreyscale;
    @FXML
    MenuItem menuGamma;


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
    ImageView imgView;

    private BufferedImage bufferedImage;
    private BufferedImage backUpImage;
    private Image img = null;
    private File f;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void radioBtnAction() throws IOException {

        bufferedImage = ImageIO.read(f);
        System.out.println("backupImage when action function is called :\n" + backUpImage + "END\n");

        if (img != null)
        {
            if (red.isSelected())
            {
                System.out.println("hi");
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

            //img = new Image(f.toURI().toString());
            imgView.setImage(new Image(f.toURI().toString()));
        }

        else {
            System.out.println("no file selected");
        }


    }



    public void menuOpenFileAction(ActionEvent e){

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
                backUpImage = ImageIO.read(selectedFile);
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


        System.out.println("backupImage when file is loaded:\n" + backUpImage + "END\n");
    }

    public void menuItemAction(ActionEvent event) throws IOException {


        if (event.getSource() == menuBrightness)
        {
            stageLoader("../Views/brightnessScene.fxml");
        }

        else if (event.getSource() == menuContrast)
        {
            stageLoader("../Views/contrastScene.fxml");
        }

        else if (event.getSource() == menuColourOver)
        {
            stageLoader("../Views/ColourOverScene.fxml");
        }

        else if (event.getSource() == menuGamma)
        {
            stageLoader("../Views/gammaScene.fxml");
        }

        else if (event.getSource() == menuGreyscale)
        {
            stageLoader("../Views/greyscaleScene.fxml");
        }


    }


    public void stageLoader(String fxmlFile) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

        Scene scene = new Scene(root);

        Stage window = (Stage) myMenuBar.getScene().getWindow();

        window.setScene(scene);
        window.show();

    }


}
