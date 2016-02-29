package slogo;

import java.util.ArrayList;

public class TurtleController {
	private ArrayList<Turtle> turtleList = new ArrayList<Turtle>();

	public TurtleController () {
		Turtle turtle = new Turtle();
		turtleList.add(turtle);
	}

	public void update (CommandNode command) {
		for (Turtle turtle : turtleList){
			command.setTurtle(turtle);
			System.out.println("Running " + command);
			command.run();
		}
	}

}
