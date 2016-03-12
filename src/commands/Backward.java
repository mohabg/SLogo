package commands;

public class Backward extends CommandNode{

	public Backward(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		setValue(getChildren().get(0).run());
		double newXLocation = getTurtle().getX() - getValue()*Math.sin(Math.toRadians(getTurtle().getOrientation()));
		double newYLocation = getTurtle().getY() - getValue()*Math.cos(Math.toRadians(getTurtle().getOrientation()));
		getTurtle().move(newXLocation, newYLocation);
		return getValue();
	}

}
