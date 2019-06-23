package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {

    @FXML
    MenuItem menuOpenFile;

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

}
