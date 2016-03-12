package commands;

import java.util.List;

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
