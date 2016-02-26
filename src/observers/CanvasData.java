package observers;

import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;


public interface CanvasData extends Data {

    public Image getTurtleImage ();

    public Point2D getTurtlePos ();

    public Set<Line> getLines ();

}
