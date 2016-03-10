package commands;

import java.util.ArrayList;
import java.util.List;

public class Command extends CommandNode {

	private Variable[] variables;

	public Command(double val) {
		super(val);
		setUsesTurtle(true);
	}

	public List<CommandNode> getVariables() {
		List<CommandNode> variablesAsList = new ArrayList<>();
		for (int i = 0; i < variables.length; i++) {
			variablesAsList.add(variables[i]);
		}
		return variablesAsList;
	}

	public void setVariables(List<CommandNode> variablesToClone) {
		variables = new Variable[variablesToClone.size()];
		for (int i = 0; i < variablesToClone.size(); i++) {
			variables[i] = (Variable) variablesToClone.get(i);
		}
		setParametersNeeded(variables.length);
	}

	@Override
	public double run() {
		setVariableValues();

		CommandNode commandsToExecute = getChildren().get(0);
		double lastExecutedCommandValue = commandsToExecute.run();

		resetVariableValues();
		return lastExecutedCommandValue;
	}

	private void setVariableValues() {
		for (int i = 1; i < getChildren().size(); i++) {
			CommandNode expression = getChildren().get(i);
			double currentParameterValue = expression.run();
			//Also adds to variable stack
			variables[i - 1].setValue(currentParameterValue);
		}
	}

	private void resetVariableValues() {
		for (Variable variable : variables) {
			variable.popFromStack();
		}
	}
}
