package gui;

import data.WorkspaceData;
import javafx.scene.control.TableView;


public class Workspace {
    private TableView<String> tableView;
    private WorkspaceData data;

    public Workspace (WorkspaceData data) {
        tableView = new TableView<String>();
        this.data = data;
        initContextMenu();
        initControls();
    }

    public TableView<String> getTableView () {
        return tableView;
    }

    private void initContextMenu () {

    }

    private void initControls () {

    }

    private void handleReturnKey () {

    }

    private void handleLeftClick () {

    }

    private void handleRightClick () {

    }

}
