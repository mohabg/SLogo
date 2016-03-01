package commands;

public class MakeVariable extends CommandNode {

	
	public MakeVariable(double val) {
		super(val);
		setParametersNeeded(2);
	}

	@Override
	public double run() {
		CommandNode expression = getChildren().get(1);
		setValue(expression.run());
		CommandNode variable = getChildren().get(0);
		variable.setValue(getValue());
		
		return getValue();

	}
}
