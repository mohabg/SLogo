package slogo;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private List<GUI> GUIs = new ArrayList<GUI>();
	private int currentGUI = 0;
	private ResourceBundle resources = ResourceBundle.getBundle("resources/Main");

	@Override
	public void start(Stage stage) {
		int width = Integer.parseInt(resources.getString("width"));
		int height = Integer.parseInt(resources.getString("height"));
		String language = resources.getString("default_language");
		String title = resources.getString("title");

		GUIs.add(new GUI(language, width, height));

		stage.setScene(getCurrentGUI().getScene());
		stage.setTitle(title);
		stage.show();
	}

	public GUI getCurrentGUI() {
		return GUIs.get(currentGUI);
	}

	public static void main(String[] args) {
		launch(args);
	}
}