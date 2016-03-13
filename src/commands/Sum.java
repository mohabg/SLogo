package commands;

public class Sum extends CommandNode {

    public Sum (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
    	double sum = 0;
    	for(CommandNode constant : getChildren()){
    		sum += constant.run();
    	}
    	setValue(sum);
        return getValue();
    }

}
