package gui;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import data.CanvasData;
import data.Line;
import data.Point;
import data.TurtleData;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import slogo.Controller;


public class MyCanvas {
    private Canvas canvas;
    private Controller controller;
    private List<Color> palette;
    private ContextMenu backgroundContextMenu, turtleContextMenu;
    // private Color backgroundColor;
    // private List<TurtleData> turtles, selectedTurtles;

    public MyCanvas (int width, int height, Controller controller) {
        this.controller = controller;
        this.canvas = new Canvas(width, height);
        this.palette = Arrays.asList(new Color[] { Color.ALICEBLUE, Color.ANTIQUEWHITE }); // TODO:
                                                                                           // empty
        this.backgroundContextMenu = createBackgroundContextMenu();
        this.turtleContextMenu = createTurtleContextMenu();
        initControls();
    }

    public Canvas getCanvas () {
        return canvas;
    }

    public void update (CanvasData data) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawTurtle(gc, data);
        drawLines(gc, data);
        Color backgroundColor = data.getBackgroundColor();
        List<TurtleData> turtles = data.getTurtles();

        // palette = data.getPalette();
        // TODO: duplicate
        this.backgroundContextMenu = createBackgroundContextMenu();
        this.turtleContextMenu = createTurtleContextMenu();
    }

    private void drawTurtle (GraphicsContext gc, CanvasData data) {
        Image image = data.getTurtleImage();

        List<Point> posList = data.getTurtlePosition();

        for (Point p : posList) { // TODO: use stream
            ImageView imageView = new ImageView(image);
            Point draw = getCartesianPos(p);

            double x = draw.getX() - image.getWidth() / 2;
            double y = draw.getY() - image.getHeight() / 2;
            imageView.setRotate(p.getTheta());

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image rotated = imageView.snapshot(params, null);
            gc.drawImage(rotated, x, y);
        }
    }

    private void drawLines (GraphicsContext gc, CanvasData data) {
        Collection<Line> lines = data.getLines();

        for (Line l : lines) { // TODO: stream
            Point a = getCartesianPos(l.getA());
            Point b = getCartesianPos(l.getB());
            gc.setStroke(data.getPenColor());
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
        }
    }

    private Menu createColorSubmenu (String name, List<Color> palette) {
        Menu submenu = new Menu(name);
        for (Color color : palette) { // TODO: stream
            submenu.getItems().add(new MenuItem(color.toString()));
        }
        return submenu;
    }

    private ContextMenu createTurtleContextMenu () {
        // TODO: place in resources
        Menu penColorSubmenu = createColorSubmenu("Select background color", palette);
        MenuItem turtleImageSubmenu = new MenuItem("Select turtle image");
        for (MenuItem penColorItem : penColorSubmenu.getItems()) {
            penColorItem.setOnAction(e -> handleSelectPenColor(penColorItem.getText()));
        }
        // selectTurtleImage.setOnAction(e -> handleSelectTurtleImage());

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(penColorSubmenu, turtleImageSubmenu);
        return contextMenu;
    }

    private ContextMenu createBackgroundContextMenu () {
        // TODO: place in resources
        Menu backgroundColorSubmenu = createColorSubmenu("Select background color", palette);
        for (MenuItem backgroundColorItem : backgroundColorSubmenu.getItems()) {
            backgroundColorItem
                    .setOnAction(e -> handleSelectBackgroundColor(backgroundColorItem.getText()));
        }
        // selectBackgroundColor.setOnAction(e -> handleSelectBackgroundColor());

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(backgroundColorSubmenu);
        return contextMenu;
    }

    private void handleSelectBackgroundColor (String hex) {
        /*
         * ColorPicker picker = new ColorPicker();
         * picker.setStyle("-fx-border-radius: 10 10 10 10;" + "-fx-background-radius: 10 10 10 10;"
         * );
         * picker.setOnAction(e -> {
         * Color selectedColor = backgroundColor;
         * picker.setValue(selectedColor);
         * int index = palette.indexOf(selectedColor);
         * String input = "setbg " + index;
         * try {
         * controller.compile(input);
         * }
         * catch (Exception e1) {
         * // TODO Auto-generated catch block
         * e1.printStackTrace();
         * }
         * });
         */

        // TODO: refactor duplicate
        Color color = hex2Color(hex);
        int index = palette.indexOf(color);
        try {
            controller.compile("setbg " + index); // TODO: language
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassNotFoundException | NoSuchMethodException
                | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * private String color2Hex (Color c) {
     * int red = (int) (c.getRed() * 255);
     * String redHex = Integer.toHexString(red);
     * int green = (int) (c.getGreen() * 255);
     * String greenHex = Integer.toHexString(green);
     * int blue = (int) (c.getBlue() * 255);
     * String blueHex = Integer.toHexString(blue);
     * 
     * return (redHex + greenHex + blueHex);
     * }
     */

    // private void updateBackgroundColor (String hex) {
    // canvas.setStyle("-fx-background-color: #" + hex + ";");
    // }

    private Color hex2Color (String hex) {
        return Color.AQUA;
    }

    private void handleSelectPenColor (String hex) {
        Color color = hex2Color(hex);
        int index = palette.indexOf(color);
        try {
            controller.compile("setpc " + index); // TODO: language
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException | ClassNotFoundException | NoSuchMethodException
                | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void handleSelectTurtleImage () {

    }

    private void initControls () {
        canvas.setOnMouseClicked(e -> {
            if (e.getButton() == MouseButton.PRIMARY) {
                handleLeftClick();
            }
            else if (e.getButton() == MouseButton.SECONDARY) {
                handleRightClick(e);
            }
        });
    }

    private void handleLeftClick () {
        backgroundContextMenu.hide();
        turtleContextMenu.hide();
    }

    private void handleRightClick (MouseEvent e) {
        double x = e.getScreenX();
        double y = e.getScreenY();
        backgroundContextMenu.show(canvas, x, y);
        // TODO: turtles
    }

    private Point getCartesianPos (Point pos) {
        double x = pos.getX() + canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2 - pos.getY();
        Point newPos = new Point(x, y, pos.getTheta());
        return newPos;
    }

}
