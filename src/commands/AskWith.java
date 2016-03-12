package commands;

import java.util.ArrayList;
import java.util.List;

import slogo.Turtle;

public class AskWith extends MultipleTurtleCommands{

	public AskWith(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		CommandNode listStart = getChildren().get(0);
		CommandNode turtleCondition = listStart.getChildren().get(0);
		List<CommandNode> commandsToExecute = getChildren().get(1).getChildren();
		List<Turtle> turtlesToUse = new ArrayList<>();
		for(Turtle turtle : getTurtleListController().getTurtleList()){
			turtleCondition.setTurtle(turtle);
			if(turtleCondition.run() == 1){
				turtlesToUse.add(turtle);
			}
		}
		return setTurtlesAndRun(turtlesToUse, commandsToExecute);
	}

}
