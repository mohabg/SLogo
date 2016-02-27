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

<<<<<<< HEAD
	public CommandNode getCommandNode(String commandName) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		// Gets correct command constructor through reflection
		
		Class commClass;
=======
	public CommandNode getCommandNode(String commandName){
		// Gets correct command constructor through reflection, instatiates node
>>>>>>> 2db17390f676a4c526d51dbc902e6a427a017c2e
		try {
			Class commClass = Class.forName(commandName);
			Constructor commConstructor = commClass.getConstructor();
<<<<<<< HEAD
			CommandNode commCalled = (CommandNode) commConstructor.newInstance();
			return commCalled;
=======
			CommandNode commCalled =  (CommandNode) commConstructor.newInstance();
			return commCalled;

>>>>>>> 2db17390f676a4c526d51dbc902e6a427a017c2e
		} catch (ClassNotFoundException e) {
			// Error in function name
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// Error in function inputs
			e.printStackTrace();
		} catch (SecurityException e) {
			// Access issues
			e.printStackTrace();
		} catch (InstantiationException e) {
			// Can't instantiate particular class
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
<<<<<<< HEAD
		
=======

>>>>>>> 2db17390f676a4c526d51dbc902e6a427a017c2e
		return null;
	}
	private void fillCommandMap(){

	}
	private void addToCommandMap(String commandName, List steps){

	}
}
