package gui;

import java.util.List;
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

    public void update (List<String[]> history) {
        data.clear();

        for (String[] command : history) {
            data.add(command[0] + ", " + command[1]);
        }
    }

    public Node getView () {
        return listView;
    }
}
