package commands;

public class Turtles extends MultipleTurtleCommands{

	public Turtles(double val) {
		super(val);
	}

	@Override
	public double run() {
		return getTurtleListController().getTurtleList().size();
	}

}
