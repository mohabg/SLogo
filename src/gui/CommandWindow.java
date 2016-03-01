package gui;

import javafx.scene.text.Text;
import slogo.Interpreter;
import slogo.Resources;


public class CommandWindow {
    private ConsoleTextArea console;

    public CommandWindow (Interpreter interpreter) {
        this.console = new ConsoleTextArea(interpreter);
        print(Resources.CONSOLE_PROMPT_STR);
    }

    public ConsoleTextArea getConsole () {
        return console;
    }

    public void printError (String message) {
        Text errorText = new Text(message);
        errorText.setStyle(Resources.ERROR_TEXT_STYLE);
        console.appendText(errorText.getText());
    }

    private void print (String message) {
        console.appendText(message);
    }

}
