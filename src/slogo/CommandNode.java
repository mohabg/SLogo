package slogo;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandNode {

	private int parametersNeeded;
	private List<CommandNode> children;
	private Turtle turtle;
	private boolean usesTurtle;
	private double value;

	public CommandNode() {
		children = new ArrayList<>();
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
	public void setTurtle(Turtle turtle){
		this.turtle = turtle;
	}
	public void setUsesTurtle(Boolean usesTurtle){
		this.usesTurtle = usesTurtle;
	}
	public boolean getUsesTurtle(){
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
