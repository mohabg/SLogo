package commands;

public class Heading extends CommandNode {

	public Heading(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().getOrientation();
	}

}
