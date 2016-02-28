package gui;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import slogo.Interpreter;
import slogo.Resources;


public class CommandWindow extends TextArea {
    Interpreter interpreter;

    public CommandWindow (Interpreter interpreter) {
        this.interpreter = interpreter;
        print("\n"); // TODO: hack!
        initControls();
    }

    private void initControls () {
        this.setOnKeyPressed(e -> handleKeyPressed(e.getCode()));
    }

    private void handleKeyPressed (KeyCode key) {
        if (key == KeyCode.ENTER) {
            handleReturnKey();
        }
    }

    public void printError (String message) {
        Text errorText = new Text(message);
        errorText.setStyle(Resources.ERROR_TEXT_STYLE);
        this.appendText(errorText.getText());
    }

    public void handleReturnKey () {
        String text = this.getText();
        System.out.println(text);
        String command = text.substring(text.lastIndexOf("\n"));
        String retStr = interpreter.interpret(command);
        print(retStr);
        // TODO: add prompt character
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
