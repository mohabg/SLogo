package slogo;
public class Forward extends CommandNode {
	
	public Forward() {
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	void run() {
		getTurtle().setX(getTurtle().getX() + getChildren().get(0).getValue()*Math.sin(getTurtle().getOrientation()));
		getTurtle().setX(getTurtle().getY() + getChildren().get(0).getValue()*Math.cos(getTurtle().getOrientation()));
	}

	@Override
	void setParameters() {		
	}
}
