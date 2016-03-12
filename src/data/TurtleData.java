package data;

import javafx.scene.image.Image;


public interface TurtleData {

    public int getID ();

    public Point getPosition ();

    public Image getImage ();

    public double getShape ();

    public Boolean isPenDown ();

    public Boolean isVisible ();

    public double getPenColor ();

    public double getPenThickness ();

}
