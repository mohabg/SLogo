package data;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;


public interface TurtleData {

    public int getID ();

    public Point getPosition();
    
    public boolean containsPoint (Point pos);
    
	public Image getImage();
	
	public double getShape();
	
	public Boolean isPenDown();
	
	public Boolean isVisible();
	
	public double getPenColor();
	
	public double getPenThickness();

}
