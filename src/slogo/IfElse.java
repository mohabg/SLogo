package slogo;

public class IfElse extends CommandNode{

	public IfElse(double val) {
		super(val);
		setUsesTurtle(true);
		setParametersNeeded(3);
	}

	@Override
	double run() {
		CommandNode expression = getChildren().get(0);
		if(expression.run() != 0){
			CommandNode trueCommands = getChildren().get(1);
			return trueCommands.run();
		}
		else{
			CommandNode falseCommands = getChildren().get(2);
			return falseCommands.run();
		}
	}

}
