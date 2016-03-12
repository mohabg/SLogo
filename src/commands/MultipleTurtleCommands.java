package commands;

import slogo.TurtleListController;

public abstract class MultipleTurtleCommands extends CommandNode{
	
	private TurtleListController model;
	
	public MultipleTurtleCommands(double val) {
		super(val);
		setUsesTurtle(true);
	}
	@Override
	public void setTurtleListController(TurtleListController controller){
		model = controller;
	}
	protected TurtleListController getTurtleListController(){
		return model;
	}
}
