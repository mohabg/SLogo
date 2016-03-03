package gui;

import java.util.List;
import data.CanvasData;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class MyCanvas {
    Canvas canvas;
    CanvasData data;
    private ContextMenu contextMenu;
    private int width;
    private int height;

    public MyCanvas (CanvasData data,
                     int width,
                     int height) {
        this.canvas = new Canvas(width, height);
        this.data = data;
        this.width = width;
        this.height = height;
        initContextMenu();
        initControls();
    }

    public Canvas getCanvas () {
        return canvas;
    }

    public void update () {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);

        drawTurtle(gc);
        drawLines(gc);
    }

    private void drawTurtle (GraphicsContext gc) {
        Image image = data.getTurtleImage();
        List<Point2D> posList = data.getTurtlePosition();
        Point2D pos = getCenterPos(image, posList.get(0));
        gc.drawImage(image, pos.getX(), pos.getY());
    }

    private void drawLines (GraphicsContext gc) {
        List<Line> lines = data.getLines();

        for (Line l : lines) { // TODO: stream
            // gc.line
            double startX = l.getStartX();
            double startY = l.getStartY();
            Point2D start = getCartesianPos(new Point2D(startX, startY));
            double endX = l.getEndX();
            double endY = l.getEndY();
            Point2D end = getCartesianPos(new Point2D(endX, endY));
            // TODO: width?
            gc.setStroke(Color.BLACK);
            gc.strokeLine(start.getX(), start.getY(), end.getX(), end.getY());
        }
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

    private void initControls () {
        canvas.setOnMouseClicked(e -> handleMouseClicked(e));
    }

    private void handleMouseClicked (MouseEvent e) {
        if (e.getButton() == MouseButton.PRIMARY) {
            handleLeftClick();
        }
        else if (e.getButton() == MouseButton.SECONDARY) {
            handleRightClick(e);
        }
    }

    private void handleLeftClick () {
        contextMenu.hide();
    }

    private void handleRightClick (MouseEvent e) {
        double x = e.getScreenX();
        double y = e.getScreenY();
        contextMenu.show(canvas, x, y);
    }

    private Point2D getCartesianPos (Point2D pos) { // TODO: test
        double x = pos.getX() + canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2 - pos.getY();
        Point2D newPos = new Point2D(x, y);
        return newPos;
    }

    private Point2D getCenterPos (Image image, Point2D centerPos) {
        double x = centerPos.getX() - image.getWidth() / 2;
        double y = centerPos.getY() - image.getHeight() / 2;
        return getCartesianPos(new Point2D(x, y));
    }

}