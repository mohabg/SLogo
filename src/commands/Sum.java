package commands;

public class Sum extends CommandNode {

    public Sum (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
        return getChildren().get(0).run() + getChildren().get(1).run();
    }

}
