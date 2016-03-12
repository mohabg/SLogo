package gui;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import slogo.Controller;
import slogo.Resources;


public class MenuFactory {
    private static ResourceBundle GUIResources = ResourceBundle.getBundle("resources/GUI");

    public static MenuBar createMenuBar (ScriptWindow scriptWindow, Controller controller) {
        ResourceBundle resources = ResourceBundle.getBundle("resources/languages");

        String langResources = resources.getString("languages");
        String[] langs = langResources.split(",");

        MenuBar menuBar = new MenuBar();
        Menu scriptMenu = new Menu(GUIResources.getString("scriptMenuLabel"));
        Menu langMenu = new Menu(GUIResources.getString("languageMenuLabel"));
        Menu helpMenu = new Menu(GUIResources.getString("helpMenuLabel"));

        for (String s : langs) {
            String trimmed = s.trim();
            MenuItem item = new MenuItem(trimmed);
            item.setOnAction(e -> controller.setLanguage(trimmed));
            langMenu.getItems().add(item);
        }

        MenuItem runMenuItem = new MenuItem(Resources.RUN_MENU_LABEL);
        runMenuItem.setOnAction(e -> scriptWindow.handleRunButton());
        scriptMenu.getItems().add(runMenuItem);

        MenuItem documentationMenuItem =
                new MenuItem(GUIResources.getString("documentationMenuLabel"));
        documentationMenuItem.setOnAction(e -> displayHelpViewer());
        helpMenu.getItems().add(documentationMenuItem);

        menuBar.getMenus().addAll(scriptMenu, langMenu, helpMenu);

        return menuBar;
    }

    // Reference: https://docs.oracle.com/javase/8/javafx/embedded-browser-tutorial/overview.htm
    private static void displayHelpViewer () {
        Stage stage = new Stage();
        stage.setTitle(GUIResources.getString("documentationMenuLabel"));
        Group root = new Group();
        stage.setScene(new Scene(root, 800, 600));
        stage.show();

        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load(GUIResources.getString("docsURL"));
        root.getChildren().add(browser);
    }
}
