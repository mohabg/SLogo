// This entire file is part of my masterpiece.
// Patrick Grady

/*
 * This ReturnData class is of my design. It is a way to encalpuslate all the data needed by the
 * GUI into a compact package that can be passed around easily. Ultimately, it attempts to create a
 * binding between the front and the backend that is portable (could be adapted to a different frontend)
 * , easily extendable, and easy to code. This is the only method of data transfer from the backend to the
 * frontend.
 * 
 * In practice, this class held up extremely well. Other interfaces were created to limit the amount of
 * information that other classes could access, and prevent modification. We never had to change the functionality
 * of this class for any of the extensions, except debugging, which we didn't implement.
 */

package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.scene.paint.Color;

public class ReturnData implements CanvasData, WorkspaceData {

	private List<Line> lines = new ArrayList<Line>();
	private List<TurtleData> turtleInfo;
	private List<Color> palette;
	private Map<String, String> userVariables;
	private Set<String> userFunctions;
	private double backgroundColor;
	private List<String[]> history;

	public List<Line> getLines() {
		return lines;
	}

	public void addLines(List<Line> lines) {
		this.lines = lines;
	}

	public double getBackgroundColor() {
		return backgroundColor;
	}

	public void setPalette(List<Color> palette) {
		this.palette = palette;
	}

	public Map<String, String> getUserVariables() {
		return userVariables;
	}

	public void setVariables(Map<String, String> variables) {
		this.userVariables = variables;

	}

	public Set<String> getUserFunctions() {
		return userFunctions;
	}

	public void setFunctions(Set<String> functionOutputs) {
		this.userFunctions = functionOutputs;

	}

	public void setHistory(List<String[]> history) {
		this.history = history;
	}

	public List<String[]> getHistory() {
		return this.history;
	}

	@Override
	public List<TurtleData> getTurtles() {
		return turtleInfo;
	}

	public void setTurtles(List<TurtleData> turtles) {
		this.turtleInfo = turtles;

	}

	@Override
	public List<StampData> getStamps() {
		return null;
	}

	@Override
	public List<Color> getPalette() {
		return palette;
	}

	public void setBackgroundColor(double color) {
		backgroundColor = color;
	}

}
