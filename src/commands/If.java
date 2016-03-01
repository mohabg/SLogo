package commands;

public class If extends CommandNode{

	public If(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		CommandNode expression = getChildren().get(0);
		if(expression.run() != 0){
			CommandNode commands = getChildren().get(1);
			setValue(commands.run());
			return getValue();
		}
		return 0;
	}

}
