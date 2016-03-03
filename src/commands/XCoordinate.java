package commands;

public class XCoordinate extends CommandNode{

	public XCoordinate(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		return getTurtle().getX();
	}

}
