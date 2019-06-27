package sample;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Controllers.Controller;

import javax.naming.ldap.Control;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{



        Parent root = FXMLLoader.load(getClass().getResource("Views/sample2.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/sample2.fxml"));






        primaryStage.setTitle("Image Edit 0.1");
        primaryStage.setScene(new Scene(root, 1920, 1080));
        primaryStage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}


