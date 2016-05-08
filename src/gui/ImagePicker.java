package gui;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import data.CanvasData;
import data.TurtleData;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ImagePicker {
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Color");
	private GridPane pane = new GridPane();
	int totalTurtles = -1;

	public ImagePicker() {
		double padding = Double.parseDouble(resources.getString("padding"));

		pane.setHgap(padding);
		pane.setVgap(padding);
	}

	public void update(CanvasData data) {
		if (totalTurtles == data.getTurtles().size())
			return;

		int gridPos = 0;

		totalTurtles = data.getTurtles().size();

		pane.getChildren().clear();
		for (TurtleData t : data.getTurtles()) {

			List<MenuItem> menuItems = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				MenuItem item = new MenuItem("Image " + i);
				menuItems.add(item);
				double d = i;
				item.setOnAction(e -> {
					t.setShape(d);
					totalTurtles = -1;
				});
			}

			ContextMenu menu = new ContextMenu();
			menu.getItems().addAll(menuItems);

			ImageView image = new ImageView(t.getImage());
			pane.add(image, gridPos % 3, gridPos / 3);

			image.setOnContextMenuRequested(e -> menu.show(image, e.getScreenX(), e.getScreenY()));

			gridPos++;
		}
	}

	public Parent getParent() {
		return pane;
	}
}
