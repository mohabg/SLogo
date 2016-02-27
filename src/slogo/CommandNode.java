package slogo;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandNode {

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
		
}
