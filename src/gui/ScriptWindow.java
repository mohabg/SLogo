package gui;

import javafx.scene.control.TextArea;
import slogo.Controller;
import slogo.Resources;

public class ScriptWindow {

	private TextArea myTextArea;
	private Controller controller;
	private CommandWindow commandWindow;

	public ScriptWindow(Controller controller, CommandWindow commandWindow) {
		myTextArea = new TextArea();
		this.controller = controller;
		this.commandWindow = commandWindow;
	}

	public void handleRunButton() {
		String text = myTextArea.getText();
		ConsoleTextArea console = commandWindow.getConsole();
		console.appendText("[script]");
		try {
			controller.compile(text);
		} catch (Exception e) {
			commandWindow.printError(e.getMessage());
		}

		console.appendText(Resources.CONSOLE_PROMPT_STR);
	}

	public TextArea getTextArea() {
		return myTextArea;
	}
}
