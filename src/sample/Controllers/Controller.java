package sample.Controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Polygon;
import sample.Frame.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.embed.swing.SwingFXUtils;

import javafx.stage.Modality;
import sample.Frame.Circle;
import sample.Frame.Drawable;
import sample.Frame.Text;
import sample.Frame.Triangle;
import sample.Model.PictureModel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static javafx.embed.swing.SwingFXUtils.fromFXImage;


public class Controller {

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
    javafx.scene.shape.Rectangle rect;

    @FXML
    javafx.scene.shape.Circle circle;

    @FXML
    javafx.scene.text.Text text;

    @FXML
    Polygon triangle;

    @FXML
    Canvas canvas;

    //@FXML
    //javafx.scene.control.ColorPicker colorPicker;

    @FXML
    ColorPicker cp;

    @FXML
    TextField sizeField;

    @FXML
    CubicCurve paintStart;

    private Image img = null;

    private BufferedImage bufferedImage = null;

    private File f;

    private static final int IMAGE_WIDTH = 300;

    private static final int IMAGE_HEIGHT = 200;

    private GraphicsContext gc;

    private PictureModel model;

    private Boolean rectangleIsPressed = false;
    private Boolean triangleIsPressed = false;
    private Boolean circleIsPressed = false;
    private Boolean textIsPressed = false;


    public Image getImage() {
        return img;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public void menuOpenFileAction(ActionEvent e) {

        File initialDir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures");
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(initialDir);

        // set supported images to load
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png, *.JPEG"));

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString(), IMAGE_WIDTH, IMAGE_HEIGHT, false, true);

            gc = canvas.getGraphicsContext2D();



            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(image, 0, 0, image.getWidth(), image.getHeight());

            // store image inside field so it can be retrieved later
            this.img = image;


            try {
                bufferedImage = ImageIO.read(selectedFile);
                f = selectedFile;
            } catch (IOException err) {
                System.out.println(err);
            }

            //instantiate a new model

            model = new PictureModel();
        } else {
            System.out.println("file not found");
        }


    }

    public void setImage(Image img) {
        this.img = img;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public void setFile(File file) {
        this.f = file;
    }

    // initiates loading of stage based on which of the menu items were clicked on
    public void menuItemAction(ActionEvent event) throws IOException {

        if (event.getSource() == menuBrightness) {
            stageLoader("../Views/brightnessScene.fxml");
        } else if (event.getSource() == menuContrast) {
            stageLoader("../Views/contrastScene.fxml");
        } else if (event.getSource() == menuColourOver) {
            stageLoader("../Views/ColourOverScene.fxml");
        } else if (event.getSource() == menuGamma) {
            stageLoader("../Views/gammaScene.fxml");
        } else if (event.getSource() == menuGreyscale) {
            stageLoader("../Views/greyscaleScene.fxml");
        }

    }


    public void stageLoader(String fxmlFile) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));

        Parent root = loader.load();

        if (fxmlFile == "../Views/brightnessScene.fxml") {
            BrightnessController brightnessController = loader.getController();
            brightnessController.setImageContext(img, bufferedImage, f, this);
        } else if (fxmlFile == "../Views/contrastScene.fxml") {
            ContrastController contrastController = loader.getController();
            contrastController.setImageContext(img, bufferedImage, f, this);
        } else if (fxmlFile == "../Views/ColourOverScene.fxml") {
            ColourOverController colourOverController = loader.getController();
            colourOverController.setImageContext(img, bufferedImage, f, this);
        } else if (fxmlFile == "../Views/gammaScene.fxml") {
            GammaController gammaController = loader.getController();
            gammaController.setImageContext(img, bufferedImage, f, this);
        } else if (fxmlFile == "../Views/greyscaleScene.fxml") {
            GreyscaleController greyscaleController = loader.getController();
            greyscaleController.setImageContext(img, bufferedImage, f, this);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

    // action to be performed once the rectangle shape has been clicked


    private void resetShapes() {
        rectangleIsPressed = false;
        triangleIsPressed = false;
        circleIsPressed = false;
        textIsPressed = false;
    }

    public void setShapeRect() {
        resetShapes();
        rectangleIsPressed = true;
    }

    public void setShapeTriangle() {
        resetShapes();
        triangleIsPressed = true;
    }

    public void setShapeCircle() {
        resetShapes();
        circleIsPressed = true;
    }

    public void setText() {
        resetShapes();
        textIsPressed = true;
    }



    public void paint() {

        GraphicsContext gc = canvas.getGraphicsContext2D();
        resetShapes();

        canvas.setOnMouseDragged(e -> {

            try {
                double size = Double.parseDouble(sizeField.getText());

                // if brush size is too big

                if (size > IMAGE_HEIGHT / 4 || size > IMAGE_WIDTH / 4  )
                {
                    size = IMAGE_HEIGHT/4;
                }

                double x = e.getX();
                double y = e.getY();

                gc.setFill(cp.getValue());
                gc.fillRect(x, y, size, size);

            }

            catch (NullPointerException err)
            {
                System.out.println("User hasn't picked a size yet" + e);
            }


        });

    }

    // decision of which shape and then placement
    public void place() {

        canvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();

            if (rectangleIsPressed) {
                Drawable r = new Rectangle(x, y, 50, 50, cp.getValue());
                model.add(r);
            }
            else if (circleIsPressed) {
                Drawable c = new Circle(x, y, 50, cp.getValue());
                model.add(c);
            }
            else if (triangleIsPressed) {
                Drawable t = new Triangle(x, y, 30, cp.getValue());
                model.add(t);
            }
            else if (textIsPressed) {

                Drawable text = new Text("tbd", x, y);
                model.add(text);
            }

            model.drawPicture(canvas);

            // take a snapshot of the canvas and write it to a writable image
            WritableImage wr = new WritableImage(IMAGE_WIDTH, IMAGE_HEIGHT);
            WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), wr);

            try {
                this.bufferedImage = canvasToBufferedImage(writableImage);
                model.savePicture(bufferedImage);
            }

            catch (IOException err)
            {
                System.out.println(err);
            }

            // set img and f to the below so that the correct data can be sent to the other controllers
            img = writableImage;
            f = new File("OutFinal.jpg");

        });

    }

    private BufferedImage canvasToBufferedImage(WritableImage writableImage) throws IOException {
        //WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), wr);

        bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

        //ImageIO.write(bufferedImage, "png", new File("OutFinal.png"));

        // adjust to right image type
        BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB); // do not change alpha stream or something like that, a wise man said

        bufferedImage1.getGraphics().drawImage(bufferedImage, 0, 0, null);

        return bufferedImage1;
    }

}
