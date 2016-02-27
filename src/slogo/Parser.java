package slogo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Parser{

    private List<Entry<String, Pattern>> mySymbols;

    public Parser () {
        mySymbols = new ArrayList<>();
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

    private void parseText(String[] text){
    	for(String word : text){
    		if(word.trim().length() > 0){
    			String symbol = getSymbol(word);
    			
    		}
    	}
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
    
    //public Tree buildCommandTree(){
    	//return;
    //}
    /*public String interpret (String command) {
        // TODO: parse
        return "test echo: " + command;
    }*/

}
