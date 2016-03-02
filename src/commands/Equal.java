package commands;

public class Equal extends CommandNode{

	public Equal(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		if(getChildren().get(0).run() == getChildren().get(1).run()){
			return 1;
		}
		return 0;
	}

}
