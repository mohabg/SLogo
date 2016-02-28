package slogo;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import javafx.geometry.Point2D;


public class SlogoManager {
	private TurtleController turtleController;
	private LogicController logicController;
	private Parser myParser;
	private List<CommandNode> currCommandTree;
	private List<CommandNode> pastCommands;
	// points that are drawn per frame, maintains history

	public SlogoManager () {
		// TODO Auto-generated constructor stub
		turtleController = new TurtleController();
		logicController = new LogicController();
		myParser = new Parser();
	}
	public void initialize(){
	}
	public void update (String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException { // called in every frame
		currCommandTree = myParser.interpret(input);
		for (CommandNode command : currCommandTree){
			if(command.getUsesTurtle()){
				turtleController.update(command);
			}
			else {
				logicController.update(command);
			}
		}
	}
}
