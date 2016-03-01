package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import commands.CommandNode;
import observers.ReturnData;
import sun.reflect.generics.tree.Tree;

public class Model {

	private Parser myParser;
	private Controller myController;
	private ReturnData returnData;
	private Map<String, CommandNode> userVariables;
	private Map<String, CommandNode> userFunctions;
	private List<Double> consoleOutputs;
	private List<CommandNode> pastCommands;
	private static Model model;
	
	public static Model getModelInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}
	
	public Model() {
		// make this set from user input, along with syntax
		myParser = new Parser("English");
		myController = new Controller();
		returnData = new ReturnData();
		userVariables = new HashMap<String, CommandNode>();
		userFunctions = new HashMap<String, CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		pastCommands = new ArrayList<CommandNode>();
	}
	
	public CommandNode getCommandForVariable(String variable){
		return userVariables.get(variable);
	}
	
	public CommandNode getCommandForFunction(String function){
		return userFunctions.get(function);
	}
	
	public void addVariableToMap(CommandNode variable, String variableName){
		System.out.println("Adding " + variable +  " " + variableName + " to map");
		userVariables.put(variableName, variable);;
	}
	public void compile (String input) {
		try {
			List<CommandNode> currCommandTree = myParser.interpret(input);
			for (CommandNode command: currCommandTree){
				consoleOutputs.addAll(myController.update(command));
			}
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addCommandToMap(CommandNode command, String functionName){
		userFunctions.put(functionName, command);
	}
	private void getCompileInfo(){
		returnData.addLines(myController.makeLines());
		returnData.addTurtlePosition(myController.getTurtlePosition());
		//returnData.addTurtleImage(myController.getTurtleImage());
		//returnData.addBackgroundColor();
	}
	
}