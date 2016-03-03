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
			CommandNode command = getChildren().get(0);
			if (command instanceof Command) {
				//Add everything except ListEnd to command's variables
				((Command) command).setVariables(variables.subList(0, variables.size() - 1));
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
