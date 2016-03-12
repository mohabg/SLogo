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
		List<CommandNode> listStart = getChildren().get(0).getChildren();
		List<CommandNode> turtleIds = listStart.subList(0, listStart.size() - 1);
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
