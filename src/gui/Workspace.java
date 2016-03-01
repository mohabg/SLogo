package gui;

import data.WorkspaceData;
import javafx.scene.control.TableView;


public class Workspace {
    private TableView<String> tableView;
    private WorkspaceData data;

    public Workspace (WorkspaceData data) {
        tableView = new TableView<String>();
        this.data = data;
    }

    public TableView<String> getTableView () {
        return tableView;
    }

    public void handleReturnKey () {

    }

    public void handleLeftClick () {

    }

    public void handleRightClick () {

    }

}
