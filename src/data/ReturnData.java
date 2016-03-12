package data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import commands.CommandNode;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class ReturnData implements CanvasData, WorkspaceData {

    private List<Line> lines = new ArrayList<Line>();
    private List<Point> turtlePositions = new ArrayList<Point>();
    private List<TurtleData> turtleInfo;
    private List<Color> palette;
    private Image turtleImage;
    private Map<String, String> userVariables;
    private Set<String> userFunctions;
    private double backgroundColor;
    private List<String[]> history;

    public ReturnData () {
    }

    public List<Line> getLines () {
        return lines;
    }

    public void addLines (List<Line> lines) {
        this.lines = lines;
    }

    public double getBackgroundColor () {
        return backgroundColor;
    }

    public void setPalette (List<Color> palette) {
        this.palette = palette;
    }

    public Map<String, String> getUserVariables () {
        // TODO
        return userVariables;
    }

    public void setVariables (Map<String, String> variables) {
        this.userVariables = variables;

    }

    public Set<String> getUserFunctions () {
        // TODO
        return userFunctions;
    }

    public void setFunctions (Set<String> functionOutputs) {
        this.userFunctions = functionOutputs;

    }

    public void setHistory (List<String[]> history) {
        this.history = history;
    }

    public List<String[]> getHistory () {
        return this.history;
    }

    @Override
    public List<TurtleData> getTurtles () {
        // TODO Auto-generated method stub
        return turtleInfo;
    }

    public void setTurtles (List<TurtleData> turtles) {
        this.turtleInfo = turtles;

    }

    @Override
    public List<StampData> getStamps () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Color> getPalette () {
        // TODO Auto-generated method stub
        return palette;
    }

    public void setBackgroundColor (double color) {
        backgroundColor = color;
    }

    @Deprecated
    public Image getTurtleImage () {
        // TODO
        return turtleImage;
    }

    @Override
    @Deprecated
    public List<Point> getTurtlePosition () {
        // TODO
        return turtlePositions;
    }

    @Deprecated
    public void addTurtlePosition (List<Point> turtlePositions) {
        this.turtlePositions = turtlePositions;
    }

    @Override
    @Deprecated
    public void setTurtleImage (Image image) {
        this.turtleImage = image;
    }

    @Override
    @Deprecated
    public boolean getPenDown () {
        return false;
    }

    @Override
    @Deprecated
    public Color getPenColor () {
        return null;
    }

}
