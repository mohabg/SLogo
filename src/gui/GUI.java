
package gui;

import data.CanvasData;
import data.ReturnData;
import data.WorkspaceData;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import slogo.Controller;
import slogo.Resources;

public class GUI {
	private ObservableList<Node> rootNodeChildren;
	private int width, height;

	// Data-transfer
	ReturnData data;
	Controller controller;

	// Windows
	private MyCanvas canvas;
	private CommandWindow commandWindow;
	private ScriptWindow scriptWindow;
	private Workspace workspace;

	public GUI(Controller controller) {
		this.controller = controller;
		this.data = controller.getReturnData();
	}

	public Scene init(int width, int height) {
		BorderPane root = new BorderPane();
		Scene myScene = new Scene(root, width, height);
		rootNodeChildren = root.getChildren();
		this.width = width;
		this.height = height;

		root.setTop(createMenuBar());

		// GUI elements
		this.canvas = new MyCanvas((CanvasData) data, getWindowWidth() / 2, getWindowHeight() / 2);
		this.commandWindow = new CommandWindow(controller);
		this.scriptWindow = new ScriptWindow(controller, commandWindow);
		this.workspace = new Workspace((WorkspaceData) data);

		// Root node and grid layout
		GridPane grid = createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace);
		root.setCenter(grid);

		return myScene;
	}

	public void step(double millisecondDelay) {
		canvas.update();
	}

	public int getWindowWidth() {
		return width;
	}

	public int getWindowHeight() {
		return height;
	}

	public static Point2D getScreenCenter() {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double screenWidth = bounds.getWidth();
		double screenHeight = bounds.getHeight();
		return (new Point2D(screenWidth / 2, screenHeight / 2));
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

	private MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu scriptMenu = new Menu("Script");
		MenuItem runMenuItem = new MenuItem(Resources.RUN_MENU_LABEL);

		scriptMenu.getItems().add(runMenuItem);
		runMenuItem.setOnAction(e -> scriptWindow.handleRunButton());
		menuBar.getMenus().add(scriptMenu);

		return menuBar;
	}
}