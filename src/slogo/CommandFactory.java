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

	public CommandNode getCommandNode(String commandName, String word){
		// Gets correct command constructor through reflection, instatiates node
		try {
			Class commClass = Class.forName("slogo." + commandName);
			Constructor commConstructor = commClass.getConstructor();
			CommandNode commCalled =  (CommandNode) commConstructor.newInstance();
			try{
			commCalled.setValue(Integer.parseInt(word));
			}catch (NumberFormatException e){
				//TODO:catch error
			}
			return commCalled;

		} catch (ClassNotFoundException e) {
			// Error in function name
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// Error in function inputs
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// Access issues
			// TODO Auto-generated catch block
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

		return null;
	}
	private void fillCommandMap(){

	}
	private void addToCommandMap(String commandName, List steps){

	}
}
