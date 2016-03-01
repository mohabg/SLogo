package gui;

import java.lang.reflect.InvocationTargetException;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import slogo.Resources;
import slogo.SlogoManager;


public class CommandWindow {
    private ConsoleTextArea console;
    private SlogoManager interpreter;

    public CommandWindow (SlogoManager interpreter) {
        this.console = new ConsoleTextArea();
        this.interpreter = interpreter;
        initControls();
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
        print("\n");

        String text = console.getText();
        int ignoreLength = Resources.CONSOLE_PROMPT_STR.length();
        String command =
                text.substring(text.lastIndexOf(Resources.CONSOLE_PROMPT_STR) + ignoreLength)
                        .trim();
        String retStr = null;
        try {
            retStr = interpreter.compile(command);
        }
        catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        print(retStr);

        print(Resources.CONSOLE_PROMPT_STR);
    }

}
