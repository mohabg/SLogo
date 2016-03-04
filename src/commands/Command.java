package commands;

import java.util.ArrayList;
import java.util.List;

public class Command extends CommandNode{
	
	private CommandNode[] variables;
	private CommandNode[] children;
	private int childrenIndex;
	
	public Command(double val) {
		super(val);
		setUsesTurtle(true);
	}
	public void setVariables(List<CommandNode> variablesToSet){
		variables = new CommandNode[variablesToSet.size()];
		children = new CommandNode[variables.length + 1];
		for(int i = 0; i < variablesToSet.size(); i++){
			variables[i] = variablesToSet.get(i);
		}
		setParametersNeeded(variables.length);
	}
	
	@Override
	public void addToChildren(CommandNode command){
		if(childrenIndex >= children.length){
			childrenIndex = 0;
		}
		children[childrenIndex++] = command;
	}
	@Override
	public List<CommandNode> getChildren(){
		List<CommandNode> childrenAsList = new ArrayList<>();
		for(int i = 0; i < children.length; i++){
			childrenAsList.add(i, children[i]);
		}
		return childrenAsList;
	}
	@Override
	public double run() {
		for(int i = 1; i < getChildren().size(); i++){
			double variableValue = getChildren().get(i).run();
			variables[i - 1].setValue(variableValue);
		}
		CommandNode commandsToExecute = getChildren().get(0);
		double lastExecutedCommandValue = commandsToExecute.run();
		//Reset variables
		getChildren().clear();
		getChildren().add(commandsToExecute);
		return lastExecutedCommandValue;
	}

}
