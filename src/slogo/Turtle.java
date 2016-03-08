package slogo;

import java.util.ArrayList;
import java.util.List;
import data.Point;
import data.TurtleData;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;


public class Turtle implements TurtleData {
    public static final String DEFAULT_IMAGE = "resources/turtle.png";
    private Point position = new Point(0, 0, 0);
    private Image myImage;
    private double visible;
    private double penDown;
    private String penColor;
    private String shape;
    private List<Point> points = new ArrayList<Point>();

    private final int TURTLE_WIDTH = 30;
    private final int TURTLE_HEIGHT = 30;

    // In case user wants to resize turtle
    // private double height;
    // private double width;

    public Turtle () {
        setImage(DEFAULT_IMAGE);
        points.add(position.clone());
        penDown = 1;
        visible = 1;
    }

    public void move (double x, double y) {
        System.out.println("turtle " + x + " " + y);
        setX(x);
        setY(y);
        points.add(position.clone());
    }

    public void setImage (String path) {
        myImage = new Image(path, TURTLE_WIDTH, TURTLE_HEIGHT, true, true);
    }

    public Point getPos () {
        return position.clone();
    }

    public double getX () {
        return position.getX();
    }

    public double getY () {
        return position.getY();
    }

    public double getOrientation () {
        return position.getTheta();
    }

    public void setOrientation (double orientationToSet) {
        position.setTheta(orientationToSet);
    }

    public Image getImage () {
        return myImage;
    }

    private void setX (double x) {
        position.setX(x);
    }

    private void setY (double y) {
        position.setY(y);
    }

    public void turn (Double angle) {
        position.setTheta(position.getTheta() + angle);
    }

    public List<Point> getPoints () {
        return points;
    }

    public void clearPoints () {
        points.clear();
    }

    public double isPenDown () {
        return penDown;
    }

    public void setPenDown () {
        penDown = 1;
    }

    public void setPenUp () {
        penDown = 0;
    }

    public void setVisible () {
        visible = 1;
    }

    public void setInvisible () {
        visible = 0;
    }

    public double isVisible () {
        return visible;
    }

    @Override
    public int getID () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Shape getShape () {
        // TODO Auto-generated method stub
        return null;
    }
}
