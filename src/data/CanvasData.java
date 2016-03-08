package data;

import java.util.Collection;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public interface CanvasData {

    @Deprecated
    public Image getTurtleImage ();

    @Deprecated
    public List<Point> getTurtlePosition ();

    @Deprecated
    public void setTurtleImage (Image image);

    @Deprecated
    public boolean getPenDown ();

    @Deprecated
    public Color getPenColor ();

    public Collection<TurtleData> getTurtles ();

    public List<Line> getLines ();

    public Color getBackgroundColor ();

    public Collection<StampData> getStamps ();

}
