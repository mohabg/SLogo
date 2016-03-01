package observers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class ReturnData implements CanvasData, WorkspaceData {

	public ReturnData() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

	public Image getTurtleImage() {
		// TODO
		return null;
	}

	public Point2D getTurtlePos() {
		// TODO empty function
		return new Point2D(0, 0);
	}

	public List<Line> getLines() {
		// TODO empty function
		ArrayList<Line> lines = new ArrayList<Line>();

		for (int i = 0; i < 10; i++) {
			lines.add(new Line(Math.random() * 2 - 1, Math.random() * 2 - 1, Math.random() * 2 - 1,
					Math.random() * 2 - 1));
		}

		return lines;
	}

	public Color gerBackgroundColor() {
		// TODO
		return null;
	}

	public Map<String, String> getUserVariables() {
		// TODO
		return null;
	}

	public Set<String> getUserFunctions() {
		// TODO
		return null;
	}

}
