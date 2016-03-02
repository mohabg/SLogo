package commands;

public class isPenDown extends CommandNode{

	public isPenDown(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().isPenDown();
	}

}
