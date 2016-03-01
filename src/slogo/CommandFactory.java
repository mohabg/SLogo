package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.util.HashMap;

import commands.Command;
import commands.CommandNode;
import commands.Variable;

public class CommandFactory {

	private HashMap<String, String> CommandMap = new HashMap<String, String>();
	private Controller controller;
	
	public CommandFactory() {
		fillCommandMap();
		controller = new Controller();
	}
	
	
	public CommandNode getCommandNode(String commandName, String word){
		double constant = 0;
		// Gets correct command constructor through reflection, instantiates node
		try{
			 constant = Integer.parseInt(word);
			}catch (NumberFormatException e){
			}
		try {
			Class commClass = Class.forName("commands." + commandName);
			Constructor commConstructor = commClass.getConstructor(double.class);
			CommandNode command =  (CommandNode) commConstructor.newInstance(constant);
			command.setInput(word);
			controller.addCommandToHistory(command);
			command = getVariableOrCommandFromModel(word, command);
			return command;

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
}

	private CommandNode getVariableOrCommandFromModel(String word, CommandNode command) {
		if(command instanceof Variable){
			CommandNode storedCommandForVariable = controller.getCommandForVariable(word);
			if(storedCommandForVariable != null){
				return storedCommandForVariable;
			}
			else{
				controller.addVariableToMap(command, word);
			}
		}
		if(command instanceof Command){
			CommandNode storedCommand = controller.getCommandForFunction(word);
			if(storedCommand != null){
				return storedCommand;
			}
			else{
				controller.addCommandToMap(command, word);
			}
		}
		return command;
	}
	private void fillCommandMap(){

	}
	private void addToCommandMap(String commandName, List steps){

	}
}
