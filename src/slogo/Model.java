package slogo;


import java.util.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import commands.CommandNode;
import data.Line;
import data.Point;
import data.ReturnData;
import data.TurtleData;


public class Model implements SaveInputs, TurtleListController{

	private ReturnData returnData;
	private List<Turtle> turtleList;
	private List<Turtle> activeTurtles;
	private List<Line> lineList;
	private Map<String, CommandNode> userVariables;
	private Map<String, CommandNode> userFunctions;
	private List<Double> myPalette;
	private List<Double> consoleOutputs;
	private List<CommandNode> pastCommands;
	private double BackgroundColor;
	private Collection<MyStamp> stamps;

	public Model() {
		returnData = new ReturnData();
		userVariables = new HashMap<String, CommandNode>();
		userFunctions = new HashMap<String, CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		pastCommands = new ArrayList<CommandNode>();
		lineList = new ArrayList<Line>();
		Turtle turtle = new Turtle(1);
		turtleList = new ArrayList<Turtle>();
		turtleList.add(turtle);
		activeTurtles = new ArrayList<>();
		activeTurtles.add(turtle);
	}

	public String getConsoleOutput() {
		StringBuilder consoleOutput = new StringBuilder();
		for (Double output : consoleOutputs) {
			consoleOutput.append(output.toString() + ", ");
		}
		return consoleOutput.toString();
	}

	private void addTurtle(double id){
		int maxId = turtleList.size();
		if(id > maxId){
			for(int i = maxId + 1; i <= id; i++){
				Turtle turtle = new Turtle(i);
				turtleList.add(turtle);		
			}
		}
	}

	public Map<String, Double> returnHistory(){
		Map<String, Double> history = new HashMap<String, Double>();
		StringBuilder commandName = new StringBuilder();
		for (CommandNode command : pastCommands) {
			String name = command.toString();
			double value = command.getValue();
			if (!name.equals("Constant")) {
				commandName.append(name + " ");
				System.out.println("command");
			}
			else {
				history.put(commandName.toString(), value);
				commandName = new StringBuilder();
			}
		}
		return history;
	}
	public List<Turtle> getTurtleList() {
		return turtleList;
	}
	public List<Turtle> getActiveTurtles(){
		return activeTurtles;
	}
	public void setActiveTurtles(List<CommandNode> activeTurtlesIds){
		List<Double> idValues = new ArrayList<>();
		double maxId = 0;
		for(CommandNode id : activeTurtlesIds){
			double newId = id.run();
			idValues.add(newId);
			if(newId > maxId){
				maxId = newId;
			}
		}
		addTurtle(maxId);
		activeTurtles.clear();
		for(Turtle turtle : turtleList){
			for(double id : idValues){
				if(turtle.getID() == id){
					activeTurtles.add(turtle);
				}
			}
		}
	}
	public void addStamp(double x, double y, double orientation, String image){
		Point stampLoc = new Point(x, y, orientation);
		stamps.add(new MyStamp(image, stampLoc));
	}

	public void setCompileInfo() {
		returnData.addLines(lineList);
		returnData.setVariables(makeVariableOutputs());
		returnData.setFunctions(makeFunctionOutputs());
		List<TurtleData> turtleData = new ArrayList<TurtleData>();
		turtleData.addAll(turtleList);
		returnData.setTurtles(turtleData);
		returnData.addBackgroundColor(BackgroundColor);
		returnData.setHistory(returnHistory());
	}

	public void addPaletteColor(double rgb){
		myPalette.add(rgb);
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

	public void addCommandToHistory(CommandNode command) {
		pastCommands.add(command);
		System.out.println("Command: " + command.toString() + "Value: " + (command.getValue()));
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

	@Override
	public void clearStamps() {
		stamps.clear();

	}
}
