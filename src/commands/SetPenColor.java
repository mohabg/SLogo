package commands;

public class SetPenColor extends CommandNode {

	public SetPenColor(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setPenColor(getValue());
		return getValue();
	}

}
