package commands;

public class Right extends CommandNode{

	public Right(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		setValue(getChildren().get(0).run());
		getTurtle().turn(getValue());
		return getValue();
	}

}
