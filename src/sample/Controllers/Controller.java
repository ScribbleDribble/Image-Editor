package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.stage.Modality;
import sample.Filters.ColourOver;


import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Controller  {

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
    ImageView imgView;


    private Image img = null;

    private BufferedImage bufferedImage = null;

    private File f;

    private Boolean image_loaded = false;

    public Image getImage(){
        return img;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }




    public void menuOpenFileAction(ActionEvent e){

        File initialDir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures");
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(initialDir);
        // set supported images to load
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png, *.JPEG"));

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null)
        {
            Image image = new Image(selectedFile.toURI().toString());



            imgView.setImage(image);
            // store image inside field so it can be retrieved later
            this.img = image;


            try {
                bufferedImage = ImageIO.read(selectedFile);
                image_loaded = true;
                f = selectedFile;


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

    public void setImage(Image img) {
        this.img = img;
        imgView.setImage(img);
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = loader.load();

        if (fxmlFile == "../Views/brightnessScene.fxml")
        {
            BrightnessController brightnessController = loader.getController();
            brightnessController.setImageContext(img, bufferedImage, f, this);
        }

        else if (fxmlFile == "../Views/contrastScene.fxml")
        {
            ContrastController contrastController = loader.getController();
            contrastController.setImageContext(img, bufferedImage, f, this);
        }

        else if (fxmlFile == "../Views/ColourOverScene.fxml")
        {
            ColourOverController colourOverController = loader.getController();
            colourOverController.setImageContext(img, bufferedImage, f, this);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();



    }



}
