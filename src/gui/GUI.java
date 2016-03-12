package gui;

import java.util.ResourceBundle;
import data.CanvasData;
import data.ReturnData;
import data.WorkspaceData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import slogo.Controller;

public class GUI {
	private Controller controller;
	private MyCanvas canvas;
	private CommandWindow commandWindow;
	private ScriptWindow scriptWindow;
	private Workspace workspace;
	private Parent root;
	private HistoryWindow historyWindow;

	private ResourceBundle resources = ResourceBundle.getBundle("resources/GUI");

	public GUI(String language, int width, int height) {
		controller = new Controller(language);

		BorderPane pane = new BorderPane();
		root = pane;
		// scene = new Scene(root, width, height);

		canvas = new MyCanvas(width / 2, height / 2, controller);
		commandWindow = new CommandWindow(controller);
		controller.initialize(commandWindow); // TODO: remove backdoor
												// dependency?
		scriptWindow = new ScriptWindow(controller, commandWindow);
		workspace = new Workspace();
		historyWindow = new HistoryWindow();

		GridPane grid = createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace,
				this.historyWindow);

		pane.setTop(MenuFactory.createMenuBar(scriptWindow, controller));
		pane.setCenter(grid);

		int msDelay = Integer.parseInt(resources.getString("frameDelayMs"));
		KeyFrame frame = new KeyFrame(Duration.millis(msDelay), e -> step());
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public Parent getParent() {
		return root;
	}

	public void step() {
		ReturnData data = controller.getReturnData();

		canvas.update((CanvasData) data);
		workspace.setData((WorkspaceData) data);
		historyWindow.update(data.getHistory());
	}

	private GridPane createGridPane(MyCanvas canvas, CommandWindow console, ScriptWindow editor, Workspace workspace,
			HistoryWindow historyWindow) {
		GridPane grid = new GridPane();
		setGridPaneStyle(grid);

		grid.getColumnConstraints().addAll(colWidth(50), colWidth(50), colWidth(40));

		grid.add(canvas.getCanvas(), 0, 0);
		grid.add(workspace.getView(), 0, 1);
		grid.add(editor.getTextArea(), 1, 0);
		grid.add(console.getConsole(), 1, 1);
		grid.add(historyWindow.getView(), 2, 0);

		return grid;
	}

	private ColumnConstraints colWidth(double percent) {
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(percent);
		return col;
	}

	private void setGridPaneStyle(GridPane grid) {
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
	}
}
