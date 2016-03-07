package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import slogo.Controller;
import slogo.Resources;

public class MenuFactory {

	public static MenuBar createMenuBar(ScriptWindow scriptWindow, Controller controller) {
		Map<String, String> langs = new HashMap<String, String>();
		langs.put("Chinese", "Chinese");
		langs.put("English", "English");
		langs.put("French", "French");
		langs.put("German", "German");
		langs.put("Italian", "Italian");
		langs.put("Portuguese", "Portuguese");
		langs.put("Russian", "Russian");
		langs.put("Spanish", "Spanish");

		MenuBar menuBar = new MenuBar();
		Menu scriptMenu = new Menu("Script");
		Menu langMenu = new Menu("Language");
		List<MenuItem> langItems = new ArrayList<MenuItem>();
		
		for(String s : langs.keySet())
		{
			MenuItem item = new MenuItem(s);
			item.setOnAction(e -> controller.setLanguage(langs.get(s)));
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
