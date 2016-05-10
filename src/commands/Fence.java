package commands;

public class Fence extends CommandNode {

	public Fence(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(0);
	}

	@Override
	public double run() {
		getTurtle().setFence(true);
		return 3;
	}

}
