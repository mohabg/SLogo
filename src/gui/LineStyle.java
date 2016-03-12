package gui;

import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.control.ComboBox;

public class LineStyle {
	ComboBox<String> comboBox = new ComboBox<>();
	ResourceBundle resources = ResourceBundle.getBundle("resources/LineStyle");

	public LineStyle() {
		for (String s : resources.keySet()) {
			comboBox.getItems().add(s);
			comboBox.setValue(s);
		}
	}

	public Parent getParent() {
		return comboBox;
	}

	public double getSelectedSpacing() {
		String s = comboBox.getValue();
		return Double.parseDouble(resources.getString(s));
	}
}
