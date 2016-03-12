package slogo;

import java.util.List;

import commands.CommandNode;

public interface TurtleListController {
	
	public List<Turtle> getTurtleList();
	
	public List<Turtle> getActiveTurtles();
	
	public void setActiveTurtles(List<CommandNode> activeTurtlesIds);
}
