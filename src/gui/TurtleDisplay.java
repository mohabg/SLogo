package gui;

import data.TurtleData;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by Tom on 5/8/2016.
 */
public class TurtleDisplay {

    Pane pane = new Pane();

    public Pane getPane() {
        return pane;
    }

    public void update(List<TurtleData> turtles) {
        pane.getChildren().clear();
        turtles.stream().forEach(t->{
            Button b = new Button("Turtle "+t.getID());
            b.setGraphic(new ImageView(t.getImage()));
            pane.getChildren().add(b);
            b.setOnMousePressed(e->chooseImage(t));
        });
    }

    private void chooseImage(TurtleData turtle) {
        String img = getImageFilepath();
        if (img != null) {
            turtle.setImage(img);
        }
    }

    private String getImageFilepath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog(new Stage());
        String filename;
        try {
            filename = file.toURI().toURL().toString();
        }
        catch (MalformedURLException e) {
            return null;
        }

        return filename;
    }

}
