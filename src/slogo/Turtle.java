package slogo;

import java.util.List;
import data.TurtleData;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;


public class Turtle implements TurtleData {

    // public static final Image DEFAULT_IMAGE

    private double xCord;
    private double yCord;
    private double orientation;
    private ImageView myImage;
    private boolean penDown;
    private String penColor;
    private List<Point2D> points;

    // In case user wants to resize turtle
    // private double height;
    // private double width;

    public Turtle () {
        xCord = 0;
        yCord = 0;
        orientation = 0;
        penDown = true;
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

    public ImageView getImage () {
        return myImage;
    }

    public void setX (Double x) {
        xCord = x;
    }

    public void setY (Double y) {
        yCord = y;
    }

    public void turn (Double angle) {
        orientation += angle;
    }
}
