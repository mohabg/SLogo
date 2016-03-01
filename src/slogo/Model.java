package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import commands.CommandNode;

public class Model {

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
	
	private Model() {
		userVariables = new HashMap<String, CommandNode>();
		userFunctions = new HashMap<String, CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		pastCommands = new ArrayList<CommandNode>();
	}
	public void addCommandToHistory(CommandNode command){
		pastCommands.add(command);
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
	public void addVariableToMap(CommandNode variable, String variableName){
		userVariables.put(variableName, variable);;
	}
	
	public void addCommandToMap(CommandNode command, String functionName){
		userFunctions.put(functionName, command);
	}
	private void getCompileInfo(){
		//manager.addFunctions(userFunctions);
		//manager.addVariables(userVariables);
		//consoleOutputs = manager.getOutputs();
	}
	public void compile(String input){
		/*try {
			manager.compile(input);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getCompileInfo();*/
	}

}