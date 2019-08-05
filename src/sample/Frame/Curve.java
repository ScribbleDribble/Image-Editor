package sample.Frame;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.LinkedList;

public class Curve implements Drawable{

    private LinkedList<Double> pointsList = new LinkedList<>();

    public Curve(double ...points)
    {
        for (double p: points)
        {
            pointsList.add(p);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();


    }

}
