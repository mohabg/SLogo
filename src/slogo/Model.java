package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import commands.CommandNode;

public class Model {

	private Map<String, CommandNode> userVariables;
	private Map<String, CommandNode> userFunctions;
	private List<Double> consoleOutputs;
	private List<CommandNode> pastCommands;
	
	public Model() {
		userVariables = new HashMap<String, CommandNode>();
		userFunctions = new HashMap<String, CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		pastCommands = new ArrayList<CommandNode>();
	}
	protected void addCommandToHistory(CommandNode command){
		System.out.println("adding to " + pastCommands.getClass().hashCode());
		pastCommands.add(command);
	}
	protected CommandNode getCommandForVariable(String variable){
		return userVariables.get(variable);
	}
	
	protected CommandNode getCommandForFunction(String function){
		return userFunctions.get(function);
	}
	
	protected List<CommandNode> getPastCommands(){
		return pastCommands;
	}
	protected void addVariableToMap(CommandNode variable, String variableName){
		userVariables.put(variableName, variable);;
	}
	
	protected void addCommandToMap(CommandNode command, String functionName){
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