package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;


import java.net.URL;
import java.util.ResourceBundle;

public class ColourOverController implements Initializable {


    @FXML
    private RadioButton red;
    @FXML
    private RadioButton green;
    @FXML
    private RadioButton yellow;
    @FXML
    private RadioButton magneta;
    @FXML
    private RadioButton cyan;
    @FXML
    private Button btnAdjustColour;

    @FXML
    private ImageView imgView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void radiobtnAction() {

    }

}
