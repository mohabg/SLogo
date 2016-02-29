package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Model {

	private Map<CommandNode, Double> userVariables;
	private Set<CommandNode> userFunctions;
	private List<Double> consoleOutputs;
	private SlogoManager manager;
	
	public Model() {
		userVariables = new TreeMap<CommandNode, Double>();
		userFunctions = new TreeSet<CommandNode>();
		consoleOutputs = new ArrayList<Double>();
		manager = new SlogoManager();
	}
	
	private void getCompileInfo(){
		manager.addFunctions(userFunctions);
		manager.addVariables(userVariables);
		consoleOutputs = manager.getOutputs();
	}
	public void compile(String input){
		try {
			manager.compile(input);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getCompileInfo();
	}

}
