package commands;

public class YCoordinate extends CommandNode{

	public YCoordinate(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().getY();
	}

}
