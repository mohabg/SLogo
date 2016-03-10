package commands;

import java.util.ArrayList;
import java.util.List;

public class Command extends CommandNode{
	
	private CommandNode[] variables;
	
	public Command(double val) {
		super(val);
		setUsesTurtle(true);
	}
	public List<CommandNode> getVariables(){
		List<CommandNode> variablesAsList = new ArrayList<>();
		for(int i = 0; i < variables.length; i++){
			variablesAsList.add(variables[i]);
		}
		return variablesAsList;
	}
	public void setVariables(List<CommandNode> variablesToSet){
		variables = new CommandNode[variablesToSet.size()];
		for(int i = 0; i < variablesToSet.size(); i++){
			variables[i] = variablesToSet.get(i);
		}
		setParametersNeeded(variables.length);
	}
	
	@Override
	public double run() {
		for(int i = 1; i < getChildren().size(); i++){
			double variableValue = getChildren().get(i).run();
			variables[i - 1].setValue(variableValue);
		}
		CommandNode commandsToExecute = getChildren().get(0);
		return commandsToExecute.run();
	}

}
