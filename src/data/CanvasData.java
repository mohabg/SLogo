package data;

import java.util.Collection;
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

    public Collection<Turtle> getTurtles ();

    public List<Line> getLines ();

    public Color getBackgroundColor ();

    // public Collection<Stamp> getStamps ();

}
