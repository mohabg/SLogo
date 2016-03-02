package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import commands.CommandNode;
import commands.MakeUserInstruction;
import data.ReturnData;
import javafx.scene.shape.Line;

public class Controller {

	private Model myModel;
	private Parser myParser;

	public Controller () {
		myModel = new Model();
		myParser = new Parser("English", myModel);
	}
	public void initialize(){
		updateModel();
	}
	public String compile (String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // called in every frame
		List<CommandNode> currCommandTree = myParser.interpret(input);
		for (CommandNode command: currCommandTree){
			update(command);
		}
		updateModel();
		return myModel.getConsoleOutput();
	}
	public Collection<Double> update (CommandNode command) {
		ArrayList<Double> outputs = new ArrayList<Double>();
		if (command.getUsesTurtle()){
			for (Turtle turtle : myModel.getTurtleList()){
				command.setTurtle(turtle);
				outputs.add(command.run());
			}
		}
		else{
			outputs.add(command.run());
		}

		return outputs;
	}

	private void updateModel(){
		myModel.setLines(makeLines());
		myModel.setCompileInfo();
	}
	public List<Line> makeLines(){
		ArrayList<Line> lines = new ArrayList<Line>();
		for (Turtle turtle : myModel.getTurtleList()){
			for (int i = 0; i< turtle.getPoints().size() - 1; i++){
				Line line = new Line(turtle.getPoints().get(i).getX(), turtle.getPoints().get(i).getY(), 
						turtle.getPoints().get(i+1).getX(), turtle.getPoints().get(i+1).getY());
				lines.add(line);
			}
		}
		return lines;
	}
	public ReturnData getReturnData(){
		return myModel.getReturnData();
	}
}
