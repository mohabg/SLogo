package commands;

public class SetBackground extends CommandNode{

	public SetBackground(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		double color = (int)getChildren().get(0).run();
		getModel().setBackgroundColor(color);
		return color;
	}

}
