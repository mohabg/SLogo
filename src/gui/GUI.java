package gui;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import observers.CanvasData;
import observers.ReturnData;
import observers.WorkspaceData;
import slogo.Interpreter;


public class GUI {
    private Stage stage;

    // Observation
    ReturnData data;
    Interpreter interpreter;

    // Windows
    private MyCanvas canvas;
    private CommandWindow commandWindow;
    private ScriptWindow scriptWindow;
    private Workspace workspace;

    // Buttons
    Button runButton, languageButton;

    public GUI (Stage stage) {
        this.stage = stage;

        this.data = new ReturnData();
        this.canvas = new MyCanvas((CanvasData) data);
        // this.commandWindow = new CommandWindow
        //
        this.workspace = new Workspace((WorkspaceData) data);

        this.interpreter = new Interpreter();
        interpreter.addObserver(data);
    }

    public Scene init (int width, int height) {
        Group root = new Group();
        Scene myScene = new Scene(root, width, height);
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
}
