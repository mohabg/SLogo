package slogo;
import java.util.List;
import javafx.geometry.Point2D;


public class SlogoManager {
    private TurtleController turtleController;
    private List<Point2D> points; // points that are drawn per frame, maintains history

    public SlogoManager () {
        // TODO Auto-generated constructor stub
    	turtleController = new TurtleController();
    }

    public void update () { // called in every frame
        turtleController.update();
        // TODO: what else?
    }
}
