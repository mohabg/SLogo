package commands;

public class ArcTangent extends CommandNode{

	public ArcTangent(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double degrees = getChildren().get(0).run();
		setValue(Math.atan(degrees));
		return getValue();
	}

}
