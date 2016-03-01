package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import commands.Command;
import commands.CommandNode;
import commands.Variable;
import slogo.Model;

public class CommandFactory {

	private HashMap<String, String> CommandMap = new HashMap<String, String>();
	private Model model;
	
	public CommandFactory() {
		fillCommandMap();
		model = Model.getModelInstance();
		// TODO Auto-generated constructor stub
	}

	public CommandNode getCommandNode(String commandName, String word){
		double constant = 0;
		// Gets correct command constructor through reflection, instatiates node
		try{
			 constant = Integer.parseInt(word);
			}catch (NumberFormatException e){
			}
		try {
			Class commClass = Class.forName("commands." + commandName);
			Constructor commConstructor = commClass.getConstructor(double.class);
			CommandNode command =  (CommandNode) commConstructor.newInstance(constant);
			command.setInput(word);
			model.addCommandToHistory(command);
			if(command instanceof Variable){
				CommandNode storedCommandForVariable = model.getCommandForVariable(word);
				if(storedCommandForVariable != null){
					return storedCommandForVariable;
				}
				else{
					model.addVariableToMap(command, word);
				}
			}
			if(command instanceof Command){
				CommandNode storedCommand = model.getCommandForFunction(word);
				if(storedCommand != null){
					return storedCommand;
				}
				else{
					model.addCommandToMap(command, word);
				}
			}
			return command;

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
}
	private void fillCommandMap(){

	}
	private void addToCommandMap(String commandName, List steps){

	}
}
