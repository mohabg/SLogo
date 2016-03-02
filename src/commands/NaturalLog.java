package commands;

public class NaturalLog extends CommandNode{

	public NaturalLog(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double value = getChildren().get(0).run();
		setValue(Math.log(value));
		return getValue();
	}

}
