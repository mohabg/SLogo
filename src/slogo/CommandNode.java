package slogo;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> 2db17390f676a4c526d51dbc902e6a427a017c2e
import java.util.List;

public abstract class CommandNode {

<<<<<<< HEAD
	private List<CommandNode> children;
	
	public CommandNode() {
		children = new ArrayList<>();
	}
	public void addToChildren(CommandNode command){
		children.add(command);
	}
	public List<CommandNode> getChildren(){
		return children;
	}
	abstract int parametersNeeded();
		
=======
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

>>>>>>> 2db17390f676a4c526d51dbc902e6a427a017c2e
}
