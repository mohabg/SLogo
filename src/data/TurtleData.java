package data;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;


public interface TurtleData {

    public int getID ();

    public boolean containsPoint (Point pos);
    
    public Point getPosition();
    
	public Image getImage();
	
	public double getShape();
	
	public Boolean isPenDown();
	
	public Boolean isVisible();
	
	public double getPenColor();
	
	public double getPenThickness();
}
