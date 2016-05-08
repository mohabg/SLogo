package commands;

public class Wrap extends CommandNode{

	public Wrap(double val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		getTurtle().enableWrap();
		return 1;
	}

}
