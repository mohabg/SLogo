package gui;

import java.util.List;
import commands.CommandNode;
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

    public void update (List<CommandNode> history) {
        data.clear();

        for (CommandNode commandNode : history) {
            String input = commandNode.toString();
            data.add(input + "\t\t\t\t" + commandNode.getValue());
        }
    }

    public Node getView () {
        return listView;
    }
}
