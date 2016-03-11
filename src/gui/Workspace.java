package gui;

import java.util.Map;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;

public class Workspace {
	private ListView<String> listView = new ListView<String>();
	private ObservableList<String> data = FXCollections.observableArrayList();

	public Workspace() {
		listView.setItems(data);
	}

	public void setData(Map<String, String> variables, Set<String> functions) {
		data.clear();

		for (String s : functions) {
			data.add("Function: " + s);
		}

		for (String name : variables.keySet()) {
			data.add(name + " " + variables.get(name));
		}
	}

	public Node getView() {
		return listView;
	}
}
