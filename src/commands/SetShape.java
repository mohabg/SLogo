package commands;

public class SetShape extends CommandNode {

	public SetShape(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setShape(getValue());
		return getValue();
	}

}
