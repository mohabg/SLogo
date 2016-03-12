package commands;
import java.util.ArrayList;
import java.util.List;

import slogo.Turtle;
import slogo.TurtleListController;

public abstract class MultipleTurtleCommands extends CommandNode{
	
	private TurtleListController model;
	
	public MultipleTurtleCommands(double val) {
		super(val);
		setUsesTurtle(true);
	}
	@Override
	public void setTurtleListController(TurtleListController controller){
		model = controller;
	}
	protected TurtleListController getTurtleListController(){
		return model;
	}
	protected double setTurtlesAndRun(List<Turtle> activeTurtles, List<CommandNode> commandsToExecute){;
		double lastExecutedCommandValue = 0;
		for(int i = 0; i < commandsToExecute.size(); i++){
			CommandNode command = commandsToExecute.get(i);
			for(Turtle turtle : activeTurtles){
				command.setTurtle(turtle);
				lastExecutedCommandValue = command.run();
			}
		}
		return lastExecutedCommandValue;
	}
}
