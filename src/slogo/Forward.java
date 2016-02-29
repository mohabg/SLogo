package slogo;
public class Forward extends CommandNode {
	
	public Forward(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}
	double run() {
		addTurtleToChildren();
		setValue(getChildren().get(0).run());
		getTurtle().move(getTurtle().getX() + getValue()*Math.sin(getTurtle().getOrientation()), 
				getTurtle().getY() + getValue()*Math.cos(getTurtle().getOrientation()));
		System.out.println(getTurtle().getX() + " " + getTurtle().getY());
		return getValue();
	}
	private void addTurtleToChildren(){
		for(int i = 0; i < getChildren().size(); i++){
			getChildren().get(i).setTurtle(getTurtle());
		}
	}
}
