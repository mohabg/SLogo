package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ReturnData implements CanvasData, WorkspaceData {

	private List<Line> lines = new ArrayList<Line>();
	private List<Point> turtlePositions = new ArrayList<Point>();
	private Image turtleImage;
	private Map<String, String> userVariables;
	private Set<String> userFunctions;
	private Color penColor;
	private double penBoolean;

	public ReturnData() {
		// TODO Auto-generated constructor stub
	}

	public Image getTurtleImage() {
		// TODO
		return turtleImage;
	}

	@Override
	public List<Point> getTurtlePosition() {
		// TODO
		return turtlePositions;
	}

	public List<Line> getLines() {
		// TODO
		return lines;
	}

	public Color getBackgroundColor() {
		// TODO
		return null;
	}

	public Map<String, String> getUserVariables() {
		// TODO
		return userVariables;
	}

	public Set<String> getUserFunctions() {
		// TODO
		return userFunctions;
	}

	public void addLines(List<Line> lines) {
		this.lines = lines;

	}

	public void addTurtlePosition(List<Point> turtlePositions) {
		this.turtlePositions = turtlePositions;

	}

	@Override
	public boolean getPenDown() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getPenColor() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setVariables(Map<String, String> variables) {
		this.userVariables = variables;

	}

	@Override
	public void setTurtleImage(Image image) {
		this.turtleImage = image;
	}

	public void addPenBoolean(double d) {
		this.penBoolean = d;

	}

	public void setFunctions(Set<String> functionOutputs) {
		this.userFunctions = functionOutputs;

	}
}
