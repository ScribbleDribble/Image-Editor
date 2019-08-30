package sample.Frame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Text implements Drawable {

    private String text;
    double x, y;

    public Text(String text, double x, double y) {
        this.text = text;
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText(text, x, y);
    }

}
