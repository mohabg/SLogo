package slogo;

import java.lang.reflect.Constructor;
import commands.Command;
import commands.CommandNode;
import commands.Variable;
import exceptions.SlogoException;


public class CommandFactory {

    private SaveInputs inputSaver;

    public CommandFactory (SaveInputs model) {
        inputSaver = model;
    }

    public CommandNode getCommandNode (String commandName, String word) {
        double constant = 0;
        // Gets correct command constructor through reflection, instantiates node
        try {
            constant = Double.parseDouble(word);
        }
        catch (NumberFormatException e) {
            // throw new
            // SlogoException(String.format(Controller.errorBundle.getString("ParameterError"),
            // word, commandName));
        }
        Class commClass;
        try {
            commClass = Class.forName("commands." + commandName);
            Constructor commConstructor = commClass.getConstructor(double.class);
            CommandNode command = (CommandNode) commConstructor.newInstance(constant);
            command.setInput(word);
            inputSaver.addCommandToHistory(command);
            command = getVariableOrCommandFromModel(word, command);
            return command;
        }
        catch (Exception e) {
            throw new SlogoException(String.format(
                                                   Controller.getErrorBundle()
                                                           .getString("CommandNameError"),
                                                   commandName));
        }
    }

    private CommandNode getVariableOrCommandFromModel (String word, CommandNode command) {
        if (command instanceof Variable) {
            CommandNode storedCommandNodeForVariable = inputSaver.getCommandForVariable(word);
            if (storedCommandNodeForVariable != null) {
                // Return Stored Variable
                return storedCommandNodeForVariable;
            }
            else {
                inputSaver.addVariableToMap(command, word);
            }
        }
        if (command instanceof Command) {
            // Do Not Return Stored Command From Map
            Command storedCommand = (Command) inputSaver.getCommandForFunction(word);
            if (storedCommand == null) {
                // Adds only the first definition for a function
                inputSaver.addCommandToMap(command, word);
            }
        }
        return command;
    }
}
