package commands;

public class Difference extends CommandNode {

    public Difference (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
    	double difference = getChildren().get(0).run();
    	for(int i = 1; i < getChildren().size(); i++){
    		difference -= getChildren().get(i).run();
    	}
        setValue(difference);
        return getValue();
    }

}
