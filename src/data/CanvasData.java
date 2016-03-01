package data;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public interface CanvasData {

    public Image getTurtleImage ();

    public void setTurtleImage ();

    public Point2D getTurtlePos ();

    public List<Line> getLines ();

    public Color getBackgroundColor ();

}
