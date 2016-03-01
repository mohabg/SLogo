package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import commands.CommandNode;
import commands.MakeUserInstruction;
import javafx.scene.shape.Line;

public class Controller {
	private List<Turtle> turtleList;
	private Model model;
	
	public Controller () {
		Turtle turtle = new Turtle();
		turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);
		model = new Model();
	}
	public void addCommandToHistory(CommandNode command){
		model.addCommandToHistory(command);
	}
	public void addVariableToMap(CommandNode variable, String variableName){
		model.addVariableToMap(variable, variableName);
	}
	
	public void addCommandToMap(CommandNode command, String functionName){
		model.addCommandToMap(command, functionName);
	}
	public CommandNode getCommandForVariable(String variable){
		return model.getCommandForVariable(variable);
	}
	
	public CommandNode getCommandForFunction(String function){
		return model.getCommandForFunction(function);
	}
	public List<CommandNode> getPastCommands(){
		System.out.println("hash " + model.getPastCommands().hashCode());
		return model.getPastCommands();
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
