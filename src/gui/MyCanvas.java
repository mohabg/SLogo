package gui;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import data.CanvasData;
import data.Line;
import data.Point;
import data.TurtleData;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import slogo.Controller;


public class MyCanvas {
    private static final Point CANVAS_MOUSE_OFFSET = new Point(-10, -50);
    private static final Color SELECTION_COLOR = Color.RED;
    private final ResourceBundle GUIResources = ResourceBundle.getBundle("resources/GUI");

    private Canvas canvas;
    private Controller controller;
    private CommandWindow console;
    private List<Color> palette;
    private ContextMenu backgroundContextMenu, turtleContextMenu;
    private List<TurtleData> turtles, selectedTurtles;

    public MyCanvas (int width, int height, Controller controller, CommandWindow console) {
        this.controller = controller;
        this.canvas = new Canvas(width, height);
        this.console = console;
        this.palette = new ArrayList<Color>();
        this.backgroundContextMenu = new ContextMenu();
        this.turtleContextMenu = new ContextMenu();
        initControls();

        this.turtles = new ArrayList<TurtleData>();
        this.selectedTurtles = new ArrayList<TurtleData>();
    }

    public void update (CanvasData data, double segLength) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Color bgColor = data.getPalette().get((int) data.getBackgroundColor());
        gc.setFill(bgColor);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        drawLines(gc, data, segLength);

        turtles = data.getTurtles();
        drawTurtles(gc, turtles);

