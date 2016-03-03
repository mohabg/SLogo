package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import commands.CommandNode;
import commands.MakeUserInstruction;
import data.Line;
import data.Point;
import data.ReturnData;
import javafx.scene.paint.Color;

public class Controller {

	private Model myModel;
	private Parser myParser;
	private List<Color> myPalette;
	protected static ResourceBundle errorBundle = ResourceBundle.getBundle("resources/Errors");

	public Controller() {
		myModel = new Model();
		myParser = new Parser("English", myModel);
	}

	public void initialize() {
		updateModel();
	}

	public String compile(String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{// frame
		List<CommandNode> currCommandTree;
		List<Double> outputs = new ArrayList<Double>();
		
		//try {
			currCommandTree = myParser.interpret(input);
			for (CommandNode command : currCommandTree) {
				outputs.addAll(update(command));
			}
			updateModel();
		/*} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			return getConsoleOutput(outputs);
	}
	public String getConsoleOutput(List<Double> consoleOutputs) {
		StringBuilder consoleOutput = new StringBuilder();
		for (Double output : consoleOutputs) {
			consoleOutput.append(output.toString() + ", ");
		}
		return consoleOutput.toString();
	}
	public Collection<Double> update(CommandNode command) {
		ArrayList<Double> outputs = new ArrayList<Double>();
		if (command.getUsesTurtle()) {
			for (Turtle turtle : myModel.getTurtleList()) {
				command.setTurtle(turtle);
				outputs.add(command.run());
			}
		} else {
			outputs.add(command.run());
		}

		return outputs;
	}

	private void updateModel() {
		myModel.setLines(makeLines());
		myModel.setCompileInfo();
	}

	public List<Line> makeLines() {
		ArrayList<Line> lines = new ArrayList<Line>();
		for (Turtle turtle : myModel.getTurtleList()) {
			for (int i = 0; i < turtle.getPoints().size() - 1; i++) {
				Point next = turtle.getPoints().get(i + 1);
				Point cur = turtle.getPoints().get(i);
				Line line = new Line(cur, next);
				lines.add(line);
			}
		}
		return lines;
	}

	public ReturnData getReturnData() {
		return myModel.getReturnData();
	}
}
