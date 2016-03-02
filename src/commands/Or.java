package commands;

public class Or extends CommandNode{

	public Or(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		if(getChildren().get(0).run() != 0 ||  getChildren().get(1).run() != 0){
			return 1;
		}
		return 0;
	}

}
