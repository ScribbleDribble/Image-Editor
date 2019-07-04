package sample.Frame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Drawable{

    private double x, y, r;
    private Color c;

    public Circle(double x, double y, double r, Color c)
    {
        this.x = x;
        this.y = y;
        this.r = r;

        this.c = c;
    }

    @Override
    public void draw(Canvas canvas)
    {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(c);
        gc.fillOval(x, y, r * 2, r * 2);
    }
}
