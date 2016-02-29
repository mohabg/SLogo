package gui;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import observers.CanvasData;


public class MyCanvas extends Canvas {
    CanvasData data;
    ObservableList<Node> canvasNodeChildren;

    public MyCanvas (CanvasData data, ObservableList<Node> canvasNodeChildren) {
        super(GUI.getScreenWidth() / 2, GUI.getScreenHeight() / 2);
        this.data = data;
        this.canvasNodeChildren = canvasNodeChildren;
    }

    public void update () {
        drawTurtle();
        drawLines();
    }

    private void drawTurtle () {
        Image image = data.getTurtleImage();
        Point2D pos = data.getTurtlePos();
        // TODO: color, pen, etc.
        ImageView turtle = new ImageView(image);
        // TODO: GUI.setCenterPos(turtle, pos);
        canvasNodeChildren.add(turtle);
    }

    private void drawLines () {
        List<Line> lines = data.getLines();
        // TODO: color, canvasNodeChildren.addAll(lines);
    }

}
