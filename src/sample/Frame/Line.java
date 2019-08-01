package sample.Frame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Line implements Drawable{

    private double x1, y1, x2, y2;
    private int line_width;

    public Line(double  x1, double y1, double x2, double y2, int line_width) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.line_width = line_width;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setLineWidth(line_width);
        gc.strokeLine(x1, y1, x2, y2);
    }

}
