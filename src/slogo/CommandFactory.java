package slogo;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import commands.CommandNode;

public class CommandFactory {

	private HashMap<String, String> CommandMap = new HashMap<String, String>();
	private ResourceBundle myResources;

	public CommandFactory(String language) {
		myResources = ResourceBundle.getBundle(language);
		//fillCommandMap();
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
			CommandNode commCalled =  (CommandNode) commConstructor.newInstance(constant);
			return commCalled;

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	private void fillCommandMap(){
		for (String resourceKey : myResources.keySet()){
			String[] values = myResources.getString(resourceKey).split("[|?\\]+");
			for (int i = 0; i < values.length; i++){
				CommandMap.put(values[i], resourceKey);
			}
		}
	}
	private void addToCommandMap(String commandName, List steps){

	}
}
