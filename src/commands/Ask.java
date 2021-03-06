package commands;

import java.util.ArrayList;
import java.util.List;

import slogo.Turtle;

public class Ask extends MultipleTurtleCommands{

	public Ask(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		CommandNode listStart = getChildren().get(0);
		List<CommandNode> turtleIds = listStart.getChildren().subList(0, listStart.getChildren().size() - 1);
		List<CommandNode> commandsToExecute = getChildren().get(1).getChildren();
		List<Turtle> turtlesToUse = new ArrayList<>();
		
		for(Turtle turtle : getTurtleListController().getTurtleList()){
			for(CommandNode id : turtleIds){
				if(turtle.getID() == id.run()){
					turtlesToUse.add(turtle);
				}
			}
		}
		return setTurtlesAndRun(turtlesToUse, commandsToExecute);
	}

}
