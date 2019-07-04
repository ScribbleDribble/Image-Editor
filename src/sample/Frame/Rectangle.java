package sample.Frame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import javafx.scene.canvas.Canvas;
import sample.Frame.Drawable;


public class Rectangle implements Drawable {

    private double x, y, w, h;
    Color c;

    public Rectangle(double x, double y, double w, double h, Color c) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.c = c;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(c);
        gc.fillRect(x, y, w, h);
    }
}
