package gui;

import data.CanvasData;
import data.ReturnData;
import data.WorkspaceData;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import slogo.Controller;


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

    // Buttons
    Button runButton, languageButton, helpButton;

    public GUI (Controller controller) {
        this.controller = controller;
        this.data = new ReturnData();
    }

    public Scene init (int width, int height) {
        Group root = new Group();
        Scene myScene = new Scene(root, width, height);
        rootNodeChildren = root.getChildren();
        this.width = width;
        this.height = height;

        // GUI elements
        this.canvas = createCanvas((CanvasData) data);
        this.commandWindow = new CommandWindow(controller);
        this.scriptWindow = new ScriptWindow();
        this.workspace = new Workspace((WorkspaceData) data);

        // Root node and grid layout
        GridPane grid =
                createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace);
        rootNodeChildren.add(grid);

        return myScene;
    }

    public void step (double millisecondDelay) {
        canvas.update();
    }

    public int getWindowWidth () {
        return width;
    }

    public int getWindowHeight () {
        return height;
    }

    public static Point2D getScreenCenter () {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        double screenWidth = bounds.getWidth();
        double screenHeight = bounds.getHeight();
        return (new Point2D(screenWidth / 2, screenHeight / 2));
    }

    // Reference: http://docs.oracle.com/javafx/2/layout/builtin_layouts.htm
    private GridPane createGridPane (MyCanvas canvas,
                                     CommandWindow console,
                                     ScriptWindow editor,
                                     Workspace workspace) {
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

    private MyCanvas createCanvas (CanvasData data) {
        Group canvasNode = new Group();
        // TODO: GUI.setCenterPos(canvasNode, ...)?
        rootNodeChildren.add(canvasNode);
        MyCanvas canvas =
                new MyCanvas(data, canvasNode.getChildren(), getWindowWidth() / 2,
                             getWindowHeight() / 2);
        return canvas;
    }
}
