package commands;

import java.util.List;

public class Tell extends MultipleTurtleCommands{

	public Tell(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		
		List<CommandNode> turtleIds = getChildren().get(0).getChildren();
		getTurtleListController().setActiveTurtles(turtleIds);
		return turtleIds.get(turtleIds.size() - 1).run();
	}

}
