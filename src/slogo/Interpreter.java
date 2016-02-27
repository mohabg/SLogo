package slogo;

import java.util.Observable;
import gui.CommandWindow;
import observers.Data;


public class Interpreter extends Observable {
    private LogicController logicController;
    private TurtleController turtleController;
    private SceneManager sceneManager;

    private CommandWindow console;

    public Interpreter (CommandWindow console) {
        this.console = console;
        // TODO
    }

    public Data interpret (String command) {
        // TODO
        return null;
    }

}
