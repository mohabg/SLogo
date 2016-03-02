package commands;

public class Pi extends CommandNode{

	public Pi(double val) {
		super(val);
	}

	@Override
	public double run() {
		setValue(Math.PI);
		return getValue();
	}

}
