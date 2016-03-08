package commands;

public class Left extends CommandNode{

	public Left(double val) {
		super(val);
		setParametersNeeded(1);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		setValue(getChildren().get(0).run());
		//System.out.println(" Turte orientation before turning " + getTurtle().getOrientation());
		getTurtle().turn(360 - getValue());
		//System.out.println("Turte orienatation after turning " + getTurtle().getOrientation());
		return getValue();
	}

}
