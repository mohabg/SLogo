package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import commands.CommandNode;


public class SlogoManager {
	private Controller controller;
	private Parser myParser;
	private List<CommandNode> currCommandTree;
	
	public SlogoManager(){
		controller = new Controller();
		myParser = new Parser();
		currCommandTree = new ArrayList<CommandNode>();
	}
	public void initialize(){
	}
	public void update(CommandNode command){
		controller.update(command);
		List<CommandNode> past = controller.getPastCommands();
		for(CommandNode c : past){
			System.out.println(c.getInput() + " " + c);
		}
	}
	public void compile (String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // called in every frame
		currCommandTree = myParser.interpret(input);
		for (CommandNode command: currCommandTree){
			update(command);
		}

	}
}