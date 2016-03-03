package data;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public interface CanvasData {

	public Image getTurtleImage();

	public List<Point> getTurtlePosition();

	public void setTurtleImage(Image image);

	public List<Line> getLines();

	public Color getBackgroundColor();

	public boolean getPenDown();

	public Color getPenColor();

}
