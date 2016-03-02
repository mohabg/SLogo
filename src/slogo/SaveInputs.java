package slogo;

import java.util.List;

import commands.CommandNode;

public interface SaveInputs {

	public void addCommandToHistory(CommandNode command);
	
	public CommandNode getCommandForVariable(String variable);

	public CommandNode getCommandForFunction(String function);

	public List<CommandNode> getPastCommands();
	
	public void addVariableToMap(CommandNode variable, String variableName);
	
	public void addCommandToMap(CommandNode command, String functionName);

}
