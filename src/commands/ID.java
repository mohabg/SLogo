package commands;

public class ID extends CommandNode {

	public ID(double val) {
		super(val);
		setParametersNeeded(0);
		setUsesTurtle(true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		// TODO Auto-generated method stub
		return getTurtle().getID();
	}

}
