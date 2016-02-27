package slogo;
public class Forward extends CommandNode {

	private Double pixels;
	
	public Forward() {
		setParametersNeeded(2);
	}

	@Override
	void run() {
		getTurtle().setX(getTurtle().getX() + pixels*Math.sin(getTurtle().getOrientation()));
		getTurtle().setX(getTurtle().getY() + pixels*Math.cos(getTurtle().getOrientation()));
	}

	@Override
	void setParameters() {		
	}
}
