package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import commands.CommandNode;
import data.Line;
import data.Point;
import data.ReturnData;
import data.TurtleData;
import javafx.scene.paint.Color;


public class Model implements SaveInputs, TurtleListController {

    private static final List<double[]> DEFAULT_PALETTE = new ArrayList<double[]>() {
        {
            add(new double[] { 0, 0, 0 });
            add(new double[] { 255, 255, 255 });
            add(new double[] { 255, 0, 0 });
            add(new double[] { 0, 255, 0 });
            add(new double[] { 0, 0, 255 });
            add(new double[] { 255, 255, 0 });
            add(new double[] { 0, 255, 255 });
            add(new double[] { 255, 0, 255 });
        }
    };
    private static final double RGB_CORRECTION = 255;

    private ReturnData returnData;
    private List<Turtle> turtleList;
    private List<Turtle> activeTurtles;
    private List<Line> lineList;
    private Map<String, CommandNode> userVariables;
    private Map<String, CommandNode> userFunctions;
    private List<double[]> myPalette;
    private List<Double> consoleOutputs;
    private List<CommandNode> pastCommands;
    private double BackgroundColor = 1;
    private Collection<MyStamp> stamps;

    public Model () {
        returnData = new ReturnData();
        userVariables = new HashMap<String, CommandNode>();
        userFunctions = new HashMap<String, CommandNode>();
        consoleOutputs = new ArrayList<Double>();
        pastCommands = new ArrayList<CommandNode>();
        myPalette = DEFAULT_PALETTE;
        lineList = new ArrayList<Line>();
        Turtle turtle = new Turtle(1);
        turtleList = new ArrayList<Turtle>();
        turtleList.add(turtle);
        activeTurtles = new ArrayList<>();
        activeTurtles.add(turtle);
    }

    public String getConsoleOutput () {
        StringBuilder consoleOutput = new StringBuilder();
        for (Double output : consoleOutputs) {
            consoleOutput.append(output.toString() + ", ");
        }
        return consoleOutput.toString();
    }

    private void addTurtle (double id) {
        int maxId = turtleList.size();
        if (id > maxId) {
            for (int i = maxId + 1; i <= id; i++) {
                Turtle turtle = new Turtle(i);
                turtleList.add(turtle);
            }
        }
    }

    public void setBackgroundColor (double c) {
        BackgroundColor = c;
    }

    public List<String[]> returnHistory () {
    	List<String[]> history = new ArrayList<String[]>();
        StringBuilder historyString = new StringBuilder();
        for (CommandNode command : pastCommands) {
            String name = command.toString();
            Double value = command.getValue();
            if (!name.equals("Constant")) {
                historyString.append(name + " ");
            }
            else {
                history.add(new String[]{historyString.toString(), value.toString()});
                historyString = new StringBuilder();
            }
        }
        return history;
    }

    public List<Turtle> getTurtleList () {
        return turtleList;
    }

    public List<Turtle> getActiveTurtles () {
        return activeTurtles;
    }

    public void setActiveTurtles (List<CommandNode> activeTurtlesIds) {
        List<Double> idValues = new ArrayList<>();
        double maxId = 0;
        for (CommandNode id : activeTurtlesIds) {
            double newId = id.run();
            idValues.add(newId);
            if (newId > maxId) {
                maxId = newId;
            }
        }
        addTurtle(maxId);
        activeTurtles.clear();
        for (Turtle turtle : turtleList) {
            for (double id : idValues) {
                if (turtle.getID() == id) {
                    activeTurtles.add(turtle);
                }
            }
        }
    }

    public void addStamp (double x, double y, double orientation, String image) {
        Point stampLoc = new Point(x, y, orientation);
        stamps.add(new MyStamp(image, stampLoc));
    }

    public void setCompileInfo () {
        returnData.addLines(lineList);
        returnData.setVariables(makeVariableOutputs());
        returnData.setFunctions(makeFunctionOutputs());
        List<TurtleData> turtleData = new ArrayList<TurtleData>();
        turtleData.addAll(turtleList);
        returnData.setTurtles(turtleData);
        returnData.setBackgroundColor(BackgroundColor);
        returnData.setHistory(returnHistory());
        returnData.setPalette(makePalette());
    }

    public void addPaletteColor (double index, double r, double g, double b) {
        if (index >= myPalette.size())
            myPalette.add(new double[] { r, g, b });
        else
            myPalette.set((int) index, new double[] { r, g, b });
    }

    public List<Color> makePalette () {
        List<Color> palette = new ArrayList<Color>();
        for (int i = 0; i < myPalette.size(); i++) {
            double[] rbg = myPalette.get(i);
            palette.add(new Color(rbg[0] / RGB_CORRECTION, rbg[1] / RGB_CORRECTION,
                                  rbg[2] / RGB_CORRECTION, 1));
        }
        return palette;
    }

    private Map<String, String> makeVariableOutputs () {
        Map<String, String> variableStringOutputs = new TreeMap<String, String>();
        for (String variable : userVariables.keySet()) {
            variableStringOutputs.put(variable,
                                      String.valueOf(userVariables.get(variable).getValue()));
        }
        return variableStringOutputs;
    }

    private Set<String> makeFunctionOutputs () {
        Set<String> functionOutputs = new TreeSet<String>();
        for (String fnName : userFunctions.keySet()) {
            functionOutputs.add(fnName);
        }
        return functionOutputs;
    }

    public void setLines (List<Line> lines) {
        lineList = lines;
    }

    public void addCommandToHistory (CommandNode command) {
        pastCommands.add(command);
        // System.out.println("Command: " + command.toString() + "Value: " +
        // (command.getValue()));
    }

    public void addVariableToMap (CommandNode variable, String variableName) {
        userVariables.put(variableName, variable);
    }

    public void addCommandToMap (CommandNode command, String functionName) {
        userFunctions.put(functionName, command);
    }

    public CommandNode getCommandForVariable (String variable) {
        return userVariables.get(variable);
    }

    public CommandNode getCommandForFunction (String function) {
        return userFunctions.get(function);
    }

    public List<CommandNode> getPastCommands () {
        return pastCommands;
    }

    public ReturnData getReturnData () {
        return returnData;
    }

    @Override
    public void clearStamps () {
        stamps.clear();

    }
}
