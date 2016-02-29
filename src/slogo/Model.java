package slogo;

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
	}

}
