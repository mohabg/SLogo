package gui;

import java.util.ResourceBundle;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import slogo.Controller;


public class CommandWindow {
    private ConsoleTextArea console;
    private Controller interpreter;
    private String consolePrompt;

    public CommandWindow (Controller interpreter) {
        this.console = new ConsoleTextArea();
        this.interpreter = interpreter;
        initControls();
        ResourceBundle resources = ResourceBundle.getBundle("resources/CommandWindow");
        consolePrompt = resources.getString("consolePromptString");
        print(consolePrompt);
    }

    public ConsoleTextArea getConsole () {
        return console;
    }

    public void printError (String message) {
        Text errorText = new Text("\nError: " + message);
        console.appendText(errorText.getText());
    }

    private void print (String message) {
        console.appendText(message);
    }

    private void initControls () {
        console.setOnKeyPressed(e -> handleKeyPressed(e));
    }

    private void handleKeyPressed (KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            handleReturnKey();
            e.consume();
        }
    }

    private void handleReturnKey () {
        String text = console.getText();
        int ignoreLength = consolePrompt.length();
        String command =
                text.substring(text.lastIndexOf(consolePrompt) + ignoreLength)
                        .trim();
        String out = interpreter.compile(command);
        print(out);

        print(consolePrompt);
    }

}
