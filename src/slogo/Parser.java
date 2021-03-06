package slogo;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import commands.Command;
import commands.CommandNode;
import exceptions.SlogoException;

public class Parser {

	private CommandFactory commandFactory;
	private List<Entry<String, Pattern>> mySymbols;
	private SaveInputs inputSaver;
	private Map <String, String> startAndEndOfLists;
	private Model model;

	public Parser(String language, Model model) {
		SaveInputs saver = (SaveInputs) model;
		mySymbols = new ArrayList<>();
		inputSaver = saver;
		addLanguage(language);
		addPatterns("Syntax");
		commandFactory = new CommandFactory(saver);
		startAndEndOfLists = new HashMap<String, String>();
		startAndEndOfLists.put( "[" , "]" );
		startAndEndOfLists.put( "(" , ")" );
		this.model = model;
	}

	public void addLanguage(String language) {
		addPatterns(language);
	}

	private void addPatterns(String pattern) {
		String filePath = String.format("resources/languages/%s", pattern);
		ResourceBundle resources = ResourceBundle.getBundle(filePath);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);

			mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.MULTILINE)));
		}
	}

	private List<CommandNode> parseText(String[] text)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {
		List<CommandNode> commandList = createCommandNodes(text);

		for (CommandNode c : commandList) {
			c.setModel(model);
		}

		List<CommandNode> commandHeads = new ArrayList<>();

		for (int i = 0; i < commandList.size(); i++) {
			commandHeads.add(commandList.get(i));
			i = createChildren(commandList, i);
		}
		return commandHeads;
	}

	private List<CommandNode> createCommandNodes(String[] text)
				throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
						IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		List<CommandNode> commandList = new ArrayList<>();
		for (int i = 0; i < text.length; i++) {
			if (text[i].trim().length() > 0) {
				CommandNode command = getCommandForWord(text, i);
				commandList.add(command);
			}
		}
		return commandList;
	}

	private CommandNode getCommandForWord(String[] text, int index)
				throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
				IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String word = text[index];
		String symbol = getSymbol(word);
		return commandFactory.getCommandNode(symbol, word);
	}

	private int createChildren(List<CommandNode> commandList, int currentIndex) {
		CommandNode currentCommand = commandList.get(currentIndex);
		int counter = 0;
		setVariablesAndCommands(currentCommand);

		while (counter++ < currentCommand.parametersNeeded()) {
			CommandNode nextCommand = commandList.get(++currentIndex);
			currentCommand.addToChildren(nextCommand);
			if(startAndEndOfLists.containsKey(nextCommand.getInput())){
				currentIndex = setChildrenForList(commandList, currentIndex);
			}
			if (nextCommand.parametersNeeded() > 0 && nextCommand.getChildren().size() == 0) {
				currentIndex = createChildren(commandList, currentIndex);
			}
		}
		return currentIndex;
	}

	private void setVariablesAndCommands(CommandNode currentCommand) {
		if (currentCommand instanceof Command) {
			Command currentFunction = (Command) currentCommand;
			String input = currentFunction.getInput();
			Command storedFunction = (Command) inputSaver.getCommandForFunction(input);
			CommandNode commandsToExecute = storedFunction.getChildren().get(0);
			currentFunction.setVariables(storedFunction.getVariables());
			currentFunction.addToChildren(commandsToExecute);
		}
	}

	private int setChildrenForList(List<CommandNode> commandList, int currentIndex) {
		CommandNode startOfList = commandList.get(currentIndex);
		currentIndex++;
		
		while (currentIndex < commandList.size()) {
			CommandNode currentCommand = commandList.get(currentIndex);
			startOfList.addToChildren(currentCommand);
			if(startAndEndOfLists.get(startOfList.getInput()).equals(currentCommand.getInput())){
				break;
			}
			currentIndex = createChildren(commandList, currentIndex) + 1;
		}
		
		if(startOfList.shouldSetParametersForChildren()){
			CommandNode commandToSetParameters = startOfList.getChildren().get(0);
			for(int i = 1; i < startOfList.getChildren().size() - 1; i++){
				commandToSetParameters.addToChildren(startOfList.getChildren().get(i));
			}
		}
		return currentIndex;
	}

	// returns the language's type associated with the given text if one exists
	private String getSymbol(String text) {
		try {
			for (Entry<String, Pattern> e : mySymbols) {
				if (match(text, e.getValue())) {
					return e.getKey();
				}
			}
		} catch (Exception e) {
			new SlogoException(Controller.getErrorBundle().getString("SyntaxError"));
		}
		// Indicates syntax error
		return null;
	}

	// returns true if the given text matches the given regular expression
	// pattern
	private boolean match(String text, Pattern regex) {
		return regex.matcher(text).matches();
	}

	public List<CommandNode> interpret(String command)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			ClassNotFoundException, NoSuchMethodException, SecurityException {
		final String WHITESPACE = "\\p{Space}";
		List<CommandNode> commandHeads = parseText(command.split(WHITESPACE));
		return commandHeads;
	}

}
