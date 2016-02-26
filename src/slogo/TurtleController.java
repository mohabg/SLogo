package slogo;

import java.awt.List;
import java.util.ArrayList;

public class TurtleController {
	private ArrayList<Turtle> turtleList = new ArrayList<Turtle>();
    private Turtle turtle; // or a Set/List of Turtles
    private SlogoManager manager;

    public TurtleController () {
        turtle = new Turtle();
        turtleList.add(turtle);
    }

    public void update () {
        // TODO: update/draw turtle(s) in scene
    }

}