        palette = data.getPalette();
    }

    public Canvas getCanvas () {
        return canvas;
    }

    private void drawLine (GraphicsContext gc, Line l, double dottedLen, Color c) {
        gc.setStroke(c);

        Point a = convertCartesianToCanvasPos(l.getA());
        Point b = convertCartesianToCanvasPos(l.getB());

        if (dottedLen < 0.1) {
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
            return;
        }

        double dx = b.getX() - a.getX();
        double dy = b.getY() - a.getY();
        double length = Math.sqrt(dx * dx + dy * dy);

        double unitX = dx / length * dottedLen;
        double unitY = dy / length * dottedLen;

        boolean drawing = true;
        for (double i = 0; i < length / dottedLen; i++) {
            if (drawing) {
                double end = Math.min(i + 1, length / dottedLen);

                double startX = a.getX() + unitX * i;
                double startY = a.getY() + unitY * i;
                double endX = a.getX() + unitX * end;
                double endY = a.getY() + unitY * end;

                gc.strokeLine(startX, startY, endX, endY);
            }
            drawing = !drawing;
        }
    }

    private void drawLines (GraphicsContext gc, CanvasData data, double lineSpacing) {
        Collection<Line> lines = data.getLines();

        for (Line l : lines) {
            int color = (int) l.getColor() % data.getPalette().size();
            drawLine(gc, l, lineSpacing, data.getPalette().get(color));
        }
    }

    private void drawTurtles (GraphicsContext gc, Collection<TurtleData> turtles) {
        for (TurtleData turtle : turtles) {
            if (!turtle.isVisible())
                continue;

            TurtleView imageView = new TurtleView(turtle, this);
            Bounds b = imageView.getBoundsInParent();
            double x = b.getMinX();
            double y = b.getMinY();

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            Image rotated = imageView.snapshot(params, null);
            gc.drawImage(rotated, x, y);

            if (selectedTurtles.contains(turtle)) {
                gc.setStroke(SELECTION_COLOR);
                gc.strokeRect(b.getMinX(), b.getMinY(), b.getWidth(), b.getHeight());
            }
        }
    }

    private Menu createColorSubmenu (String name, List<Color> palette) {
        Menu submenu = new Menu(name);
        for (Color color : palette) {
            submenu.getItems().add(new MenuItem(color.toString()));
        }
        return submenu;
    }

    private void updateTurtleContextMenu () {
        Menu penColorSubmenu =
                createColorSubmenu(GUIResources.getString("selectPenColor"), palette);
        for (MenuItem penColorItem : penColorSubmenu.getItems()) {
            penColorItem.setOnAction(e -> handleSelectColor(penColorItem.getText(), "SetPenColor"));
        }

        MenuItem turtleImageSubmenu = new MenuItem(GUIResources.getString("selectTurtleImage"));
        turtleImageSubmenu.setOnAction(e -> handleSelectTurtleImage());

        turtleContextMenu.getItems().clear();
        turtleContextMenu.getItems().addAll(penColorSubmenu, turtleImageSubmenu);
    }

    private void updateBackgroundContextMenu () {
        Menu backgroundColorSubmenu =
                createColorSubmenu(GUIResources.getString("selectBackgroundColor"), palette);
        for (MenuItem backgroundColorItem : backgroundColorSubmenu.getItems()) {
            backgroundColorItem
                    .setOnAction(e -> handleSelectColor(backgroundColorItem.getText(),
                                                        "SetBackground"));
        }

        backgroundContextMenu.getItems().clear();
        backgroundContextMenu.getItems().addAll(backgroundColorSubmenu);
    }

    private void handleSelectColor (String hex, String key) {
        Color color = Color.web(hex);
        int index = palette.indexOf(color);
        String language = controller.getLanguage();
        ResourceBundle langResources = ResourceBundle.getBundle("resources.languages/" + language);

        String commands = langResources.getString(key);
        int beginIndex = 0;// commands.indexOf(',');
        int endIndex = commands.indexOf('|');
        if (endIndex < 0) { // doesn't contain
            endIndex = commands.length();
        }
        String command = commands.substring(beginIndex, endIndex);
        controller.compile(command + " " + index);
    }

    /*
     * private String color2Hex (Color c) { int red = (int) (c.getRed() * 255);
     * String redHex = Integer.toHexString(red); int green = (int) (c.getGreen()
     * * 255); String greenHex = Integer.toHexString(green); int blue = (int)
     * (c.getBlue() * 255); String blueHex = Integer.toHexString(blue);
     * 
     * return (redHex + greenHex + blueHex); }
     */

    private void initControls () {
        canvas.setOnMouseClicked(e -> {
            backgroundContextMenu.hide();
            turtleContextMenu.hide();

            Point mouseCanvasPos = new Point(e.getSceneX(), e.getSceneY()).add(CANVAS_MOUSE_OFFSET);
            Point mouseScreenPos = new Point(e.getScreenX(), e.getScreenY());

            // GraphicsContext gc = canvas.getGraphicsContext2D();
            // gc.setFill(Color.DARKORANGE);
            // gc.fillOval(mouseCanvasPos.getX(),
            // mouseCanvasPos.getY(), 3, 3);
            // gc.setFill(Color.DARKMAGENTA);

            if (e.getButton() == MouseButton.PRIMARY) {
                handleLeftClick(mouseCanvasPos);
            }
            else if (e.getButton() == MouseButton.SECONDARY) {
                handleRightClick(mouseCanvasPos, mouseScreenPos);
            }
        });
    }

    private void handleLeftClick (Point mouseCanvasPos) {
        Collection<TurtleData> clickedTurtles =
                findTurtlesContainingCanvasPos(turtles, mouseCanvasPos);
        for (TurtleData turtle : clickedTurtles) {
            if (!selectedTurtles.remove(turtle)) { // toggle selection
                selectedTurtles.add(turtle);
            }
        }
    }

    private void handleRightClick (Point mouseCanvasPos, Point mouseScreenPos) {
        double screenX = mouseScreenPos.getX();
        double screenY = mouseScreenPos.getY();

        Collection<TurtleData> clickedTurtles =
                findTurtlesContainingCanvasPos(turtles, mouseCanvasPos);
        if (clickedTurtles.isEmpty()) {
            updateBackgroundContextMenu();
            backgroundContextMenu.show(canvas, screenX, screenY);
        }
        else { // handle clicked turtle(s)
            updateTurtleContextMenu();
            turtleContextMenu.show(canvas, screenX, screenY);
        }
    }

    public Point convertCartesianToCanvasPos (Point myCartesian) {
        double x = myCartesian.getX() + canvas.getWidth() / 2;
        double y = canvas.getHeight() / 2 - myCartesian.getY();
        return new Point(x, y, myCartesian.getTheta());
    }

    // private Point convertCanvasPosToCartesian (Point canvasPos) {
    // double x = canvasPos.getX() - canvas.getWidth() / 2;
    // double y = canvas.getHeight() / 2 - canvasPos.getY();
    // return new Point(x, y, canvasPos.getTheta());
    // }

    private Collection<TurtleData> findTurtlesContainingCanvasPos (Collection<TurtleData> turtles,
                                                                   Point canvasPos) {
        Collection<TurtleData> ret = new ArrayList<TurtleData>();
        for (TurtleData turtle : turtles) {
            TurtleView turtleView = new TurtleView(turtle, this);
            Point2D p = new Point2D(canvasPos.getX(), canvasPos.getY());
            if (turtleView.contains(p)) {
                ret.add(turtle);
            }
        }
        return ret;
    }

    private void handleSelectTurtleImage () {
        String img = getUserSelectedImage();
        if (img == null) {
            return;
        }

        selectedTurtles.stream().forEach(t -> t.setImage(img));
    }

    private String getUserSelectedImage () {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .addAll(new ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog(new Stage());
        String filename;
        try {
            filename = file.toURI().toURL().toString();
        }
        catch (MalformedURLException e) {
            console.printError(e.getMessage());
            return null;
        }

        return filename;
    }

}
