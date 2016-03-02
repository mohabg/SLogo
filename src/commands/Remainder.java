package commands;

public class Remainder extends CommandNode {

    public Remainder (double val) {
        super(val);
        setParametersNeeded(2);
    }

    @Override
    public double run () {
        return getChildren().get(0).run() % getChildren().get(1).run();
    }

}
