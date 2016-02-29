package slogo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Parser{

	private CommandFactory commandFactory;
	private List<Entry<String, Pattern>> mySymbols;

	public Parser () {
		commandFactory = new CommandFactory();
		mySymbols = new ArrayList<>();
		addLanguage("English");
		addLanguage("Syntax");
	}

	public void addLanguage(String language){
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
			for(int i = 0; i < commandList.size() - 1; i++){
				System.out.println(i);
				headCommandIndices.add(i);
				i = createChildren(commandList, i);
			}
			for(int i = 0; i < headCommandIndices.size(); i++){
				int headCommandIndex = headCommandIndices.get(i);
				commandHeads.add(commandList.get(headCommandIndex));
			}
			return commandHeads;
	}
	
		private List<CommandNode> createCommandNodes(String[] text){
			List<CommandNode> commandList = new ArrayList<>();
			for(int i = 0; i < text.length; i++){
				String word = text[i];
				if(word.trim().length() > 0){
					String symbol = getSymbol(word);
					CommandNode command = commandFactory.getCommandNode(symbol, word);
					commandList.add(command);
				}
			}
			return commandList;
		}
		private int createChildren(List<CommandNode> commandList, int currentIndex) {
			CommandNode currentCommand = commandList.get(currentIndex);
			int lastChildIndex = currentIndex + currentCommand.parametersNeeded();
		   for(int i = currentIndex + 1; i <= lastChildIndex; i++){
			   CommandNode nextCommand = commandList.get(i);
				currentCommand.addToChildren(nextCommand);
				if(nextCommand.parametersNeeded() > 0){
					i = createChildren(commandList, i);
					lastChildIndex = i;
				}
			}
		return lastChildIndex;
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
