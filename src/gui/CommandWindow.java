package gui;

import java.lang.reflect.InvocationTargetException;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import slogo.Controller;
import slogo.Model;
import slogo.Resources;


public class CommandWindow extends TextArea {
    Controller interpreter;

    public CommandWindow (Controller interpreter) {
        this.interpreter = interpreter;
        print(Resources.CONSOLE_PROMPT_STR); // TODO: hack!
        initControls();
    }

    private void initControls () {
        this.setOnKeyPressed(e -> handleKeyPressed(e));
    }

    private void handleKeyPressed (KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            handleReturnKey();
            e.consume();
        }
    }

    public void printError (String message) {
        Text errorText = new Text(message);
        errorText.setStyle(Resources.ERROR_TEXT_STYLE);
        this.appendText(errorText.getText());
    }

    public void handleReturnKey () {
        print("\n");

        String text = this.getText();
        int ignoreLength = Resources.CONSOLE_PROMPT_STR.length();
        String command =
                text.substring(text.lastIndexOf(Resources.CONSOLE_PROMPT_STR) + ignoreLength)
                        .trim();
        String retStr = null;
		try {
			retStr = interpreter.compile(command);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        print(retStr);

        print(Resources.CONSOLE_PROMPT_STR);
    }

    private void print (String message) {
        this.appendText(message);
    }

    // Reference:
    // http://stackoverflow.com/questions/29699040/javafx-how-to-restrict-manipulation-of-textarea-to-last-row
    @Override
    public void replaceText (int start, int end, String text) {
        String current = getText();
        // only insert if no new lines after insert position:
        if (!current.substring(start).contains("\n")) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection (String text) {
        String current = getText();
        int selectionStart = getSelection().getStart();
        if (!current.substring(selectionStart).contains("\n")) {
            super.replaceSelection(text);
        }
    }

}