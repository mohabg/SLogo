package gui;

import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import observers.CanvasData;


public class MyCanvas {
    Canvas canvas;
    CanvasData data;
    ObservableList<Node> canvasNodeChildren;
    private ContextMenu contextMenu;

    public MyCanvas (CanvasData data,
                     ObservableList<Node> canvasNodeChildren,
                     int width,
                     int height) {
        canvas = new Canvas(width, height);
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

    private void initContextMenu () {
        contextMenu = new ContextMenu();
        MenuItem selectBackgroundColor = new MenuItem("Select background color");
        MenuItem selectPenColor = new MenuItem("Select pen color");
        contextMenu.getItems().addAll(selectBackgroundColor, selectPenColor);
        selectBackgroundColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Select background color");
            }
        });
        selectPenColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Select pen color");
            }
        });
    }

    private void handleLeftClick () {
        contextMenu.hide();
    }

    private void handleRightClick () {

        contextMenu.show(canvas, 0, 0);
    }

}
