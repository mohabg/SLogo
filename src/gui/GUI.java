package gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import observers.CanvasData;
import observers.ReturnData;
import observers.WorkspaceData;
import slogo.Interpreter;

public class GUI {
	private Stage stage;
	private ObservableList<Node> rootNodeChildren;

	// Data-transfer
	ReturnData data;
	Interpreter interpreter;

	// Windows
	private final int WIDTH = 1000;
	private final int HEIGHT = 800;
	private MyCanvas canvas;
	private CommandWindow commandWindow;
	private ScriptWindow scriptWindow;
	private Workspace workspace;

	// Buttons
	Button runButton, languageButton, helpButton;

	public GUI(Stage stage) {
		this.stage = stage;

		this.data = new ReturnData();

		// TODO: design flaw? Interpreter and CommandWindow reference each other
		this.interpreter = new Interpreter(commandWindow);
		interpreter.addObserver(data);
	}

	public Scene init() {
		Group root = new Group();
		Scene myScene = new Scene(root, WIDTH, HEIGHT);
		rootNodeChildren = root.getChildren();

		// GUI elements
		this.canvas = createCanvas((CanvasData) data);
		this.commandWindow = new CommandWindow(interpreter);
		this.scriptWindow = new ScriptWindow();
		this.workspace = new Workspace((WorkspaceData) data);

		// Root node and grid layout
		GridPane grid = createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace);
		rootNodeChildren.add(grid);

		return myScene;
	}

	public void step(double millisecondDelay) {
		// TODO: data.update()?
		canvas.update();
	}

	public int getScreenWidth() {
		return WIDTH;
	}

	public int getScreenHeight() {
		return HEIGHT;
	}

	public static Point2D getScreenCenter() {
		Screen screen = Screen.getPrimary();
		Rectangle2D bounds = screen.getVisualBounds();
		double screenWidth = bounds.getWidth();
		double screenHeight = bounds.getHeight();
		return (new Point2D(screenWidth / 2, screenHeight / 2));
	}

	public static void setCenterPos(Node node, Point2D centerPos) {
		double x = centerPos.getX();
		double y = centerPos.getY();
		node.setTranslateX(x - node.getBoundsInLocal().getWidth() / 2);
		node.setTranslateY(y - node.getBoundsInLocal().getHeight() / 2);
	}

	// Reference: http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
	private GridPane createGridPane(MyCanvas canvas, CommandWindow console, ScriptWindow editor, Workspace workspace) {
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		grid.add(canvas, 0, 0);
		grid.add(console, 1, 1);
		grid.add(editor, 1, 0);
		grid.add(workspace, 0, 1);

		return grid;
	}

	// TODO: remove
	private void example(GridPane grid) {
		// Right label in column 4 (top), row 3
		Text servicesPercent = new Text("Services\n20%");
		GridPane.setValignment(servicesPercent, VPos.TOP);
		grid.add(servicesPercent, 3, 2);
	}

	private MyCanvas createCanvas(CanvasData data) {
		Group canvasNode = new Group();
		// TODO: GUI.setCenterPos(canvasNode, ...)?
		rootNodeChildren.add(canvasNode);
		MyCanvas canvas = new MyCanvas(data, canvasNode.getChildren(), getScreenWidth() / 2, getScreenHeight() / 2);
		// TODO
		return canvas;
	}
}
