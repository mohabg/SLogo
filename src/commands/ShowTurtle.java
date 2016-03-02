package commands;

public class ShowTurtle extends CommandNode{

	public ShowTurtle(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		getTurtle().setVisible();
		return 1;
	}

}
