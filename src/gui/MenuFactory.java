package gui;

import java.util.ResourceBundle;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import slogo.Controller;
import slogo.Resources;

public class MenuFactory {

	public static MenuBar createMenuBar(ScriptWindow scriptWindow, Controller controller) {
		ResourceBundle resources = ResourceBundle.getBundle("resources/languages");

		String langResources = resources.getString("languages");
		String[] langs = langResources.split(",");

		MenuBar menuBar = new MenuBar();
		Menu scriptMenu = new Menu("Script");
		Menu langMenu = new Menu("Language");

		for (String s : langs) {
			String trimmed = s.trim();
			MenuItem item = new MenuItem(trimmed);
			item.setOnAction(e -> controller.setLanguage(trimmed));
			langMenu.getItems().add(item);
		}

		MenuItem runMenuItem = new MenuItem(Resources.RUN_MENU_LABEL);

		scriptMenu.getItems().add(runMenuItem);
		runMenuItem.setOnAction(e -> scriptWindow.handleRunButton());
		menuBar.getMenus().add(scriptMenu);
		menuBar.getMenus().add(langMenu);

		return menuBar;
	}
}
