package slogo;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point2D;


public class SlogoManager {
	private TurtleController turtleController;
	private LogicController logicController;
	private Parser myParser;
	private List<CommandNode> currCommandTree;
	private List<CommandNode> pastCommands;
	private Map<CommandNode, Double> userVariables;
	private List<CommandNode> userFunctions;

	// points that are drawn per frame, maintains history

	public SlogoManager () {
		turtleController = new TurtleController();
		logicController = new LogicController();
		pastCommands = new ArrayList<>();
		myParser = new Parser();
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
	}
	public void compile (String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // called in every frame
		currCommandTree = myParser.interpret(input);
		for (CommandNode command: currCommandTree){
			update(command);
		}
		// Send data to GUI here

	}
}
