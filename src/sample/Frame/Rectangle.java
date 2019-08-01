package sample.Frame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.canvas.Canvas;


public class Rectangle implements Drawable {

    private double x, y;
    int size;
    Color c;

    public Rectangle(double x, double y, int size, Color c) {
        this.x = x;
        this.y = y;
        this.size = size;

        this.c = c;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(c);
        gc.fillRect(x, y, size, size);
    }
}
