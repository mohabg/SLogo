package gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import slogo.Controller;
import slogo.Resources;


public class CommandWindow {
    private ConsoleTextArea console;
    private Controller interpreter;

    public CommandWindow (Controller interpreter) {
        this.console = new ConsoleTextArea();
        this.interpreter = interpreter;
        initControls();
        print("Yesterday it worked\n" + "Today it is not working\n" + "Windows is like that" +
              Resources.CONSOLE_PROMPT_STR);
    }

    public ConsoleTextArea getConsole () {
        return console;
    }

    public void printError (String message) {
        Text errorText = new Text("\nError: " + message);
        // errorText.setStyle(Resources.ERROR_TEXT_STYLE);
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
        // print("\n");

        String text = console.getText();
        int ignoreLength = Resources.CONSOLE_PROMPT_STR.length();
        String command =
                text.substring(text.lastIndexOf(Resources.CONSOLE_PROMPT_STR) + ignoreLength)
                        .trim();
        String out = interpreter.compile(command);
        print(out);

        print(Resources.CONSOLE_PROMPT_STR);
    }

}
