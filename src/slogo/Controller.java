package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import commands.CommandNode;
import commands.MakeUserInstruction;
import javafx.scene.shape.Line;

public class Controller {
	private List<Turtle> turtleList;

	public Controller () {
		Turtle turtle = new Turtle();
		turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);

	}

	public Collection<Double> update (CommandNode command) {
		ArrayList<Double> outputs = new ArrayList<Double>();
		if (command.getUsesTurtle()){
			for (Turtle turtle : turtleList){
				command.setTurtle(turtle);
					outputs.add(command.run());
			}
		}
		else{
			outputs.add(command.run());
		}
		return outputs;
	}
	
	private List<Line> makeLines(Turtle turtle){
		ArrayList<Line> lines = new ArrayList<Line>();
		for (int i = 0; i< turtle.getPoints().size() - 1; i++){
			Line line = new Line(turtle.getPoints().get(i).getX(), turtle.getPoints().get(i).getY(), 
					turtle.getPoints().get(i+1).getX(), turtle.getPoints().get(i+1).getY());
			lines.add(line);
		}
	return lines;
	}

}
