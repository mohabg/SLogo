package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Browser extends Application {

    public Browser (String url) {
        launch();
    }

    @Override
    public void start (Stage stage) {
        stage.setTitle("Documentation");
        BorderPane root = new BorderPane();
        stage.setScene(new Scene(root, 450, 450));
        stage.show();

        // WebView browser = new WebView();
        // WebEngine webEngine = browser.getEngine();
        // webEngine
        // .load("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands2_J2W.php");
        // root.getChildren().add(browser);
    }

}
