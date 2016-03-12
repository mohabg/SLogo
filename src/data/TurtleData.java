package data;

import javafx.scene.image.Image;


public interface TurtleData {

    int getID ();

    Point getPosition ();

    Image getImage ();

    double getShape ();

    Boolean isPenDown ();

    Boolean isVisible ();

    double getPenColor ();

    double getPenThickness ();
}
