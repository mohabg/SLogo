package slogo;

import java.util.Observable;
import gui.CommandWindow;


public class Interpreter extends Observable {
    private LogicController logicController;
    private TurtleController turtleController;
    private SlogoManager manager;

    private CommandWindow console;

    public Interpreter (CommandWindow console) {
        this.console = console;
        // TODO
    }

    public String interpret (String command) {
        // TODO: parse
        return "test echo: " + command;
    }

}
