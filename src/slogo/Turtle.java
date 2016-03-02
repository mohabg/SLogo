package slogo;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;


public class Turtle {

    // public static final Image DEFAULT_IMAGE

    public static final String DEFAULT = "resources/turtle.png";
    private double xCord;
    private double yCord;
    private double orientation;
    private Image myImage;
    private boolean visible;
    private boolean penDown;
    private String penColor;
    private List<Point2D> points = new ArrayList<Point2D>();

    // In case user wants to resize turtle
    // private double height;
    // private double width;

    public Turtle () {
        myImage = new Image(getClass().getClassLoader().getResourceAsStream(DEFAULT));
        xCord = 0;
        yCord = 0;
        orientation = 0;
        points.add(new Point2D(xCord, yCord));
        penDown = true;
        visible = true;
    }

    public void move (double x, double y) {
        // System.out.println("turtle " + x + " " + y);
        setX(x);
        setY(y);
        points.add(new Point2D(xCord, yCord));
    }

    public double getX () {
        return xCord;
    }

    public double getY () {
        return yCord;
    }

    public double getOrientation () {
        return orientation;
    }

    public Image getImage () {
        return myImage;
    }

    private void setX (Double x) {
        xCord = x;
    }

    private void setY (Double y) {
        yCord = y;
    }

    public void turn (Double angle) {
        orientation += angle;
    }

    public List<Point2D> getPoints () {
        return points;
    }

    public Boolean getPenBoolean () {
        return penDown;
    }
}
