package slogo;

import java.util.ResourceBundle;

import gui.GUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	// private List<GUI> GUIs = new ArrayList<GUI>();
	// private int currentGUI = 0;
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Main");
	private TabPane tabPane = new TabPane();

	public void start(Stage stage) {
		addGUI();

		Pane mainPane = new AnchorPane();
		Button newTabButton = addNewTabButton();

		mainPane.getChildren().addAll(tabPane, newTabButton);

		stage.setScene(new Scene(mainPane));

		String title = resources.getString("title");
		stage.setTitle(title);
		stage.show();
	}

	private Button addNewTabButton() {
		Button newTabBtn = new Button("New Tab");
		newTabBtn.setOnAction(e -> addGUI());

		double right = Double.parseDouble(resources.getString("buttonRightSpacing"));
		double top = Double.parseDouble(resources.getString("buttonTopSpacing"));
		AnchorPane.setRightAnchor(newTabBtn, right);
		AnchorPane.setTopAnchor(newTabBtn, top);

		return newTabBtn;
	}

	public void addGUI() {
		int width = Integer.parseInt(resources.getString("width"));
		int height = Integer.parseInt(resources.getString("height"));
		String language = resources.getString("default_language");

		GUI gui = new GUI(language, width, height);
		// GUIs.add(gui);

		Tab tab = new Tab("Workspace");
		tab.setContent(gui.getParent());
		tabPane.getTabs().add(tab);
	}

	/*
	 * public GUI getCurrentGUI() { return GUIs.get(currentGUI); }
	 */

	public static void main(String[] args) {
		launch(args);
	}
}
