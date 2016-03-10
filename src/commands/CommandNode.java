package commands;

import java.util.ArrayList;
import java.util.List;
import slogo.CommandIterator;
import slogo.Turtle;

public abstract class CommandNode {

	private int parametersNeeded;
	private List<CommandNode> children;
	private Turtle turtle;
	private boolean usesTurtle;
	private double value;
	private String input;
	private CommandIterator commandIterator;
	
	public CommandNode(double val){
		children = new ArrayList<>();
		commandIterator = new CommandIterator();
		this.value = val;
	}
	public int parametersNeeded(){
		return parametersNeeded;
	}
	public void setInput(String userInput){
		input = userInput;
	}
	public String getInput(){
		return input;
	}
	public void addToChildren(CommandNode command){
		children.add(command);
	}
	/*
	@Override
	public CommandNode clone() throws CloneNotSupportedException{
		CommandNode clone = (CommandNode) super.clone();
		clone.setInput(getInput());
		clone.setValue(getValue());
		clone.setParametersNeeded(parametersNeeded);
		clone.setTurtle(getTurtle());
		for(int i = 0; i < getChildren().size(); i++){
			clone.addToChildren(getChildren().get(i).clone());
		}
		return clone;
	}
	*/
	public abstract double run();
	
	public Turtle getTurtle(){
		return turtle;
	}
	public boolean hasTurtle(){
		return turtle != null;
	}
	public void setTurtle(Turtle turtle){
		List<CommandNode> allCommandsInChildren = commandIterator.iterate(this, new ArrayList<>());
		allCommandsInChildren.add(this);
		for(CommandNode command : allCommandsInChildren){
			if(command.usesTurtle){
				command.turtle = turtle;
			}
		}
	}
	public void setUsesTurtle(Boolean usesTurtle){
		this.usesTurtle = usesTurtle;
	}
	public boolean getUsesTurtle(){
		List<CommandNode> allCommandsInChildren = commandIterator.iterate(this, new ArrayList<>());
		for(CommandNode command : allCommandsInChildren){
			if(command.usesTurtle){
				return true;
			}
		}
		return usesTurtle;
	}
	public void setParametersNeeded(int parameters){
		parametersNeeded = parameters;
	}
	public void updateVariablesInChildren(){
		for(CommandNode variable : getChildren()){
        	if(variable instanceof Variable){
        		variable.setValue(getValue());
        	}
        }
	}
	public void setValue(double value){
		this.value = value;
	}
	public double getValue(){
		return value;
	}
	public List<CommandNode> getChildren(){
		return children;
	}
	
	@Override
	public String toString() {
		String out = this.getClass().getSimpleName();
		return out;
	}
}
