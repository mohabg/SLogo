package commands;

public class For extends CommandNode{

	public For(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		CommandNode parameters = getChildren().get(0);
		CommandNode variable = parameters.getChildren().get(0);
		CommandNode start = parameters.getChildren().get(1);
		CommandNode end = parameters.getChildren().get(2);
		CommandNode increment = parameters.getChildren().get(3);
		CommandNode commands = getChildren().get(1);
		double lastExecutedCommandValue = 0;
		while(start.getValue() < end.getValue()){
			variable.setValue(start.getValue());
			start.setValue(start.getValue() + increment.getValue());
			lastExecutedCommandValue = commands.run();
		}
		return lastExecutedCommandValue;
	}
}
