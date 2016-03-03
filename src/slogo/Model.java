package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import commands.CommandNode;
import data.Line;
import data.Point;
import data.ReturnData;

public class Model implements SaveInputs {

	private ReturnData returnData;
	private List<Turtle> turtleList;
	private List<Line> lineList;
	private Map<String, CommandNode> userVariables;
	private Map<String, CommandNode> userFunctions;
	private List<Double> consoleOutputs;
	private List<CommandNode> pastCommands;
	private String BackgroundColor;

	public Model() {
		returnData = new ReturnData();
		userVariables = new HashMap<String, CommandNode>();
		userFunctions = new HashMap<String, CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		pastCommands = new ArrayList<CommandNode>();
		lineList = new ArrayList<Line>();
		Turtle turtle = new Turtle();
		turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);
	}

	public String getConsoleOutput() {
		StringBuilder consoleOutput = new StringBuilder();
		for (Double output : consoleOutputs) {
			consoleOutput.append(output.toString() + ", ");
		}
		return consoleOutput.toString();
	}

	private List<Point> getTurtlePosition() {
		List<Point> turtlePositions = new ArrayList<Point>();
		for (Turtle turtle : turtleList) {
			turtlePositions.add(turtle.getPos());
		}
		return turtlePositions;
	}

	public void setCompileInfo() {
		returnData.addLines(lineList);
		returnData.addTurtlePosition(getTurtlePosition());
		returnData.setVariables(makeVariableOutputs());
		returnData.setFunctions(makeFunctionOutputs());
		returnData.setTurtleImage(getTurtleList().get(0).getImage());
		returnData.addPenBoolean(getTurtleList().get(0).isPenDown());
		// returnData.addBackgroundColor();
	}

	private Map<String, String> makeVariableOutputs() {
		Map<String, String> variableStringOutputs = new TreeMap<String, String>();
		for (String variable : userVariables.keySet()) {
			variableStringOutputs.put(variable, String.valueOf(userVariables.get(variable).getValue()));
		}
		return variableStringOutputs;
	}

	private Set<String> makeFunctionOutputs() {
		Set<String> functionOutputs = new TreeSet<String>();
		for (String fnName : userFunctions.keySet()) {
			functionOutputs.add(fnName);
		}
		return functionOutputs;
	}

	public void setLines(List<Line> lines) {
		lineList = lines;
	}

	public List<Turtle> getTurtleList() {
		return turtleList;
	}

	public void addCommandToHistory(CommandNode command) {
		pastCommands.add(command);
	}

	public void addVariableToMap(CommandNode variable, String variableName) {
		userVariables.put(variableName, variable);
	}

	public void addCommandToMap(CommandNode command, String functionName) {
		userFunctions.put(functionName, command);
	}

	public CommandNode getCommandForVariable(String variable) {
		return userVariables.get(variable);
	}

	public CommandNode getCommandForFunction(String function) {
		return userFunctions.get(function);
	}

	public List<CommandNode> getPastCommands() {
		return pastCommands;
	}

	public ReturnData getReturnData() {
		return returnData;
	}

}