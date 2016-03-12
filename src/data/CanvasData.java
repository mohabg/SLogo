package data;

import java.util.Collection;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public interface CanvasData {

    @Deprecated
    Image getTurtleImage ();

    @Deprecated
    List<Point> getTurtlePosition ();

    @Deprecated
    void setTurtleImage (Image image);

    @Deprecated
    boolean getPenDown ();

    @Deprecated
    Color getPenColor ();

    List<TurtleData> getTurtles ();

    Collection<Line> getLines ();

    double getBackgroundColor ();

    Collection<StampData> getStamps ();

    List<Color> getPalette ();

}
