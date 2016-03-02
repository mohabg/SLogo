package commands;

public class Power extends CommandNode{

	public Power(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		double base = getChildren().get(0).run();
		double exponent = getChildren().get(1).run();
		setValue(Math.pow(base, exponent));
		return getValue();
	}

}
