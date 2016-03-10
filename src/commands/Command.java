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
		setVariableValuesInChildren(getChildren(), new ArrayList<>());

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
			variable.getVariableStack().pop();
		}
	}

	private void setVariableValuesInChildren(List<CommandNode> children, List<CommandNode> visited) {
		for (int i = 0; i < children.size(); i++) {
			CommandNode command = children.get(i);
			for (int j = 0; j < variables.length; j++) {
				if (variables[j].getInput().equals(command.getInput())) {
					//children.set(i, variables[j]);
					//Variable variableInChildren = (Variable) command;
					//variableInChildren.setValue(variables[j].getValue());
				}
			} 

			if (!visited.contains(command)) {
				visited.add(command);
				setVariableValuesInChildren(command.getChildren(), visited);
			}
		}
	}
}
