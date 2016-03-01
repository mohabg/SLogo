package commands;

import java.util.ArrayList;
import java.util.List;

import slogo.Turtle;

public abstract class CommandNode {

	private int parametersNeeded;
	private List<CommandNode> children;
	private Turtle turtle;
	private boolean usesTurtle;
	private double value;
	private String input;

	public CommandNode(double val){
		children = new ArrayList<>();
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
	
	public abstract double run();
	
	public Turtle getTurtle(){
		return turtle;
	}
	public void setTurtle(Turtle turtle){
		this.turtle = turtle;
		for(int i = 0; i < getChildren().size(); i++){
			CommandNode child = getChildren().get(i);
			if(child.getUsesTurtle()){
			child.setTurtle(getTurtle());
			}
		}
	}
	public void setUsesTurtle(Boolean usesTurtle){
		this.usesTurtle = usesTurtle;
	}
	public boolean getUsesTurtle(){
		for(CommandNode child : getChildren()){
			if(child.getUsesTurtle()){
				return true;
			}
		}
		return usesTurtle;
	}
	public void setParametersNeeded(int parameters){
		parametersNeeded = parameters;
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
}
