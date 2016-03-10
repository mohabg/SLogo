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

	private String language = "English";
	private List<Model> myModels;
	private List<Parser> myParsers;
	private List<Color> myPalette;
	protected static ResourceBundle errorBundle = ResourceBundle.getBundle("resources/Errors");
	private String language = null;

<<<<<<< HEAD
	public Controller() {
		myModels = new ArrayList<Model>();
		Model model1 = new Model();
		Parser parser1 = new Parser(language, model1);
		myModels.add(model1);
		myParsers.add(parser1);

	}

	public void initialize() {
		updateModel(myModels.get(0));
	}
	public void addModel(){
		Model newModel = new Model();
		Parser newParser = new Parser(language, newModel);
	}
	public String compile(String input, int modelNum) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException {// frame
=======
	public Controller(String language) {
		this.language = language;
		reset();		
	}
	
	public void reset()
	{
		myModel = new Model();
		myParser = new Parser(language, myModel);
		updateModel();
	}
	
	public void setLanguage(String language)
	{
		this.language = language;
		reset();
	}

	public String compile(String input) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, SecurityException{// frame
>>>>>>> master
		List<CommandNode> currCommandTree;
		List<Double> outputs = new ArrayList<Double>();
		currCommandTree = myParsers.get(modelNum).interpret(input);
		for (CommandNode command : currCommandTree) {
			outputs.addAll(update(command, myModels.get(modelNum)));
		}
		updateModel(myModels.get(modelNum));
		return getConsoleOutput(outputs);
	}
	public String getConsoleOutput(List<Double> consoleOutputs) {
		StringBuilder consoleOutput = new StringBuilder();
		for (Double output : consoleOutputs) {
			if (consoleOutput.length() == 0){
				consoleOutput.append("\n" + output.toString());
			}
			else{
				consoleOutput.append(", \n" + output.toString());
			}
		}
		return consoleOutput.toString();
	}
	public Collection<Double> update(CommandNode command, Model model) {
		ArrayList<Double> outputs = new ArrayList<Double>();
		if (command.getUsesTurtle()) {
			for (Turtle turtle : model.getTurtleList()) {
				command.setTurtle(turtle);
				outputs.add(command.run());
			}
		} else {
			outputs.add(command.run());
		}

		return outputs;
	}

	private void updateModel(Model model) {
		model.setLines(makeLines(model));
		model.setCompileInfo();
	}

	public List<Line> makeLines(Model model) {
		ArrayList<Line> lines = new ArrayList<Line>();
		for (Turtle turtle : model.getTurtleList()) {
			for (int i = 0; i < turtle.getPoints().size() - 1; i++) {
				Point next = turtle.getPoints().get(i + 1);
				Point cur = turtle.getPoints().get(i);
				Line line = new Line(cur, next);
				lines.add(line);
			}
		}
		return lines;
	}

	public ReturnData getReturnData(Model model) {
		return model.getReturnData();
	}
}
