package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import commands.Command;
import commands.CommandNode;
import commands.Variable;

public class CommandFactory {

	private HashMap<String, String> CommandMap = new HashMap<String, String>();

	private Controller controller;
	private SaveInputs inputSaver;

	public CommandFactory(SaveInputs model) {
		inputSaver = model;
	}

	public CommandNode getCommandNode(String commandName, String word){
		double constant = 0;
		// Gets correct command constructor through reflection, instantiates node
		try{
			constant = Integer.parseInt(word);
		}catch (NumberFormatException e){
		}
			System.out.println("Creating " + word + " " + commandName);
			Class commClass;
			try {
				commClass = Class.forName("commands." + commandName);

				Constructor commConstructor = commClass.getConstructor(double.class);
							CommandNode command =  (CommandNode) commConstructor.newInstance(constant);
				command.setInput(word);
				inputSaver.addCommandToHistory(command);
				command = getVariableOrCommandFromModel(word, command);
				return command;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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