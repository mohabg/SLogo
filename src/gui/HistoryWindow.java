package gui;

import java.util.LinkedHashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;


public class HistoryWindow {
    private ListView<String> listView;
    private ObservableList<String> data;

    public HistoryWindow () {
        listView = new ListView<String>();
        data = FXCollections.observableArrayList();
        listView.setItems(data);
    }

    public void update (LinkedHashMap<String, Double> history) {
        data.clear();

        for (String input : history.keySet()) {
            data.add(input + "\t\t" + history.get(input));
        }
    }

    public Node getView () {
        return listView;
    }
}