package observers;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class ReturnData implements CanvasData, WorkspaceData {

    public ReturnData () {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void update (Observable o, Object arg) {
        // TODO Auto-generated method stub

    }

    public Image getTurtleImage () {
        // TODO
        return null;
    }

    public Point2D getTurtlePos () {
        // TODO
        return null;
    }

    public List<Line> getLines () {
        // TODO
        return null;
    }

    public Color gerBackgroundColor () {
        // TODO
        return null;
    }

    public Map<String, String> getUserVariables () {
        // TODO
        return null;
    }

    public Set<String> getUserFunctions () {
        // TODO
        return null;
    }

}
