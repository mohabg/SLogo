package gui;

import data.TurtleData;
import data.WorkspaceData;
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

	public void setData(WorkspaceData state) {
		data.clear();

		for (TurtleData t : state.getTurtles()) {
			data.add(String.format("Turtle %d, (%.1f, %.1f)", t.getID(), t.getPosition().getX(), t.getPosition().getY()));
		}

		for (String s : state.getUserFunctions()) {
			data.add("Function: " + s);
		}

		for (String name : state.getUserVariables().keySet()) {
			data.add(name + " " + state.getUserVariables().get(name));
		}
	}

	public Node getView() {
		return listView;
	}
}
