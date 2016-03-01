package slogo;

public class SetPosition extends CommandNode{

	public SetPosition(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	double run() {
		getTurtle().move(getChildren().get(0).getValue(), getChildren().get(1).getValue());
		return 0;
		
	}

	@Override
	void setParameters() {
		// TODO Auto-generated method stub
		
	}

}