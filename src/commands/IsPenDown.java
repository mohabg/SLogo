package commands;

public class IsPenDown extends CommandNode{

	public IsPenDown(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().isPenDown();
	}

}
