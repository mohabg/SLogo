package commands;

public class SetTowards extends CommandNode{

	public SetTowards(double val) {
		super(val);
		setParametersNeeded(2);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		
		return 0;
	}

}
