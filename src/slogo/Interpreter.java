package slogo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import gui.CommandWindow;


public class Interpreter extends Observable {
    private LogicController logicController;
    private TurtleController turtleController;
    private SlogoManager manager;
    
    private CommandFactory commandFactory;
    private CommandWindow console;
    
    private List<CommandNode> commands;
    private List<Entry<String, Pattern>> mySymbols;

    public Interpreter (CommandWindow console) {
        this.console = console;
        commandFactory = new CommandFactory();
        mySymbols = new ArrayList<>();
        commands = new ArrayList<>();
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

    private List<CommandNode> parseText(String[] text) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
    	for(int i = 0; i < text.length; i++){
    		String word = text[i];
    		if(word.trim().length() > 0){
    			String symbol = getSymbol(word);
    			CommandNode command = commandFactory.getCommandNode(symbol);
    			commands.add(command);
    			int childrenNeeded = command.parametersNeeded();
    			for(int j = 0; j < childrenNeeded; j++){
    				String nextWord = text[i++];
    				String nextSymbol = getSymbol(nextWord);
    				command.addToChildren(commandFactory.getCommandNode(nextSymbol));
    			}
    			
    		}
    	}
    	return commands;
    }
    // returns the language's type associated with the given text if one exists 
    private String getSymbol (String text) {
        final String ERROR = "NO MATCH";
        for (Entry<String, Pattern> e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
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
