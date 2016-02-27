package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	
	private HashMap<String, String> CommandMap = new HashMap<String, String>();

	public CommandFactory() {
		fillCommandMap();
		// TODO Auto-generated constructor stub
	}

	public CommandNode getCommandNode(String commandName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// Gets correct command constructor through reflection
		
		Class commClass;
		try {
			commClass = Class.forName(CommandMap.get(commandName));
			Constructor commConstructor = commClass.getConstructor();
			CommandNode commCalled = (CommandNode) commConstructor.newInstance();
			return commCalled;
		} catch (ClassNotFoundException e) {
			// Error in function name
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// Error in function inputs
			e.printStackTrace();
		} catch (SecurityException e) {
			// Access issues
			e.printStackTrace();
		}
		
		return null;
	}
	private void fillCommandMap(){
		
	}
	private void addToCommandMap(String commandName, List steps){
		
	}
}
