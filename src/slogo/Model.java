package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import commands.CommandNode;
import javafx.geometry.Point2D;
import javafx.scene.shape.Line;
import observers.ReturnData;
import sun.reflect.generics.tree.Tree;

public class Model implements SaveInputs{

	private ReturnData returnData;
	private List<Turtle> turtleList;
	private List<Line> lineList;
	private Map<String, CommandNode> userVariables;
	private Map<String, CommandNode> userFunctions;
	private List<Double> consoleOutputs;
	private List<CommandNode> pastCommands;


	public Model() {
		returnData = new ReturnData();
		userVariables = new HashMap<String, CommandNode>();
		userFunctions = new HashMap<String, CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		pastCommands = new ArrayList<CommandNode>();
		Turtle turtle = new Turtle();
		turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);
	}
	public String getConsoleOutput() {
		StringBuilder consoleOutput = new StringBuilder();
		for(Double output : consoleOutputs){
			consoleOutput.append(output.toString() + ", ");
		}
		return consoleOutput.toString();
	}
	private List<Point2D> getTurtlePosition(){
		List<Point2D> turtlePositions = new ArrayList<Point2D>();
		for(Turtle turtle : turtleList){
			turtlePositions.add(turtle.getPoints().get(turtle.getPoints().size() - 1));
		}
		return turtlePositions;
	}
	
	public void setCompileInfo(){
		returnData.addLines(lineList);
		returnData.addTurtlePosition(getTurtlePosition());
		//returnData.addVariables()
		//returnData.addTurtleImage(.getTurtleImage());
		//returnData.addBackgroundColor();
	}
	//private Map<String, String> makeVariableOutputs(){
		
	//}
	public void setLines(List<Line> lines){
		lineList = lines;
	}
	public List<Turtle> getTurtleList(){
		return turtleList;
	}
	
	public void addCommandToHistory(CommandNode command){
		System.out.println("adding to " + pastCommands.getClass().hashCode());
		pastCommands.add(command);
	}
	
	public void addVariableToMap(CommandNode variable, String variableName){
		userVariables.put(variableName, variable);
	}
	
	public void addCommandToMap(CommandNode command, String functionName){
		userFunctions.put(functionName, command);
	}
	
	public CommandNode getCommandForVariable(String variable){
		return userVariables.get(variable);
	}

	public CommandNode getCommandForFunction(String function){
		return userFunctions.get(function);
	}

	public List<CommandNode> getPastCommands(){
		return pastCommands;
	}
	

}