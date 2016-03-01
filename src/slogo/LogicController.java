
package slogo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LogicController {
    private Interpreter interpreter;
    private List<Double> logicResults;

    public LogicController () {
        logicResults = new ArrayList<Double>();
    }

    public Collection<Double> update(CommandNode command){    	
    	ArrayList<Double> outputs = new ArrayList<Double>();
    	outputs.add(command.run());
    	return outputs;
    }
}