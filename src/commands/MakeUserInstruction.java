package commands;

import java.util.List;

public class MakeUserInstruction extends CommandNode {

	public MakeUserInstruction(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(3);
	}

	@Override
	public void addToChildren(CommandNode commandToAdd) {
		getChildren().add(commandToAdd);
		if (getChildren().size() == parametersNeeded()) {
			List<CommandNode> variables = getChildren().get(1).getChildren();
			Command command = (Command) getChildren().get(0);
			command.setVariables(variables.subList(0, variables.size() - 1));
			CommandNode commandsToExecute = getChildren().get(2);
			command.addToChildren(commandsToExecute);
		}
	}

	@Override
	public double run() {
		if (getChildren().size() == 3) {
			return 1;
		}
		return 0;
	}

}
