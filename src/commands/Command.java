package commands;

import java.util.ArrayList;
import java.util.List;

public class Command extends CommandNode{
	
	private List<CommandNode> variables;
	
	public Command(double val) {
		super(val);
		variables = new ArrayList<>();
		setUsesTurtle(true);
	}
	public void setVariables(List<CommandNode> variablesToSet){
		for(int i = 0; i < variablesToSet.size(); i++){
			variables.add(variablesToSet.get(i));
		}
		setParametersNeeded(variables.size());
	}
	@Override
	public double run() {
		int variableIndex = 0;
		for(int i = 1; i < getChildren().size(); i++){
			double variableValue = getChildren().get(i).run();
			variables.get(variableIndex++).setValue(variableValue);
		}
		CommandNode commandsToExecute = getChildren().get(0);
		return commandsToExecute.run();
	}

}
