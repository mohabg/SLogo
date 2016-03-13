package commands;

import java.util.ArrayList;
import java.util.List;
import slogo.CommandIterator;
import slogo.Model;
import slogo.Turtle;
import slogo.TurtleListController;

public abstract class CommandNode {

	private int parametersNeeded;
	private List<CommandNode> children;
	private Turtle turtle;
	private boolean usesTurtle;
	private double value;
	private String input;
	private CommandIterator commandIterator;
	private Model model;

	public CommandNode(double val) {
		children = new ArrayList<>();
		commandIterator = new CommandIterator();
		this.value = val;
	}

	public void setModel(Model m) {
		model = m;
	}

	public Model getModel() {
		return model;
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

	public void updateVariablesInChildren() {
		for (CommandNode variable : getChildren()) {
			if (variable instanceof Variable) {
				variable.setValue(getValue());
			}
		}
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
