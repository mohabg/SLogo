package commands;

public class SetPenColor extends CommandNode {

	public SetPenColor(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		double color = getChildren().get(0).run();
		getTurtle().setPenColor(color);
		return getValue();
	}

}
