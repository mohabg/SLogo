package gui;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.control.TextArea;
import slogo.Controller;
import slogo.Resources;


public class ScriptWindow {

    private TextArea myTextArea;
    private Controller controller;
    private CommandWindow commandWindow;

    public ScriptWindow (Controller controller, CommandWindow commandWindow) {
        myTextArea = new TextArea();
        this.controller = controller;
        this.commandWindow = commandWindow;
    }

    public void handleRunButton () { // TODO: redundant
        String text = myTextArea.getText();
        String[] commands = text.split("\n");
        ConsoleTextArea console = commandWindow.getConsole();
        console.appendText("script");
        for (String command : commands) {
                if (!command.isEmpty()) {
                    String out = "";
                    try {
                        out = controller.compile(command);
                    }
                    catch (Exception e) {
                        commandWindow.printError(e.getMessage());
                    }
                    // System.out.println("******" + out + "******");
                    console.appendText("\n" + out);
                }
            }

        console.appendText(Resources.CONSOLE_PROMPT_STR);
    }

    public TextArea getTextArea () {
        return myTextArea;
    }
}
