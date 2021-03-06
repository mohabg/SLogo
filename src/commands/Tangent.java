package commands;

public class Tangent extends CommandNode{

	public Tangent(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double degrees = getChildren().get(0).run();
		setValue(Math.tan(Math.toRadians(degrees)));
		return getValue();
	}

}
