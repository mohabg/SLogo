package gui;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import slogo.Interpreter;
import slogo.Resources;


public class ConsoleTextArea extends TextArea {
    private Interpreter interpreter;

    public ConsoleTextArea (Interpreter interpreter) {
        this.interpreter = interpreter;
        initControls();
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

    private void initControls () {
        this.setOnKeyPressed(e -> handleKeyPressed(e));
    }

    private void handleKeyPressed (KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            handleReturnKey();
            e.consume();
        }
    }

    public void handleReturnKey () {
        this.appendText("\n");

        String text = this.getText();
        int ignoreLength = Resources.CONSOLE_PROMPT_STR.length();
        String command =
                text.substring(text.lastIndexOf(Resources.CONSOLE_PROMPT_STR) + ignoreLength)
                        .trim();
        String retStr = interpreter.interpret(command);
        this.appendText(retStr);

        this.appendText(Resources.CONSOLE_PROMPT_STR);
    }
}
