package sample.Controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import sample.Filters.FlipHorizontal;
import sample.Filters.FlipVertical;
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
import java.util.concurrent.CountDownLatch;

import sample.Toolkit.PaintBucket;

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
    MenuItem menuEdgeDetection;
    @FXML
    MenuItem menuBox;
    @FXML
    MenuItem menuGaussian;

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

    @FXML
    Line line;

    @FXML
    javafx.scene.image.ImageView fill;
    @FXML
    javafx.scene.image.ImageView vFlip;
    @FXML
    javafx.scene.image.ImageView hFlip;
    @FXML
    AnchorPane anchorPaneLeft;
    @FXML
    AnchorPane anchorPaneUp;

    @FXML
    public Pane pane;

    private javafx.scene.shape.Rectangle r;

    private Image img = null;
    private BufferedImage bufferedImage = null;
    private File f;
    private static final int IMAGE_WIDTH = 550;
    private static final int IMAGE_HEIGHT = 550;
    private double img_width;
    private double img_height;
    private static final int LINE_POINTS = 4;

    private int shapeOffset = 0;

    private double[] mouseCoords = new double[2];

    private int line_points = 2;

    private GraphicsContext gc;

    private PictureModel model;
    private PointStorage pointStorage;

    private Boolean rectangleIsPressed = false;
    private Boolean triangleIsPressed = false;
    private Boolean circleIsPressed = false;
    private Boolean textIsPressed = false;
    private Boolean lineIsPressed = false;
    private Boolean fillIsPressed = false;
    private Boolean paintIsPressed = false;

    // flags for responseLoop
    private Boolean ready = false;
    private Boolean backgroundThreadRunning = false;
    private Boolean switchOffBackgroundThread = false;

    // let canvasDraw = true be fault value
    // this variable here is to differentiate between drawing on canvas and writing straight to buffered image
    private Boolean canvasDraw = true;



    public Image getImage() {
        return img;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void menuOpenFileAction(ActionEvent e) {
        File initialDir = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures");
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(initialDir);

        // set supported images to load
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png, *.JPEG"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            Image image = new Image(selectedFile.toURI().toString(), IMAGE_WIDTH, IMAGE_HEIGHT, true, true);
            //testing
            img_height =  image.getHeight();
            img_width = image.getWidth();
            //
            gc = canvas.getGraphicsContext2D();

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.drawImage(image, 0, 0, image.getWidth(), image.getHeight());

            //canvas.setWidth(IMAGE_WIDTH);
            //canvas.setHeight(IMAGE_HEIGHT);

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
            pointStorage = new PointStorage();

        } else {
            // TODO display warning msg or image to user
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

        else if (event.getSource() == menuEdgeDetection)
        {
            stageLoader("../Views/EdgeDetection.fxml");
        }

        else if (event.getSource() == menuGaussian)
        {
            stageLoader("../Views/Gaussian.fxml");
        }
        else if (event.getSource() == menuBox)
        {
            stageLoader("../Views/BoxBlur.fxml");
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

        else if (fxmlFile == "../Views/EdgeDetection.fxml")
        {
            EdgeDetectionController edgeDetection = loader.getController();
            edgeDetection.setImageContext(img, bufferedImage, f, this);
        }

        // gaussian and box controllers will share same controller class due to similar functionality

        else if (fxmlFile == "../Views/Gaussian.fxml")
        {
            GaussianBlurController gaussianBlurController = loader.getController();
            gaussianBlurController.setImageContext(img, bufferedImage, f, this, "gaussian");
        }

        else if (fxmlFile == "../Views/BoxBlur.fxml")
        {
            GaussianBlurController gaussianBlurController = loader.getController();
            gaussianBlurController.setImageContext(img, bufferedImage, f, this, "boxBlur");
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }


    public void verticalFlip() throws IOException{

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


        BufferedImage imgCopy = ImageIO.read(f);
        FlipVertical verticalFlip = new FlipVertical(bufferedImage, imgCopy);
        verticalFlip.adjustPixels();
        verticalFlip.saveChanges();

        f = new File("OutFinal.jpg");
        img = new Image(f.toURI().toString(), 550, 550, true, true);

        // update canvas and buffered image
        setImage(img);
        setBufferedImage(verticalFlip.getCopyBufferedImage());

        gc.drawImage(img, 0, 0);

    }

    public void horizontalFlip() throws IOException{

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        BufferedImage imgCopy = ImageIO.read(f);
        FlipHorizontal horizontalFlip = new FlipHorizontal(bufferedImage, imgCopy);
        horizontalFlip.adjustPixels();
        horizontalFlip.saveChanges();

        f = new File("OutFinal.jpg");
        img = new Image(f.toURI().toString(), 550, 550, true, true);

        // update canvas and buffered image
        setImage(img);
        setBufferedImage(horizontalFlip.getCopyBufferedImage());

        gc.drawImage(img, 0, 0);
    }

    // action to be performed once the rectangle shape has been clicked

    // use array instead and loop through all to set as false?
    private void resetObjects() {
        rectangleIsPressed = false;
        triangleIsPressed = false;
        circleIsPressed = false;
        textIsPressed = false;
        lineIsPressed = false;
        fillIsPressed = false;
        paintIsPressed = false;

        if (backgroundThreadRunning)
        {
            switchOffBackgroundThread = true;
        }
    }

    public void setShapeRect() {
        resetObjects();
        // trigger flag to close current response loop thread if there is any
        rectangleIsPressed = true;
        responseLoop();



    }

    public void setShapeTriangle() {
        resetObjects();
        triangleIsPressed = true;
        responseLoop();
    }

    public void setShapeCircle() {
        resetObjects();
        circleIsPressed = true;
        responseLoop();

    }

    public void setLine() {
        resetObjects();
        lineIsPressed = true;
        switchOffBackgroundThread = true;
    }

    public void setText() {
        resetObjects();
        textIsPressed = true;
    }

    public void setFill() {
        resetObjects();
        fillIsPressed = true;

    }

    public void setPaint() {
        resetObjects();
        paintIsPressed = true;
        responseLoop();
    }



    public void paint() {

        GraphicsContext gc = canvas.getGraphicsContext2D();

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

                // workaround - couldn't get switch this part off
                if (!rectangleIsPressed && !circleIsPressed && !triangleIsPressed && !lineIsPressed && !textIsPressed
                      && !fillIsPressed)
                {
                    gc.setFill(cp.getValue());
                    gc.fillRect(x, y, size, size);
                }

            }

            catch (NullPointerException err)
            {
                System.out.println("User hasn't picked a size yet");
            }

        });

    }
    // decision of which shape and then placement
    // TODO break up place method into smaller methods
    public void place() {

        canvas.setOnMouseClicked(e -> {
            double x = e.getX();
            double y = e.getY();
            int shape_size = Integer.parseInt(sizeField.getText());

            if (rectangleIsPressed) {
                Drawable r = new Rectangle(x, y, shape_size, cp.getValue());
                model.add(r);
            }
            else if (circleIsPressed) {
                Drawable c = new Circle(x, y, shape_size, cp.getValue());
                model.add(c);
            }
            else if (triangleIsPressed) {
                Drawable t = new Triangle(x, y, shape_size, cp.getValue());
                model.add(t);
            }
            else if (textIsPressed) {

                Drawable text = new Text("tbd", x, y);
                model.add(text);
            }

            else if (lineIsPressed)
            {

                if (pointStorage.size() != 4)
                {
                    pointStorage.pushXY(e.getX(), e.getY());
                }

                if ( pointStorage.size() == LINE_POINTS )
                {
                    int line_width = Integer.parseInt(sizeField.getText());

                    double[] arr = {0, 0, 0, 0};
                    int j = 3;

                    for (int i = 0; i < 4; i++)
                    {
                        arr[j--] = pointStorage.pop();
                    }

                    Drawable line = new sample.Frame.Line(arr[0], arr[1], arr[2], arr[3], line_width, cp.getValue());
                    model.add(line);
                }

            }

            else if (fillIsPressed)
            {

                Color newCol = cp.getValue();

                double red, green, blue;

                red =  cp.getValue().getRed() * 250;
                green = newCol.getGreen() * 250;
                blue =  newCol.getBlue() * 250;

                int new_colour = (int) red << 16 | (int) green << 8 | (int) blue ;
                int old_colour = bufferedImage.getRGB((int)x, (int)y);

                java.awt.Color c = new java.awt.Color(new_colour);
                //System.out.println(c.getRed()+"\n"+ c.getGreen()+"\n"+c.getBlue());

                PaintBucket pb = new PaintBucket(bufferedImage);
                pb.floodFill((int) x,(int) y, old_colour, new_colour);

                try {
                    File out = new File("OutFinal.jpg");
                    ImageIO.write(bufferedImage, "jpg", out);
                }

                catch(IOException e2) {
                    System.out.println(e2);
                }
                try {
                    img = new Image(f.toURI().toString(), 550, 550, true, true);
                    gc.clearRect(
                            0,
                            0,
                            canvas.getWidth(),
                            canvas.getHeight()
                    );
                    gc.drawImage(img, 0, 0);
                    canvasDraw = false;
                }

                catch (Exception e3)
                {
                    System.out.println(e3);
                }

            }

            if (canvasDraw) {
                model.drawPicture(canvas);

                // take a snapshot of the canvas and write it to a writable image
                WritableImage wr = new WritableImage((int) img_width, (int) img_height);
                WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), wr);

                try {
                    this.bufferedImage = canvasToBufferedImage(writableImage);
                    model.savePicture(bufferedImage);
                } catch (IOException err) {
                    System.out.println(err);
                }

                // set img and f to the below so that the correct data can be sent to the other controllers
                img = writableImage;
                f = new File("OutFinal.jpg");
            }
            canvasDraw = true;
        });

    }

    private BufferedImage canvasToBufferedImage(WritableImage writableImage) {

        bufferedImage = SwingFXUtils.fromFXImage(writableImage, null);

        // adjust to right image type
        BufferedImage bufferedImage1 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB); // do not change alpha stream or something like that, a wise man said

        bufferedImage1.getGraphics().drawImage(bufferedImage, 0, 0, null);

        return bufferedImage1;
    }

    // interactivity section


    public synchronized double[] setMouseCoords(MouseEvent e) {
        mouseCoords[0] = e.getX();
        mouseCoords[1] = e.getY();

        return mouseCoords;
    }

    private Shape chooseShape(int size) {


        if (rectangleIsPressed)
        {
            javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(0, 0, size, size);
            shapeOffset = 1;
            return rectangle;
        }

        else if (circleIsPressed)
        {
            javafx.scene.shape.Circle circle = new javafx.scene.shape.Circle(0, 0, size);
            shapeOffset = size;
            return circle;
        }

        else if (triangleIsPressed)
        {
            Polygon polygon = new Polygon();
            Triangle triangle = new Triangle(0, 0, size, cp.getValue());
            double[] points = triangle.calculatePoints();
            polygon.getPoints().addAll(new Double[]{points[0], points[1], points[2], points[3], points[4], points[5]});
            return polygon;
        }

        else if (paintIsPressed)
        {
            javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(0, 0, size, size);
            shapeOffset = 1;
            return rectangle;
        }

        return null;
    }

    public synchronized void responseLoop() {

        int size = Integer.parseInt(sizeField.getText());

        Shape shape;
        shape = chooseShape(size);

        shape.setFill(cp.getValue());


        pane.getChildren().addAll(shape);

        Task<Void> backGroundLoop = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (true)
                {
                    final CountDownLatch latch = new CountDownLatch(1);
                    backgroundThreadRunning = true;

                    if (switchOffBackgroundThread && ready)
                    {
                        // reset
                        ready = false;
                        switchOffBackgroundThread = false;
                        return null;
                    }

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {

                            double mouseX, mouseY;

                            System.out.println("Shape| isVisible " + shape + shape.isVisible());

                            shape.setFill(cp.getValue());

                            if (switchOffBackgroundThread)
                            {
                                shape.setVisible(false);
                                ready = true;

                            }

                            try {
                                mouseX = mouseCoords[0];
                                mouseY = mouseCoords[1];

                                // boundaries for responsive shapes so that they dont go over tool bars and potentially
                                // block options

                                if (paintIsPressed)
                                    paint();

                                if (mouseX - shape.getLayoutX() + shapeOffset > anchorPaneLeft.getLayoutX() &&
                                        mouseX - shape.getLayoutX() + shapeOffset < anchorPaneLeft.getLayoutX() + anchorPaneLeft.getWidth())
                                {
                                    shape.setTranslateX(anchorPaneLeft.getLayoutX() + anchorPaneLeft.getWidth());
                                    shape.setTranslateY(mouseY - shape.getLayoutX() + shapeOffset);
                                }

                                else if (mouseY - shape.getLayoutY() + shapeOffset > anchorPaneUp.getLayoutY() &&
                                        mouseY - shape.getLayoutY() + shapeOffset < anchorPaneUp.getLayoutY() + anchorPaneUp.getHeight())
                                {
                                    shape.setTranslateX(mouseX - shape.getLayoutX() + shapeOffset);
                                    shape.setTranslateY(anchorPaneUp.getLayoutY() + anchorPaneUp.getHeight());
                                }

                                else
                                {
                                    shape.setTranslateX(mouseX - shape.getLayoutX() + shapeOffset);
                                    shape.setTranslateY(mouseY - shape.getLayoutY() + shapeOffset);
                                }

                            }

                            finally {
                                latch.countDown();
                            }
                        }
                    });
                    latch.await();
                }

            }
        };

        new Thread(backGroundLoop).start();
    }
}
