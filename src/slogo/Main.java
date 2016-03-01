package slogo;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

    private GUI myGUI;
    private SlogoManager myManager;

    /**
     * Set things up at the beginning.
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws  
     */
    @Override
    public void start (Stage stage) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    	myManager = new SlogoManager();
    	myManager.compile("to poly [ :length :angle ] [ fd :length fd :angle ] poly 3 4");
        myGUI = new GUI(stage);
        // TODO: stage.setTitle();

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        int screenWidth = (int) bounds.getWidth();
        int screenHeight = (int) bounds.getHeight();

        // attach game to the stage and display it
        Scene scene = myGUI.init();
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