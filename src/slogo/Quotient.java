package slogo;

public class Quotient extends CommandNode{

	public Quotient(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	double run() {
		return getChildren().get(0).getValue() / getChildren().get(1).getValue();
	}

}
