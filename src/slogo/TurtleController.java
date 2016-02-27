package slogo;

import java.awt.List;
import java.util.ArrayList;

public class TurtleController {
	private ArrayList<Turtle> turtleList = new ArrayList<Turtle>();

    public TurtleController () {
        Turtle turtle = new Turtle();
        turtleList.add(turtle);
    }

    public void update () {
        for (Turtle turtle : turtleList){
        	
        }
    }

}
