package slogo;

import data.Point;
import javafx.scene.image.Image;

public interface TurtleInfo {

	public Point getPosition();
	public Image getImage();
	public String getShape();
	public Boolean isPenDown();
	public Boolean isVisible();
	public String getPenColor();
	public double getPenThickness();

}
