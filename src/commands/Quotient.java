package commands;

public class Quotient extends CommandNode{

	public Quotient(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		double quotient = getChildren().get(0).run();
		for(int i = 1 ; i < getChildren().size(); i++){
			quotient /= getChildren().get(i).run();
		}
		setValue(quotient);
		return getValue();
	}

}
