package commands;

public class ID extends CommandNode {

	public ID(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().getID();
	}

}
