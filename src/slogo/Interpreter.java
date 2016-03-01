package slogo;

import gui.CommandWindow;


public class Interpreter {
    private LogicController logicController;
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