package slogo;

public class SetPosition extends CommandNode{

	public SetPosition() {
		setUsesTurtle(true);
		setParametersNeeded(2);
	}

	@Override
	void run() {
		getTurtle().move(getChildren().get(0).getValue(), getChildren().get(1).getValue());
		
	}

}
