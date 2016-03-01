package slogo;

import java.lang.reflect.InvocationTargetException;
import gui.GUI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * This is the main program, which runs Cell Society.
 */
public class Main extends Application {

    private GUI myGUI;
    private SlogoManager myManager;

    /**
     * Set things up at the beginning.
     * 
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public void start (Stage stage) throws InstantiationException, IllegalAccessException,
                                    IllegalArgumentException, InvocationTargetException {
        myManager = new SlogoManager();
        myManager.update("fd 50");
        myGUI = new GUI(myManager);
        // TODO: stage.setTitle();

        // attach game to the stage and display it
        Scene scene = myGUI.init(Resources.WIDTH, Resources.HEIGHT);
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
