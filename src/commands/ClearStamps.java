package commands;

public class ClearStamps extends CommandNode {

	public ClearStamps(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(false);
	}

	@Override
	public double run() {
		// TODO Auto-generated method stub
		// model.clearStamps()
		return getValue();
	}

}
