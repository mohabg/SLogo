package commands;

public class PenUp extends CommandNode{

	public PenUp(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setPenUp();
		return 0;
	}

}
