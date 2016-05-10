package commands;

public class Window extends CommandNode {

	public Window(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(0);
	}

	@Override
	public double run() {
		getTurtle().setFence(false);
		return 2;
	}

}
