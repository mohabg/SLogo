package slogo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class SlogoManager {
	private TurtleController turtleController;
	private LogicController logicController;
	private Parser myParser;
	private List<CommandNode> currCommandTree;
	private List<CommandNode> pastCommands;
	private Map<CommandNode, Double> userVariables;
	private List<CommandNode> userFunctions;
	private Collection<Double> outputs;

	// points that are drawn per frame, maintains history

	public SlogoManager () {
		turtleController = new TurtleController();
		logicController = new LogicController();
		myParser = new Parser();
		currCommandTree = new ArrayList<CommandNode>();
		pastCommands = new ArrayList<CommandNode>();
		userVariables = new HashMap<CommandNode, Double>();
		userFunctions = new ArrayList<CommandNode>();
		outputs = new ArrayList<Double>();
	}
	public void initialize(){
	}
	public void update(CommandNode command){
		pastCommands.add(command);
		if (command.getClass().equals("MakeUserInstruction")){
			userFunctions.add(command);
		}
		if (command.getClass().equals("Make")){
			userVariables.put(command, command.getValue());
		}
		if(command.getUsesTurtle()){
			turtleController.update(command);
		}
		else {
			logicController.update(command);
		}
		if (command.getChildren().size() != 0){
			for (CommandNode subcommand : command.getChildren()){
				update(subcommand);
			}
		}
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
}
