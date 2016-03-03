package data;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.Turtle;


public interface CanvasData {

    public Image getTurtleImage ();

    public List<Point2D> getTurtlePosition ();

    public void setTurtleImage (Image image);

    public boolean getPenDown ();

    public Color getPenColor ();

    public List<Turtle> getTurtles ();

    public List<Line> getLines ();

    public Color getBackgroundColor ();

}
