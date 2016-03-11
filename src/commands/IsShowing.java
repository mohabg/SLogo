package commands;

public class IsShowing extends CommandNode{

	public IsShowing(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
	}

	@Override
	public double run() {
		if(getTurtle().isVisible()){
			return 1;
		}
		else{
			return 0;
		}
	}

}
