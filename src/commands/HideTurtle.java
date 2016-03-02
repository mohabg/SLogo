package commands;

public class HideTurtle extends CommandNode{

	public HideTurtle(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setInvisible();
		return 0;
	}

}
