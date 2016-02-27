package slogo;

import java.util.List;

public abstract class CommandNode {

	private int parametersNeeded;
	private List<CommandNode> children;
	private Turtle turtle;
	
	public CommandNode() {
	}
	abstract void setParameters();
	
	public int parametersNeeded(){
		return parametersNeeded;
	}
	public void addToChildren(CommandNode command){
		children.add(command);
	}
	abstract void run();
	
	public Turtle getTurtle(){
		return turtle;
	}
	public void setParametersNeeded(int numParameters){
		parametersNeeded = numParameters;
	}

}
