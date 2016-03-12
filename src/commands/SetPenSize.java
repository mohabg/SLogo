package commands;

public class SetPenSize extends CommandNode {

	public SetPenSize(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setPenThickness(getValue());
		return getValue();
	}

}
