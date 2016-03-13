package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import commands.CommandNode;
import commands.MultipleTurtleCommands;
import data.Line;
import data.ReturnData;
import gui.CommandWindow;

public class Controller {

	private Model myModel;
	private Parser myParser;
	private String language;
	private SaveSettings mySaver;
	private CommandWindow console;
	private static ResourceBundle errorBundle = ResourceBundle.getBundle("resources/Errors");

	public Controller(String language) {
		this.language = language;
		myModel = new Model();
		myParser = new Parser(language, myModel);
	}

	public void initialize(CommandWindow console) {
		this.console = console;
		mySaver = new SaveSettings(console);
		updateModel();
	}

	public void saveSettings(String filename) {
		mySaver.saveInfo(myModel.returnHistory(), filename);
	}

	public String compile(String input) {// frame
		List<CommandNode> currCommandTree;
		try {
			currCommandTree = myParser.interpret(input);
		} catch (Exception e) {
			console.printError(e.getMessage());
			return "";
		}
		List<Double> outputs = new ArrayList<Double>();
		for (CommandNode command : currCommandTree) {
			outputs.addAll(update(command));
		}
		updateModel();
		return getConsoleOutput(outputs);
	}

	private void updateModel() {
		myModel.setLines(makeLines());
		myModel.setCompileInfo();
	}

	// Double check this- do we ever output more than one thing?
	public String getConsoleOutput(List<Double> consoleOutputs) {
		StringBuilder consoleOutput = new StringBuilder();
		for (Double output : consoleOutputs) {
			if (consoleOutput.length() == 0) {
				consoleOutput.append("\n" + output.toString());
			} else {
				consoleOutput.append(", \n" + output.toString());
			}
		}
		return consoleOutput.toString();
	}

	public Collection<Double> update(CommandNode command) {
		ArrayList<Double> outputs = new ArrayList<Double>();
		if (command instanceof MultipleTurtleCommands) {
			MultipleTurtleCommands multipleCommand = (MultipleTurtleCommands) command;
			multipleCommand.setTurtleListController(myModel);
			outputs.add(multipleCommand.run());
			return outputs;
		}
		if (command.getUsesTurtle()) {
			for (int i = 0; i < myModel.getActiveTurtles().size(); i++) {
				if (i >= myModel.getActiveTurtles().size()) {
					break;
				}
				Turtle turtle = myModel.getActiveTurtles().get(i);
				command.setTurtle(turtle);
				outputs.add(command.run());
			}
		} else {
			outputs.add(command.run());
		}
		return outputs;
	}

	public List<Line> makeLines() {
		ArrayList<Line> lines = new ArrayList<Line>();
		for (Turtle turtle : myModel.getTurtleList()) {
			lines.addAll(turtle.getLines());
		}
		return lines;
	}

	public ReturnData getReturnData() {
		return myModel.getReturnData();
	}

	public void setLanguage(String language) {
		this.language = language;
		myParser.addLanguage(language);
	}

	public String getLanguage() {
		return this.language;
	}

	public static ResourceBundle getErrorBundle() {
		return errorBundle;
	}
}
