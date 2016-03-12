package commands;

import java.util.List;

public class Tell extends MultipleTurtleCommands{

	public Tell(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		List<CommandNode> listStart = getChildren().get(0).getChildren();
		List<CommandNode> turtleIds = listStart.subList(0, listStart.size() - 1 );
		getTurtleListController().setActiveTurtles(turtleIds);
		return turtleIds.get(turtleIds.size() - 1).run();
	}

}
