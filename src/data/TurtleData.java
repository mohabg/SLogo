// This entire file is part of my masterpiece.
// Patrick Grady

package data;

import javafx.scene.image.Image;


public interface TurtleData {

    int getID ();

    Point getPosition ();

    Image getImage ();

    void setImage (String path);

    double getShape ();

    Boolean isPenDown ();

    Boolean isVisible ();

    double getPenColor ();

    double getPenThickness ();

}
