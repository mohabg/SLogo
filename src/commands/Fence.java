package commands;

public class Fence extends CommandNode{

	public Fence(double val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		getTurtle().enableFence();
		return 3;
	}

}
