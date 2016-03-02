package data;

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
	
	private List<Line> lines = new ArrayList<Line>();
	private List<Point2D> turtlePosition = new ArrayList<Point2D>();
	private Map userVariables;
	private Map userFunctions;

    public ReturnData () {
        // TODO Auto-generated constructor stub
    }

    public Image getTurtleImage () {
        // TODO
        return null;
    }

    public List<Point2D> getTurtlePosition () {
        // TODO
        return null;
    }

    public List<Line> getLines () {
        // TODO
        return null;
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
/*public void setUserFunctions(){
	this.UserFunctions =
}
public void setUserVariables(){
	this.UserVariables
}*/

	public void addLines(List<Line> lines) {
		this.lines = lines;
		
	}

	public void addTurtlePosition(List<Point2D> turtlePosition) {
		this.turtlePosition = turtlePosition;
		
	}

	@Override
	public void setTurtleImage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D getTurtlePos() {
		// TODO Auto-generated method stub
		return null;
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
}
