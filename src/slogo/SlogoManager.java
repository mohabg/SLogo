package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import commands.CommandNode;
import gui.CommandWindow;


public class SlogoManager {
    private Controller controller;
    private Parser myParser;
    private List<CommandNode> currCommandTree;
    private List<CommandNode> pastCommands;
    // points that are drawn per frame, maintains history

    CommandWindow console; // required to print errors
    ReturnData data;

    public SlogoManager () {
        controller = new Controller();
        myParser = new Parser();
        currCommandTree = new ArrayList<CommandNode>();
    }

    public ReturnData initialize (CommandWindow console) { // called in GUI()
        this.console = console;
        this.data = new ReturnData();
        return data;
    }

    public String compile (String input) throws InstantiationException, IllegalAccessException,
                                         IllegalArgumentException, InvocationTargetException { // called
                                                                                               // in
                                                                                               // every
                                                                                               // frame
        currCommandTree = myParser.interpret(input);
        for (CommandNode command : currCommandTree) {
            controller.update(command);
        }
        // TODO: must provide console output (even if empty)
        return "";
    }
}
