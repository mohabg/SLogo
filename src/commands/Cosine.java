package commands;

public class Cosine extends CommandNode{

	public Cosine(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double degrees = getChildren().get(0).run();
		setValue(Math.cos(Math.toRadians(degrees)));
		return getValue();
	}

}
