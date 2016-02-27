package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
	
	private HashMap<String, String> CommandMap = new HashMap<String, String>();

	public CommandFactory() {
		fillCommandMap();
		// TODO Auto-generated constructor stub
	}

	public Command getCommand(String commandName, int iterations){
		// Gets correct command constructor through reflection
		
		Class commClass;
		try {
			commClass = Class.forName(CommandMap.get(commandName));
			Constructor commConstructor = commClass.getConstructor();
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
		}
		
		
		// Need way to get specific arguments for specific constructor in
		Command commCalled =  new Command(iterations);//commConstructor.newInstance();
		
		return commCalled;
	}
	private void fillCommandMap(){
		
	}
	private void addToCommandMap(String commandName, List steps){
		
	}
}
