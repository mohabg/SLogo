package gui;

import java.util.List;
import data.CanvasData;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;


public class MyCanvas {
    Canvas canvas;
    CanvasData data;
    ObservableList<Node> canvasNodeChildren;
    private ContextMenu contextMenu;

    public MyCanvas (CanvasData data,
                     ObservableList<Node> canvasNodeChildren,
                     int width,
                     int height) {
        this.canvas = new Canvas(width, height);
        this.data = data;
        this.canvasNodeChildren = canvasNodeChildren;
        initContextMenu();
    }

    public Canvas getCanvas () {
        return canvas;
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

        for (Line l : lines) {

        }

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
                // TODO: use lambda
            }
        });
        selectPenColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event) {
                System.out.println("Select pen color");
                // TODO: use lambda
            }
        });
    }

    private void handleLeftClick () {
        contextMenu.hide();
    }

    private void handleRightClick (MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        contextMenu.show(canvas, x, y);
    }

    private Point2D getCartesianPos (Point2D pos) { // TODO: test
        double x = pos.getX() + canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2 - pos.getY();
        Point2D newPos = new Point2D(x, y);
        return newPos;
    }

    private void setCenterPos (Node node, Point2D centerPos) {
        double x = centerPos.getX() - node.getBoundsInLocal().getWidth() / 2;
        double y = centerPos.getY() - node.getBoundsInLocal().getHeight() / 2;
        Point2D newCenterPos = getCartesianPos(new Point2D(x, y));
        node.setTranslateX(newCenterPos.getX());
        node.setTranslateY(newCenterPos.getY());
    }

}
