package commands;

public class DoTimes extends CommandNode{

	public DoTimes(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		CommandNode parameters = getChildren().get(0);
		CommandNode variable = parameters.getChildren().get(0);
		CommandNode limitExpression = parameters.getChildren().get(1);
		CommandNode commands = getChildren().get(1);
		int iteration = 1;
		double lastExecutedCommandValue = 0;
		double limit = limitExpression.run();
		while(iteration <= limit){
			variable.setValue(iteration++);
			lastExecutedCommandValue = commands.run();
		}
		return lastExecutedCommandValue;
	}

}
