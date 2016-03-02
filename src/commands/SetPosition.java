package commands;

public class SetPosition extends CommandNode{

	public SetPosition(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		double oldX = getTurtle().getX();
		double oldY = getTurtle().getY();
		getTurtle().move(getChildren().get(0).getValue(), getChildren().get(1).getValue());
		double distanceMoved = Math.sqrt(Math.pow((getTurtle().getX() - oldX), 2) + Math.pow((getTurtle().getY() - oldY), 2));
		return distanceMoved;
		
	}

}