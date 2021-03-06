package commands;

public class Left extends CommandNode{

	public Left(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		setValue(getChildren().get(0).run());
		getTurtle().turn(360 - getValue());
		return getValue();
	}

}
