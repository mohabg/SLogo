package slogo;
public class Forward extends CommandNode {
	
	public Forward() {
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	double run() {
		setValue(getChildren().get(0).run());
		double pixelsToMove = getValue();
		double x = (getTurtle().getX() + pixelsToMove*Math.sin(getTurtle().getOrientation()));
		double y = (getTurtle().getY() + pixelsToMove*Math.cos(getTurtle().getOrientation()));
		getTurtle().move(x, y);
		return pixelsToMove;
	}

}
