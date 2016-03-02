package commands;

public class SetPosition extends CommandNode{

	public SetPosition(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		getTurtle().move(getChildren().get(0).getValue(), getChildren().get(1).getValue());
		return 0;
		
	}

}