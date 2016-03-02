package commands;

public class LessThan extends CommandNode{

	public LessThan(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		if(getChildren().get(0).run() < getChildren().get(1).run()){
			return 1;
		}
		return 0;
	}

}
