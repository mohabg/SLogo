package gui;

import javafx.scene.control.TableView;
import observers.WorkspaceData;


public class Workspace extends TableView<String> {
    private WorkspaceData data;

    public Workspace (WorkspaceData data) {
        this.data = data;
    }

    public void handleReturnKey () {

    }

    public void handleLeftClick () {

    }

    public void handleRightClick () {

    }

}
