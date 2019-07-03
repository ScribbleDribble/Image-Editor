package sample.Events;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sample.Frame.Drawable;
import sample.Model.PictureModel;


import java.io.IOException;

public class RectanglePlacement {

    public static void placement(MouseEvent event, Canvas canvas, PictureModel model) {

        System.out.println("hi");

        canvas.setOnMouseClicked(e -> {
            //double x =  event.getSceneX() * 0.57;
            //double y = (int) event.getSceneY() * 0.59;

            double x = event.getX();
            double x2 = event.getSceneX();
            double x3 = event.getScreenX();
            double y = event.getScreenY();

            System.out.println(x);
            System.out.println(x2);
            System.out.println(x3);
            System.out.println(y + "\n----------------");

            Drawable rectangle = new sample.Frame.Rectangle(0, 0, 50, 50);

            model.add(rectangle);
            model.drawPicture(canvas);

        });

    }

}