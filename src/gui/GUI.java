package gui;

import java.util.ResourceBundle;

import data.CanvasData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import slogo.Controller;

public class GUI {
	Controller controller;

	private MyCanvas canvas;
	private CommandWindow commandWindow;
	private ScriptWindow scriptWindow;
	private Workspace workspace;
	private Scene scene;

	private ResourceBundle resources = ResourceBundle.getBundle("resources/GUI");

	public GUI(String language, int width, int height) {
		controller = new Controller(language);
		controller.initialize();
		
		BorderPane root = new BorderPane();
		scene = new Scene(root, width, height);

		canvas = new MyCanvas(width / 2, height / 2, controller);
		commandWindow = new CommandWindow(controller);
		scriptWindow = new ScriptWindow(controller, commandWindow);
		workspace = new Workspace();

		GridPane grid = createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace);

		root.setTop(MenuFactory.createMenuBar(scriptWindow, controller));
		root.setCenter(grid);

		int msDelay = Integer.parseInt(resources.getString("frameDelayMs"));
		KeyFrame frame = new KeyFrame(Duration.millis(msDelay), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public Scene getScene() {
		return scene;
	}

	public void step() {
		canvas.update((CanvasData) controller.getReturnData());
	}

	// Reference: http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
	private GridPane createGridPane(MyCanvas canvas, CommandWindow console, ScriptWindow editor, Workspace workspace) {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		grid.add(canvas.getCanvas(), 0, 0);
		grid.add(console.getConsole(), 1, 1);
		grid.add(editor.getTextArea(), 1, 0);
		grid.add(workspace.getTableView(), 0, 1);

		return grid;
	}
}
