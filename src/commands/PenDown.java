package commands;

public class PenDown extends CommandNode{

	public PenDown(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setPenDown();
		return 1;
	}

}
