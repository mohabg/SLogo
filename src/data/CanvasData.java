// This entire file is part of my masterpiece.
// Patrick Grady

package data;

import java.util.Collection;
import java.util.List;
import javafx.scene.paint.Color;


public interface CanvasData {

    List<TurtleData> getTurtles ();

    Collection<Line> getLines ();

    double getBackgroundColor ();

    Collection<StampData> getStamps ();

    List<Color> getPalette ();

}
