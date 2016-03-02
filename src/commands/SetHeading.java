package commands;

public class SetHeading extends CommandNode{

	public SetHeading(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		setValue(getChildren().get(0).run());
		double oldOrientation = getTurtle().getOrientation();
		getTurtle().setOrientation(getValue());
		return Math.abs(oldOrientation - getValue());
	}

}
