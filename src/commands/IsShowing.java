package commands;

public class IsShowing extends CommandNode{

	public IsShowing(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().isVisible();
	}

}
