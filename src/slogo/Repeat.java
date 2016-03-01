package slogo;

public class Repeat extends CommandNode{

	public Repeat(double val) {
		super(val);
		setParametersNeeded(2);
		setUsesTurtle(true);
	}

	@Override
	double run() {
		setValue(getChildren().get(0).run());
		int i = 0;
		double listStartReturnValue = 0;
		while(i++ < getValue()){
				listStartReturnValue = getChildren().get(1).run();
		}
		return listStartReturnValue;
	}
	
}
