package slogo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SlogoManager {
	private Controller controller;
	private Parser myParser;
	private List<CommandNode> currCommandTree;
	private List<CommandNode> pastCommands;
	private Map<CommandNode, Double> userVariables;
	private Map<String, CommandNode> userFunctions;
	private List<Double> outputs;

	// points that are drawn per frame, maintains history

	public SlogoManager () {
		controller = new Controller();
		myParser = new Parser();
		currCommandTree = new ArrayList<CommandNode>();
		pastCommands = new ArrayList<CommandNode>();
		userVariables = new HashMap<CommandNode, Double>();
		userFunctions = new HashMap<String, CommandNode>();
		outputs = new ArrayList<Double>();
	}
	public void initialize(){
	}
	public void update(CommandNode command){
		pastCommands.add(command);
		if (command.getClass().equals("MakeUserInstruction")){
			userFunctions.put(((Make)command).getName(), command);
		}
		if (command.getClass().equals("Make")){
			userVariables.put(command, command.getValue());
		}
		controller.update(command);
	}
	public void compile (String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // called in every frame
		currCommandTree = myParser.interpret(input);
		for (CommandNode command: currCommandTree){
			update(command);
		}

	}
	public void addVariables(Map variables){
		for(CommandNode variable : userVariables.keySet()){
			variables.put(variable, userVariables.get(variable));			
		}
	}
	public void addFunctions(Set functions){
		for (int i=0; i < userFunctions.size(); i++){
			functions.add(userFunctions.get(i));
		}
	}
	public List getOutputs(){
		return outputs;
	}
}
