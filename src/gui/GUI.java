package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GUI {
    private Stage stage;

    public GUI (Stage stage) {
        this.stage = stage;
    }

    public Scene init (int width, int height) {
        Group root = new Group();
        Scene myScene = new Scene(root, width, height);
        return myScene;
    }

    public void step (double millisecondDelay) {
        // TODO
    }

}
