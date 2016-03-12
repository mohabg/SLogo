package gui;

import data.Point;
import data.TurtleData;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


public class TurtleView extends ImageView {
    private Node clip;

    public TurtleView (TurtleData turtle, MyCanvas canvas) {
        super(turtle.getImage());
        Point turtlePos = canvas.convertCartesianToCanvasPos(turtle.getPosition());
        setCenterPos(turtlePos);
        clip =
                new Rectangle(this.getX(), this.getY(), this.getImage().getWidth(),
                              this.getImage().getHeight());
        this.setClip(clip);
        this.setRotate(turtlePos.getTheta());
    }

    public void setCenterPos (Point center) {
        this.setX(center.getX() - this.getImage().getWidth() / 2);
        this.setY(center.getY() - this.getImage().getHeight() / 2);
    }

    /*
     * public void rotate (double angle) {
     * // this.setRotationAxis(Rotate.Z_AXIS.add(this.getImage().getWidth() / 2,
     * // this.getImage().getHeight() / 2, 0));
     * this.setRotate(angle);
     * // Rotate rotate = new Rotate(this.getRotate());
     * // clip.getTransforms().add(rotate);
     * clip.setRotationAxis(Rotate.Z_AXIS.add(this.getImage().getWidth() / 2,
     * this.getImage().getHeight() / 2, 0));
     * clip.setRotate(angle);
     * }
     */

}
