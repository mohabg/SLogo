package commands;

public class ClearScreen extends CommandNode {

	public ClearScreen(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		double distanceMoved = Math.sqrt(Math.pow(getTurtle().getX(), 2) + Math.pow(getTurtle().getY(), 2));
		getTurtle().move(0, 0);
		getTurtle().setOrientation(0);
		getTurtle().setVisible();
		getTurtle().clearLines();
		return distanceMoved;
	}

}
