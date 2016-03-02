package commands;

public class Random extends CommandNode{

	public Random(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double randomDouble = Math.random();
		setValue(getChildren().get(0).run() * randomDouble);
		return getValue();
	}

}
