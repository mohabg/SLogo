package slogo;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import gui.CommandWindow;
import javafx.geometry.Point2D;


public class SlogoManager {
	private TurtleController turtleController;
	private Parser myParser;
	private List<CommandNode> currCommandTree;
	private List<CommandNode> pastCommands;
	// points that are drawn per frame, maintains history
	
	CommandWindow console; // required to print errors
	ReturnData data;

	public SlogoManager () {
		// TODO Auto-generated constructor stub
		turtleController = new TurtleController();
		myParser = new Parser();
	}
	public ReturnData initialize(CommandWindow console){ // called in GUI()
	    this.console = console;
	    this.data = new ReturnData();
	    return data;
	}
	public String update (String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // called in every frame
		currCommandTree = myParser.interpret(input);
		for (CommandNode command : currCommandTree){
			if(command.getUsesTurtle()){
				turtleController.update(command);
			}
		}
		// TODO: must provide console output (even if empty)
		return "";
	}
}
