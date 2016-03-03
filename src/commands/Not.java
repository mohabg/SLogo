package commands;

public class Not extends CommandNode{

	public Not(double val) {
		super(val);
		setParametersNeeded(1);
	}

	@Override
	public double run() {
		if(getChildren().get(0).run() == 0){
			return 1;
		}
		return 0;
	}

}
