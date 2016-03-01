package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import commands.CommandNode;

public class TurtleController {
	private List<Turtle> turtleList;
	private List<Double> turtleOutput;

	public TurtleController () {
		Turtle turtle = new Turtle();
		turtleOutput = new ArrayList<Double>();
		turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);
		
	}

	public Collection<Double> update (CommandNode command) {
		ArrayList<Double> outputs = new ArrayList<Double>();
		for (Turtle turtle : turtleList){
			command.setTurtle(turtle);
			outputs.add(command.run());
		}
		return outputs;
	}
	public List getTurtleOutput(){
		return turtleOutput;
	}

}