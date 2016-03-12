package gui;

import java.util.List;
import java.util.ResourceBundle;
import data.CanvasData;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class ColorDisplay {
    private ResourceBundle resources = ResourceBundle.getBundle("resources/Color");
    private GridPane pane = new GridPane();

    public ColorDisplay () {
        double padding = Double.parseDouble(resources.getString("padding"));

        pane.setHgap(padding);
        pane.setVgap(padding);
    }

    public void update (CanvasData data) {
        pane.getChildren().clear();

        int width = Integer.parseInt(resources.getString("rowWidth"));
        List<Color> palette = data.getPalette();
        for (int i = 0; i < palette.size(); i++) {
            Node n = generateNode(palette.get(i), i);
            pane.add(n, i % width, i / width);
        }
    }

    public Parent getParent () {
        return pane;
    }

    private Node generateNode (Color c, int id) {
        int size = Integer.parseInt(resources.getString("size"));

        Rectangle r = new Rectangle(size, size);
        r.setFill(c);
        Text t = new Text(Integer.toString(id));
        StackPane stack = new StackPane(r, t);

        return stack;
    }
}
