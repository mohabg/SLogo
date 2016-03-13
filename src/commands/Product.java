package commands;

public class Product extends CommandNode {

    public Product (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
    	double product = 1;
    	for(CommandNode constant : getChildren()){
    		product *= constant.run();
    	}
        setValue(product);
        return getValue();
    }

}
