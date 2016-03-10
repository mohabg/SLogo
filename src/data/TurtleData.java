package data;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;


public interface TurtleData {
    public Point getPosition ();

    public Image getImage ();

    public double isVisible ();

    public int getID ();

    public double getShape ();

    public boolean containsPoint (Point pos);
}
