package commands;

public class Sine extends CommandNode{

	public Sine(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double degrees = getChildren().get(0).run();
		setValue(Math.sin(Math.toRadians(degrees)));
		return getValue();
	}

}
