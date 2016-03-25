package commands;

import java.util.ArrayList;
import java.util.List;

import slogo.ColorSetter;
import slogo.CommandIterator;
import slogo.Model;
import slogo.Turtle;
import slogo.TurtleListController;
//This entire file is part of my masterpiece
//Mohab Gabal
/*
 * This is the abstract super class that all commands inherit from. It has all of the basic components,
 * such as its children, a turtle to execute on, a value, the user input, etc. Also, it has an instance
 * of an interface that is responsible for changing the background and palette color. This was done as 
 * a compromise with the front end, since they originally passed in an instance of the Model, for two methods,
 * and I created an interface with those two methods and passed that in, instead.
 */
public abstract class CommandNode {

	private int parametersNeeded;
	private List<CommandNode> children;
	private Turtle turtle;
	private boolean usesTurtle;
	private double value;
	private String input;
	private CommandIterator commandIterator;
	private ColorSetter colorSetter;

	public CommandNode(double val) {
		children = new ArrayList<>();
		commandIterator = new CommandIterator();
		this.value = val;
	}

	public void setColorSetter(Model m) {
		colorSetter = m;
	}

	public ColorSetter getColorSetter() {
		return colorSetter;
	}

	public int parametersNeeded() {
		return parametersNeeded;
	}

	public void setInput(String userInput) {
		input = userInput;
	}

	public String getInput() {
		return input;
	}

	public void addToChildren(CommandNode command) {
		children.add(command);
	}

	public abstract double run();

	public Turtle getTurtle() {
		return turtle;
	}

	public boolean hasTurtle() {
		return turtle != null;
	}

	public void setTurtle(Turtle turtle) {
		List<CommandNode> allCommandsInChildren = commandIterator.iterate(this, new ArrayList<>());
		allCommandsInChildren.add(this);
		for (CommandNode command : allCommandsInChildren) {
			if (command.usesTurtle) {
				command.turtle = turtle;
			}
		}
	}

	public void setUsesTurtle(Boolean usesTurtle) {
		this.usesTurtle = usesTurtle;
	}

	public boolean getUsesTurtle() {
		List<CommandNode> allCommandsInChildren = commandIterator.iterate(this, new ArrayList<>());
		for (CommandNode command : allCommandsInChildren) {
			if (command.usesTurtle) {
				return true;
			}
		}
		return usesTurtle;
	}

	public void setParametersNeeded(int parameters) {
		parametersNeeded = parameters;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public List<CommandNode> getChildren() {
		return children;
	}

	public CommandIterator getCommandIterator() {
		return commandIterator;
	}
	public boolean shouldSetParametersForChildren(){
		return false;
	}
	@Override
	public String toString() {
		String out = this.getClass().getSimpleName();
		return out;
	}
}
