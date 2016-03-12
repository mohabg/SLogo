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
    private static int gapSize = 10;

    private Controller controller;
    private MyCanvas canvas;
    private CommandWindow commandWindow;
    private ScriptWindow scriptWindow;
    private Workspace workspace;
    private Parent root;
    private HistoryWindow historyWindow;
    private ColorDisplay colorDisplay;
    private LineStyle lineStyle;

    private ResourceBundle resources = ResourceBundle.getBundle("resources/GUI");

    public GUI (String language, int width, int height) {
        controller = new Controller(language);

        BorderPane pane = new BorderPane();
        root = pane;
        // scene = new Scene(root, width, height);

        commandWindow = new CommandWindow(controller);
        canvas = new MyCanvas(width / 2, height / 2, controller, commandWindow);
        controller.initialize(commandWindow); // required
        scriptWindow = new ScriptWindow(controller, commandWindow);
        workspace = new Workspace();
        historyWindow = new HistoryWindow();
        colorDisplay = new ColorDisplay();
        lineStyle = new LineStyle();

        GridPane grid =
                createGridPane(this.canvas, this.commandWindow, this.scriptWindow, this.workspace,
                               this.historyWindow, colorDisplay, lineStyle);

        pane.setTop(MenuFactory.createMenuBar(scriptWindow, controller));
        pane.setCenter(grid);

        int msDelay = Integer.parseInt(resources.getString("frameDelayMs"));
        KeyFrame frame = new KeyFrame(Duration.millis(msDelay), e -> step());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    public Parent getParent () {
        return root;
    }

    public void step () {
        ReturnData data = controller.getReturnData();

        canvas.update((CanvasData) data, lineStyle.getSelectedSpacing());
        workspace.setData((WorkspaceData) data);
        historyWindow.update(data.getHistory());
        colorDisplay.update((CanvasData) data);
    }

    private GridPane createGridPane (MyCanvas canvas,
                                     CommandWindow console,
                                     ScriptWindow editor,
                                     Workspace workspace,
                                     HistoryWindow historyWindow,
                                     ColorDisplay colorDisplay,
                                     LineStyle lineStyle) {
        GridPane grid = new GridPane();
        setGridPaneStyle(grid);

        grid.getColumnConstraints().addAll(colWidth(50), colWidth(50), colWidth(40));

        grid.add(canvas.getCanvas(), 0, 0);
        grid.add(workspace.getView(), 0, 1);
        grid.add(editor.getTextArea(), 1, 0);
        grid.add(console.getConsole(), 1, 1);
        grid.add(historyWindow.getView(), 2, 0);

        GridPane subGrid = new GridPane();
        setGridPaneStyle(subGrid);
        subGrid.add(colorDisplay.getParent(), 0, 0);
        subGrid.add(lineStyle.getParent(), 0, 1);

        grid.add(subGrid, 2, 1);

        return grid;
    }

    private ColumnConstraints colWidth (double percent) {
        ColumnConstraints col = new ColumnConstraints();
        col.setPercentWidth(percent);
        return col;
    }

    private void setGridPaneStyle (GridPane grid) {
        grid.setHgap(gapSize);
        grid.setVgap(gapSize);
        grid.setPadding(new Insets(0, gapSize, 0, gapSize));
    }

}
