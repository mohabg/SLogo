import gui.GUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * This is the main program, which runs Cell Society.
 */
public class Main extends Application {
    // public static final int WIDTH = 600;
    // public static final int HEIGHT = 400;

    private GUI myGUI;

    /**
     * Set things up at the beginning.
     */
    @Override
    public void start (Stage stage) {
        myGUI = new GUI(stage);
        // TODO: stage.setTitle();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        int screenWidth = (int) bounds.getWidth();
        int screenHeight = (int) bounds.getHeight();

        // attach game to the stage and display it
        Scene scene = myGUI.init(screenWidth, screenHeight);
        // Scene scene = myGame.init(WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.show();

        // sets the game's loop
        KeyFrame frame =
                new KeyFrame(Duration.millis(Resources.millisecondDelay),
                             e -> myGUI.step(Resources.secondDelay));
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    /**
     * Start the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
