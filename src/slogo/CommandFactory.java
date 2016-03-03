package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.ResourceBundle;

import commands.Command;
import commands.CommandNode;
import commands.Variable;

public class CommandFactory {

	private HashMap<String, String> CommandMap = new HashMap<String, String>();

	private Controller controller;
	private SaveInputs inputSaver;
	private ResourceBundle myResources;

	public CommandFactory(SaveInputs model) {
		inputSaver = model;
		myResources = ResourceBundle.getBundle("resources/Errors");
	}

	public CommandNode getCommandNode(String commandName, String word){
		double constant = 0;
		System.out.println(word);
		// Gets correct command constructor through reflection, instantiates node
		constant = Integer.parseInt(word);
		Class commClass;
		try {
			commClass = Class.forName("commands." + commandName);
			System.out.println("Creating " + word + " " + commandName);
			Constructor commConstructor = commClass.getConstructor(double.class);
			CommandNode command =  (CommandNode) commConstructor.newInstance(constant);
			command.setInput(word);
			inputSaver.addCommandToHistory(command);
			command = getVariableOrCommandFromModel(word, command);
			return command;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			throw new SlogoException(String.format(myResources.getString("CommandNameError"), commandName));
		}
		return null;
	}

	private CommandNode getVariableOrCommandFromModel(String word, CommandNode command) {
		if(command instanceof Variable){
			CommandNode storedCommandForVariable = inputSaver.getCommandForVariable(word);
			if(storedCommandForVariable != null){
				return storedCommandForVariable;
			}
			else{
				inputSaver.addVariableToMap(command, word);
			}
		}
		if(command instanceof Command){
			CommandNode storedCommand = inputSaver.getCommandForFunction(word);
			if(storedCommand != null){
				return storedCommand;
			}
			else{
				inputSaver.addCommandToMap(command, word);
			}
		}
		return command;
	}
}
