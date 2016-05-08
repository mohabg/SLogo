package commands;

public class Window extends CommandNode{

	public Window(double val) {
		super(val);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double run() {
		getTurtle().enableWindow();
		return 2;
	}

}
