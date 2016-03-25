package commands;
import java.util.List;
import slogo.Turtle;
import slogo.TurtleListController;

//This entire file is part of my masterpiece.
//Mohab Gabal
/*
 * This class is the second level of abstraction in the command hierarchy that is responsible for the 
 * implementation of the multiple turtle commands. It extends the CommandNode class, and its only 
 * difference is the presence of the interface TurtleListController. Also, there is a method called
 * setTurtlesAndRun in which all of the turtles in the commands that are to be executed by this command.
 * recursively, have each turtle in the list of active turtles set to them, and are then executed. This
 * removes a lot of potential duplicated code, across the subclasses. 
 */
public abstract class MultipleTurtleCommands extends CommandNode{
	
	private TurtleListController model;
	
	public MultipleTurtleCommands(double val) {
		super(val);
		setUsesTurtle(true);
	}

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
			if(command.getUsesTurtle()){
				for(Turtle turtle : activeTurtles){
					command.setTurtle(turtle);
					lastExecutedCommandValue = command.run();
				}
			}
			else{
				lastExecutedCommandValue = command.run();
			}
		}
		return lastExecutedCommandValue;
	}
}
