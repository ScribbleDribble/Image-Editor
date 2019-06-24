package sample.Controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    MenuItem menuOpenFile;

    @FXML
    MenuBar myMenuBar;


    @FXML
    MenuItem menuBrightness;

    @FXML
    ImageView imgView;

    public void menuOpenFileAction(ActionEvent e) {

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
        }

        else
        {
            System.out.println("file not found");
        }

    }

    public void menuBrightnessAction(ActionEvent event) throws IOException {

        Parent brightnessView = FXMLLoader.load(getClass().getResource("../brightnessScene.fxml"));
        Scene brightScene = new Scene(brightnessView);

        // get stage information
        Stage window = (Stage) myMenuBar.getScene().getWindow();


        window.setScene(brightScene);
        //window.setFullScreen(true);
        window.show();



    }



}
