package commands;

public class And extends CommandNode{

	public And(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		if(getChildren().get(0).run() != 0 &&  getChildren().get(1).run() != 0){
			return 1;
		}
		return 0;
	}

}
