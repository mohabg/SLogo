package slogo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import data.CanvasData;
import data.WorkspaceData;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class ReturnData implements CanvasData, WorkspaceData {

    public ReturnData () {
        // TODO Auto-generated constructor stub
    }

    public Image getTurtleImage () {
        File f = new File("src/resources/turtle.png");
        Image i = new Image(f.toURI().toString(), 60, 60, true, true);
        return i;
    }

    public void setTurtleImage () {
        // TODO
    }

    static double tick = 0;

    public Point2D getTurtlePos () {
        // TODO empty function
        return new Point2D(Math.sin(tick++ / 1000), 0);
    }

    public List<Line> getLines () {
        // TODO empty function
        ArrayList<Line> lines = new ArrayList<Line>();

        for (int i = 0; i < 10; i++) {
            lines.add(new Line(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1,
                               Math.random() * 2 - 1));
        }

        return lines;
    }

    public Color getBackgroundColor () {
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

    @Override
    public boolean getPenDown () {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Color getPenColor () {
        // TODO Auto-generated method stub
        return null;
    }

}
