package data;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;


public interface TurtleData {
    public Point getPos ();

    public Image getImage ();

    public double isVisible ();

    // public int getID ();

    public Shape getShape ();
}
