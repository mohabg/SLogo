package commands;

public class Quotient extends CommandNode{

	public Quotient(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		return getChildren().get(0).run() / getChildren().get(1).run();
	}

}
