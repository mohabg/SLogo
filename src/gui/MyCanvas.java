package gui;

import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import observers.CanvasData;


public class MyCanvas extends Canvas {
    CanvasData data;

    public MyCanvas (CanvasData data) {
        super(GUI.getScreenWidth() / 2, GUI.getScreenHeight() / 2);
        this.data = data;
    }

    public void update () {
        drawTurtle();
        drawLines();
    }

    private void drawTurtle () {
        Image image = data.getTurtleImage();
        Point2D pos = data.getTurtlePos();
        // TODO
    }

    private void drawLines () {
        Set<Line> lines = data.getLines();
        // TODO
    }

}
