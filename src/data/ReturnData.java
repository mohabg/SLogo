package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import slogo.Turtle;


public class ReturnData implements CanvasData, WorkspaceData {

	private List<Line> lines = new ArrayList<Line>();
	private List<Point> turtlePositions = new ArrayList<Point>();
	private List<TurtleData> turtleInfo;
	private List<Double> palette;
	private Image turtleImage;
	private Map<String, String> userVariables;
	private Set<String> userFunctions;
	private Color penColor;
	private double penBoolean;
	private double BackgroundColor;

	public ReturnData () {
		// TODO Auto-generated constructor stub
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

	public List<Line> getLines () {
		// TODO
		return lines;
	}

	public void addLines (List<Line> lines) {
		this.lines = lines;
	}

	public Color getBackgroundColor () {
		// TODO
		return null;
	}

	public Map<String, String> getUserVariables () {
		// TODO
		return userVariables;
	}

	public Set<String> getUserFunctions () {
		// TODO
		return userFunctions;
	}

	@Deprecated
	public void addTurtlePosition (List<Point> turtlePositions) {
		this.turtlePositions = turtlePositions;
	}

	public void setVariables (Map<String, String> variables) {
		this.userVariables = variables;

	}

	@Override
	@Deprecated
	public void setTurtleImage (Image image) {
		this.turtleImage = image;
	}

	@Deprecated
	public void addPenBoolean (double d) {
		this.penBoolean = d;

	}

	public void setFunctions (Set<String> functionOutputs) {
		this.userFunctions = functionOutputs;

	}

	@Override
	@Deprecated
	public boolean getPenDown () {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@Deprecated
	public Color getPenColor () {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TurtleData> getTurtles () {
		// TODO Auto-generated method stub
		return turtleInfo;
	}
	public void setTurtles(List<TurtleData> turtles){
		this.turtleInfo = turtles;

	}
	@Override
	public List<StampData> getStamps () {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Double> getPalette () {
		// TODO Auto-generated method stub
		return palette;
	}

	public void addBackgroundColor(double color) {
		BackgroundColor = color;

	}
}
