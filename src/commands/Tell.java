package commands;

import java.util.List;
//This entire file is part of my masterpiece
//Mohab Gabal
/*
 * This is included to demonstrate an example of the ease with which a new multiple turtle command
 * can be added.
 */
public class Tell extends MultipleTurtleCommands{

	public Tell(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		CommandNode listStart = getChildren().get(0);
		List<CommandNode> turtleIds = listStart.getChildren().subList(0, listStart.getChildren().size() - 1 );
		getTurtleListController().setActiveTurtles(turtleIds);
		return turtleIds.get(turtleIds.size() - 1).run();
	}

}
