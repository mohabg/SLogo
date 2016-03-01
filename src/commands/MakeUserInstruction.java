package commands;

import java.util.List;

public class MakeUserInstruction extends CommandNode {

	private boolean ranOnce;

	public MakeUserInstruction(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(3);
		ranOnce = false;
	}

	@Override
	public double run() {
		if (!ranOnce) {
			ranOnce = true;
			List<CommandNode> variables = getChildren().get(1).getChildren();
			// Remove the ListEnd
			variables.remove(variables.size() - 1);
			CommandNode command = getChildren().get(0);
			if (command instanceof Command) {
				((Command) command).setVariables(variables);
			}
			CommandNode commandsToExecute = getChildren().get(2);
			command.addToChildren(commandsToExecute);
			if (commandsToExecute.getChildren().size() > 0) {
				return 1;
			}
		}
		return 0;
	}

}
