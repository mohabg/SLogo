package gui;

import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import observers.CanvasData;
import observers.ReturnData;
import observers.WorkspaceData;
import slogo.Interpreter;
import slogo.Resources;


public class GUI {
    private Stage stage;

    // Data-transfer
    ReturnData data;
    Interpreter interpreter;

    // Windows
    private MyCanvas canvas;
    private CommandWindow commandWindow;
    private ScriptWindow scriptWindow;
    private Workspace workspace;

    // Buttons
    Button runButton, languageButton, helpButton;

    public GUI (Stage stage) {
        this.stage = stage;

        this.data = new ReturnData();

        this.canvas = createCanvas((CanvasData) data);
        this.commandWindow = createCommandWindow();
        this.scriptWindow = createScriptWindow();
        this.workspace = createWorkspace((WorkspaceData) data);

        this.interpreter = new Interpreter(commandWindow);
        interpreter.addObserver(data);
    }

    public Scene init (int width, int height) {
        Group root = new Group();
        Scene myScene = new Scene(root, width, height);

        GridPane grid =
                createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace);
        root.getChildren().add(grid);

        return myScene;
    }

    public void step (double millisecondDelay) {
        // TODO: data.update()?
        canvas.update();
    }

    public static double getScreenWidth () {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        return bounds.getWidth();
    }

    public static double getScreenHeight () {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        return bounds.getHeight();
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

        grid.add(canvas, 0, 0);
        grid.add(console, 1, 0);
        grid.add(editor, 1, 1);
        grid.add(workspace, 0, 1);

        return grid;
    }

    private void example (GridPane grid) {
        // Category in column 2, row 1
        Text title = new Text(Resources.TITLE);
        title.setFont(Font.font(Resources.FONT, FontWeight.BOLD, Resources.TITLE_FONT_SIZE));
        grid.add(title, 1, 0);

        // Right label in column 4 (top), row 3
        Text servicesPercent = new Text("Services\n20%");
        GridPane.setValignment(servicesPercent, VPos.TOP);
        grid.add(servicesPercent, 3, 2);
    }

    private MyCanvas createCanvas (CanvasData data) {
        MyCanvas canvas = new MyCanvas(data);
        // TODO
        return canvas;
    }

    private CommandWindow createCommandWindow () {
        CommandWindow console = new CommandWindow();
        // TODO
        return console;
    }

    private ScriptWindow createScriptWindow () {
        ScriptWindow scriptWindow = new ScriptWindow();
        // TODO
        return scriptWindow;
    }

    private Workspace createWorkspace (WorkspaceData data) {
        Workspace workspace = new Workspace(data);
        // TODO
        return workspace;
    }
}
