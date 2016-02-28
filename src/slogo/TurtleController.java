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
			System.out.println("Before: " + turtle.getX());
			System.out.println("Before: " + turtle.getY());
			command.setTurtle(turtle);
			System.out.println(command);
			command.run();
			System.out.println("After: " + turtle.getX());
			System.out.println("After: " + turtle.getX());
			System.out.println(turtle.getPoints().size());
		}
	}

}
