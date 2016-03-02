package slogo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import commands.*;

public class Parser{

	private CommandFactory commandFactory;
	private List<Entry<String, Pattern>> mySymbols;
	private String language;

	public Parser (String language, SaveInputs model) {
		addLanguage(language);
		addLanguage("Syntax");
		commandFactory = new CommandFactory(model);
		mySymbols = new ArrayList<>();
	}

	private void addLanguage(String language){
		addPatterns(language);
	}
	private void addPatterns (String language) {
		String filePath = String.format("resources/languages/%s", language);
		ResourceBundle resources = ResourceBundle.getBundle(filePath);
		Enumeration<String> iter = resources.getKeys();
		while (iter.hasMoreElements()) {
			String key = iter.nextElement();
			String regex = resources.getString(key);
			mySymbols.add(new SimpleEntry<>(key,
					Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
		}
	}
	private List<CommandNode> parseText(String[] text) throws InstantiationException, IllegalAccessException,
	IllegalArgumentException, InvocationTargetException{

		List<CommandNode> commandList = createCommandNodes(text);
		List<CommandNode> commandHeads = new ArrayList<>();
		List<Integer> headCommandIndices = new ArrayList<>();

		for(int i = 0; i < commandList.size(); i++){
			headCommandIndices.add(i);
			i = createChildren(commandList, i);
		}

		for(int i = 0; i < headCommandIndices.size(); i++){
			int headCommandIndex = headCommandIndices.get(i);
			commandHeads.add(commandList.get(headCommandIndex));
		}
		System.out.println(commandHeads);
		printCommandHeads(commandHeads);
		return commandHeads;
	}

	private void printCommandHeads(List<CommandNode> commandHeads) {
		for(int i = 0; i < commandHeads.size(); i++){
			System.out.println(commandHeads.get(i) + " children " + commandHeads.get(i).getChildren());
			printCommandHeads(commandHeads.get(i).getChildren());
		}

	}

	private List<CommandNode> createCommandNodes(String[] text){
		List<CommandNode> commandList = new ArrayList<>();
		for(int i = 0; i < text.length; i++){
			if(text[i].trim().length() > 0){
				CommandNode command = getCommandForWord(text, i);
				commandList.add(command);
			}
		}
		System.out.println(commandList);
		return commandList;
	}

	private CommandNode getCommandForWord(String[] text, int index){
		String word = text[index];
		String symbol = getSymbol(word);
		return commandFactory.getCommandNode(symbol, word);
	}

	private int createChildren(List<CommandNode> commandList, int currentIndex) {
		CommandNode currentCommand = commandList.get(currentIndex);
		int counter = 0;
		while(counter++ < currentCommand.parametersNeeded()){
			CommandNode nextCommand = commandList.get(++currentIndex);
			currentCommand.addToChildren(nextCommand);
			//System.out.println("Adding " + nextCommand + " to " + currentCommand);
			if(nextCommand instanceof ListStart){
				currentIndex = setChildrenForList(commandList, currentIndex);
			}
			if(nextCommand.parametersNeeded() > 0){
				currentIndex = createChildren(commandList, currentIndex) ;
			}
		}
		if(currentCommand instanceof MakeUserInstruction){
			currentCommand.run();
		}
		return currentIndex;
	}

	private int setChildrenForList(List<CommandNode> commandList, int currentIndex) {
		CommandNode startOfList = commandList.get(currentIndex);
		currentIndex++;
		while(true){
			//System.out.println("Adding " + commandList.get(currentIndex) + " to " + startOfList);
			startOfList.addToChildren(commandList.get(currentIndex));
			currentIndex = createChildren(commandList, currentIndex) + 1;
			if(commandList.get(currentIndex) instanceof ListEnd){
				startOfList.addToChildren(commandList.get(currentIndex));
				//System.out.println("Adding " + commandList.get(currentIndex) + " to " + startOfList);
				break;
			}
		}
		return currentIndex;
	}

	// returns the language's type associated with the given text if one exists 
	private String getSymbol (String text) {
		final String ERROR = "NO MATCH";
		for (Entry<String, Pattern> e : mySymbols) {
			if (match(text, e.getValue())) {
				return e.getKey();
			}
		}
		// Indicates syntax error
		return ERROR;
	}

	// returns true if the given text matches the given regular expression pattern
	private boolean match (String text, Pattern regex) {
		return regex.matcher(text).matches();
	}
	public List<CommandNode> interpret (String command) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final String WHITESPACE = "\\p{Space}";
		return parseText(command.split(WHITESPACE));
	}

}