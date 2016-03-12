package gui;

import java.util.ResourceBundle;
import javafx.scene.control.TextArea;
import slogo.Controller;


public class ScriptWindow {
    private static ResourceBundle resources = ResourceBundle.getBundle("resources/CommandWindow");

    private TextArea myTextArea;
    private Controller controller;
    private CommandWindow commandWindow;

    public ScriptWindow (Controller controller, CommandWindow commandWindow) {
        myTextArea = new TextArea();
        this.controller = controller;
        this.commandWindow = commandWindow;
    }

    public void handleRunButton () {
        String text = myTextArea.getText();
        String[] eachLine = text.split("\n");
        StringBuilder textWithoutComments = new StringBuilder();

        for (String s : eachLine) {
            String cut = s.trim();
            if (!cut.startsWith("#"))
                textWithoutComments.append(s + "\n");
        }

        ConsoleTextArea console = commandWindow.getConsole();
        console.appendText("[script]");
        controller.compile(textWithoutComments.toString());

        console.appendText(resources.getString("consolePromptString"));
    }

    public TextArea getTextArea () {
        return myTextArea;
    }
}
