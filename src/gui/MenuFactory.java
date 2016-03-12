package gui;

import java.util.ResourceBundle;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import slogo.Controller;
import slogo.Resources;


public class MenuFactory {

    public static MenuBar createMenuBar (ScriptWindow scriptWindow, Controller controller) {
        ResourceBundle resources = ResourceBundle.getBundle("resources/languages");

        String langResources = resources.getString("languages");
        String[] langs = langResources.split(",");

        MenuBar menuBar = new MenuBar();
        Menu scriptMenu = new Menu("Script");
        Menu langMenu = new Menu("Language");
        Menu helpMenu = new Menu("Help");

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
                new MenuItem(ResourceBundle.getBundle("resources/GUI")
                        .getString("documentationMenuLabel"));
        runMenuItem.setOnAction(e -> displayHelpViewer());
        helpMenu.getItems().add(documentationMenuItem);

        menuBar.getMenus().addAll(scriptMenu, langMenu, helpMenu);

        return menuBar;
    }

    // Reference: https://docs.oracle.com/javase/8/javafx/embedded-browser-tutorial/overview.htm
    private static void displayHelpViewer () {
        new Browser("http://www.cs.duke.edu/courses/compsci308/spring16/assign/03_slogo/commands2_J2W.php");
    }
}
